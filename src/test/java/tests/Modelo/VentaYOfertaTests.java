package tests.Modelo;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Calendar;

import modelo.OfertaBuilder;
import modelo.OfertasRepository;
import modelo.Producto;
import modelo.Venta;
import modelo.acciones.AccionOfertaFactory;
import modelo.condiciones.CondicionOfertaFactory;

import org.junit.Before;
import org.junit.Test;

public class VentaYOfertaTests {

	private Calendar ofertaDesde;
	private Calendar ofertaHasta;
	private AccionOfertaFactory accionOfertaFactory;
	private CondicionOfertaFactory condicionOfertaFactory;
	private OfertaBuilder ofertaBuilder;
	private OfertasRepository ofertasRepositorio;

	@Before
	public void doBefore() {
		ofertaDesde = Calendar.getInstance();
		ofertaDesde.add(Calendar.MONTH, -1);
		ofertaHasta = Calendar.getInstance();
		ofertaHasta.add(Calendar.MONTH, 1);

		accionOfertaFactory = new AccionOfertaFactory();
		condicionOfertaFactory = new CondicionOfertaFactory();
		ofertaBuilder = new OfertaBuilder();
		ofertasRepositorio = new OfertasRepository();
	}

	@Test
	public void aplicarDescuentoPorCategoria() {
		String categoria = "categoriaTest";
		double descuento = 0.1;
		double precio = 10;

		Producto productoMock = mock(Producto.class);
		when(productoMock.getId()).thenReturn(1);
		when(productoMock.getPrecio()).thenReturn(precio);
		when(productoMock.getCategoria()).thenReturn(categoria);
		when(productoMock.getDescripcion()).thenReturn("Coca Cola");

		ofertaBuilder.crearNuevaOferta(ofertaDesde, ofertaHasta, categoria);

		ofertaBuilder.agregarCondicion(condicionOfertaFactory
				.CondicionCategoria(categoria));

		ofertaBuilder.agregarAccion(accionOfertaFactory
				.AccionDescontarDeVentaCompleta(descuento));

		ofertasRepositorio.addOferta(ofertaBuilder.getOferta());

		Venta venta = new Venta(Calendar.getInstance(), 1);
		venta.addProducto(productoMock, 1);

		ofertasRepositorio.aplicarOfertas(venta);

		venta.cobrar();

		assertEquals("El descuento calculado es incorrecto", productoMock.getPrecio() * 0.9, venta.getTotal(), 1);

	}

	@Test
	// Testea una venta con descuento levando n prods iguales, pagas M < N
	public void ventaConOfertaLlevaNpagaM() {
		double precio = 10;

		// Creo un producto con precio $10
		Producto productoMock = mock(Producto.class);
		when(productoMock.getId()).thenReturn(1);
		when(productoMock.getPrecio()).thenReturn(precio);
		when(productoMock.getMarca()).thenReturn("Coca Cola Company");
		when(productoMock.getDescripcion()).thenReturn("Coca Cola");

		// Creo una nueva oferta
		ofertaBuilder.crearNuevaOferta(ofertaDesde, ofertaHasta,
				"2x1 Coca Cola");
		// Le agrego la condicion de que si lleva dos de un producto.
		ofertaBuilder.agregarCondicion(condicionOfertaFactory
				.CondicionLlevaNProducto(productoMock, 2));
		// Le descuenta una unidad.
		ofertaBuilder.agregarAccion(accionOfertaFactory
				.AccionDescontarDeProducto(productoMock, 1, 1));

		// Cargo la oferta al repositorio de ofertas.
		ofertasRepositorio.addOferta(ofertaBuilder.getOferta());

		Venta venta = new Venta(Calendar.getInstance(), 1);

		// le cargo 2 unidades del producto de la promo 2X1
		venta.addProducto(productoMock, 2);

		ofertasRepositorio.aplicarOfertas(venta);

		venta.cobrar();

		// Dado que hay una promocion 2X1 para un producto, cuando lleva ese
		// prodcuto, entonces paga solo 1 de los 2.
		// El precio del produco es $10, lleva 2, esta en promo. Paga $10.
		assertEquals("El precio total calculado es incorrecto", venta.getTotal(), precio, 1);
	}

