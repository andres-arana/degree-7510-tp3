package modelo.condiciones;

import java.util.List;

import modelo.CondicionRecurrente;
import modelo.IVenta;
import modelo.LineItemProducto;
import modelo.Producto;
import modelo.queriesMarcado.QueryProducto;

public class CondicionLLevaNProducto extends CondicionRecurrente {

	private Producto producto;
	private int cantidad;

	public CondicionLLevaNProducto(Producto producto, int cantidad) {
		this.producto = producto;
		this.cantidad = cantidad;

	}

	@Override
	public boolean chequearCondicion(IVenta venta) {
		List<LineItemProducto> items = venta.getItemsProducto();
		for (LineItemProducto lineItem : items) {
			if (lineItem.getProducto().getId() == producto.getId()) {
				if (lineItem.getCantidad()
						- lineItem.getProductosConOfertasPorProductoAplicadas() >= cantidad)
					return true;
			}
		}
		return false;
	}

	@Override
	public void aplicarCondicion(IVenta venta) {
		venta.marcarProductosOfertados(new QueryProducto(producto.getId()),
				cantidad);
	}

}
