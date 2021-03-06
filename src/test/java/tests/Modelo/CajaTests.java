package tests.Modelo;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import modelo.Caja;
import modelo.OfertasRepository;
import modelo.OperacionCajaInvalidaException;
import modelo.Producto;
import modelo.VentasRepository;

import org.junit.Before;
import org.junit.Test;

public class CajaTests {
	private Caja caja;
	private OfertasRepository repositorioOfertas;
	private VentasRepository repositorioVentas;

	@Before
	public void setUp() throws Exception {
		repositorioOfertas = new OfertasRepository();
		repositorioVentas = new VentasRepository();
		caja = new Caja(12, "Sucursal", repositorioOfertas, repositorioVentas);
	}

	@Test
	public void noSePuedeCerrarUnaCajaCerrada() {
		boolean lanzoExcepcion = false;
		try {
			caja.cerrarCaja();
		} catch (OperacionCajaInvalidaException e) {
			lanzoExcepcion = true;
		}
		assertTrue(lanzoExcepcion);
	}

	@Test
	public void noSePuedeIniciarUnaCompraEnUnaCajaCerrada() {
		boolean lanzoExcepcion = false;
		try {
			caja.iniciarVenta();
		} catch (OperacionCajaInvalidaException e) {
			lanzoExcepcion = true;
		}
		assertTrue(lanzoExcepcion);
	}

	@Test
	public void noSePuedeConfirmarUnaVentaEnUnaCajaCerrada() {
		boolean lanzoExcepcion = false;
		try {
			caja.confirmarVenta("Efectivo");
		} catch (OperacionCajaInvalidaException e) {
			lanzoExcepcion = true;
		}
		assertTrue(lanzoExcepcion);
	}

	@Test
	public void noSePuedeAgregarUnProductoEnUnaCajaCerrada() {
		boolean lanzoExcepcion = false;
		Producto p = new Producto("Telefono", 12, 12, "Electronica", "Nokia");
		try {
			caja.addProducto(p, 2);
		} catch (OperacionCajaInvalidaException e) {
			lanzoExcepcion = true;
		}
		assertTrue(lanzoExcepcion);
	}

	@Test
	public void noSePuedeCancelarUnaVentaEnUnaCajaCerrada() {
		boolean lanzoExcepcion = false;
		try {
			caja.cancelarVenta();
		} catch (OperacionCajaInvalidaException e) {
			lanzoExcepcion = true;
		}
		assertTrue(lanzoExcepcion);
	}

	@Test
	public void sePuedeAbrirUnaCajaEnUnaCajaCerrada() {
		boolean lanzoExcepcion = false;
		try {
			caja.abrirCaja();
		} catch (OperacionCajaInvalidaException e) {
			lanzoExcepcion = true;
		}
		assertFalse(lanzoExcepcion);
	}

	@Test
	public void noSePuedeConfirmarUnaVentaEnUnaCajaAbierta() {
		boolean lanzoExcepcion = false;
		caja.abrirCaja();
		try {
			caja.confirmarVenta("Efectivo");
		} catch (OperacionCajaInvalidaException e) {
			lanzoExcepcion = true;
		}
		assertTrue(lanzoExcepcion);
	}

	@Test
	public void sePuedeCerrarUnaCajaAbierta() {
		boolean lanzoExcepcion = false;
		caja.abrirCaja();
		try {
			caja.cerrarCaja();
		} catch (OperacionCajaInvalidaException e) {
			lanzoExcepcion = true;
		}
		assertFalse(lanzoExcepcion);
	}

	@Test
	public void sePuedeIniciarUnaCompraEnUnaCajaAbierta() {
		boolean lanzoExcepcion = false;
		caja.abrirCaja();
		try {
			caja.iniciarVenta();
		} catch (OperacionCajaInvalidaException e) {
			lanzoExcepcion = true;
		}
		assertFalse(lanzoExcepcion);
	}

	@Test
	public void noSePuedeAgregarUnProductoEnUnaCajaAbierta() {
		boolean lanzoExcepcion = false;
		Producto p = new Producto("Telefono", 12, 12, "Electronica", "Nokia");
		caja.abrirCaja();
		try {
			caja.addProducto(p, 2);
		} catch (OperacionCajaInvalidaException e) {
			lanzoExcepcion = true;
		}
		assertTrue(lanzoExcepcion);
	}

	@Test
	public void noSePuedeCancelarUnaVentaEnUnaCajaAbierta() {
		boolean lanzoExcepcion = false;
		caja.abrirCaja();
		try {
			caja.cancelarVenta();
		} catch (OperacionCajaInvalidaException e) {
			lanzoExcepcion = true;
		}
		assertTrue(lanzoExcepcion);
	}

	@Test
	public void noSePuedeAbrirUnaCajaAbierta() {
		boolean lanzoExcepcion = false;
		caja.abrirCaja();
		try {
			caja.abrirCaja();
		} catch (OperacionCajaInvalidaException e) {
			lanzoExcepcion = true;
		}
		assertTrue(lanzoExcepcion);
	}

	@Test
	public void sePuedeConfirmarUnaVentaEnUnaCajaEfectuandoUnaVenta() {
		boolean lanzoExcepcion = false;
		caja.abrirCaja();
		caja.iniciarVenta();
		try {
			caja.confirmarVenta("Efectivo");
		} catch (OperacionCajaInvalidaException e) {
			lanzoExcepcion = true;
		}
		assertFalse(lanzoExcepcion);
	}

	@Test
	public void noSePuedeCerrarUnaCajaEfectuandoUnaVenta() {
		boolean lanzoExcepcion = false;
		caja.abrirCaja();
		caja.iniciarVenta();
		try {
			caja.cerrarCaja();
		} catch (OperacionCajaInvalidaException e) {
			lanzoExcepcion = true;
		}
		assertTrue(lanzoExcepcion);
	}

	@Test
	public void noSePuedeIniciarUnaCompraEnUnaCajaEfectuandoUnaVenta() {
		boolean lanzoExcepcion = false;
		caja.abrirCaja();
		caja.iniciarVenta();
		try {
			caja.iniciarVenta();
		} catch (OperacionCajaInvalidaException e) {
			lanzoExcepcion = true;
		}
		assertTrue(lanzoExcepcion);
	}

	@Test
	public void sePuedeAgregarUnProductoEnUnaCajaEfectuandoUnaVenta() {
		boolean lanzoExcepcion = false;
		Producto p = new Producto("Telefono", 12, 12, "Electronica", "Nokia");
		caja.abrirCaja();
		caja.iniciarVenta();
		try {
			caja.addProducto(p, 2);
		} catch (OperacionCajaInvalidaException e) {
			lanzoExcepcion = true;
		}
		assertFalse(lanzoExcepcion);
	}

	@Test
	public void sePuedeCancelarUnaVentaEnUnaCajaEfectuandoUnaVenta() {
		boolean lanzoExcepcion = false;
		caja.abrirCaja();
		caja.iniciarVenta();
		try {
			caja.cancelarVenta();
		} catch (OperacionCajaInvalidaException e) {
			lanzoExcepcion = true;
		}
		assertFalse(lanzoExcepcion);
	}

	@Test
	public void noSePuedeAbrirUnaCajaEfectuandoUnaVenta() {
		boolean lanzoExcepcion = false;
		caja.abrirCaja();
		caja.iniciarVenta();
		try {
			caja.abrirCaja();
		} catch (OperacionCajaInvalidaException e) {
			lanzoExcepcion = true;
		}
		assertTrue(lanzoExcepcion);
	}


}
