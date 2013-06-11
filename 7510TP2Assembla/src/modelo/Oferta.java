package modelo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Oferta implements IOferta {
	private Calendar validezDesde;
	private Calendar validezHasta;
	private List<ICondicionOferta> listaCondiciones;
	private List<IAccionOferta> listaAcciones;
	private String detalle;

	public Oferta(Calendar validezDesde, Calendar validezHasta, String detalle) {
		this.validezDesde = validezDesde;
		this.validezHasta = validezHasta;
		this.detalle = detalle;
		listaCondiciones = new ArrayList<ICondicionOferta>();
		listaAcciones = new ArrayList<IAccionOferta>();
	}

	public void addCondicion(ICondicionOferta condicion) {
		listaCondiciones.add(condicion);
	}

	public void addAccion(IAccionOferta accion) {
		listaAcciones.add(accion);
	}

	public boolean chequearCondiciones(IVenta venta) {
		if (isOfertaActiva(venta.getFecha())) {
			for (ICondicionOferta i : listaCondiciones) {
				if (!i.chequearCondicion(venta))
					return false;
			}
			return true;
		}
		return false;
	}

	public void aplicarOferta(IVenta venta) {
		for (ICondicionOferta i : listaCondiciones)
			i.aplicarCondicion(venta);
		for (IAccionOferta i : listaAcciones)
			i.ejecutarAccion(venta, detalle);
	}

	public boolean isRecurrente() {
		boolean esRecurrente = false;
		for (ICondicionOferta i : listaCondiciones) {
			esRecurrente = esRecurrente || i.isRecurrente();
		}
		return esRecurrente;
	}

	private boolean isOfertaActiva(Calendar fecha) {
		if (fecha.before(validezDesde) || fecha.after(validezHasta))
			return false;
		return true;
	}

}
