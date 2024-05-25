package supermercado;

public class Cliente {
	private String nombre;
	private Integer dni;
	private Carrito carrito;

	public Cliente(String nombre, Integer dni) {
		this.nombre = nombre;
		this.dni = dni;
		this.carrito = new Carrito();
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getDni() {
		return dni;
	}

	public void setDni(Integer dni) {
		this.dni = dni;
	}

	public Carrito getCarrito() {
		return carrito;
	}
}
