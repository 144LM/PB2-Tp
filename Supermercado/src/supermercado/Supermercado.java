package supermercado;

import java.util.ArrayList;
import java.util.List;

public class Supermercado implements ISupermercado {

	private List<Cliente> clientes;
	private List<ProductoCantidad>inventario;

	public Supermercado() {
		this.clientes = new ArrayList<>();
		this.inventario = new ArrayList<>();
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

	@Override
	public Boolean agregarProductoAInventario(Producto producto, Integer cantidad) {
		
		ProductoCantidad productoCantidad = new ProductoCantidad(producto,cantidad);
		return inventario.add(productoCantidad);
		
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
	public boolean agregarProductoAlCarrito(Integer idProducto, Integer dniCliente) {
		Cliente cliente = buscarClientePorDni(dniCliente);
		Producto producto = buscarProductoPorId(idProducto);

		if (cliente != null && producto != null) {
			cliente.agregarProductoAlCarrito(producto);
			return true;
		} else {
			return false;
		}
	}

	private Producto buscarProductoPorId(Integer idProducto) {
		for (ProductoCantidad productoCantidad : inventario) {
			Producto producto = productoCantidad.getProducto();
			if (producto.getIdProducto().equals(idProducto)) {
				return producto;
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
		Producto productoAEliminar = buscarProductoPorId(idProducto);

		if (cliente != null && productoAEliminar != null) {
			cliente.eliminarProductoDelCarrito(productoAEliminar);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void actualizarPrecio(Double nuevoPrecio, int idProducto) {
		Producto producto= buscarProductoPorId(idProducto);
		producto.setPrecio(nuevoPrecio);
	}
}
