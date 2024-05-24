package supermercado;

import static org.junit.Assert.*;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Optional;

public class SupermercadoTest {
	@Test
	public void queSePuedaAgregarCliente() {
		// Implementación del test
		Supermercado supermercado = new Supermercado();

		ArrayList<Producto> productos = new ArrayList<>();
		Carrito carrito = new Carrito(productos);

		Cliente cliente = new Cliente("Juan",234234, carrito);

		supermercado.agregarCliente(cliente);

		assertEquals(1, supermercado.getClientes().size());
	}


	@Test
	public void queSePuedaEliminarCliente() {

		Supermercado supermercado = new Supermercado();

		ArrayList<Producto> productos = new ArrayList<>();
		Carrito carrito = new Carrito(productos);

		Cliente cliente = new Cliente("Juan",234234, carrito);
		
		supermercado.agregarCliente(cliente);

		assertEquals(1, supermercado.getClientes().size());

		supermercado.eliminarClientePorDni(234234);

		assertEquals(0, supermercado.getClientes().size());

	}

	@Test
	public void queSePuedaAgregarProductoAlInventario() {
		Supermercado supermercado = new Supermercado();
		
		Integer idProducto = 3421;
		String nombre = "Fideo";
		Double precio = 920D;
		Integer idCategoria = 4343;
		String nombreCategoria = "Alimentos";
		String descripcion = "Productos comestibles y consumibles";

		Categoria categoria = new Categoria(idCategoria, nombreCategoria, descripcion);

		Producto producto = new Producto(idProducto, nombre, precio, categoria);
		Integer cantidad = 5;

		Boolean resultado = supermercado.agregarProductoAInventario(producto, cantidad);

		assertTrue(resultado);

	}

	@Test
	public void queSePuedaEliminarProductoDelInventario() {

		Supermercado supermercado = new Supermercado();
		Integer idProducto = 3421;
		String nombre = "Fideo";
		Double precio = 920D;

		Integer idCategoria = 4343;
		String nombreCategoria = "Alimentos";
		String descripcion = "Productos comestibles y consumibles";
		Categoria categoria = new Categoria(idCategoria, nombreCategoria, descripcion);
		Producto producto = new Producto(idProducto, nombre, precio, categoria);

		Integer cantidad = 5;
		Boolean resultado = supermercado.agregarProductoAInventario(producto, cantidad);

		assertTrue(resultado);
		assertEquals(1, supermercado.getInventario().size());

		supermercado.eliminarProductoPorId(idProducto);

		assertEquals(0, supermercado.getInventario().size());

	}
	@Test
	public void queSePuedaActualizarInventario() {

	}

	@Test
	public void queSePuedaActualizarPrecioProducto() {
		Supermercado supermercado = new Supermercado();
		Categoria categoria = new Categoria(1, "Bebida", "descripcion");
		Producto producto = new Producto(1 ,"Coca-cola", 0.50, categoria);
		Double nuevoPrecio = 200.0;

		supermercado.agregarProductoAInventario(producto, 2);
		supermercado.actualizarPrecio(nuevoPrecio, 1);

		assertEquals(200.0, producto.getPrecio(), 0.01);

	}

	@Test
	public void queSePuedaAgregarProductoAlCarrito() {
		Supermercado supermercado = new Supermercado();
		ArrayList<Producto> productos = new ArrayList<>();
		Carrito carrito = new Carrito(productos);
		Categoria categoria = new Categoria(1, "Bebida", "descripcion");
		Producto producto = new Producto(1 ,"Coca-cola", 0.50, categoria);
		Cliente cliente = new Cliente("juan", 12333, carrito);

		supermercado.agregarCliente(cliente);
		supermercado.agregarProductoAInventario(producto, 2);
		supermercado.agregarProductoAlCarrito(1, 12333);


		assertEquals(1, cliente.getCarrito().getProductos().size());
		assertEquals("Coca-cola", cliente.getCarrito().getProductos().get(0).getNombre());
		assertEquals(0.50, cliente.getCarrito().getProductos().get(0).getPrecio(), 0.001);
	}

	@Test
	public void queSePuedaEliminarProductoDelCarrito() {
		Supermercado supermercado = new Supermercado();
		ArrayList<Producto> productos = new ArrayList<>();
		Carrito carrito = new Carrito(productos);
		Categoria categoria = new Categoria(1, "Bebida", "descripcion");
		Producto producto = new Producto(1 ,"Coca-cola", 20.50, categoria);
		Producto producto2 = new Producto(2 ,"Pepsi", 30.50, categoria);
		Producto producto3 = new Producto(3 ,"Quilmes", 100.50, categoria);
		Cliente cliente = new Cliente("juan", 12333, carrito);

		supermercado.agregarCliente(cliente);

		supermercado.agregarProductoAInventario(producto, 2);
		supermercado.agregarProductoAInventario(producto2, 3);
		supermercado.agregarProductoAInventario(producto3, 5);

		supermercado.agregarProductoAlCarrito(1, 12333);
		supermercado.agregarProductoAlCarrito(2, 12333);
		supermercado.agregarProductoAlCarrito(3, 12333);

		supermercado.eliminarProductoDelCarrito(2, 1233);

		assertEquals(2, cliente.getCarrito().getProductos().size()); //solucionar
	}

	@Test
	public void queSePuedaCalcularElTotalDelCarrito() {
		Supermercado supermercado = new Supermercado();
		ArrayList<Producto> productos = new ArrayList<>();
		Carrito carrito = new Carrito(productos);
		Categoria categoria = new Categoria(1, "Bebida", "descripcion");
		Producto producto = new Producto(1 ,"Coca-cola", 20.50, categoria);
		Producto producto2 = new Producto(2 ,"Pepsi", 30.50, categoria);
		Producto producto3 = new Producto(3 ,"Quilmes", 100.50, categoria);
		Producto producto4 = new Producto(4,"Manaos",30.0,categoria);
		Cliente cliente = new Cliente("juan", 12333, carrito);
		
		supermercado.agregarCliente(cliente);

		supermercado.agregarProductoAInventario(producto, 2);
		supermercado.agregarProductoAInventario(producto2, 3);
		supermercado.agregarProductoAInventario(producto3, 5);
		supermercado.agregarProductoAInventario(producto4, 5);

		supermercado.agregarProductoAlCarrito(1, 12333);
		supermercado.agregarProductoAlCarrito(2, 12333);
		supermercado.agregarProductoAlCarrito(3, 12333);
		supermercado.agregarProductoAlCarrito(4, 12333);
		
		assertEquals((Double)181.5,cliente.getCarrito().getTotal());
	}

	@Test
	public void queSePuedaSeleccionarFormaDePago() {
		
	}

	@Test
	public void queSePuedaRealizarVentaYActualizarInventario() {

	}

	@Test
	public void queSePuedaActualizarSaldoDelClienteDespuesDeLaVenta() {
		
	}

	@Test
	public void queSePuedaActualizarBalanceDeLaTiendaDespuesDeLaVenta() {
		
	}

	@Test
	public void queSePuedaRegistrarVentaEnElSistema() {
		
	}

}
