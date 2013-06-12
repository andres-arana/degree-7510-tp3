package modelo.condiciones;

import java.util.List;

import modelo.CondicionNoRecurrente;
import modelo.IVenta;
import modelo.LineItemProducto;

public class CondicionLlevaNProductosDeCategoriaX extends CondicionNoRecurrente {
	private int cantidad;
	private String categoria;

	public CondicionLlevaNProductosDeCategoriaX(int cantidad, String categoria) {
		this.cantidad = cantidad;
		this.categoria = categoria;
	}

	@Override
	public boolean chequearCondicion(IVenta venta) {
		int encontrados = 0;
		List<LineItemProducto> items = venta.getItemsProducto();
		for (LineItemProducto lineItem : items) {
			if (lineItem.getProducto().getCategoria() == categoria) {
				encontrados += lineItem.getCantidad();
				if (encontrados >= cantidad)
					return true;
			}
		}
		return false;
	}

	@Override
	public void aplicarCondicion(IVenta venta) {

	}

}
