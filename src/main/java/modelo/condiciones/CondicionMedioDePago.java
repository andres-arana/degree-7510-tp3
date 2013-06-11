package modelo.condiciones;

import modelo.CondicionNoRecurrente;
import modelo.IVenta;

public class CondicionMedioDePago extends CondicionNoRecurrente {

	private String medioPago;

	public CondicionMedioDePago(String medioPago) {
		this.medioPago = medioPago;

	}

	@Override
	public boolean chequearCondicion(IVenta venta) {
		if (medioPago == venta.getMedioDePago())
			return true;
		return false;
	}

	@Override
	public void aplicarCondicion(IVenta venta) {
	}

}
