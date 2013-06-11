package app;
import modelo.Caja;
import modelo.OfertasRepository;
import modelo.ProductosRepository;
import modelo.VentasRepository;
import datos.InyectorDeDatos;

public class Main {
	public static void main(String[] args) {

		ProductosRepository productos = new ProductosRepository();
		OfertasRepository ofertas = new OfertasRepository();
		VentasRepository repositorioVentas = new VentasRepository();
		InyectorDeDatos.inyectarProductos(productos);
		InyectorDeDatos.inyectarOfertas(ofertas);
		Caja caja = new Caja(1, "Moron", ofertas, repositorioVentas);

		caja.abrirCaja();
		// Venta Con Descuentos
		caja.iniciarVenta();
		caja.addProducto(productos.getProductoByDetalle("Coca Cola"), 5);
		caja.addProducto(productos.getProductoByDetalle("Michel Torino"), 3);
		caja.addProducto(productos.getProductoByDetalle("Sprite"), 1);
		caja.addProducto(productos.getProductoByDetalle("Fanta"), 1);
		caja.addProducto(productos.getProductoByDetalle("Bon Aqua"), 3);
		caja.addProducto(productos.getProductoById(18), 2);
		caja.addProducto(productos.getProductoById(21), 2);
		caja.addProducto(productos.getProductoById(16), 2);
		caja.addProducto(productos.getProductoById(26), 1);
		caja.addProducto(productos.getProductoById(28), 2);
		caja.addProducto(productos.getProductoById(30), 2);
		caja.addProducto(productos.getProductoByDetalle("Trapiche"), 4);
		caja.confirmarVenta("Efectivo");

		// Venta Cancelada
		caja.iniciarVenta();
		caja.addProducto(productos.getProductoById(16), 2);
		caja.addProducto(productos.getProductoById(26), 4);
		caja.addProducto(productos.getProductoById(28), 1);
		caja.addProducto(productos.getProductoById(30), 1);
		caja.cancelarVenta();

		// Venta Sin Descuentos
		caja.iniciarVenta();
		caja.addProducto(productos.getProductoById(16), 2);
		caja.addProducto(productos.getProductoById(26), 4);
		caja.addProducto(productos.getProductoById(28), 1);
		caja.addProducto(productos.getProductoById(30), 1);
		caja.addProducto(productos.getProductoByDetalle("Pepsi"), 2);
		caja.addProducto(productos.getProductoByDetalle("7 Up"), 4);
		caja.confirmarVenta("MasterCard");

		// Venta Con Descuentos
		caja.iniciarVenta();
		caja.addProducto(productos.getProductoById(4), 6);
		caja.addProducto(productos.getProductoById(12), 45);
		caja.addProducto(productos.getProductoById(16), 4);
		caja.addProducto(productos.getProductoById(28), 1);
		caja.addProducto(productos.getProductoById(4), 8);
		caja.addProducto(productos.getProductoById(4), 3);
		caja.confirmarVenta("Visa");

		// Otra Venta con mismo medio de pago
		caja.iniciarVenta();
		caja.addProducto(productos.getProductoById(5), 2);
		caja.addProducto(productos.getProductoById(20), 10);
		caja.addProducto(productos.getProductoById(7), 5);
		caja.confirmarVenta("Visa");

		// Otra Venta descuento Jubilado
		caja.iniciarVenta();
		caja.addProducto(productos.getProductoById(5), 2);
		caja.addProducto(productos.getProductoById(20), 10);
		caja.addProducto(productos.getProductoById(7), 5);
		caja.setEsJubilado();
		caja.confirmarVenta("Visa");

		// Otra Venta descuento Jubilado
		caja.iniciarVenta();
		caja.addProducto(productos.getProductoById(32), 2);
		caja.canjearCupon(1000);
		caja.confirmarVenta("Efectivo");

		// Imprime totales
		System.out.println();
		repositorioVentas.imprimirTotalCaja(caja.getCajaNro());
		System.out.println();
		repositorioVentas.ImprimirTotalDescuentosCaja(caja.getCajaNro());
		System.out.println();
		repositorioVentas.imprimirTotalPorMediosDePagoDeLaCaja(caja
				.getCajaNro());
		System.out.println();
		repositorioVentas.imprimirProductosRanking();

		caja.cerrarCaja();

	}
}
