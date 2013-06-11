package modelo;

/**
 * La oferta está compuesta por una serie de condiciones
 * y una serie de acciones a aplicar si dichas condiciones
 * se cumplen. Esta interfaz debe ser implementada por todas
 * las condiciones que vayan a formar parte de una ofeta. 
 * 
 * @author Grupo 15
 *
 */
public interface ICondicionOferta {

	/**
	 * Verifica si una venta cumple con esta condición
	 * @param venta: Objeta tipo IVenta a la cual se aplica la oferta
	 * @return true si al menos un producto cumple la condición, false en caso contrario.
	 */
	boolean chequearCondicion(IVenta venta);

	/**
	 * Si la la condición hace referencia a un producto y al mismo
	 * solo se puede aplicar una oferta con esta condición, se marca 
	 * que la condición fue aplicada.  
	 * @param venta: Objeta tipo IVenta a la cual se aplica la oferta
	 */
	public void aplicarCondicion(IVenta venta);

	/**
	 * Si al aplicar una oferta existen más productos a los cuales se puede aplicar
	 * la misma oferta, la condición se chequea recurrentemente. Este método da a cononer
	 * si la condición actual es o no recurrente. 
	 * @return true si la condición es recurrente, false caso contrario
	 */
	public boolean isRecurrente();

}
