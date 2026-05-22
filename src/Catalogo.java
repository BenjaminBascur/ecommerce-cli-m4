import java.util.ArrayList;
import java.util.List;

public class Catalogo {
    
    // Aquí declaramos nuestra colección: una lista de Productos
    private List<Producto> listaProductos;

    // Constructor: Al crear el catálogo, inicializamos la lista vacía
    public Catalogo() {
        this.listaProductos = new ArrayList<>();
    }

    // Método básico para agregar un producto a nuestra lista
    public void agregarProducto(Producto producto) {
        listaProductos.add(producto);
        System.out.println("Producto agregado exitosamente: " + producto.getNombre());
    }

    // Método básico para mostrar todos los productos guardados
    public void listarProductos() {
        if (listaProductos.isEmpty()) {
            System.out.println("El catálogo actualmente está vacío.");
        } else {
            System.out.println("\n--- Catálogo de Temuco Dulce ---");
            // Recorremos la lista e imprimimos cada producto
            for (Producto p : listaProductos) {
                System.out.println(p.toString());
            }
            System.out.println("--------------------------------\n");
        }
    }

    // Método para buscar por nombre o categoría
    public void buscarProducto(String palabraClave) {
        boolean encontrado = false;
        System.out.println("\n--- Resultados de Búsqueda ---");
        // Recorremos la lista de productos
        for (Producto p : listaProductos) {
            // Pasamos todo a minúsculas para que la búsqueda no falle por mayúsculas
            if (p.getNombre().toLowerCase().contains(palabraClave.toLowerCase()) || 
                p.getCategoria().toLowerCase().contains(palabraClave.toLowerCase())) {
                System.out.println(p.toString());
                encontrado = true;
            }
        }
        if (!encontrado) {
            System.out.println("No se encontraron productos que coincidan con: " + palabraClave);
        }
        System.out.println("------------------------------\n");
    }
    
    // Método para eliminar un producto por su ID
    public boolean eliminarProducto(String id) {
        // Recorremos la lista buscando el ID
        for (int i = 0; i < listaProductos.size(); i++) {
            if (listaProductos.get(i).getId().equalsIgnoreCase(id)) {
                Producto eliminado = listaProductos.remove(i); // Lo sacamos de la lista
                System.out.println("El producto '" + eliminado.getNombre() + "' ha sido eliminado.");
                return true; // Retornamos verdadero si se borró con éxito
            }
        }
        return false; // Retornamos falso si no encontramos el ID
    }

    // Método para editar un producto existente
    public boolean editarProducto(String id, String nuevoNombre, String nuevaCategoria, double nuevoPrecio) {
        // Recorremos la lista buscando el ID
        for (Producto p : listaProductos) {
            if (p.getId().equalsIgnoreCase(id)) {
                // Si lo encontramos, usamos los setters para cambiar sus valores
                p.setNombre(nuevoNombre);
                p.setCategoria(nuevaCategoria);
                p.setPrecio(nuevoPrecio);
                System.out.println("El producto ha sido actualizado exitosamente.");
                return true; 
            }
        }
        return false; // Retornamos falso si no existe el ID
    }

    // Método para buscar un producto específico por su ID (para el carrito)
    public Producto buscarPorId(String id) {
        for (Producto p : listaProductos) {
            if (p.getId().equalsIgnoreCase(id)) {
                return p; // Retorna el objeto si lo encuentra
            }
        }
        return null; // Retorna nulo si no encuentra nada
    }
}