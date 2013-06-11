package tests.Modelo;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import modelo.Producto;
import modelo.ProductosRepository;

import org.junit.Before;
import org.junit.Test;

public class ProductosRepositoryTests {

	private Producto productoMock;

	@Before
	public void doBefore() {
		productoMock = mock(Producto.class);
	}

	@Test
	public void incrementarCantidadDeProductosEnUnoAlAgregarProducto() {

		ProductosRepository productoRepo = new ProductosRepository();
		productoRepo.addProducto(productoMock);
		assertEquals(productoRepo.getProductos().size(), 1);
	}

	@Test
	public void cantidadDeProductosNuncaMenorACero() {
		ProductosRepository productoRepo = new ProductosRepository();
		productoRepo.removeProductoById(productoMock.getId());
		assertEquals(productoRepo.getProductos().size(), 0);
	}

	@Test
	public void disminuirCantidadDeProductosEnUnoAlQuitarProducto() {

		ProductosRepository productoRepo = new ProductosRepository();
		productoRepo.addProducto(productoMock);
		productoRepo.removeProductoById(productoMock.getId());
		assertEquals(productoRepo.getProductos().size(), 0);
	}

	@Test
	public void obtenerProductoPorDetalle() {
		ProductosRepository productoRepo = new ProductosRepository();
		productoRepo.addProducto(productoMock);
		assertEquals(
				productoMock,
				productoRepo.getProductoByDetalle(productoMock.getDescripcion()));
	}

	@Test
	public void obtenerProductoPorId() {
		ProductosRepository productoRepo = new ProductosRepository();
		productoRepo.addProducto(productoMock);
		assertEquals(productoMock,
				productoRepo.getProductoById(productoMock.getId()));
	}
}
