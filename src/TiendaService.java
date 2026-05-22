public class TiendaService {
    
    private Catalogo catalogo;
    private Carrito carrito; // <--- 1. Agregamos el carrito como atributo

    public TiendaService(Catalogo catalogo) {
        this.catalogo = catalogo;
        this.carrito = new Carrito(); // <--- 2. Lo inicializamos al crear el servicio
    }

    // --- MÉTODOS DEL ADMINISTRADOR (Tus métodos existentes...) ---
    public void mostrarCatalogo() { catalogo.listarProductos(); }
    public void crearProducto(String id, String nombre, String categoria, double precio) {
        if (precio <= 0) { System.out.println("Error: El precio debe ser mayor a 0."); return; }
        catalogo.agregarProducto(new Producto(id, nombre, categoria, precio));
    }
    public void buscarProducto(String palabraClave) { catalogo.buscarProducto(palabraClave); }
    public void eliminarProducto(String id) {
        if (!catalogo.eliminarProducto(id)) System.out.println("Error: ID no encontrado.");
    }
    public void editarProducto(String id, String nombre, String categoria, double precio) {
        if (precio <= 0) { System.out.println("Error: El precio debe ser mayor a 0."); return; }
        if (!catalogo.editarProducto(id, nombre, categoria, precio)) System.out.println("Error: ID no encontrado.");
    }

    // --- 3. NUEVOS MÉTODOS PARA EL USUARIO ---

    public void agregarAlCarrito(String id, int cantidad) {
        // Buscamos el producto usando el método que creamos en el Catalogo
        Producto p = catalogo.buscarPorId(id); 
        
        if (p != null) {
            try {
                // Si el producto existe, le pedimos al carrito que lo guarde
                carrito.agregarItem(p, cantidad);
            } catch (CantidadInvalidaException e) {
                // Si la cantidad es inválida, atrapamos el error aquí
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("Error: No existe ningún producto con el ID: " + id);
        }
    }

    public void verCarrito() {
        carrito.mostrarCarrito();
    }
    
 // Método para confirmar la compra
    public void confirmarCompra() {
        if (carrito.estaVacio()) { // Asegúrate de tener este método en Carrito
            System.out.println("No puedes comprar, el carrito está vacío.");
            return;
        }

        double totalBase = carrito.calcularTotalBase();
        double descuento = 0;

        // Ejemplo de regla de descuento: Si total > 20000, 10% de descuento
        if (totalBase > 20000) {
            descuento = totalBase * 0.10;
        }

        double totalFinal = totalBase - descuento;

        System.out.println("\n--- Resumen de Compra ---");
        System.out.println("Total Base: $" + totalBase);
        System.out.println("Descuento aplicado: -$" + descuento);
        System.out.println("TOTAL A PAGAR: $" + totalFinal);
        System.out.println("¡Gracias por su compra en Temuco Dulce!");
        
        // Vaciamos el carrito tras la compra
        carrito.vaciarCarrito();
    }
}