package modelo.acciones;

import modelo.IAccionOferta;
import modelo.IVenta;
import modelo.Producto;

public class AccionDescontarDeProducto implements IAccionOferta {

	int cantidad;
	Producto producto;
	double descuento;

	public AccionDescontarDeProducto(Producto producto, int cantidad,
			double descuento) {
		this.cantidad = cantidad;
		this.producto = producto;
		this.descuento = descuento;
	}

	@Override
	public void ejecutarAccion(IVenta venta, String detalleOferta) {
		venta.addOferta(detalleOferta, cantidad, producto.getPrecio()
				* descuento);
	}
}
