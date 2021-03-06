package modelo.queriesMarcado;

import modelo.IQuery;
import modelo.Producto;

public class QueryMarca implements IQuery {
	private String marca;

	public QueryMarca(String marca) {
		this.marca = marca;
	}

	@Override
	public boolean query(Producto productoAComparar) {
		if (marca == productoAComparar.getMarca())
			return true;
		return false;
	}

}
