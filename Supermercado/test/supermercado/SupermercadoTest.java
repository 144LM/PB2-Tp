package supermercado;

import static org.junit.Assert.*;

import org.junit.Test;

public class SupermercadoTest {
	@Test
	public void queSePuedaAgregarCliente() {
		// Implementación del test
		Supermercado supermercado = new Supermercado();

		Cliente cliente = new Cliente("Juan");

		supermercado.agregarCliente(cliente);

		assertEquals(1, supermercado.getClientes().size());
	}

	@Test
	public void queSePuedaAgregarProductoAlInventario() {
	    // Implementación del test
	}

	@Test
	public void queSePuedaEliminarProductoDelInventario() {
	    // Implementación del test
	}
	
	@Test
	public void queSePuedaActualizarInventario() {
	    // Implementación del test
	}
	
	@Test
	public void queSePuedaEliminarCliente() {
	    // Implementación del test
	}

	@Test
	public void queSePuedaAgregarProductoAlCarrito() {
	    // Implementación del test
	}

	@Test
	public void queSePuedaEliminarProductoDelCarrito() {
	    // Implementación del test
	}
	
	@Test
	public void queSePuedaCalcularElTotalDelCarrito() {
	    // Implementación del test
	}

	@Test
	public void queSePuedaSeleccionarFormaDePago() {
	    // Implementación del test
	}

	@Test
	public void queSePuedaRealizarVentaYActualizarInventario() {
	  
	}
	
	
	@Test
	public void queSePuedaActualizarSaldoDelClienteDespuesDeLaVenta() {
	    // Implementación del test
	}
	
	
	@Test
	public void queSePuedaActualizarBalanceDeLaTiendaDespuesDeLaVenta() {
	    // Implementación del test
	}
	
	@Test
	public void queSePuedaRegistrarVentaEnElSistema() {
	    // Implementación del test
	}




}
