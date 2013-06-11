package modelo;

public abstract class CondicionRecurrente implements ICondicionOferta {

	@Override
	public boolean isRecurrente() {
		return true;
	}

}
