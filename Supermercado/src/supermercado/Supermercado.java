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
	public Boolean eliminarClientePorDni(Integer dni) throws ClienteNoEncontradoException {
		for (Cliente cliente : clientes) {
			if (cliente.getDni().equals(dni)) {
				clientes.remove(cliente);
				return true;
			}
		}
		throw new ClienteNoEncontradoException("Cliente no encontrado");

	}

	public Boolean agregarProductoAInventario(Producto producto, Integer cantidad) {//BUSCA SI EXISTE EL PRODUCTO EN EL INVENTARIO
																					//SI EXISTE LE AGREGA MAS CANTIDAD SINO LO CREA Y LO AGREGA
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
	public Boolean eliminarProductoPorId(Integer idProducto) throws ProductoNoEncontradoException {
		for (ProductoCantidad productoCantidad : inventario) {
			if (productoCantidad.getProducto().getIdProducto().equals(idProducto)) {
				inventario.remove(productoCantidad);
				return true;
			}
		}
		throw new ProductoNoEncontradoException("Producto no encontrado");
	}

	@Override
	public boolean agregarProductoAlCarrito(Integer idProducto, Integer dniCliente, Integer cantidadDelProducto) throws NullPointerException, ProductoNoEncontradoException {
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

	public ProductoCantidad buscarProductoCantidadPorId(Integer idProducto) throws ProductoNoEncontradoException {
		for (ProductoCantidad productoCantidad : inventario) {
			if (productoCantidad.getProducto().getIdProducto().equals(idProducto)) {
				return productoCantidad;
			}
		}
		throw new ProductoNoEncontradoException("ProductoNoEncontrado");
	}

	public Cliente buscarClientePorDni(Integer dniCliente) throws NullPointerException{
		for (Cliente cliente : clientes) {
			if (cliente.getDni().equals(dniCliente)) {
				return cliente;
			}
		}
		throw new NullPointerException();
	}

	@Override
	public boolean eliminarProductoDelCarrito(Integer idProducto, Integer dniCliente) throws NullPointerException {
		Cliente cliente = buscarClientePorDni(dniCliente);

		if (cliente != null) {
			cliente.getCarrito().eliminarProducto(idProducto);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void actualizarPrecio(Double nuevoPrecio, int idProducto) throws ProductoNoEncontradoException {
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
		if (idCompra <= 0 || idCompra > compras.size()) {
			return null;
		}
		return compras.get(idCompra-1);
	}

	public void iniciarCompra(Cliente cliente) {
		Integer idCompra = generadorIdCompra++;
		Compra nuevaCompra = new Compra(idCompra, cliente, LocalDateTime.now(), MetodoPago.EFECTIVO,
				EstadoCompra.PENDIENTE);
		compras.add(nuevaCompra);
	}

	public boolean realizarVenta(Integer dniCliente) throws NullPointerException, ProductoNoEncontradoException {
	    Cliente cliente = buscarClientePorDni(dniCliente);
	    if (cliente == null) {
	        return false; 
	    }
	    Carrito carrito = cliente.getCarrito();
	    List<ProductoCantidad> productosCarrito = carrito.getProductos();
	    
	    // verificar si el carrito está vacio
	    if (productosCarrito.isEmpty()) {
	        return false; // no se puede realizar la venta con un carrito vacio
	    }
	    
	    
	    for (ProductoCantidad productoCarrito : productosCarrito) {
	        Producto producto = productoCarrito.getProducto();
	        ProductoCantidad productoInventario = buscarProductoCantidadPorId(producto.getIdProducto());
	        
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
	
	public List<Producto> buscarProductosPorNombre(String nombre) {
        List<Producto> productosEncontrados = new ArrayList<>();
        for (ProductoCantidad productoCantidad : inventario) {
            if (productoCantidad.getProducto().getNombre().equals(nombre)) {
                productosEncontrados.add(productoCantidad.getProducto());
            }
        }
        return productosEncontrados;
    }
}
