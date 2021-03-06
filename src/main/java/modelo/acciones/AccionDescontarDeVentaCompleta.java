package modelo.acciones;

import modelo.IAccionOferta;
import modelo.IVenta;

public class AccionDescontarDeVentaCompleta implements IAccionOferta {
	private double descuento;

	public AccionDescontarDeVentaCompleta(double descuento) {
		this.descuento = descuento;
	}

	@Override
	public void ejecutarAccion(IVenta venta, String detalleOferta) {
		venta.aplicarDescuentoAlTotal(descuento, detalleOferta);
	}

}
