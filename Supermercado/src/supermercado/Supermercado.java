package supermercado;

import java.util.ArrayList;
import java.util.List;

public class Supermercado {

	private List<Cliente> clientes;
	private List<ProductoCantidad>inventario;

	public Supermercado() {
		this.clientes = new ArrayList<>();
		this.inventario = new ArrayList<>();
	}
	

	public Boolean agregarCliente(Cliente cliente) {
		return clientes.add(cliente);
	}

	public List<Cliente> getClientes() {
		return clientes;
	}
	
	public List<ProductoCantidad> getInventario() {
		return inventario;
	}

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
		
		ProductoCantidad productoCantidad = new ProductoCantidad(producto,cantidad);
		return inventario.add(productoCantidad);
		
	}


	public boolean eliminarProductoPorId(Integer idProducto) {
		
		for (ProductoCantidad productoCantidad : inventario) {
			if (productoCantidad.getProducto().getIdProducto().equals(idProducto)) {
				inventario.remove(productoCantidad);
				return true;
			}
		}
		return false;
		
	}
}
