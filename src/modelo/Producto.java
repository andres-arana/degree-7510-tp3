package modelo;

public class Producto {
	private String descripcion;
	private double precio;
	private int id;
	private String categoria;
	private String marca;

	private float puntajeAsociado = 0.f;
	
	public Producto(String descripcion, double precio, int id,
			String categoria, String marca, float puntaje) {
		this.descripcion = descripcion;
		this.precio = precio;
		this.id = id;
		this.categoria = categoria;
		this.marca = marca;
		this.puntajeAsociado = puntaje;
	}
	
	public Producto(String descripcion, double precio, int id,
			String categoria, String marca) {
		this(descripcion, precio, id, categoria, marca,0);
	}

	public float getPuntajeAsociado() {
		return puntajeAsociado;
	}

	public void setPuntajeAsociado(float puntajeAsociado) {
		this.puntajeAsociado = puntajeAsociado;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

}
