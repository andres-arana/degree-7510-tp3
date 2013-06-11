package modelo;

import java.util.Calendar;
import java.util.List;
/**
 * Interfaz para definir metodos de una venta. 
 * La venta est� compuesta por productos y las ofertas aplicadas
 * a dichos productos o a la venta en general
 * 
 * @author Grupo 15
 *
 */
public interface IVenta {
	void addProducto(Producto producto, int cantidad);

	/**
	 * Agrega una nueva oferta a la venta
	 * @param detalle: descripcion de la oferta aplicada
	 * @param cantidad: cantidad de productos a los que aplica la oferta
	 * @param montoUnitario: cantidad a descontar a los productos 
	 */
	void addOferta(String detalle, int cantidad, double montoUnitario);

	/**
	 * Calcula el total de la venta. Incluye los descuentos realizados
	 * @return importe total
	 */
	double getTotal();

	double getTotalDescuentos();

	double getTotalSinDescuentos();

	String getMedioDePago();

	Calendar getFecha();

	/**
	 * Al cobrar si corresponde se aplican los descuentas que hagan
	 * al total de la venta
	 * 
	 */
	void cobrar();

	int getCajaNro();

	String getUltimaLinea();

	List<LineItemProducto> getProductos();

	void aplicarDescuentoAlTotal(double descuento, String detalleOferta);

	void setFormaDePago(String formaDePago);

	/**
	 * Al aplicar una oferta a un producto se debe marcar el mismo
	 * para indicar que no se puede agregar una nueva oferta por producto 
	 * 
	 * @param query: objeto que define con que criterio se buscan los productos para marcarlos
	 * @param cantidad: cantidad de productos a marcar
	 */
	void marcarProductosOfertados(IQuery query, int cantidad);

	/**
	 * Marca todos los productos como que tienen una oferta aplicada, salvo
	 * que el producto est� dentro de la lista de excepciones a los cuales
	 * la oferta no aplica
	 * @param query: objeto que define con que criterio se buscan los productos para marcarlos
	 * @param excepciones: lista de productos para los cuales la oferta no aplica
	 */
	void marcarTodosLosProductosOfertados(IQuery query,
			List<Producto> excepciones);

	/**
	 * Indica si el comprador es jubilado. Esta informaci�n se usa para 
	 * aplicar una oferta a jubilados, si es que existiera. 
	 * @param jubilado: true si es jubilado, false caso contrario
	 */
	void setEsJubilado(boolean jubilado);

	boolean esJubilado();

	int getTotalPuntos();


}
