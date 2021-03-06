package modelo.condiciones;

import modelo.DiasSemanaEnum;
import modelo.ICondicionOferta;
import modelo.Producto;

public class CondicionOfertaFactory {
	public ICondicionOferta CondicionCategoria(String categoria) {
		return new CondicionCategoria(categoria);
	}

	public ICondicionOferta CondicionDiasSemana(DiasSemanaEnum[] dias) {
		return new CondicionDiasSemana(dias);
	}

	public ICondicionOferta CondicionLlevaNProducto(Producto producto,
			int cantidad) {
		return new CondicionLLevaNProducto(producto, cantidad);
	}

	public ICondicionOferta CondicionMarcaProducto(String marca) {
		return new CondicionMarcaProducto(marca);
	}

	public ICondicionOferta CondicionMedioDePago(String medioPago) {
		return new CondicionMedioDePago(medioPago);
	}

	public ICondicionOferta CondicionLlevaNProductosDeMarcaX(int cantidad,
			String marca) {
		return new CondicionLlevaNProductosDeMarcaX(cantidad, marca);
	}

	public ICondicionOferta CondicionLlevaNProductosDeCategoriaX(int cantidad,
			String categoria) {
		return new CondicionLlevaNProductosDeCategoriaX(cantidad, categoria);
	}

	public ICondicionOferta CondicionDescuentoJubilado() {
		return new CondicionJubilado();
	}
}
