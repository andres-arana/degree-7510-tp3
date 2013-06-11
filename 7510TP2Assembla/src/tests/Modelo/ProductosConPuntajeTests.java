package tests.Modelo;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import modelo.Caja;
import modelo.OfertasRepository;
import modelo.Producto;
import modelo.ProductosRepository;
import modelo.VentasRepository;

import org.junit.Before;
import org.junit.Test;

public class ProductosConPuntajeTests {
	@Test
	public void deberiaAcumularPuntos() {
		ProductosRepository productos = new ProductosRepository();
		productos.addProducto(new Producto("Coca Cola", 10, 1, "Bebidas",
				"Coca Cola Company", 0.1f));
		productos.addProducto(new Producto("TV Flat", 5000, 2, "Tecno",
				"Sony", 100.0f));
		
		OfertasRepository ofertas = new OfertasRepository();
		
		VentasRepository repositorioVentas = new VentasRepository();
		
		Caja caja = new Caja(1, "Moron", ofertas, repositorioVentas);

		caja.abrirCaja();
		caja.iniciarVenta();
		caja.addProducto(productos.getProductoByDetalle("Coca Cola"), 12);
		caja.addProducto(productos.getProductoByDetalle("TV Flat"), 1);
		caja.confirmarVenta("Efectivo");
		
		assertEquals(101, caja.getPuntosObtenidos());
	}
}
