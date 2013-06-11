package modelo;

import java.text.DecimalFormat;

/**
 * Al aplicar una oferta a un LineItemProducto, la misma es guardada en 
 * la venta como un lineItem de oferta. De esta manera se lleva un control 
 * de las ofertas aplicadas y permite calcular el total de descuentos a aplicar. 
 * @author Yamil
 *
 */
public class LineItemOferta extends LineItem {
	private double montoUnitario;
	private String detalle;

	public LineItemOferta(String detalleOferta, int cantidad,
			double montoUnitario, int numeroLinea) {
		super(cantidad, numeroLinea);
		this.montoUnitario = montoUnitario;
		this.detalle = detalleOferta;
	}

	@Override
	public double montoUnitario() {
		return montoUnitario;
	}

	@Override
	public double montoTotal() {
		return montoUnitario * super.getCantidad();
	}

	@Override
	public String descripcion() {
		return detalle;
	}

	@Override
	public String toString() {
		StringBuilder out = new StringBuilder();
		DecimalFormat df = new DecimalFormat("#.00");
		out.append(super.getCantidad() + "\t" + "Descuento " + detalle + "\t"
				+ df.format(montoUnitario) + "\t" + "-"
				+ df.format(montoTotal()));
		return out.toString();
	}

}
