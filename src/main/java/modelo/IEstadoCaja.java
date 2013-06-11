package modelo;

/**
 * Interfaz para determinar el comportamiento v�lido de la caja en base
 * al estado en que se encuentra. Por ejemplo, si la caja no est� abierta
 * no se puede agregar un prodcuto. 
 * 
 * @author Grupo 15
 *
 */
public interface IEstadoCaja {
	void puedeAbrirCaja();

	void puedeAgregarProducto();

	void puedeIniciarVenta();

	void puedeConfirmarVenta();

	void puedeCancelarventa();

	void puedeCerrarCaja();
}
