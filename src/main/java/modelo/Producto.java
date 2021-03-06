package modelo;

public class Producto {
	private String descripcion;
	private double precio;
	private int id;
	private String categoria;
	private String marca;

	public Producto(String descripcion, double precio, int id,
			String categoria, String marca) {
		this.descripcion = descripcion;
		this.precio = precio;
		this.id = id;
		this.categoria = categoria;
		this.marca = marca;
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
