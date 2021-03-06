package datos;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import modelo.DiasSemanaEnum;
import modelo.OfertaBuilder;
import modelo.OfertasRepository;
import modelo.Producto;
import modelo.ProductosRepository;
import modelo.acciones.AccionOfertaFactory;
import modelo.condiciones.CondicionOfertaFactory;

/**
 * Clase para crear los objetos de productos y ofertas para luego
 * utilizar en el main
 * 
 * @author Grupo 15
 *
 */
public class InyectorDeDatos {

	public static void inyectarProductos(ProductosRepository productos) {
		productos.addProducto(new Producto("Coca Cola", 10, 1, "Bebidas",
				"Coca Cola Company"));
		productos.addProducto(new Producto("Sprite", 10, 2, "Bebidas",
				"Coca Cola Company"));
		productos.addProducto(new Producto("Fanta", 10, 3, "Bebidas",
				"Coca Cola Company"));
		productos.addProducto(new Producto("Bon Aqua", 8, 4, "Bebidas",
				"Coca Cola Company"));
		productos.addProducto(new Producto("Pepsi", 9, 5, "Bebidas", "Pepsi"));
		productos.addProducto(new Producto("7 Up", 9, 6, "Bebidas", "Pepsi"));
		productos
				.addProducto(new Producto("Mirinda", 9, 7, "Bebidas", "Pepsi"));
		productos.addProducto(new Producto("Termidor", 10, 8, "Vinos",
				"Bodega Termidor"));
		productos.addProducto(new Producto("Etchart Malbec", 14, 9, "Vinos",
				"Bodegas Etchart"));
		productos.addProducto(new Producto("Etchart Syrah", 14, 10, "Vinos",
				"Bodegas Etchart"));
		productos.addProducto(new Producto("Vasco Viejo", 12, 11, "Vinos",
				"Bodegas Etchart"));
		productos.addProducto(new Producto("Trapiche Malbec", 14, 12, "Vinos",
				"Bodegas Trapiche"));
		productos.addProducto(new Producto("Trapiche", 14, 13, "Vinos",
				"Bodegas Trapiche"));
		productos.addProducto(new Producto("Trapiche Reserva", 14, 14, "Vinos",
				"Bodegas Trapiche"));
		productos.addProducto(new Producto("Michel Torino", 18, 15, "Vinos",
				"Bodega Michel Torino"));
		productos.addProducto(new Producto("Hamburgesas x 24 Granja del Sol",
				42.4, 16, "Alimentos", "Granja del Sol"));
		productos.addProducto(new Producto("Hamburgesas x 12 Granja del Sol",
				25.6, 17, "Alimentos", "Granja del Sol"));
		productos.addProducto(new Producto("Hamburgesas x 4 Granja del Sol",
				14.5, 18, "Alimentos", "Granja del Sol"));
		productos.addProducto(new Producto("Salchichas x 10 Granja del Sol",
				10.5, 19, "Alimentos", "Granja del Sol"));
		productos.addProducto(new Producto("Salchichas x 20 Granja del Sol",
				23, 20, "Alimentos", "Granja del Sol"));
		productos.addProducto(new Producto(
				"Patitas de Pollo x 400gr. Granja del Sol", 25, 21,
				"Alimentos", "Granja del Sol"));
		productos.addProducto(new Producto(
				"Patitas de Pollo x 800gr. Granja del Sol", 48.32, 22,
				"Alimentos", "Granja del Sol"));
		productos.addProducto(new Producto("Hamburgesas x 24 Swift", 40.32, 23,
				"Alimentos", "Swift"));
		productos.addProducto(new Producto("Hamburgesas x 12 Swift", 23.23, 24,
				"Alimentos", "Swift"));
		productos.addProducto(new Producto("Hamburgesas x 4 Swift", 12.5, 25,
				"Alimentos", "Swift"));
		productos.addProducto(new Producto("Salchichas x 10 Swift", 10, 26,
				"Alimentos", "Swift"));
		productos.addProducto(new Producto("Salchichas x 20 Swift", 22, 27,
				"Alimentos", "Swift"));
		productos.addProducto(new Producto("Arroz Gallo Oro x 1kg.", 22, 28,
				"Alimentos", "Molinos"));
		productos.addProducto(new Producto("Arroz Gallo Oro x 0,5kg.", 12, 29,
				"Alimentos", "Molinos"));
		productos.addProducto(new Producto("Arroz Luchetti x 0,5kg.", 20, 30,
				"Alimentos", "Molinos"));
		productos.addProducto(new Producto("Arroz Luchetti x 1kg.", 11, 31,
				"Alimentos", "Molinos"));
		productos.addProducto(new Producto("Maceta de barro. Chica", 20, 32,
				"Jardineria", "Barritos SA"));
	}

