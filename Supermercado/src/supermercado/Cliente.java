package supermercado;

public class Cliente {
	private String nombre;
	private Integer dni;
	private Double saldo;
	private Carrito carrito;

	public Cliente(String nombre, Integer dni,Double saldo) {
		this.nombre = nombre;
		this.dni = dni;
		this.saldo = saldo;
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
	
	public Double getSaldo() {
		return saldo;
	}

	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}

	public void setCarrito(Carrito carrito) {
		this.carrito = carrito;
	}

	public Carrito getCarrito() {
		return carrito;
	}
}
