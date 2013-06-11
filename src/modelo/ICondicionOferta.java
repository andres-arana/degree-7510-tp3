package modelo;

/**
 * La oferta est� compuesta por una serie de condiciones
 * y una serie de acciones a aplicar si dichas condiciones
 * se cumplen. Esta interfaz debe ser implementada por todas
 * las condiciones que vayan a formar parte de una ofeta. 
 * 
 * @author Grupo 15
 *
 */
public interface ICondicionOferta {

	/**
	 * Verifica si una venta cumple con esta condici�n
	 * @param venta: Objeta tipo IVenta a la cual se aplica la oferta
	 * @return true si al menos un producto cumple la condici�n, false en caso contrario.
	 */
	boolean chequearCondicion(IVenta venta);

	/**
	 * Si la la condici�n hace referencia a un producto y al mismo
	 * solo se puede aplicar una oferta con esta condici�n, se marca 
	 * que la condici�n fue aplicada.  
	 * @param venta: Objeta tipo IVenta a la cual se aplica la oferta
	 */
	public void aplicarCondicion(IVenta venta);

	/**
	 * Si al aplicar una oferta existen m�s productos a los cuales se puede aplicar
	 * la misma oferta, la condici�n se chequea recurrentemente. Este m�todo da a cononer
	 * si la condici�n actual es o no recurrente. 
	 * @return true si la condici�n es recurrente, false caso contrario
	 */
	public boolean isRecurrente();

}
