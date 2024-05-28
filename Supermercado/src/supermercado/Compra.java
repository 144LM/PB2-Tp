package supermercado;

import java.time.LocalDateTime;

public class Compra {
	private Integer idCompra;
	private Cliente cliente;
	private LocalDateTime now;
	private MetodoPago metodoPago;
	private EstadoCompra estadoCompra;

	public Compra(Integer idCompra, Cliente cliente, LocalDateTime now, MetodoPago metodoPago, EstadoCompra estadoCompra) {
		this.idCompra = idCompra;
		this.cliente = cliente;
		this.now = now;
		this.metodoPago = metodoPago;
		this.estadoCompra = estadoCompra;

	}

	public Integer getIdCompra() {
		return idCompra;
	}

	public void setIdCompra(Integer idCompra) {
		this.idCompra = idCompra;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public LocalDateTime getNow() {
		return now;
	}

	public void setNow(LocalDateTime now) {
		this.now = now;
	}

	public MetodoPago getMetodoPago() {
		return metodoPago;
	}

	public void setMetodoPago(MetodoPago metodoPago) {
		this.metodoPago = metodoPago;
	}

	public EstadoCompra getEstadoCompra() {
		return estadoCompra;
	}

	public void setEstadoCompra(EstadoCompra estadoCompra) {
		this.estadoCompra = estadoCompra;
	}

}
