package tests.Modelo;

import static org.junit.Assert.assertEquals;
import modelo.Caja;
import modelo.Cliente;
import modelo.Oferta;
import modelo.OfertasRepository;
import modelo.Producto;
import modelo.ProductosRepository;
import modelo.VentasRepository;
import modelo.acciones.AccionAgregarPuntos;
import modelo.acciones.AccionAgregarPuntosPorPago;
import modelo.condiciones.CondicionLLevaNProducto;

import org.junit.Test;

public class PuntosPorTotalTests extends AbstractOfertasTest {
	@Test
	public void deberiaAcumularPuntos() {
		ProductosRepository productos = buildProductos(maceta(), tv(), chocolate(), coca());
		OfertasRepository ofertas = buildOfertas(
				oferta100Puntos1TV(productos), oferta1PuntoCada50Pesos());
		VentasRepository repositorioVentas = new VentasRepository();

		Cliente cliente = new Cliente("99.999.999");
		Caja caja = new Caja(1, "Moron", ofertas, repositorioVentas);

		caja.abrirCaja();
		caja.iniciarVenta(cliente);
		caja.addProducto(productos.getProductoByDetalle("Maceta"), 10);
		caja.addProducto(productos.getProductoByDetalle("Coca Cola"), 6);
		caja.addProducto(productos.getProductoByDetalle("Chocolate"), 1);
		caja.addProducto(productos.getProductoByDetalle("TV Flat"), 1);
		caja.confirmarVenta("Efectivo");

		assertEquals(102, cliente.getPuntos());
	}
	
	private Oferta oferta1PuntoCada50Pesos() {
		Oferta oferta = ofertaVigente("1 punto cada 50 pesos");
		oferta.addAccion(new AccionAgregarPuntosPorPago(50, 1));
		return oferta;
	}

	private Oferta oferta100Puntos1TV(ProductosRepository productos) {
		Oferta oferta = ofertaVigente("100 puntos por TV Flat");
		Producto tv = productos.getProductoById(tvId());
		oferta.addCondicion(new CondicionLLevaNProducto(tv, 1));
		oferta.addAccion(new AccionAgregarPuntos(100));
		return oferta;
	}
	
	private Producto chocolate() {
		return new Producto("Chocolate", 20, chocolateId(), "Alimento", "Milka");
	}

	private int chocolateId() {
		return 4;
	}
	
	private Producto maceta() {
		return new Producto("Maceta", 6, macetaId(), "Jardin", "Generico");
	}

	private int macetaId() {
		return 3;
	}

	private Producto tv() {
		return new Producto("TV Flat", 5470, tvId(), "Tecno", "Sony");
	}

	private int tvId() {
		return 2;
	}

	private Producto coca() {
		return new Producto("Coca Cola", 5, cocaId(), "Bebidas", "Coca Cola");
	}

	private int cocaId() {
		return 1;
	}
}
