package modelo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Observable;

/**
 * Clase para implementar interfaz de IVenta. Al agregar un producto
 * o aplicar una oferta informa a los observadores para notificar el cambio
 * @author Yamil
 *
 */
public class Venta extends Observable implements IVenta {
	private enum UltimoLineItem {
		OFERTA, PRODUCTO
	}

	private List<LineItemProducto> items;
	private List<LineItemOferta> ofertasAplicadas;
	private LineItemFactory lineItemFactory;
	private UltimoLineItem ultimoLineItem;
	private double descuentoAlTotalDeLaVenta;
	private String detalleOfertaTotalVenta;
	private String formaDePago;
	private Calendar fechaVenta;
	int nroCaja;
	boolean esJubilado;

	public List<LineItemProducto> getProductos() {
		return Collections.unmodifiableList(items);
	}

	public Venta(Calendar fechaVenta, int nroCaja) {
		this.items = new ArrayList<LineItemProducto>();
		this.ofertasAplicadas = new ArrayList<LineItemOferta>();
		this.lineItemFactory = new LineItemFactory();
		this.descuentoAlTotalDeLaVenta = 0;
		this.detalleOfertaTotalVenta = "";
		this.fechaVenta = fechaVenta;
		this.formaDePago = "";
		this.nroCaja = nroCaja;
		this.esJubilado = false;
	}

	@Override
	public void setFormaDePago(String medioDePago) {
		this.formaDePago = medioDePago;
	}

	@Override
	public double getTotal() {
		return getTotalSinDescuentos() - getTotalDescuentos();
	}

	@Override
	public double getTotalDescuentos() {
		double total = 0;
		for (LineItem i : ofertasAplicadas) {
			total += i.montoTotal();
		}
		return total;
	}

	@Override
	public double getTotalSinDescuentos() {
		double total = 0;
		for (LineItem i : items) {
			total += i.montoTotal();
		}
		return total;
	}

	public List<LineItemOferta> getOfertasAplicadas() {
		return this.ofertasAplicadas;
	}

	@Override
	public void addProducto(Producto producto, int cantidad) {
		LineItemProducto item = lineItemFactory.lineItemProducto(producto,
				cantidad);
		items.add(item);
		ultimoLineItem = UltimoLineItem.PRODUCTO;
		setChanged();
		notifyObservers();
	}

	@Override
	public void addOferta(String detalle, int cantidad, double montoUnitario) {
		LineItemOferta lineItemOferta = lineItemFactory.lineItemOferta(detalle,
				cantidad, montoUnitario);
		ofertasAplicadas.add(lineItemOferta);
		ultimoLineItem = UltimoLineItem.OFERTA;
		setChanged();
		notifyObservers();
	}

	@Override
	public String getMedioDePago() {
		return formaDePago;
	}

	@Override
	public Calendar getFecha() {
		return fechaVenta;
	}

	@Override
	public String getUltimaLinea() {
		if (ultimoLineItem == UltimoLineItem.PRODUCTO)
			return getUltimoLineItemProducto().toString();
		else
			return getUltimoLineItemOferta().toString();
	}

	@Override
	public int getCajaNro() {
		return nroCaja;
	}

	@Override
	public void aplicarDescuentoAlTotal(double descuento, String detalleOferta) {
		if (descuento > descuentoAlTotalDeLaVenta) {
			descuentoAlTotalDeLaVenta = descuento;
			detalleOfertaTotalVenta = detalleOferta;
		}
	}

	@Override
	public void cobrar() {
		if (descuentoAlTotalDeLaVenta > 0)
			addOferta(detalleOfertaTotalVenta, 1, getTotal()
					* descuentoAlTotalDeLaVenta);
	}

	@Override
	public void marcarTodosLosProductosOfertados(IQuery query,
			List<Producto> excepciones) {
		for (LineItemProducto i : items) {
			if ((query.query(i.getProducto()))
					&& (noEsUnaExcepcion(i.getProducto().getId(), excepciones))) {
				i.setProductosConOfertasPorCategoriaOMarcaAplicadas(i
						.getCantidad());
			}
		}
	}

	@Override
	public void marcarProductosOfertados(IQuery query, int cantidad) {
		int marcados = 0;
		int dif;
		for (LineItemProducto i : items) {
			if ((query.query(i.getProducto()))
					&& (i.getProductosConOfertasPorProductoAplicadas() < i
							.getCantidad())) {
				dif = i.getCantidad()
						- i.getProductosConOfertasPorProductoAplicadas();
				if (cantidad - marcados <= dif) {
					i.setProductosConOfertasPorProductoAplicadas(i
							.getProductosConOfertasPorProductoAplicadas()
							+ cantidad - marcados);
					break;
				} else {
					i.setProductosConOfertasPorProductoAplicadas(i
							.getCantidad());
					marcados += dif;
				}
			}
		}
	}

	private boolean noEsUnaExcepcion(int idProducto, List<Producto> excepciones) {
		for (Producto p : excepciones) {
			if (p.getId() == idProducto)
				return false;
		}
		return true;
	}

	private LineItem getUltimoLineItemOferta() {
		return ofertasAplicadas.get(ofertasAplicadas.size() - 1);
	}

	private LineItem getUltimoLineItemProducto() {
		return items.get(items.size() - 1);
	}

	@Override
	public void setEsJubilado(boolean jubilado) {
		this.esJubilado = jubilado;

	}

	@Override
	public boolean esJubilado() {
		return this.esJubilado;
	}

	@Override
	public int getTotalPuntos() {
		int resultado = 0;
		for (LineItemProducto item : items) {
			resultado += item.procesarPuntaje();
		}
		return resultado;
	}



}
