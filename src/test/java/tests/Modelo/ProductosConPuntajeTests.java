package tests.Modelo;

import static org.junit.Assert.assertEquals;
import modelo.Caja;
import modelo.Cliente;
import modelo.Oferta;
import modelo.OfertasRepository;
import modelo.Producto;
import modelo.ProductosRepository;
import modelo.VentasRepository;
import modelo.acciones.AccionAgregarPuntos;
import modelo.condiciones.CondicionLLevaNProducto;

import org.junit.Test;

public class ProductosConPuntajeTests extends AbstractOfertasTest {
	@Test
	public void deberiaAcumularPuntos() {
		ProductosRepository productos = buildProductos(coca(), tv());
		OfertasRepository ofertas = buildOfertas(
				oferta1Punto10Cocas(productos), oferta100Puntos1TV(productos));
		VentasRepository repositorioVentas = new VentasRepository();

		Cliente cliente = new Cliente("99.999.999");
		Caja caja = new Caja(1, "Moron", ofertas, repositorioVentas);

		caja.abrirCaja();
		caja.iniciarVenta(cliente);
		caja.addProducto(productos.getProductoByDetalle("Coca Cola"), 12);
		caja.addProducto(productos.getProductoByDetalle("TV Flat"), 1);
		caja.confirmarVenta("Efectivo");

		assertEquals(101, cliente.getPuntos());
	}

	private Oferta oferta100Puntos1TV(ProductosRepository productos) {
		Oferta oferta = ofertaVigente("100 puntos por TV Flat");
		Producto tv = productos.getProductoById(tvId());
		oferta.addCondicion(new CondicionLLevaNProducto(tv, 1));
		oferta.addAccion(new AccionAgregarPuntos(100));
		return oferta;
	}

	private Oferta oferta1Punto10Cocas(ProductosRepository productos) {
		Oferta oferta = ofertaVigente("1 punto por 10 Coca Cola");
		Producto coca = productos.getProductoById(cocaId());
		oferta.addCondicion(new CondicionLLevaNProducto(coca, 10));
		oferta.addAccion(new AccionAgregarPuntos(1));
		return oferta;
	}

	private Producto tv() {
		return new Producto("TV Flat", 8000, tvId(), "Tecno", "Sony");
	}

	private int tvId() {
		return 2;
	}

	private Producto coca() {
		return new Producto("Coca Cola", 18, cocaId(), "Bebidas", "Coca Cola");
	}

	private int cocaId() {
		return 1;
	}
}
