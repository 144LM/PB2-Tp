package supermercado;

public class Producto {
    private Integer id;
    private String nombre;
    private Double precio;

    public Producto(String nombre, Double precio) {
        this.nombre = nombre;
        this.precio = precio;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}