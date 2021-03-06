package modelo.queriesMarcado;

import modelo.IQuery;
import modelo.Producto;

public class QueryCategoria implements IQuery {
	private String categoria;

	public QueryCategoria(String categoria) {
		this.categoria = categoria;
	}

	@Override
	public boolean query(Producto productoAComparar) {
		if (categoria == productoAComparar.getCategoria())
			return true;
		return false;
	}

}
