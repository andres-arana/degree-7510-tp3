package modelo.acciones;

import java.util.ArrayList;
import java.util.List;

import modelo.IAccionOferta;
import modelo.IVenta;
import modelo.LineItemProducto;
import modelo.Producto;
import modelo.queriesMarcado.QueryCategoria;

public class AccionDescontarPorCategoria implements IAccionOferta {
	private double descuento;
	private String categoria;
	private List<Producto> excepciones;

	public AccionDescontarPorCategoria(String categoria,
			List<Producto> excepciones, double descuento) {
		this.categoria = categoria;
		this.descuento = descuento;
		this.excepciones = new ArrayList<Producto>();
		if (excepciones != null) {
			for (Producto p : excepciones) {
				if (p.getCategoria() == categoria)
					this.excepciones.add(p);
			}
		}
	}

	@Override
	public void ejecutarAccion(IVenta venta, String detalleOferta) {
		List<LineItemProducto> items = venta.getProductos();
		for (LineItemProducto lineItem : items) {
			int cantidad = lineItem.getCantidad()
					- lineItem
							.getProductosConOfertasPorCategoriaOMarcaAplicadas();
			if ((lineItem.getProducto().getCategoria() == categoria)
					&& (cantidad > 0)) {
				boolean noEstaEnLasExcepciones = true;
				if (excepciones != null) {
					for (Producto p : excepciones) {
						if (p.getId() == lineItem.getProducto().getId()) {
							noEstaEnLasExcepciones = false;
							break;
						}
					}
				}
				if (noEstaEnLasExcepciones) {
					venta.addOferta(detalleOferta, cantidad,
							lineItem.montoUnitario() * descuento);
				}
			}
		}
		venta.marcarTodosLosProductosOfertados(new QueryCategoria(categoria),
				excepciones);
	}

}
