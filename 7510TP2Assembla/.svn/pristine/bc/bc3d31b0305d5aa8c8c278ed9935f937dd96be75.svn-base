package modelo;

public class CajaEfectuandoVenta implements IEstadoCaja {

	private static CajaEfectuandoVenta instance = null;

	public static CajaEfectuandoVenta getInstance() {
		if (instance == null)
			instance = new CajaEfectuandoVenta();
		return instance;
	}

	private CajaEfectuandoVenta() {

	}

	@Override
	public void puedeAbrirCaja() {
		throw new OperacionCajaInvalidaException();

	}

	@Override
	public void puedeAgregarProducto() {

	}

	@Override
	public void puedeIniciarVenta() {
		throw new OperacionCajaInvalidaException();

	}

	@Override
	public void puedeConfirmarVenta() {

	}

	@Override
	public void puedeCancelarventa() {

	}

	@Override
	public void puedeCerrarCaja() {
		throw new OperacionCajaInvalidaException();
	}

}
