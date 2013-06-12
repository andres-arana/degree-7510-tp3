package tests.Modelo;

import java.util.Calendar;

import modelo.Oferta;
import modelo.OfertasRepository;
import modelo.Producto;
import modelo.ProductosRepository;

public abstract class AbstractOfertasTest {

	protected Oferta ofertaVigente(final String descripcion) {
		Calendar fechaDesde = Calendar.getInstance();
		fechaDesde.add(Calendar.DAY_OF_MONTH, -5);
		Calendar fechaHasta = Calendar.getInstance();
		fechaHasta.add(Calendar.DAY_OF_MONTH, 5);
		return new Oferta(fechaDesde, fechaHasta, descripcion);
	}

	protected OfertasRepository buildOfertas(Oferta... ofertas) {
		OfertasRepository resultado = new OfertasRepository();
		for (Oferta o : ofertas) {
			resultado.addOferta(o);
		}
		return resultado;
	}

	protected ProductosRepository buildProductos(Producto... productos) {
		ProductosRepository resultado = new ProductosRepository();
		for (Producto p : productos) {
			resultado.addProducto(p);
		}
		return resultado;
	}

}
