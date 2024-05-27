package supermercado;

import static org.junit.Assert.*;

import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class SupermercadoTest {
	@Test
	public void queSePuedaAgregarCliente() {
	
		Supermercado supermercado = new Supermercado();

		Cliente cliente = new Cliente("Juan",234234,500.0);

		supermercado.agregarCliente(cliente);

		assertEquals(1, supermercado.getClientes().size());
	}


	@Test
	public void queSePuedaEliminarCliente() {

		Supermercado supermercado = new Supermercado();

		Cliente cliente = new Cliente("Juan",234234,500.0);
		
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

		Producto producto = new Bebida(idProducto, nombre, precio);
		Integer cantidad = 5;

		Boolean resultado = supermercado.agregarProductoAInventario(producto, cantidad);

		assertTrue(resultado);

	}

	@Test
	public void queSePuedaEliminarProductoDelInventario() {

		Supermercado supermercado = new Supermercado();
		Integer idProducto = 3421;
		String nombre = "CocaCola";
		Double precio = 920D;
		Producto producto = new Bebida(idProducto, nombre, precio);

		Integer cantidad = 5;
		Boolean resultado = supermercado.agregarProductoAInventario(producto, cantidad);

		assertTrue(resultado);
		assertEquals(1, supermercado.getInventario().size());

		supermercado.eliminarProductoPorId(idProducto);

		assertEquals(0, supermercado.getInventario().size());

	}

	@Test
	public void queSePuedaActualizarPrecioProducto() {
		Supermercado supermercado = new Supermercado();
		Producto producto = new Bebida(1 ,"Coca-cola", 0.50);
		Double nuevoPrecio = 200.0;

		supermercado.agregarProductoAInventario(producto, 2);
		supermercado.actualizarPrecio(nuevoPrecio, 1);

		assertEquals(200.0, producto.getPrecio(), 0.01);

	}

	@Test
	public void queSePuedaAgregarProductoAlCarrito() {
		Supermercado supermercado = new Supermercado();		
		Producto producto = new Bebida(1 ,"Coca-cola", 0.50);
		Cliente cliente = new Cliente("juan", 12333,500.0);

		supermercado.agregarCliente(cliente);
		supermercado.agregarProductoAInventario(producto, 2);
		Integer idProducto = 1;
		Integer dniCliente = 12333;
		Integer CantidadDelProducto = 2;
		supermercado.agregarProductoAlCarrito(idProducto, dniCliente,CantidadDelProducto);


		assertEquals(1, cliente.getCarrito().getProductos().size());
		assertEquals("Coca-cola", cliente.getCarrito().getProductos().get(0).getProducto().getNombre());
		assertEquals(0.50, cliente.getCarrito().getProductos().get(0).getProducto().getPrecio(), 0.001);
	}

	@Test
	public void queSePuedaEliminarProductoDelCarrito() {
		Supermercado supermercado = new Supermercado();
		Producto producto = new Bebida(1 ,"Coca-cola", 20.50);
		Producto producto2 = new Bebida(2 ,"Pepsi", 30.50);
		Producto producto3 = new Bebida(3 ,"Quilmes", 100.50);
		Cliente cliente = new Cliente("juan", 12333,500.0);

		supermercado.agregarCliente(cliente);

		supermercado.agregarProductoAInventario(producto, 2);
		supermercado.agregarProductoAInventario(producto2, 3);
		supermercado.agregarProductoAInventario(producto3, 5);

		supermercado.agregarProductoAlCarrito(1, 12333,1);
		supermercado.agregarProductoAlCarrito(2, 12333,2);
		supermercado.agregarProductoAlCarrito(3, 12333,2);

		supermercado.eliminarProductoDelCarrito(2, 12333);

		assertEquals(2, cliente.getCarrito().getProductos().size()); 
	}

	@Test
	public void queSePuedaCalcularElTotalDelCarrito() {
		Supermercado supermercado = new Supermercado();
		Producto producto = new Bebida(1 ,"Coca-cola", 20.50);
		Producto producto2 = new Bebida(2 ,"Pepsi", 30.50);
		Producto producto3 = new Bebida(3 ,"Quilmes", 100.50);
		Producto producto4 = new Bebida(4,"Manaos",30.0);
		Cliente cliente = new Cliente("juan", 12333,500.0);
		
		supermercado.agregarCliente(cliente);

		supermercado.agregarProductoAInventario(producto, 2);
		supermercado.agregarProductoAInventario(producto2, 3);
		supermercado.agregarProductoAInventario(producto3, 5);
		supermercado.agregarProductoAInventario(producto4, 5);

		Integer CantidadDeProducto1 = 1;
		Integer CantidadDeProducto2 = 1;
		Integer CantidadDeProducto3 = 2;
		Integer CantidadDeProducto4 = 3;
		
		supermercado.agregarProductoAlCarrito(1, 12333,CantidadDeProducto1);
		supermercado.agregarProductoAlCarrito(2, 12333,CantidadDeProducto2);
		supermercado.agregarProductoAlCarrito(3, 12333,CantidadDeProducto3);
		supermercado.agregarProductoAlCarrito(4, 12333,CantidadDeProducto4);
		
		Double resultadoEsperado = 342.0;
		assertEquals(resultadoEsperado,cliente.getCarrito().getTotal());
	}

	@Test
	public void queSePuedaSeleccionarFormaDePago() {
		Supermercado supermercado = new Supermercado();
		Cliente cliente = new Cliente("juan", 12333,500.0);
		MetodoPago metodoPago = MetodoPago.TARJETA_CREDITO;
		Integer idCompra = 1;

		supermercado.iniciarCompra(cliente);
		supermercado.seleccionarMetodoDePago(metodoPago, idCompra);

		Compra compra = supermercado.buscarCompraPorId(idCompra);

		assertEquals(MetodoPago.TARJETA_CREDITO, compra.getMetodoPago());
	}

	@Test
	public void queSePuedaRealizarVentaYActualizarInventario() {
		Supermercado supermercado = new Supermercado();
		Producto producto = new Bebida(1 ,"Coca-cola", 20.50);
		Producto producto2 = new Bebida(2,"Pepsi", 90.50);
		Cliente cliente = new Cliente("juan", 12333,500.0);
		MetodoPago metodoPago = MetodoPago.TARJETA_CREDITO;
		Integer idCompra = 1;

		supermercado.agregarCliente(cliente);
		supermercado.agregarProductoAInventario(producto, 2);
		supermercado.agregarProductoAInventario(producto2, 5);

		supermercado.agregarProductoAlCarrito(1, 12333,1);
		supermercado.agregarProductoAlCarrito(2, 12333,1);

		supermercado.iniciarCompra(cliente);
		supermercado.seleccionarMetodoDePago(metodoPago, idCompra);

		Boolean compraExitosa = supermercado.realizarVenta(cliente.getDni());
		assertTrue(compraExitosa);

		ProductoCantidad productoCantidad1 = supermercado.buscarProductoCantidadPorId(1);
		ProductoCantidad productoCantidad2 = supermercado.buscarProductoCantidadPorId(2);

		Integer cantidadEsperada1 = 1;
		Integer cantiadaEsperada2 = 4;

		assertEquals(cantidadEsperada1, productoCantidad1.getCantidad());
		assertEquals(cantiadaEsperada2, productoCantidad2.getCantidad());


		assertTrue(cliente.getCarrito().getProductos().isEmpty());
	}

	@Test
	public void queSePuedaObtenerUnaListaConTodasLasBebidasDelCarrito() {
		Supermercado supermercado = new Supermercado();
		Producto producto = new Bebida(1 ,"Coca-cola", 20.50);
		Producto producto2 = new Bebida(2 ,"Pepsi", 30.50);
		Producto producto3 = new Bebida(3 ,"Quilmes", 100.50);
		Producto producto4 = new Bebida(4,"Manaos",30.0);
		Producto producto5 = new Galletita(5,"Oreo",50.0);
		Cliente cliente = new Cliente("juan", 12333,500.0);
		
		supermercado.agregarCliente(cliente);

		supermercado.agregarProductoAInventario(producto, 2);
		supermercado.agregarProductoAInventario(producto2, 3);
		supermercado.agregarProductoAInventario(producto3, 5);
		supermercado.agregarProductoAInventario(producto4, 5);
		supermercado.agregarProductoAInventario(producto5, 10);

		Integer CantidadDeProducto1 = 1;
		Integer CantidadDeProducto2 = 1;
		Integer CantidadDeProducto3 = 2;
		Integer CantidadDeProducto4 = 3;
		Integer CantidadDeProducto5 = 5;
		
		supermercado.agregarProductoAlCarrito(1, 12333,CantidadDeProducto1);
		supermercado.agregarProductoAlCarrito(2, 12333,CantidadDeProducto2);
		supermercado.agregarProductoAlCarrito(3, 12333,CantidadDeProducto3);
		supermercado.agregarProductoAlCarrito(4, 12333,CantidadDeProducto4);
		supermercado.agregarProductoAlCarrito(5, 12333,CantidadDeProducto5);
		
		List<ProductoCantidad> bebidasDelCarrito = cliente.getCarrito().obtenerBebidas();
		
		assertEquals(4,bebidasDelCarrito.size());
		
		
	}
	
	
	@Test
	public void queNoSePuedaRealizarLaVentaSiElClienteNoTieneDineroSuficiente() {
		Supermercado supermercado = new Supermercado();
		Producto producto = new Bebida(1 ,"Coca-cola", 20.50);
		Producto producto2 = new Bebida(2,"Pepsi", 90.50);
		Cliente cliente = new Cliente("juan", 12333,30.0);
		MetodoPago metodoPago = MetodoPago.TARJETA_CREDITO;
		Integer idCompra = 1;

		supermercado.agregarCliente(cliente);
		supermercado.agregarProductoAInventario(producto, 2);
		supermercado.agregarProductoAInventario(producto2, 5);

		supermercado.agregarProductoAlCarrito(1, 12333,1);
		supermercado.agregarProductoAlCarrito(2, 12333,1);

		supermercado.iniciarCompra(cliente);
		supermercado.seleccionarMetodoDePago(metodoPago, idCompra);

		Boolean ventaFallida = supermercado.realizarVenta(cliente.getDni());
		
		assertFalse(ventaFallida);
		
	}

	@Test
	public void queSePuedaActualizarBalanceDeLaTiendaDespuesDeLaVenta() {
		
	}

	@Test
	public void queSePuedaRegistrarVentaEnElSistema() {
		
	}

}
