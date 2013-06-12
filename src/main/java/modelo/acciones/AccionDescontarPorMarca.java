package modelo.acciones;

import java.util.ArrayList;
import java.util.List;

import modelo.IAccionOferta;
import modelo.IVenta;
import modelo.LineItemProducto;
import modelo.Producto;
import modelo.queriesMarcado.QueryMarca;

public class AccionDescontarPorMarca implements IAccionOferta {
	private double descuento;
	private String marca;
	private ArrayList<Producto> excepciones;

	public AccionDescontarPorMarca(String marca, List<Producto> excepciones,
			double descuento) {
		this.marca = marca;
		this.descuento = descuento;
		this.excepciones = new ArrayList<Producto>();
		if (excepciones != null) {
			for (Producto p : excepciones) {
				if (p.getMarca() == marca)
					this.excepciones.add(p);
			}
		}
	}

	@Override
	public void ejecutarAccion(IVenta venta, String detalleOferta) {
		List<LineItemProducto> items = venta.getItemsProducto();
		for (LineItemProducto lineItem : items) {
			int cantidad = lineItem.getCantidad()
					- lineItem
							.getProductosConOfertasPorCategoriaOMarcaAplicadas();
			if ((lineItem.getProducto().getMarca() == marca) && (cantidad > 0)) {
				boolean noEstaEnLasExcepciones = true;
				for (Producto p : excepciones) {
					if (p.getId() == lineItem.getProducto().getId()) {
						noEstaEnLasExcepciones = false;
						break;
					}
				}
				if (noEstaEnLasExcepciones) {
					venta.addOferta(detalleOferta, cantidad,
							lineItem.montoUnitario() * descuento);
				}
			}
		}
		venta.marcarTodosLosProductosOfertados(new QueryMarca(marca),
				excepciones);
	}

}
