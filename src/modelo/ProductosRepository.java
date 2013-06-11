package modelo;

import java.util.ArrayList;
import java.util.List;

public class ProductosRepository {
	private List<Producto> productos;

	public ProductosRepository() {
		productos = new ArrayList<Producto>();
	}

	public List<Producto> getProductos() {
		return this.productos;
	}

	public boolean addProducto(Producto producto) {
		boolean noEstaElProducto = true;
		for (Producto p : productos) {
			if (p.getId() == producto.getId()
					|| p.getDescripcion() == producto.getDescripcion())
				noEstaElProducto = false;
		}
		if (noEstaElProducto) {
			productos.add(producto);
			return true;
		}
		return false;
	}

	public Producto getProductoById(int idProducto) {
		for (Producto p : productos) {
			if (p.getId() == idProducto)
				return p;
		}
		return null;
	}

	public Producto getProductoByDetalle(String detalleProducto) {
		for (Producto p : productos) {
			if (p.getDescripcion() == detalleProducto)
				return p;
		}
		return null;
	}

	public boolean removeProductoById(int idProducto) {
		for (Producto p : productos) {
			if (p.getId() == idProducto) {
				productos.remove(p);
				return true;
			}
		}
		return false;
	}

	public boolean removeProductoByDetalle(String detalleProducto) {
		for (Producto p : productos) {
			if (p.getDescripcion() == detalleProducto) {
				productos.remove(p);
				return true;
			}
		}
		return false;
	}

}
