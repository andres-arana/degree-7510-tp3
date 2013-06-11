package modelo;

/**
 * Interfaz para implementar una Oferta. La oferta está compuesta
 * por una lista de condiciones, que en el caso de cumplirse todas
 * se aplica una serie de acciones. 
 * 
 * @author Grupo 15
 *
 */
public interface IOferta {
	void addCondicion(ICondicionOferta condicion);

	void addAccion(IAccionOferta accion);

	boolean chequearCondiciones(IVenta venta);

	/**
	 * Aplica la oferta a los productos que corresponda o a toda la venta, según el 
	 * tipo de oferta
	 * @param venta: objeto tipo IVenta que representa la venta a la cual se aplica la oferta
	 */
	void aplicarOferta(IVenta venta);

	/**
	 * Si al aplicar una oferta existen más productos a los cuales se puede aplicar
	 * la misma oferta, la oferta se aplica recurrentemente. Este método da a conocer
	 * si la oferta es o no recurrente. 
	 * @return true si la la oferta se aplica recurrentemente, false caso contrario
	 */
	boolean isRecurrente();
}
