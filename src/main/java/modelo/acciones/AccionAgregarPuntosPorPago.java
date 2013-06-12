package modelo.acciones;

import modelo.IAccionOferta;
import modelo.IVenta;
import modelo.LineItemProducto;

public class AccionAgregarPuntosPorPago implements IAccionOferta {
	private final int precio;
	private final int puntos;

	public AccionAgregarPuntosPorPago(final int precio, int puntos) {
		this.precio = precio;
		this.puntos = puntos;
	}

	@Override
	public void ejecutarAccion(IVenta venta, String detalleOferta) {
		double valorTotal = calcularValorTotalElegible(venta);
		int multiplicador = (int) (valorTotal / (double) precio);
		venta.registrarPuntosEnCliente(puntos * multiplicador);
		
	}

	private double calcularValorTotalElegible(IVenta venta) {
		double valorTotal = 0.0;
		for (LineItemProducto item : venta.getItemsProducto()) {
			valorTotal += item.montoTotalSinOfertasPorProductoAplicadas();
		}
		return valorTotal;
	}
}
