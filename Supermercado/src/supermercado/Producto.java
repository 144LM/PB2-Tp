package supermercado;

import java.util.Objects;

public abstract class Producto {

	private Integer idProducto;
	private String nombre;
	private Double precio;
	

	public Producto(Integer idProducto, String nombre, Double precio) {
		super();
		this.idProducto = idProducto;
		this.nombre = nombre;
		this.precio = precio;
		
	}

	public Integer getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(Integer idProducto) {
		this.idProducto = idProducto;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Producto producto = (Producto) o;
		return idProducto.equals(producto.idProducto);
	}

	@Override
	public int hashCode() {
		return Objects.hash(idProducto);
	}


}
