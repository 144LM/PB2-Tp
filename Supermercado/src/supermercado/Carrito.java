package supermercado;

import java.util.ArrayList;

public class Carrito {
	
    private ArrayList<ProductoCantidad> productos;

    public Carrito() {
        this.productos = new ArrayList<ProductoCantidad>();
    }

    public void agregarProducto(ProductoCantidad producto) {
        productos.add(producto);
    }

    public void eliminarProducto(Integer idproducto) {
        ProductoCantidad productoCantidadDelCarrito = buscarProductoCantidadPorId(idproducto);
        if(productoCantidadDelCarrito != null) {
        	productos.remove(productoCantidadDelCarrito);
        }
    }
    
    private ProductoCantidad buscarProductoCantidadPorId(Integer idProducto) {
		for (ProductoCantidad productoCantidad : productos) {
			
			if (productoCantidad.getProducto().getIdProducto().equals(idProducto)) {
				return productoCantidad;
			}
		}
		return null;
	}

    public ArrayList<ProductoCantidad> getProductos() {
        return productos;
    }

    public Double getTotal() {
        double total = 0.0;
        for (ProductoCantidad productoCantidad : productos) {
            total += productoCantidad.getProducto().getPrecio() * productoCantidad.getCantidad();
        }
        return total;
    }


    public void vaciarContenido() {
        productos.clear();
    }
}
