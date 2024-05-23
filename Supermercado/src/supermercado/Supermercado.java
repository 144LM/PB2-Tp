package supermercado;

import java.util.ArrayList;
import java.util.List;

public class Supermercado {
    private List<Cliente> clientes = new ArrayList<Cliente>();

    public void agregarCliente(Cliente cliente) {
        clientes.add(cliente);
    }

    public List<Cliente> getClientes() {
        return clientes;
    }
}
