package modelo;

/**
 * Interfaz para definir un criterio de b�squeda de productos. 
 * Por ejemplo, por categor�a, marca, id, etc. 
 * @author Grupo 15
 *
 */
public interface IQuery {
	public boolean query(Producto productoAComparar);
}
