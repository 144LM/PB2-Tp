package supermercado;

import java.util.ArrayList;

public class Supermercado {
	
	private ArrayList<Cliente>clientes;

	public Supermercado() {
		this.clientes = new ArrayList<Cliente>();
	}

	public Boolean agregarCliente(Cliente cliente) {
		return clientes.add(cliente);
	}




}