	@Test
	public void ventaConOfertaLlevandoNyMpagaMenosElZ() {
		double precioCoca = 10;
		double precioSprite = 20;
		double precioFanta = 30;

		Producto productoCocaMock = mock(Producto.class);
		when(productoCocaMock.getId()).thenReturn(1);
		when(productoCocaMock.getPrecio()).thenReturn(precioCoca);
		when(productoCocaMock.getDescripcion()).thenReturn("Coca Cola");

		Producto productoSpriteMock = mock(Producto.class);
		when(productoSpriteMock.getId()).thenReturn(1);
		when(productoSpriteMock.getPrecio()).thenReturn(precioSprite);
		when(productoSpriteMock.getDescripcion()).thenReturn("Sprite");

		Producto productoFantaMock = mock(Producto.class);
		when(productoFantaMock.getId()).thenReturn(1);
		when(productoFantaMock.getPrecio()).thenReturn(precioFanta);
		when(productoFantaMock.getDescripcion()).thenReturn("Fanta");

		// Creo una nueva oferta
		ofertaBuilder.crearNuevaOferta(ofertaDesde, ofertaHasta,
				"Coca + Sprite = Fanta al 50%");
		// Le agrego la condicion de que si lleva el producto Coca Cola y el
		// Producto Sprite
		ofertaBuilder.agregarCondicion(condicionOfertaFactory
				.CondicionLlevaNProducto(productoCocaMock, 1));
		ofertaBuilder.agregarCondicion(condicionOfertaFactory
				.CondicionLlevaNProducto(productoSpriteMock, 1));
		ofertaBuilder.agregarCondicion(condicionOfertaFactory
				.CondicionLlevaNProducto(productoFantaMock, 1));
		// Le descuenta la mitad a la Fanta.
		ofertaBuilder.agregarAccion(accionOfertaFactory
				.AccionDescontarDeProducto(productoFantaMock, 1, 0.5));

		// Cargo la oferta al repositorio de ofertas.
		ofertasRepositorio.addOferta(ofertaBuilder.getOferta());

		// Creo la venta
		Venta venta = new Venta(Calendar.getInstance(), 1);
		// Y le cargo 1 unidades de cada uno de los prods de la promo
		venta.addProducto(productoCocaMock, 1);
		venta.addProducto(productoSpriteMock, 1);
		venta.addProducto(productoFantaMock, 1);

		ofertasRepositorio.aplicarOfertas(venta);

		venta.cobrar();
		// Dado que hay una promocion Cocal + Sprite = Fanta al 50%, cuando
		// lleva esos 3 productos prodcuto, entonces paga la fanta al 50%
		assertEquals("El precio total calculado es incorrecto", venta.getTotal(), precioCoca + precioSprite
				+ (precioFanta / 2), 1);

	}

