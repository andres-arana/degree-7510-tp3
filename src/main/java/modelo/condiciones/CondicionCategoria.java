package modelo.condiciones;

import java.util.List;

import modelo.CondicionNoRecurrente;
import modelo.IVenta;
import modelo.LineItemProducto;

public class CondicionCategoria extends CondicionNoRecurrente {

	String categoria;

	public CondicionCategoria(String categoria) {
		this.categoria = categoria;
	}

	@Override
	public boolean chequearCondicion(IVenta venta) {
		List<LineItemProducto> items = venta.getItemsProducto();
		for (LineItemProducto lineItem : items) {
			if ((lineItem.getProducto().getCategoria() == categoria)
					&& (lineItem.getCantidad()
							- lineItem
									.getProductosConOfertasPorCategoriaOMarcaAplicadas() > 0))
				return true;
		}
		return false;
	}

	@Override
	public void aplicarCondicion(IVenta venta) {
	}

}
