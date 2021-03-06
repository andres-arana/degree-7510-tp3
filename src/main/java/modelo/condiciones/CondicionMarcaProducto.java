package modelo.condiciones;

import java.util.List;

import modelo.CondicionNoRecurrente;
import modelo.IVenta;
import modelo.LineItemProducto;

public class CondicionMarcaProducto extends CondicionNoRecurrente {

	private String marca;

	public CondicionMarcaProducto(String marca) {
		this.marca = marca;
	}

	@Override
	public boolean chequearCondicion(IVenta venta) {
		List<LineItemProducto> items = venta.getItemsProducto();
		for (LineItemProducto lineItem : items) {
			if ((lineItem.getProducto().getMarca() == marca)
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
