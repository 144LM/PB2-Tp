package supermercado;

import java.util.List;

public interface ISupermercado {
	Boolean agregarCliente(Cliente cliente);
	List<Cliente> getClientes();
	List<ProductoCantidad> getInventario();
	Boolean eliminarClientePorDni(Integer dni) throws ClienteNoEncontradoException;
	Boolean agregarProductoAInventario(Producto producto, Integer cantidad);
	Boolean eliminarProductoPorId(Integer idProducto) throws ProductoNoEncontradoException;
	boolean agregarProductoAlCarrito(Integer idProducto, Integer dniCliente, Integer cantidadDelProducto) throws Exception;
	boolean eliminarProductoDelCarrito(Integer idProducto, Integer dniCliente) throws Exception;
	void actualizarPrecio(Double nuevoPrecio, int idProducto) throws ProductoNoEncontradoException;
	void seleccionarMetodoDePago(MetodoPago metodoPago, Integer idCompra);
}
