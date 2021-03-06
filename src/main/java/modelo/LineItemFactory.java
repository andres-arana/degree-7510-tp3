package modelo;

public class LineItemFactory {
	private int numeroLinea;

	public LineItemFactory() {
		numeroLinea = 0;
	}

	public LineItemOferta lineItemOferta(String detalleOferta, int cantidad,
			double montoUnitario) {
		numeroLinea++;
		return new LineItemOferta(detalleOferta, cantidad, montoUnitario,
				numeroLinea);
	}

	public LineItemProducto lineItemProducto(Producto producto, int cantidad) {
		numeroLinea++;
		return new LineItemProducto(producto, cantidad, numeroLinea);
	}
}
