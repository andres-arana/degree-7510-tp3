package modelo;

public class Cliente {
	private final String dni;
	private int puntos;

	public Cliente(String dni) {
		this.dni = dni;
		this.puntos = 0;
	}

	public int getPuntos() {
		return puntos;
	}
	
	public void asignarPuntos(int puntos) {
		this.puntos += puntos;
	}
	
	public void quitarPuntos(int puntos) {
		this.puntos -= puntos;
	}

	public String getDni() {
		return dni;
	}
}
