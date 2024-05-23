package supermercado;

import static org.junit.Assert.*;

import org.junit.Test;

public class SupermercadoTest {
	@Test
	public void queSePuedaAgregarCliente() {
		// Implementación del test
		Supermercado supermercado = new Supermercado();

		Cliente cliente = new Cliente("Juan",234234);

		supermercado.agregarCliente(cliente);

		assertEquals(1, supermercado.getClientes().size());
	}


	@Test
	public void queSePuedaEliminarCliente() {

		Supermercado supermercado = new Supermercado();

		Cliente cliente = new Cliente("Juan",234234);
		
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
	public void queSePuedaAgregarProductoAlCarrito() {
		
	}

	@Test
	public void queSePuedaEliminarProductoDelCarrito() {
	
	}

	@Test
	public void queSePuedaCalcularElTotalDelCarrito() {
		
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
