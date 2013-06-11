package modelo;

public class CajaAbierta implements IEstadoCaja {
	private static CajaAbierta instance = null;

	public static CajaAbierta getInstance() {
		if (instance == null)
			instance = new CajaAbierta();
		return instance;
	}

	private CajaAbierta() {

	}

	@Override
	public void puedeAbrirCaja() {
		throw new OperacionCajaInvalidaException();
	}

	@Override
	public void puedeAgregarProducto() {
		throw new OperacionCajaInvalidaException();
	}

	@Override
	public void puedeIniciarVenta() {

	}

	@Override
	public void puedeConfirmarVenta() {
		throw new OperacionCajaInvalidaException();
	}

	@Override
	public void puedeCancelarventa() {
		throw new OperacionCajaInvalidaException();

	}

	@Override
	public void puedeCerrarCaja() {

	}

}
