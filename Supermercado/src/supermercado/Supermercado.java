package supermercado;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Supermercado implements ISupermercado {
	private List<Cliente> clientes;
	private List<ProductoCantidad> inventario;
	private List<Compra> compras;
	private Integer generadorIdCompra;

	public Supermercado() {
		this.clientes = new ArrayList<>();
		this.inventario = new ArrayList<>();
		this.compras = new ArrayList<>();
		this.generadorIdCompra = 0;
	}

	@Override
	public Boolean agregarCliente(Cliente cliente) {
		return clientes.add(cliente);
	}

	@Override
	public List<Cliente> getClientes() {
		return clientes;
	}

	@Override
	public List<ProductoCantidad> getInventario() {
		return inventario;
	}

	@Override
	public Boolean eliminarClientePorDni(Integer dni) {
		for (Cliente cliente : clientes) {
			if (cliente.getDni().equals(dni)) {
				clientes.remove(cliente);
				return true;
			}
		}
		return false;

	}

	public Boolean agregarProductoAInventario(Producto producto, Integer cantidad) {
		for (ProductoCantidad productoCantidad : inventario) {
			if (productoCantidad.getProducto().getIdProducto().equals(producto.getIdProducto())) {
				productoCantidad.setCantidad(productoCantidad.getCantidad() + cantidad);
				return true;
			}
		}
		ProductoCantidad nuevoProductoCantidad = new ProductoCantidad(producto, cantidad);
		return inventario.add(nuevoProductoCantidad);
	}

	@Override
	public Boolean eliminarProductoPorId(Integer idProducto) {

		for (ProductoCantidad productoCantidad : inventario) {
			if (productoCantidad.getProducto().getIdProducto().equals(idProducto)) {
				inventario.remove(productoCantidad);
				return true;
			}
		}
		return false;

	}

	@Override
	public boolean agregarProductoAlCarrito(Integer idProducto, Integer dniCliente, Integer cantidadDelProducto) {
		Cliente cliente = buscarClientePorDni(dniCliente);
		ProductoCantidad producto = buscarProductoCantidadPorId(idProducto);

		if (producto.getCantidad() < cantidadDelProducto) {
			return false;
		}

		if (cliente != null && producto != null) {
			ProductoCantidad productoCantidadEnElCarrito = new ProductoCantidad(producto.getProducto(),
					cantidadDelProducto);
			cliente.getCarrito().agregarProducto(productoCantidadEnElCarrito);
			return true;
		} else {
			return false;
		}
	}

	public ProductoCantidad buscarProductoCantidadPorId(Integer idProducto) {
		for (ProductoCantidad productoCantidad : inventario) {

			if (productoCantidad.getProducto().getIdProducto().equals(idProducto)) {
				return productoCantidad;
			}
		}
		return null;
	}

	private Cliente buscarClientePorDni(Integer dniCliente) {
		for (Cliente cliente : clientes) {
			if (cliente.getDni().equals(dniCliente)) {
				return cliente;
			}
		}
		return null;
	}

	@Override
	public boolean eliminarProductoDelCarrito(Integer idProducto, Integer dniCliente) {
		Cliente cliente = buscarClientePorDni(dniCliente);

		if (cliente != null) {
			cliente.getCarrito().eliminarProducto(idProducto);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void actualizarPrecio(Double nuevoPrecio, int idProducto) {
		ProductoCantidad productoCantidad = buscarProductoCantidadPorId(idProducto);
		productoCantidad.getProducto().setPrecio(nuevoPrecio);
	}

	public void seleccionarMetodoDePago(MetodoPago metodoPago, Integer idCompra) {
		Compra compra = buscarCompraPorId(idCompra);
		if (compra != null) {
			compra.setMetodoPago(metodoPago);
		}
	}

	public Compra buscarCompraPorId(Integer idCompra) {
		return compras.get(idCompra - 1);
	}

	public void iniciarCompra(Cliente cliente) {
		Integer idCompra = generadorIdCompra++;
		Compra nuevaCompra = new Compra(idCompra, cliente, LocalDateTime.now(), MetodoPago.EFECTIVO,
				EstadoCompra.PENDIENTE);
		compras.add(nuevaCompra);
	}

	public boolean realizarVenta(Integer dniCliente) {
		Cliente cliente = buscarClientePorDni(dniCliente);
		if (cliente == null) {
			return false;
		}
		Carrito carrito = cliente.getCarrito();
		List<ProductoCantidad> productosCarrito = carrito.getProductos();

		for (ProductoCantidad productoCarrito : productosCarrito) {
			Producto producto = productoCarrito.getProducto();
			ProductoCantidad productoInventario = inventario.get(producto.getIdProducto() - 1);

			if (productoInventario == null || productoInventario.getCantidad() < productoCarrito.getCantidad()
					|| cliente.getSaldo() < carrito.getTotal()) {
				return false; // sin stock suficiente o sin dinero suficiente
			}
			productoInventario.setCantidad(productoInventario.getCantidad() - productoCarrito.getCantidad());
		}
		Double restoDeSaldo = cliente.getSaldo() - carrito.getTotal();
		cliente.setSaldo(restoDeSaldo);
		vaciarCarrito(carrito);
		return true;
	}

	public void vaciarCarrito(Carrito carrito) {
		carrito.vaciarContenido();
	}

	public List<ProductoCantidad> buscarProductosPorCategoria(Integer idCategoria) {
		List<ProductoCantidad> productosEncontrados = new ArrayList<>();
		for (ProductoCantidad productoCantidad : inventario) {
			if (productoCantidad.getProducto().getCategoria().getIdCategoria().equals(idCategoria)) {
				productosEncontrados.add(productoCantidad);
			}
		}
		return productosEncontrados;
	}
}
