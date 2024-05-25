package supermercado;

import java.util.List;

public interface ISupermercado {
	
	Boolean agregarCliente(Cliente cliente);
	List<Cliente> getClientes();
	List<ProductoCantidad> getInventario();
	Boolean eliminarClientePorDni(Integer dni);
	Boolean agregarProductoAInventario(Producto producto, Integer cantidad);
	Boolean eliminarProductoPorId(Integer idProducto);
	boolean agregarProductoAlCarrito(Integer idProducto, Integer dniCliente,Integer cantidadDelProducto);
	boolean eliminarProductoDelCarrito(Integer idProducto, Integer dniCliente);
	void actualizarPrecio(Double nuevoPrecio, int idProducto);
}
