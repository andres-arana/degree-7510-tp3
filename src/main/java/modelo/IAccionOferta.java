package modelo;


/**
 * La oferta esta compuesta por una serie de condiciones
 * y una serie de acciones a aplicar si dichas condiciones
 * se cumplen. Esta interfaz debe ser implementada por todas
 * las acciones que que vayan a ser agregadas a una oferta
 * 
 * @author Grupo 15
 *
 */
public interface IAccionOferta {

	void ejecutarAccion(IVenta venta, String detalleOferta);

}
