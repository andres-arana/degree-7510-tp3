package modelo;

/**
 * Clase pada representar los elementos que componen una venta. 
 * Dichos elementos pueden ser productos u ofertas. 
 * Un LineItem está compuesto por: un número de linea, cantidad, monto unitario, 
 * monto total y descripción
 * 
 * @author Grupo 15
 *
 */
public abstract class LineItem {
	private int cantidad;
	private int numeroLinea;

	public int getNumeroLinea() {
		return numeroLinea;
	}

	public LineItem(int cantidad, int numeroLinea) {
		this.cantidad = cantidad;
		this.numeroLinea = numeroLinea;
	}

	public int getCantidad() {
		return cantidad;
	}

	public abstract double montoUnitario();

	public abstract double montoTotal();

	public abstract String descripcion();
}
