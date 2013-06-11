package modelo;

/**
 * Interfaz para definir un criterio de busqueda de productos. 
 * Por ejemplo, por categoria, marca, id, etc. 
 * @author Grupo 15
 *
 */
public interface IQuery {
	public boolean query(Producto productoAComparar);
}
