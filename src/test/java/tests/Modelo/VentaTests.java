package tests.Modelo;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import modelo.IQuery;
import modelo.LineItemProducto;
import modelo.Producto;
import modelo.Venta;

import org.junit.Test;

public class VentaTests {


	@Test
	public void marcarProductosComoOfertados() {

		Venta venta = new Venta(Calendar.getInstance(), 1);
		int cantidadaUnidades = 3;
		IQuery query = mock(IQuery.class);
		when(query.query((Producto) any())).thenReturn(true);
		Producto producto = mock(Producto.class);
		when(producto.getId()).thenReturn(1);
		venta.addProducto(producto, cantidadaUnidades);

		venta.marcarTodosLosProductosOfertados(query, new ArrayList<Producto>());

		List<LineItemProducto> lineItems = venta.getItemsProducto();
		assertEquals("La cantidad de unidades marcadas como ofertadas es incorrecta",lineItems.get(0)
				.getProductosConOfertasPorCategoriaOMarcaAplicadas(),
				cantidadaUnidades);

	}

	@Test
	public void marcarProductosComoOfertadosConExcepciones() {

		Venta venta = new Venta(Calendar.getInstance(), 1);
		int cantidadaUnidades = 10;
		IQuery query = mock(IQuery.class);

		when(query.query((Producto) any())).thenReturn(true);
		Producto producto1 = mock(Producto.class);
		Producto producto2 = mock(Producto.class);
		Producto producto3 = mock(Producto.class);
		when(producto1.getId()).thenReturn(1);
		when(producto2.getId()).thenReturn(2);
		when(producto3.getId()).thenReturn(3);
		venta.addProducto(producto1, cantidadaUnidades);
		venta.addProducto(producto2, cantidadaUnidades);
		venta.addProducto(producto3, cantidadaUnidades);
		ArrayList<Producto> productosQueNoAplican = new ArrayList<Producto>();
		productosQueNoAplican.add(producto3);

		venta.marcarTodosLosProductosOfertados(query, productosQueNoAplican);

		List<LineItemProducto> lineItems = venta.getItemsProducto();
		int cantidadProductosConOferta = lineItems.get(0)
				.getProductosConOfertasPorCategoriaOMarcaAplicadas();
		cantidadProductosConOferta += lineItems.get(1)
				.getProductosConOfertasPorCategoriaOMarcaAplicadas();

		assertEquals("La cantidad de unidades marcadas como ofertadas es incorrecta",cantidadProductosConOferta, cantidadaUnidades * 2);

	}

}
