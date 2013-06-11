package modelo.acciones;

import java.util.List;

import modelo.IAccionOferta;
import modelo.Producto;

public class AccionOfertaFactory {

	public IAccionOferta AccionDescontarDeProducto(Producto producto,
			int cantidad, double descuento) {
		return new AccionDescontarDeProducto(producto, cantidad, descuento);
	}

	public IAccionOferta AccionDescontarDeVentaCompleta(double descuento) {
		return new AccionDescontarDeVentaCompleta(descuento);
	}

	public IAccionOferta AccionDescontarPorCategoria(String categoria,
			List<Producto> excepciones, double descuento) {
		return new AccionDescontarPorCategoria(categoria, excepciones,
				descuento);
	}

	public IAccionOferta AccionDescontarPorMarca(String marca,
			List<Producto> excepciones, double descuento) {
		return new AccionDescontarPorMarca(marca, excepciones, descuento);
	}

	public IAccionOferta AccionDescontarAJubilados(double descuento) {
		return new AccionDescuentoJubilado(descuento);
	}

}
