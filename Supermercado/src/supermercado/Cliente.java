package supermercado;

public class Cliente extends Persona {
	private double saldo;
	private Carrito carrito;

	public Cliente(String nombre, int dni, double saldo) {
		super(nombre, dni);
		this.saldo = saldo;
		this.carrito = new Carrito();
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public Carrito getCarrito() {
		return carrito;
	}

	public void setCarrito(Carrito carrito) {
		this.carrito = carrito;
	}
}
