package modelo;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Observable;
import java.util.Observer;

public class Caja implements Observer {
	private IEstadoCaja estadoCaja;
	private VentasRepository repositorioVentas;
	private OfertasRepository repositorioOfertas;
	private IVenta ventaEnCurso;
	private int cajaNro;
	private String sucursal;

	public int getCajaNro() {
		return cajaNro;
	}

	public String getSucursal() {
		return sucursal;
	}

	public Caja(int cajaNro, String sucursal,
			OfertasRepository ofertasRepository,
			VentasRepository ventasRepository) {
		this.repositorioVentas = ventasRepository;
		this.repositorioOfertas = ofertasRepository;
		this.cajaNro = cajaNro;
		this.sucursal = sucursal;
		ventaEnCurso = new Venta(null, Calendar.getInstance(), cajaNro);
		estadoCaja = CajaCerrada.getInstance();
	}

	public void abrirCaja() {
		estadoCaja.puedeAbrirCaja();
		estadoCaja = CajaAbierta.getInstance();
	}

	public void addProducto(Producto producto, int cantidad) {
		estadoCaja.puedeAgregarProducto();
		ventaEnCurso.addProducto(producto, cantidad);
		repositorioOfertas.aplicarOfertas(ventaEnCurso);
	}
	
	public void iniciarVenta() {
		iniciarVenta(null);
	}
	
	public void iniciarVenta(final Cliente cliente) {
		estadoCaja.puedeIniciarVenta();
		ventaEnCurso = new Venta(cliente, Calendar.getInstance(), cajaNro);
		((Venta) ventaEnCurso).addObserver(this);
		estadoCaja = CajaEfectuandoVenta.getInstance();
		imprimirCabecera();
	}

	public IVenta confirmarVenta(final String formaDePago) {
		estadoCaja.puedeConfirmarVenta();
		ventaEnCurso.setFormaDePago(formaDePago);
		repositorioOfertas.aplicarOfertas(ventaEnCurso);
		ventaEnCurso.cobrar();
		imprimirTotalVentaActual();
		repositorioVentas.add(ventaEnCurso);
		estadoCaja = CajaAbierta.getInstance();
		return ventaEnCurso;
	}

	public void cancelarVenta() {
		estadoCaja.puedeCancelarventa();
		imprimirVentaCancelada();
		estadoCaja = CajaAbierta.getInstance();
	}

	public void cerrarCaja() {
		estadoCaja.puedeCerrarCaja();
		estadoCaja = CajaCerrada.getInstance();
	}

	@Override
	public void update(Observable o, Object arg) {
		imprimirLinea();
	}

	private void imprimirLinea() {
		System.out.println(ventaEnCurso.getUltimaLinea());
	}

	private void imprimirTotalVentaActual() {
		DecimalFormat df = new DecimalFormat("0.00");
		System.out.println("Total de la compra sin descuentos: "
				+ df.format(ventaEnCurso.getTotalSinDescuentos()));
		System.out.println("Total descuentos: "
				+ df.format(ventaEnCurso.getTotalDescuentos()));
		System.out.println("Total de la compra: "
				+ df.format(ventaEnCurso.getTotal()));
	}

	private void imprimirVentaCancelada() {
		System.out.println("Compra Cancelada");
	}

	private void imprimirCabecera() {
		System.out.println();
		System.out.println("Bienvenido al Supermercado Sin Nombre");
		System.out.println("Sucursal: " + sucursal + " Caja: " + cajaNro);
	}

	public boolean canjearCupon(int idCupon) {

		IOferta oferta = repositorioOfertas.getOfertaDelCupon(idCupon);

		if (oferta != null) {
			oferta.aplicarOferta(ventaEnCurso);
			return true;
		} else {
			return false;
		}

	}
	
	public double getTotalCaja() {

		return repositorioVentas.calcularTotalCaja(cajaNro);
	}

	public void setEsJubilado() {

		ventaEnCurso.setEsJubilado(true);
	}

	public void aplicarPuntos(int puntos) {
		ventaEnCurso.aplicarPuntos(puntos);
	}
}
