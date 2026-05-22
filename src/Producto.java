public class Producto {
    private String id;
    private String nombre;
    private String categoria;
    private double precio;

    // Constructor: Aquí "fabricamos" el producto con sus datos
    public Producto(String id, String nombre, String categoria, double precio) {
        this.id = id;
        this.nombre = nombre;
        this.categoria = categoria;
        this.precio = precio;
    }

    // Getters y Setters (Para leer y modificar los datos de forma segura)
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }

    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }

    // El método toString nos ayuda a imprimir el producto bonito en la consola
    @Override
    public String toString() {
        return "[" + id + "] " + nombre + " (Categoría: " + categoria + ") - Precio: $" + precio;
    }
}