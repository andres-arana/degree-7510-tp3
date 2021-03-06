package tests.Modelo;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;

import modelo.IAccionOferta;
import modelo.ICondicionOferta;
import modelo.Oferta;
import modelo.OfertasRepository;
import modelo.Producto;
import modelo.Venta;
import modelo.acciones.AccionDescontarDeVentaCompleta;
import modelo.condiciones.CondicionMedioDePago;

import org.junit.Test;

public class OfertaTests {

	@Test
	// Si una venta es pagada con un medio de pago que tiene descuento, entonces
	// esa venta
	// cumple la condicion de la oferta de ese medio de pago.
	public void chequearCondicionMedioDePago() {
		// Creo una condicion por forma de pago.
		String formaPago = "debito frances";
		ICondicionOferta condicion = new CondicionMedioDePago(formaPago);

		// Creo una oferta vigente.
		Calendar fechaDesde = Calendar.getInstance();
		fechaDesde.add(Calendar.DAY_OF_MONTH, -1);
		Calendar fechaHasta = Calendar.getInstance();
		fechaDesde.add(Calendar.DAY_OF_MONTH, 1);
		Calendar fechaVenta = Calendar.getInstance();
		Oferta oferta = new Oferta(fechaDesde, fechaHasta,
				"Descuento pagando con debito frances");

		// Agrego la condicion de forma de pago a la oferta.
		oferta.addCondicion(condicion);

		// Si hago una venta con fecha entre el rango de la oferta pagada con
		// esa forma de pago
		Venta venta = new Venta(fechaVenta, 1);
		venta.setFormaDePago(formaPago);
		venta.cobrar();

		// Entonces tendria que aplicar la oferta a la venta, ya que la �ltima
		// cumple las condiciones de la oferta.
		assertEquals(true, oferta.chequearCondiciones(venta));
	}

	@Test
	// Debe descontar el porcentaje del descuento al total de la factura.
	public void aplicarOfertaMedioPago() {
		// Creo la accion de la oferta. En este caso es descontar 10% al total
		// de la venta.
		float descuento = 0.10f;
		int prodPrecio = 100;

		IAccionOferta accion = new AccionDescontarDeVentaCompleta(descuento);

		Calendar fechaDesde = Calendar.getInstance();
		fechaDesde.add(Calendar.DAY_OF_MONTH, -1);
		Calendar fechaHasta = Calendar.getInstance();
		fechaDesde.add(Calendar.DAY_OF_MONTH, 1);
		Calendar fechaVenta = Calendar.getInstance();

		// Creo la oferta y le agrego la accion a realizarle a las ventas que se
		// le aplique.
		Oferta oferta = new Oferta(fechaDesde, fechaHasta,
				"Descuento pagando con debito frances");
		oferta.addAccion(accion);

		// Creo un producto con precion $100
		Producto producto = new Producto("ProdTest", prodPrecio, 1, "Test",
				"Test");

		// Si hago una venta con fecha entre el rango de la oferta pagada con
		// esa forma de pago
		OfertasRepository ofertasRepository = new OfertasRepository();
		ofertasRepository.addOferta(oferta);
		Venta venta = new Venta(fechaVenta, 1);
		venta.addProducto(producto, 1);

		// Guardo el total de la factura sin descuento.
		double totalSinDescuento = venta.getTotalSinDescuentos();

		// Aplico el descuento.
		venta.setFormaDePago("debito frances");
		ofertasRepository.aplicarOfertas(venta);
		venta.cobrar();

		// Obtengo el total con el descuento aplicado.
		double totalConDescuento = venta.getTotal();

		double esperado = totalSinDescuento - (totalSinDescuento * 0.1);
		assertEquals(esperado, totalConDescuento, 1);

	}
}
