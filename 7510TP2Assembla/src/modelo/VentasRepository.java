package modelo;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * Clase para almacenar todas las ventas de una sucursal. 
 * Permite calcular totales de caja, de descuentos, de medios
 * de pago y ranking de productos más vendidos. 
 * 
 * @author Grupo 15
 *
 */
public class VentasRepository {
	private ArrayList<IVenta> ventas;

	public VentasRepository() {
		ventas = new ArrayList<IVenta>();
	}

	public void add(IVenta venta) {
		ventas.add(venta);
	}

	public ArrayList<IVenta> getVentasCaja(int nroCaja) {

		ArrayList<IVenta> ventasDeLaCaja = new ArrayList<IVenta>();

		for (Iterator<IVenta> it = ventas.iterator(); it.hasNext();) {
			IVenta venta = it.next();
			if (venta.getCajaNro() == nroCaja) {
				ventasDeLaCaja.add(venta);
			}
		}

		return ventasDeLaCaja;
	}

	public double calcularTotalCaja(int nroCaja) {
		double total = 0;
		ArrayList<IVenta> ventasDeLaCaja = getVentasCaja(nroCaja);

		for (Iterator<IVenta> it = ventasDeLaCaja.iterator(); it.hasNext();) {

			total += it.next().getTotal();

		}
		return total;
	}

	public double calcularTotalDescuentosCaja(int nroCaja) {
		double total = 0;
		ArrayList<IVenta> ventasDeLaCaja = getVentasCaja(nroCaja);

		for (Iterator<IVenta> it = ventasDeLaCaja.iterator(); it.hasNext();) {

			total += it.next().getTotalDescuentos();

		}
		return total;
	}

	public double calcularTotalMedioDePago(int nroCaja, String medioDePago) {
		double total = 0;
		ArrayList<IVenta> ventasDeLaCaja = getVentasCaja(nroCaja);

		for (Iterator<IVenta> it = ventasDeLaCaja.iterator(); it.hasNext();) {
			IVenta venta = it.next();
			if (venta.getMedioDePago() == medioDePago) {
				total += venta.getTotal();
			}
		}
		return total;

	}

	public ArrayList<String> getMediosDePago() {
		ArrayList<String> mediosDePago = new ArrayList<String>();

		for (Iterator<IVenta> it = ventas.iterator(); it.hasNext();) {
			IVenta venta = it.next();
			if (!mediosDePago.contains(venta.getMedioDePago())) {
				mediosDePago.add(venta.getMedioDePago());
			}
		}

		return mediosDePago;

	}

	public Map<String, Integer> imprimirProductosRanking() {

		Map<String, Integer> mapa = new HashMap<String, Integer>();

		for (Iterator<IVenta> it = ventas.iterator(); it.hasNext();) {
			IVenta venta = it.next();
			List<LineItemProducto> listaLineItem = venta.getProductos();

			for (Iterator<LineItemProducto> itP = listaLineItem.iterator(); itP
					.hasNext();) {
				LineItemProducto line = itP.next();

				Integer cantidad = mapa
						.get(line.getProducto().getDescripcion());
				if (cantidad == null)
					cantidad = line.getCantidad();
				else {
					cantidad += line.getCantidad();
				}

				mapa.put(line.getProducto().getDescripcion(), cantidad);

			}

		}

		System.out.println("Ranking de productos");

		ValueComparator bvc = new ValueComparator(mapa);
		TreeMap<String, Integer> sorted_map = new TreeMap<String, Integer>(bvc);

		sorted_map.putAll(mapa);

		Set<String> claves = sorted_map.keySet();

		Collection<Integer> valores = sorted_map.values();
		Iterator<Integer> itValores = valores.iterator();

		for (Iterator<String> itClaves = claves.iterator(); itClaves.hasNext();) {

			System.out.println(itValores.next() + ": " + itClaves.next());
		}

		return mapa;
	}

	public void imprimirTotalPorMediosDePagoDeLaCaja(int cajaNro) {
		DecimalFormat df = new DecimalFormat("0.00");
		System.out.println("Total de la caja nro." + cajaNro
				+ " discriminado por medio de pago");

		ArrayList<String> mediosDePago = getMediosDePago();
		for (Iterator<String> it = mediosDePago.iterator(); it.hasNext();) {
			String medioPago = it.next();
			String total = df.format(calcularTotalMedioDePago(cajaNro,
					medioPago));
			System.out.println("\t" + medioPago + ": " + total);
		}

	}

	public void imprimirTotalCaja(int cajaNro) {
		DecimalFormat df = new DecimalFormat("0.00");
		System.out.println("Total en la caja: $"
				+ df.format(calcularTotalCaja(cajaNro)));
	}

	public void ImprimirTotalDescuentosCaja(int cajaNro) {
		DecimalFormat df = new DecimalFormat("0.00");
		System.out.println("Total de los descuentos aplicados: $"
				+ df.format(calcularTotalDescuentosCaja(cajaNro)));
	}

}

class ValueComparator implements Comparator<String> {

	Map<String, Integer> base;

	public ValueComparator(Map<String, Integer> base) {
		this.base = base;
	}

	// Note: this comparator imposes orderings that are inconsistent with
	// equals.
	public int compare(String a, String b) {
		if (base.get(a) >= base.get(b)) {
			return -1;
		} else {
			return 1;
		} // returning 0 would merge keys
	}
}