package supermercado;

import java.util.ArrayList;

public class Carrito {
    private ArrayList<Producto> productos;

    public Carrito(ArrayList<Producto> productos) {
        this.productos = productos;
    }

    public void agregarProducto(Producto producto) {
        productos.add(producto);
    }

    public void eliminarProducto(Producto producto) {
        productos.remove(producto);
    }

    public ArrayList<Producto> getProductos() {
        return productos;
    }

	public Double getTotal() {
		Double total = 0.0;
		for (Producto producto : productos) {
			total+=producto.getPrecio();
		}
		
		return total;
	}


}
