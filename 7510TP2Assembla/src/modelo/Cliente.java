package modelo;

public class Cliente {
	private final String dni;
	private int puntos;

	public Cliente(String dni, int puntos) {
		this.dni = dni;
		this.puntos = puntos;
	}

	public int getPuntos() {
		return puntos;
	}

	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}

	public String getDni() {
		return dni;
	}
}
