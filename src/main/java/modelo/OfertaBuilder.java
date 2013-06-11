package modelo;

import java.util.Calendar;

/**
 * Una oferta puede estar conformada por varias condiciones y varias acciones. 
 * OfertaBuilder permite agregar todas la condiciones y acciones necesarias 
 * y a partir de ellas crear la oferta en cuestion. 
 * 
 * @author Grupo 15
 *
 */
public class OfertaBuilder {
	private Oferta oferta;

	public void crearNuevaOferta(Calendar ofertaDesde, Calendar ofertaHasta,
			String detalleOferta) {
		oferta = new Oferta(ofertaDesde, ofertaHasta, detalleOferta);
	}

	public void agregarCondicion(ICondicionOferta condicion) {
		oferta.addCondicion(condicion);
	}

	public void agregarAccion(IAccionOferta accion) {
		oferta.addAccion(accion);
	}

	public Oferta getOferta() {
		return oferta;
	}
}
