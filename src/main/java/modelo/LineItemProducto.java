package modelo;

import java.text.DecimalFormat;

/**
 * Un LineItemProducto representa un producto que pertenece a una venta
 * LLeva la cuenta de a cuantas unidades se le aplicaron una oferta de tipo 
 * por producto y de a cuantas por tipo categoria o marca.
 * El control anterior sirve para saber si luego de aplicar una oferta a este producto, 
 * la misma puede ser vuelta a aplicar al resto de las unidades. 
 * 
 * @author Grupo 15
 *
 */
public class LineItemProducto extends LineItem {
	private Producto producto;
	private int productosConOfertasPorProductoAplicadas;
	private int productosConOfertasPorCategoriaOMarcaAplicadas;

	public int getProductosConOfertasPorProductoAplicadas() {
		return productosConOfertasPorProductoAplicadas;
	}

	public void setProductosConOfertasPorProductoAplicadas(
			int productosConOfertasPorProductoAplicadas) {
		this.productosConOfertasPorProductoAplicadas = productosConOfertasPorProductoAplicadas;
	}

	public int getProductosConOfertasPorCategoriaOMarcaAplicadas() {
		return productosConOfertasPorCategoriaOMarcaAplicadas;
	}

	public void setProductosConOfertasPorCategoriaOMarcaAplicadas(
			int productosConOfertasPorCategoriaOMarcaAplicadas) {
		this.productosConOfertasPorCategoriaOMarcaAplicadas = productosConOfertasPorCategoriaOMarcaAplicadas;
	}

	public LineItemProducto(Producto producto, int cantidad, int numeroLinea) {
		super(cantidad, numeroLinea);
		this.producto = producto;
		productosConOfertasPorCategoriaOMarcaAplicadas = 0;
		productosConOfertasPorProductoAplicadas = 0;
	}

	public Producto getProducto() {
		return producto;
	}

	@Override
	public double montoUnitario() {
		return producto.getPrecio();
	}

	@Override
	public double montoTotal() {
		return producto.getPrecio() * super.getCantidad();
	}
	
	public double montoTotalSinOfertasPorProductoAplicadas() {
		int cantidad = getCantidad() - productosConOfertasPorProductoAplicadas;
		return cantidad * producto.getPrecio();
	}
	
	@Override
	public String descripcion() {
		return producto.getDescripcion();
	}

	public String toString() {
		StringBuilder out = new StringBuilder();
		DecimalFormat df = new DecimalFormat("#.00");
		out.append(super.getCantidad() + "\t" + producto.getDescripcion()
				+ "\t" + df.format(producto.getPrecio()) + "\t"
				+ df.format(montoTotal()));
		return out.toString();
	}
}
