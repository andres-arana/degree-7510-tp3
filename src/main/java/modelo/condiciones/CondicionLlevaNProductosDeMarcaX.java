package modelo.condiciones;

import java.util.List;

import modelo.CondicionNoRecurrente;
import modelo.IVenta;
import modelo.LineItemProducto;

public class CondicionLlevaNProductosDeMarcaX extends CondicionNoRecurrente {
	private int cantidad;
	private String marca;

	public CondicionLlevaNProductosDeMarcaX(int cantidad, String marca) {
		this.cantidad = cantidad;
		this.marca = marca;
	}

	@Override
	public boolean chequearCondicion(IVenta venta) {
		int encontrados = 0;
		List<LineItemProducto> items = venta.getItemsProducto();
		for (LineItemProducto lineItem : items) {
			if (lineItem.getProducto().getMarca() == marca) {
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
