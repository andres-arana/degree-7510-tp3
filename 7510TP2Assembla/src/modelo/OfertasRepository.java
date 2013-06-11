package modelo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Clase para contener el total de las ofertas a aplicar en una sucursal
 * Contiene la lista de ofertas generales y de ofertas que solo se consiguen 
 * por medio de cupones. 
 * 
 * @author Grupo 15
 *
 */
public class OfertasRepository {
	private List<IOferta> ofertas;
	private HashMap<Integer, IOferta> hashOfertasCupones;

	public OfertasRepository() {
		ofertas = new ArrayList<IOferta>();
		hashOfertasCupones = new HashMap<Integer, IOferta>();
	}

	public void addOferta(IOferta oferta) {
		ofertas.add(oferta);
	}

	/**
	 * Dada una venta se verifica si se puede aplicar una o más
	 * ofertas contenidas en este repositorio. 
	 * 
	 * @param venta: objeto tipo IVenta al cual se aplican las ofertas
	 */
	public void aplicarOfertas(IVenta venta) {
		for (IOferta oferta : ofertas) {
			while (oferta.chequearCondiciones(venta)) {
				oferta.aplicarOferta(venta);
				if (oferta.isRecurrente() == false)
					break;
			}
		}
	}

	/**
	 * Devuelve la oferta que corresponde a un cupón. 
	 * @param idCupon: identificador del cupón
	 * @return objeto tipo IOferta que corresponde al cupón dado. 
	 */
	public IOferta getOfertaDelCupon(int idCupon) {

		return hashOfertasCupones.get(idCupon);

	}

	public boolean addOfertaParaCupon(int idOferta, Oferta oferta) {

		if (existeOfertaConId(idOferta)) {
			return false;
		}

		if (idOferta > 0 && oferta != null) {
			hashOfertasCupones.put(idOferta, oferta);
			return true;
		} else {
			return false;
		}

	}

	private boolean existeOfertaConId(int idOferta) {

		if (hashOfertasCupones.get(idOferta) == null) {
			return false;
		} else {
			return true;
		}
	}
}
