package modelo;

public class CajaCerrada implements IEstadoCaja {
	private static CajaCerrada instance = null;

	public static CajaCerrada getInstance() {
		if (instance == null)
			instance = new CajaCerrada();
		return instance;
	}

	private CajaCerrada() {

	}

	@Override
	public void puedeAbrirCaja() {

	}

	@Override
	public void puedeAgregarProducto() {
		throw new OperacionCajaInvalidaException();

	}

	@Override
	public void puedeIniciarVenta() {
		throw new OperacionCajaInvalidaException();

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
		throw new OperacionCajaInvalidaException();
	}

}