	public static void inyectarOfertas(OfertasRepository repositorioDeOfertas) {
		ProductosRepository productos = new ProductosRepository();
		InyectorDeDatos.inyectarProductos(productos);
		CondicionOfertaFactory condicionOfertaFactory = new CondicionOfertaFactory();
		AccionOfertaFactory accionOfertaFactory = new AccionOfertaFactory();
		OfertaBuilder ofertaBuilder = new OfertaBuilder();
		Calendar ofertaDesde;
		Calendar ofertaHasta;

		ofertaDesde = Calendar.getInstance();
		ofertaHasta = Calendar.getInstance();
		ofertaHasta.add(Calendar.MONTH, 1);

		// Oferta 1
		ofertaBuilder.crearNuevaOferta(ofertaDesde, ofertaHasta,
				"2x1 Coca Cola");
		ofertaBuilder.agregarCondicion(condicionOfertaFactory
				.CondicionLlevaNProducto(productos.getProductoById(1), 2));
		ofertaBuilder.agregarAccion(accionOfertaFactory
				.AccionDescontarDeProducto(productos.getProductoById(1), 1, 1));
		repositorioDeOfertas.addOferta(ofertaBuilder.getOferta());

		// Oferta 2
		ofertaBuilder.crearNuevaOferta(ofertaDesde, ofertaHasta,
				"Coca + Sprite = Fanta al 50%");
		ofertaBuilder.agregarCondicion(condicionOfertaFactory
				.CondicionLlevaNProducto(productos.getProductoById(1), 1));
		ofertaBuilder.agregarCondicion(condicionOfertaFactory
				.CondicionLlevaNProducto(
						productos.getProductoByDetalle("Sprite"), 1));
		ofertaBuilder.agregarCondicion(condicionOfertaFactory
				.CondicionLlevaNProducto(
						productos.getProductoByDetalle("Fanta"), 1));
		ofertaBuilder.agregarAccion(accionOfertaFactory
				.AccionDescontarDeProducto(
						productos.getProductoByDetalle("Fanta"), 1, 0.5));
		repositorioDeOfertas.addOferta(ofertaBuilder.getOferta());

		// Oferta 3
		ofertaBuilder.crearNuevaOferta(ofertaDesde, ofertaHasta,
				"10% Pago en Efectivo");
		ofertaBuilder.agregarCondicion(condicionOfertaFactory
				.CondicionMedioDePago("Efectivo"));
		ofertaBuilder.agregarAccion(accionOfertaFactory
				.AccionDescontarDeVentaCompleta(0.1));
		repositorioDeOfertas.addOferta(ofertaBuilder.getOferta());

		// Oferta 4
		String marca = "Coca Cola Company";
		ofertaBuilder.crearNuevaOferta(ofertaDesde, ofertaHasta,
				"Marca Coca Cola con 50% de descuento");
		ofertaBuilder.agregarCondicion(condicionOfertaFactory
				.CondicionMarcaProducto(marca));
		ofertaBuilder.agregarAccion(accionOfertaFactory
				.AccionDescontarPorMarca(marca, null, 0.5));
		repositorioDeOfertas.addOferta(ofertaBuilder.getOferta());

		// Oferta 5
		List<Producto> excepciones = new ArrayList<Producto>();
		excepciones.add(productos.getProductoByDetalle("Michel Torino"));
		DiasSemanaEnum[] dias = { DiasSemanaEnum.JUEVES };
		ofertaBuilder.crearNuevaOferta(ofertaDesde, ofertaHasta,
				"Jueves 20% en Vinos excepto Michel Torino");
		ofertaBuilder.agregarCondicion(condicionOfertaFactory
				.CondicionDiasSemana(dias));
		ofertaBuilder.agregarAccion(accionOfertaFactory
				.AccionDescontarPorCategoria("Vinos", excepciones, 0.2));
		repositorioDeOfertas.addOferta(ofertaBuilder.getOferta());

		// Oferta 6
		ofertaBuilder
				.crearNuevaOferta(ofertaDesde, ofertaHasta,
						"LLevando 3 o mas productos Granja del Sol 10% en todos sus productos");
		ofertaBuilder.agregarCondicion(condicionOfertaFactory
				.CondicionLlevaNProductosDeMarcaX(3, "Granja del Sol"));
		ofertaBuilder.agregarAccion(accionOfertaFactory
				.AccionDescontarPorMarca("Granja del Sol", null, 0.1));
		repositorioDeOfertas.addOferta(ofertaBuilder.getOferta());

		// Oferta 7
		DiasSemanaEnum[] dias1 = { DiasSemanaEnum.JUEVES };
		ofertaBuilder.crearNuevaOferta(ofertaDesde, ofertaHasta,
				"Jueves 20% Pago en Efectivo");
		ofertaBuilder.agregarCondicion(condicionOfertaFactory
				.CondicionDiasSemana(dias1));
		ofertaBuilder.agregarCondicion(condicionOfertaFactory
				.CondicionMedioDePago("Efectivo"));
		ofertaBuilder.agregarAccion(accionOfertaFactory
				.AccionDescontarDeVentaCompleta(0.2));
		repositorioDeOfertas.addOferta(ofertaBuilder.getOferta());

		// Oferta 8
		ofertaBuilder.crearNuevaOferta(ofertaDesde, ofertaHasta,
				"15% Bodegas Trapiche");
		ofertaBuilder.agregarCondicion(condicionOfertaFactory
				.CondicionMarcaProducto("Bodegas Trapiche"));
		ofertaBuilder.agregarAccion(accionOfertaFactory
				.AccionDescontarPorMarca("Bodegas Trapiche", null, 0.15));
		repositorioDeOfertas.addOferta(ofertaBuilder.getOferta());

		// Oferta 9
		ofertaBuilder.crearNuevaOferta(ofertaDesde, ofertaHasta,
				"Visa 25% de Descuento");
		ofertaBuilder.agregarCondicion(condicionOfertaFactory
				.CondicionMedioDePago("Visa"));
		ofertaBuilder.agregarAccion(accionOfertaFactory
				.AccionDescontarDeVentaCompleta(0.25));
		repositorioDeOfertas.addOferta(ofertaBuilder.getOferta());

		// Oferta 10
		ofertaBuilder.crearNuevaOferta(ofertaDesde, ofertaHasta,
				"10 % Jubilados");
		ofertaBuilder.agregarCondicion(condicionOfertaFactory
				.CondicionDescuentoJubilado());
		ofertaBuilder.agregarAccion(accionOfertaFactory
				.AccionDescontarAJubilados(0.1));
		repositorioDeOfertas.addOferta(ofertaBuilder.getOferta());

		// OFERTAS PARA CUPONES
		String marca2 = "Barritos SA";
		int idOferta1 = 1000;
		ofertaBuilder.crearNuevaOferta(ofertaDesde, ofertaHasta,
				"Cupon: Maceta chica 2x1");
		ofertaBuilder.agregarCondicion(condicionOfertaFactory
				.CondicionMarcaProducto(marca2));
		ofertaBuilder.agregarAccion(accionOfertaFactory
				.AccionDescontarPorMarca(marca2, null, 0.5));
		repositorioDeOfertas.addOfertaParaCupon(idOferta1, ofertaBuilder.getOferta());

	}
}
