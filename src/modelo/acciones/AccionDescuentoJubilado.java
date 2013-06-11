package modelo.acciones;

import modelo.IAccionOferta;
import modelo.IVenta;

public class AccionDescuentoJubilado implements IAccionOferta {

	private double descuento;

	public AccionDescuentoJubilado(double descuento) {

		this.descuento = descuento;
	}

	@Override
	public void ejecutarAccion(IVenta venta, String detalleOferta) {
		venta.addOferta(detalleOferta, 1, venta.getTotal() * descuento);

	}

}
