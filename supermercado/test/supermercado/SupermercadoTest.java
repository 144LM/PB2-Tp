package supermercado;

import static org.junit.Assert.*;

import org.junit.Test;

public class SupermercadoTest {

	@Test
	public void queSePuedaAgregarCliente() {

		Supermercado admin = new Supermercado();
		String nombre = "Juan Perez";
		Integer dni = 12345678;
		Cliente cliente = new Cliente(nombre, dni);

		Boolean resultado = admin.agregarCliente(cliente);

		assertTrue(resultado);
	}

}
