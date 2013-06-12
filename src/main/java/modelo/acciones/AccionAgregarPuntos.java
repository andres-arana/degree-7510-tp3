package modelo.acciones;

import modelo.IAccionOferta;
import modelo.IVenta;

public class AccionAgregarPuntos implements IAccionOferta {
	private final int puntos;

	public AccionAgregarPuntos(final int puntos) {
		this.puntos = puntos;
	}

	@Override
	public void ejecutarAccion(IVenta venta, String detalleOferta) {
		venta.registrarPuntosEnCliente(puntos);
	}
}