	@Test
	// Testea una venta con descuento por marca: Llevando algun producto de una
	// marca dada, hay un descuento de algun porcentaje.
	public void ventaConOfertaPorMarca() {
		String marca = "Coca Cola Company";

		double precioCoca = 10;
		double precioSprite = 20;
		double precioFanta = 30;

		Producto productoCocaMock = mock(Producto.class);
		when(productoCocaMock.getId()).thenReturn(1);
		when(productoCocaMock.getPrecio()).thenReturn(precioCoca);
		when(productoCocaMock.getMarca()).thenReturn(marca);

		Producto productoSpriteMock = mock(Producto.class);
		when(productoSpriteMock.getId()).thenReturn(1);
		when(productoSpriteMock.getPrecio()).thenReturn(precioSprite);
		when(productoSpriteMock.getMarca()).thenReturn(marca);

		Producto productoFantaMock = mock(Producto.class);
		when(productoFantaMock.getId()).thenReturn(1);
		when(productoFantaMock.getPrecio()).thenReturn(precioFanta);
		when(productoFantaMock.getMarca()).thenReturn(marca);

		// Creo una nueva oferta
		ofertaBuilder.crearNuevaOferta(ofertaDesde, ofertaHasta,
				"Marca Coca Cola con 50% de descuento");
		// Le agrego la condicion de que si lleva dos de un producto.
		ofertaBuilder.agregarCondicion(condicionOfertaFactory
				.CondicionMarcaProducto(marca));
		// Le descuenta la mitad a los productos de la marca.
		ofertaBuilder.agregarAccion(accionOfertaFactory
				.AccionDescontarPorMarca(marca, null, 0.5));

		// Cargo la oferta al repositorio de ofertas.
		ofertasRepositorio.addOferta(ofertaBuilder.getOferta());

		// Creo la venta
		Venta venta = new Venta(Calendar.getInstance(), 1);
		// Y le cargo 3 productos de la marca.
		venta.addProducto(productoCocaMock, 1);
		venta.addProducto(productoSpriteMock, 1);
		venta.addProducto(productoFantaMock, 1);

		ofertasRepositorio.aplicarOfertas(venta);

		// Dado que hay una promocion tal que hace el 50% de desc a todos los
		// prods de la marca Coca Cola,
		// cuando lleva 3 productos de $10, $20 y $30
		// Entonces solo debe cobrar la mitdad de cada uno: $30
		assertEquals("El precio total calculado es incorrecto",venta.getTotal(), precioCoca / 2 + precioSprite / 2
				+ precioFanta / 2, 1);
	}

	@Test
	// Testea una venta con oferta de pagando con un medio de pago dado
	// (Efectivo en este caso) hay un descuento de algun porcentaje.
	public void ventaConOfertaPorMedioDePago() {
		double precioCoca = 10;
		double precioSprite = 20;
		double precioFanta = 30;
		String medioPago = "Efectivo";
		double descuento = 0.1;

		//
		Producto productoCocaMock = mock(Producto.class);
		when(productoCocaMock.getId()).thenReturn(1);
		when(productoCocaMock.getPrecio()).thenReturn(precioCoca);

		Producto productoSpriteMock = mock(Producto.class);
		when(productoSpriteMock.getId()).thenReturn(1);
		when(productoSpriteMock.getPrecio()).thenReturn(precioSprite);

		Producto productoFantaMock = mock(Producto.class);
		when(productoFantaMock.getId()).thenReturn(1);
		when(productoFantaMock.getPrecio()).thenReturn(precioFanta);

		// Creo una nueva oferta
		ofertaBuilder.crearNuevaOferta(ofertaDesde, ofertaHasta,
				"10% Pago en Efectivo");
		// Le agrego la condicion de que si paga con el medio de la oferta.
		ofertaBuilder.agregarCondicion(condicionOfertaFactory
				.CondicionMedioDePago(medioPago));
		// Le descuenta el porcentaje de la oferta.
		ofertaBuilder.agregarAccion(accionOfertaFactory
				.AccionDescontarDeVentaCompleta(descuento));

		// Cargo la oferta al repositorio de ofertas.
		ofertasRepositorio.addOferta(ofertaBuilder.getOferta());

		// Creo la venta
		Venta venta = new Venta(Calendar.getInstance(), 1);
		// Y le cargo 3 productos de la marca.
		venta.addProducto(productoCocaMock, 1);
		venta.addProducto(productoSpriteMock, 1);
		venta.addProducto(productoFantaMock, 1);
		venta.setFormaDePago(medioPago);

		ofertasRepositorio.aplicarOfertas(venta);
		venta.cobrar();
		// Dado que hay una promocion tal que hace el 10% de desc a todos los
		// prods si se paga con efectivo,
		// cuando lleva 3 productos de $10, $20 y $30
		// Entonces debe cobrar $54
		assertEquals("El precio total calculado es incorrecto", (precioCoca + precioSprite + precioFanta)
				* (1 - descuento), venta.getTotal(), 1);

	}
}
