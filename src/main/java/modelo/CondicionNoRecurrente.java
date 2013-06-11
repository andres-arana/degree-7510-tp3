package modelo;

public abstract class CondicionNoRecurrente implements ICondicionOferta {

	@Override
	public boolean isRecurrente() {
		return false;
	}
}
