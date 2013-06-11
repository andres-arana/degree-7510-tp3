package tests.Modelo;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Calendar;

import modelo.OfertasRepository;
import modelo.Producto;
import modelo.Venta;
import modelo.VentasRepository;

import org.junit.Before;
import org.junit.Test;

public class VentasRepositoryTests {

	OfertasRepository ofertasMock;
	Venta venta1Mock, venta2Mock, venta3Mock;

	@Before
	public void doBefore() {
		
		ofertasMock = mock(OfertasRepository.class);
		venta1Mock = mock(Venta.class);
		when(venta1Mock.getMedioDePago()).thenReturn("Visa - Banco Itau");

		venta2Mock = mock(Venta.class);
		when(venta2Mock.getMedioDePago()).thenReturn("Visa - Banco Santander");

		venta3Mock = mock(Venta.class);
		when(venta3Mock.getMedioDePago()).thenReturn("Visa - Banco Santander");
	}

	@Test
	public void totaldeVentasIgualACeroAlAbrirCaja() {

		VentasRepository ventaRepo = new VentasRepository();
		assertEquals("El total de la ventas no es cero al abrir la caja", 0, ventaRepo.calcularTotalCaja(0), 1);
	}

	@Test
	public void incrementarTotalVentasAlAgregarUnaVenta() {
		// Creo un producto de $10.
		Producto prod = new Producto("des", 10, 1, "1", "marca");
		// Creo una venta en la caja 1 y le agrego el producto anterior.
		Venta venta = new Venta(Calendar.getInstance(), 1);
		venta.addProducto(prod, 1);
		// Creo el repositorio de Ventas y le agrego la venta de $10.
		VentasRepository ventaRepo = new VentasRepository();
		ventaRepo.add(venta);
		// Entonces el total de la caja 1 debe ser $10.
		assertEquals("El total de venta no es correcto", 10, ventaRepo.calcularTotalCaja(1), 1);

	}

	@Test
	public void obtenerMediosDePagoTodosDistintos() {

		VentasRepository ventasRepositorio = new VentasRepository();
		ventasRepositorio.add(venta1Mock);
		ventasRepositorio.add(venta2Mock);

		ArrayList<String> mediosDePagoCalculados = ventasRepositorio
				.getMediosDePago();

		ArrayList<String> mediosDePagosEsperados = new ArrayList<String>();
		mediosDePagosEsperados.add("Visa - Banco Itau");
		mediosDePagosEsperados.add("Visa - Banco Santander");

		assertEquals("Medios de pagos obtenidos incorrectamente", mediosDePagosEsperados, mediosDePagoCalculados);

	}

	@Test
	public void obtenerMediosDePagoHabiendoRepetidos() {

		VentasRepository ventasRepositorio = new VentasRepository();
		ventasRepositorio.add(venta1Mock);
		ventasRepositorio.add(venta2Mock);
		ventasRepositorio.add(venta3Mock);

		ArrayList<String> mediosDePagoCalculados = ventasRepositorio
				.getMediosDePago();

		ArrayList<String> mediosDePagosEsperados = new ArrayList<String>();
		mediosDePagosEsperados.add("Visa - Banco Itau");
		mediosDePagosEsperados.add("Visa - Banco Santander");

		assertEquals("Medios de pagos obtenidos incorrectamente", mediosDePagosEsperados, mediosDePagoCalculados);
	}

}
