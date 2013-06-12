package tests.Modelo;

import static org.junit.Assert.assertEquals;
import modelo.Caja;
import modelo.Cliente;
import modelo.IVenta;
import modelo.OfertasRepository;
import modelo.Producto;
import modelo.ProductosRepository;
import modelo.VentasRepository;

import org.junit.Test;

public class PagoConPuntosTests extends AbstractOfertasTest {
	@Test
	public void deberiaPoderPagarConPuntos() {
		ProductosRepository productos = buildProductos(coca(), vino());
		OfertasRepository ofertas = buildOfertas();
		VentasRepository repositorioVentas = new VentasRepository();

		Cliente cliente = new Cliente("99.999.999");
		cliente.asignarPuntos(12);
		Caja caja = new Caja(1, "Moron", ofertas, repositorioVentas);

		caja.abrirCaja();
		caja.iniciarVenta(cliente);
		caja.addProducto(productos.getProductoByDetalle("Coca Cola"), 2);
		caja.addProducto(productos.getProductoByDetalle("Vino"), 1);
		caja.aplicarPuntos(12);
		IVenta venta = caja.confirmarVenta("Efectivo");

		assertEquals(0, cliente.getPuntos());
		assertEquals(4.0, venta.getTotal(), 0.01);
	}

	private Producto vino() {
		return new Producto("Vino", 14, vinoId(), "Bebidas", "Bodega");
	}

	private int vinoId() {
		return 2;
	}

	private Producto coca() {
		return new Producto("Coca Cola", 1, cocaId(), "Bebidas", "Coca Cola");
	}

	private int cocaId() {
		return 1;
	}
}
