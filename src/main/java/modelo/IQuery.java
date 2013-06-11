package modelo;

/**
 * Interfaz para definir un criterio de búsqueda de productos. 
 * Por ejemplo, por categoría, marca, id, etc. 
 * @author Grupo 15
 *
 */
public interface IQuery {
	public boolean query(Producto productoAComparar);
}
