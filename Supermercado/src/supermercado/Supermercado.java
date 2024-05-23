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

    public void agregarProducto(Producto producto){
        // asignar el próximo identificador al producto
        producto.setId(idProducto);
        inventario.put(idProducto, producto);
        //incrementar el identificador cada vez que se llame al metodo
        idProducto++;
    }

    public HashMap<Integer, Producto> getProductos() {
        return inventario;
    }
}
