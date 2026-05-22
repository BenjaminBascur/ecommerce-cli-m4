import java.util.HashMap;
import java.util.Map;

public class Carrito {
    
    // Map para relacionar un Producto (llave) con su Cantidad deseada (valor)
    private Map<Producto, Integer> items;

    public Carrito() {
        this.items = new HashMap<>();
    }

    // Método para agregar productos al carrito
    public void agregarItem(Producto producto, int cantidad) throws CantidadInvalidaException {
        // se valida que la cantidad sea mayor a 0, tal como exigen las reglas
        if (cantidad <= 0) {
            // Aquí por fin lanzamos la excepción personalizada que creamos en el paso 1
            throw new CantidadInvalidaException("Error: La cantidad a agregar debe ser mayor a 0.");
        }

        // Si el producto ya está en el carrito, simplemente sumamos la nueva cantidad
        if (items.containsKey(producto)) {
            int cantidadActual = items.get(producto);
            items.put(producto, cantidadActual + cantidad);
        } else {
            // Si es un producto nuevo en el carrito, lo agregamos por primera vez
            items.put(producto, cantidad);
        }
        System.out.println("Se agregaron " + cantidad + " unidad(es) de '" + producto.getNombre() + "' al carrito.");
    }

    // Método inicial para revisar qué llevamos en el carrito
    public void mostrarCarrito() {
        if (items.isEmpty()) {
            System.out.println("El carrito está vacío.");
            return;
        }
        System.out.println("\n--- Mi Carrito ---");
        for (Map.Entry<Producto, Integer> entry : items.entrySet()) {
            Producto p = entry.getKey();
            int cantidad = entry.getValue();
            System.out.println("- " + p.getNombre() + " | Cantidad: " + cantidad + " | Subtotal: $" + (p.getPrecio() * cantidad));
        }
        System.out.println("------------------\n");
    }
    
 // Método 1: Verifica si el carrito está vacío
    public boolean estaVacio() {
        return items.isEmpty();
    }

    // Método 2: Calcula el total base del carrito
    public double calcularTotalBase() {
        double total = 0;
        for (Map.Entry<Producto, Integer> entry : items.entrySet()) {
            total += entry.getKey().getPrecio() * entry.getValue();
        }
        return total;
    }

    // Método 3: Vacía el carrito (útil después de confirmar la compra)
    public void vaciarCarrito() {
        items.clear();
    }
}