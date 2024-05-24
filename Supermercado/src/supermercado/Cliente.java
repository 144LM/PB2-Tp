package supermercado;

public class Cliente {
	private String nombre;
	private Integer dni;
	private Carrito carrito;

	public Cliente(String nombre, Integer dni, Carrito carrito) {
		this.nombre = nombre;
		this.dni = dni;
		this.carrito = carrito;
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

	public void agregarProductoAlCarrito(Producto producto) {
		this.carrito.agregarProducto(producto);
	}
	public void eliminarProductoDelCarrito(Producto producto) {
		this.carrito.eliminarProducto(producto);
	}
	public Carrito getCarrito() {
		return carrito;
	}
}
