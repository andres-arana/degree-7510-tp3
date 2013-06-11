package tests.Modelo;

import static org.junit.Assert.assertEquals;
import modelo.LineItem;
import modelo.LineItemComparator;
import modelo.LineItemProducto;
import modelo.Producto;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class LineItemComparatorTests {

	LineItemComparator comparator;
	LineItem lineItem1;
	LineItem lineItem2;
	int nroLinea1, nroLinea2;


	@Before
	public void setUp() throws Exception {
		comparator = new LineItemComparator();

	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void AlCompararUnLineItemConNroDeLineaMenorAOtroLineItemDevuelveMenosUno() {
		nroLinea1 = 1;
		nroLinea2 = 2;
		lineItem1 = new LineItemProducto(new Producto("Producto", 12, 1,
				"Categoria", "Marca"), 2, nroLinea1);
		lineItem2 = new LineItemProducto(new Producto("Producto", 12, 1,
				"Categoria", "Marca"), 2, nroLinea2);
		assertEquals(-1, comparator.compare(lineItem1, lineItem2));
	}

	@Test
	public void AlCompararUnLineItemConNroDeLineaMayorAOtroLineItemDevuelveUno() {
		nroLinea1 = 2;
		nroLinea2 = 1;
		lineItem1 = new LineItemProducto(new Producto("Producto", 12, 1,
				"Categoria", "Marca"), 2, nroLinea1);
		lineItem2 = new LineItemProducto(new Producto("Producto", 12, 1,
				"Categoria", "Marca"), 2, nroLinea2);
		assertEquals(1, comparator.compare(lineItem1, lineItem2));
	}

	@Test
	public void AlCompararUnLineItemConNroDeLineaIgualAOtroLineItemDevuelveCero() {
		nroLinea1 = 2;
		nroLinea2 = 2;
		lineItem1 = new LineItemProducto(new Producto("Producto", 12, 1,
				"Categoria", "Marca"), 2, nroLinea1);
		lineItem2 = new LineItemProducto(new Producto("Producto", 12, 1,
				"Categoria", "Marca"), 2, nroLinea2);
		assertEquals(0, comparator.compare(lineItem1, lineItem2));
	}

}
