package modelo.queriesMarcado;

import modelo.IQuery;
import modelo.Producto;

public class QueryProducto implements IQuery {
	private int idProducto;

	public QueryProducto(int idProducto) {
		this.idProducto = idProducto;
	}

	@Override
	public boolean query(Producto productoAComparar) {
		if (idProducto == productoAComparar.getId())
			return true;
		return false;
	}
}
