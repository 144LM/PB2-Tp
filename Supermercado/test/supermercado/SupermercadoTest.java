package supermercado;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class SupermercadoTest {
    private Supermercado supermercado;
    private Categoria categoriaBebidas;
    private Categoria categoriaGalletitas;
    private Cliente cliente;
    private Categoria categoriaLacteos;

    @Before
    public void setUp() {
        supermercado = new Supermercado();
        categoriaBebidas = new Categoria(4343, "bebidas", "Productos bebibles");
        categoriaGalletitas = new Categoria(2344, "galletitas", "Productos comestibles");
        categoriaLacteos = new Categoria(3445, "lacteos", "Productos lácteos");
        cliente = new Cliente("Juan", 12333, 500.0);
        supermercado.agregarCliente(cliente);
    }

    @Test
    public void queSePuedaAgregarCliente() {
        Cliente clienteNuevo = new Cliente("Pedro", 45678, 300.0);
        supermercado.agregarCliente(clienteNuevo);
        assertEquals(2, supermercado.getClientes().size());
    }

    @Test
    public void queSePuedaEliminarCliente() {
        supermercado.eliminarClientePorDni(cliente.getDni());
        assertEquals(0, supermercado.getClientes().size());
    }

    @Test
    public void queSePuedaAgregarProductoAlInventario() {
        Producto producto = new Producto(3421, "Fideo", 920.0, categoriaBebidas);
        Integer cantidad = 5;
        Boolean resultado = supermercado.agregarProductoAInventario(producto, cantidad);

        assertTrue(resultado);
        assertEquals(cantidad, supermercado.buscarProductoCantidadPorId(producto.getIdProducto()).getCantidad());
    }

    @Test
    public void queSePuedaEliminarProductoDelInventario() {
        Producto producto = new Producto(3421, "CocaCola", 920.0, categoriaBebidas);
        Integer cantidad = 5;
        supermercado.agregarProductoAInventario(producto, cantidad);
        assertEquals(cantidad, supermercado.buscarProductoCantidadPorId(producto.getIdProducto()).getCantidad());
        supermercado.eliminarProductoPorId(producto.getIdProducto());
        assertEquals(0, supermercado.getInventario().size());
    }

    @Test
    public void queSePuedaActualizarPrecioProducto() {
        Producto producto = new Producto(1, "Coca-cola", 0.50, categoriaBebidas);
        supermercado.agregarProductoAInventario(producto, 2);
        Double nuevoPrecio = 200.0;
        supermercado.actualizarPrecio(nuevoPrecio, producto.getIdProducto());

        assertEquals(nuevoPrecio, producto.getPrecio(), 0.01);
    }

    @Test
    public void queSePuedaAgregarProductoAlCarrito() {
        Producto producto = new Producto(1, "Coca-cola", 0.50, categoriaBebidas);
        supermercado.agregarProductoAInventario(producto, 2);
        supermercado.agregarProductoAlCarrito(producto.getIdProducto(), cliente.getDni(), 2);

        assertEquals(1, cliente.getCarrito().getProductos().size());
        assertEquals("Coca-cola", cliente.getCarrito().getProductos().get(0).getProducto().getNombre());
        assertEquals(0.50, cliente.getCarrito().getProductos().get(0).getProducto().getPrecio(), 0.001);
    }

    @Test
    public void queSePuedaEliminarProductoDelCarrito() {
        Producto producto1 = new Producto(1, "Coca-cola", 20.50, categoriaBebidas);
        Producto producto2 = new Producto(2, "Pepsi", 30.50, categoriaBebidas);
        supermercado.agregarProductoAInventario(producto1, 2);
        supermercado.agregarProductoAInventario(producto2, 3);
        supermercado.agregarProductoAlCarrito(producto1.getIdProducto(), cliente.getDni(), 1);
        supermercado.agregarProductoAlCarrito(producto2.getIdProducto(), cliente.getDni(), 2);
        supermercado.eliminarProductoDelCarrito(producto2.getIdProducto(), cliente.getDni());

        assertEquals(1, cliente.getCarrito().getProductos().size());
    }

    @Test
    public void queSePuedaCalcularElTotalDelCarrito() {
        Producto producto1 = new Producto(1, "Coca-cola", 20.50, categoriaBebidas);
        Producto producto2 = new Producto(2, "Pepsi", 30.50, categoriaBebidas);
        Producto producto3 = new Producto(3, "Quilmes", 100.50, categoriaBebidas);
        Producto producto4 = new Producto(4, "Manaos", 30.0, categoriaBebidas);
        supermercado.agregarProductoAInventario(producto1, 2);
        supermercado.agregarProductoAInventario(producto2, 3);
        supermercado.agregarProductoAInventario(producto3, 5);
        supermercado.agregarProductoAInventario(producto4, 5);
        supermercado.agregarProductoAlCarrito(producto1.getIdProducto(), cliente.getDni(), 1);
        supermercado.agregarProductoAlCarrito(producto2.getIdProducto(), cliente.getDni(), 1);
        supermercado.agregarProductoAlCarrito(producto3.getIdProducto(), cliente.getDni(), 2);
        supermercado.agregarProductoAlCarrito(producto4.getIdProducto(), cliente.getDni(), 3);

        assertEquals(342.0, cliente.getCarrito().getTotal(), 0.01);
    }

    @Test
    public void queSePuedaSeleccionarFormaDePago() {
        supermercado.iniciarCompra(cliente);
        supermercado.seleccionarMetodoDePago(MetodoPago.TARJETA_CREDITO, 1);
        assertEquals(MetodoPago.TARJETA_CREDITO, supermercado.buscarCompraPorId(1).getMetodoPago());
    }

    @Test
    public void queSePuedaRealizarVentaYActualizarInventario() {
        Producto producto1 = new Producto(1, "Coca-cola", 20.50, categoriaBebidas);
        Producto producto2 = new Producto(2, "Pepsi", 90.50, categoriaBebidas);

        supermercado.agregarProductoAInventario(producto1, 2);
        supermercado.agregarProductoAInventario(producto2, 5);
        supermercado.agregarProductoAlCarrito(producto1.getIdProducto(), cliente.getDni(), 1);
        supermercado.agregarProductoAlCarrito(producto2.getIdProducto(), cliente.getDni(), 1);
        supermercado.iniciarCompra(cliente);
        supermercado.seleccionarMetodoDePago(MetodoPago.TARJETA_CREDITO, 1);

        assertTrue(supermercado.realizarVenta(cliente.getDni()));
        assertEquals((Integer) 1, supermercado.buscarProductoCantidadPorId(producto1.getIdProducto()).getCantidad());
        assertEquals((Integer) 4, supermercado.buscarProductoCantidadPorId(producto2.getIdProducto()).getCantidad());
        assertTrue(cliente.getCarrito().getProductos().isEmpty());
    }

    @Test
    public void queSePuedaHacerUnaListaDeProductosPorCategoriaEnCarrito() {
        Producto producto1 = new Producto(1, "Coca-cola", 20.50, categoriaBebidas);
        Producto producto2 = new Producto(2, "Pepsi", 30.50, categoriaBebidas);
        Producto producto3 = new Producto(3, "Quilmes", 100.50, categoriaBebidas);
        Producto producto4 = new Producto(4, "Manaos", 30.0, categoriaBebidas);
        Producto producto5 = new Producto(5, "Oreo", 50.0, categoriaGalletitas);

        supermercado.agregarProductoAInventario(producto1, 2);
        supermercado.agregarProductoAInventario(producto2, 3);
        supermercado.agregarProductoAInventario(producto3, 5);
        supermercado.agregarProductoAInventario(producto4, 5);
        supermercado.agregarProductoAInventario(producto5, 10);
        supermercado.agregarProductoAlCarrito(producto1.getIdProducto(), cliente.getDni(), 1);
        supermercado.agregarProductoAlCarrito(producto2.getIdProducto(), cliente.getDni(), 1);
        supermercado.agregarProductoAlCarrito(producto3.getIdProducto(), cliente.getDni(), 2);
        supermercado.agregarProductoAlCarrito(producto4.getIdProducto(), cliente.getDni(), 3);
        supermercado.agregarProductoAlCarrito(producto5.getIdProducto(), cliente.getDni(), 5);

        assertEquals(4, cliente.getCarrito().buscarProductosPorCategoria(categoriaBebidas.getIdCategoria()).size());
    }

    @Test
    public void queSePuedaHacerUnaListaDeProductosPorCategoriaEnInventario() {

        Producto producto1 = new Producto(1, "Coca-cola", 20.50, categoriaBebidas);
        Producto producto2 = new Producto(2, "Pepsi", 30.50, categoriaBebidas);
        Producto producto3 = new Producto(3, "Quilmes", 100.50, categoriaBebidas);
        Producto producto4 = new Producto(4, "Manaos", 30.0, categoriaBebidas);
        Producto producto5 = new Producto(5, "Oreo", 50.0, categoriaGalletitas);
        Producto producto6 = new Producto(6, "Milkaut", 60.0, categoriaLacteos);
        Producto producto7 = new Producto(7, "La Serenisima", 70.0, categoriaLacteos);

        supermercado.agregarProductoAInventario(producto1, 2);
        supermercado.agregarProductoAInventario(producto2, 3);
        supermercado.agregarProductoAInventario(producto3, 5);
        supermercado.agregarProductoAInventario(producto4, 5);
        supermercado.agregarProductoAInventario(producto5, 10);
        supermercado.agregarProductoAInventario(producto6, 6);
        supermercado.agregarProductoAInventario(producto7, 8);

        assertEquals(4, supermercado.buscarProductosPorCategoria(categoriaBebidas.getIdCategoria()).size());
        assertEquals(1, supermercado.buscarProductosPorCategoria(categoriaGalletitas.getIdCategoria()).size());
        assertEquals(2, supermercado.buscarProductosPorCategoria(categoriaLacteos.getIdCategoria()).size());
    }

    @Test
    public void queNoSePuedaRealizarLaVentaSiElClienteNoTieneDineroSuficiente() {
        Producto producto1 = new Producto(1, "Coca-cola", 20.50, categoriaBebidas);
        Producto producto2 = new Producto(2, "Pepsi", 90.50, categoriaBebidas);
        Cliente clienteSinDinero = new Cliente("Pedro", 45678, 30.0);

        supermercado.agregarCliente(clienteSinDinero);
        supermercado.agregarProductoAInventario(producto1, 2);
        supermercado.agregarProductoAInventario(producto2, 5);
        supermercado.agregarProductoAlCarrito(producto1.getIdProducto(), clienteSinDinero.getDni(), 1);
        supermercado.agregarProductoAlCarrito(producto2.getIdProducto(), clienteSinDinero.getDni(), 1);

        assertFalse(supermercado.realizarVenta(clienteSinDinero.getDni()));
    }
    
    @Test
    public void queNoSePuedaEliminarClienteInexistente() {
        Integer dniInexistente = 12345678;
        boolean resultado = supermercado.eliminarClientePorDni(dniInexistente);
        assertFalse(resultado);
    }
    
    @Test
    public void queNoSePuedaRealizarVentaConCarritoVacio() {
        Cliente clienteNuevo = new Cliente("Pedro", 45678, 1000.0);
        supermercado.agregarCliente(clienteNuevo);
        supermercado.iniciarCompra(clienteNuevo);
        supermercado.seleccionarMetodoDePago(MetodoPago.TARJETA_CREDITO, 1);

        boolean resultado = supermercado.realizarVenta(clienteNuevo.getDni());
        assertFalse(resultado);
    }
    
    
    
}