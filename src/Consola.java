import java.util.Scanner;

public class Consola {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Catalogo miCatalogo = new Catalogo();
        TiendaService miServicio = new TiendaService(miCatalogo);

        // Productos de prueba iniciales
        miServicio.crearProducto("P01", "Kuchen de Nuez", "Kuchen", 12000);
        miServicio.crearProducto("P02", "Alfajor de Maicena", "Alfajor", 1500);

        int opcionPrincipal = -1;

        System.out.println("¡Bienvenido a Temuco Dulce!");

        while (opcionPrincipal != 0) {
            System.out.println("\n--- Menú Principal ---");
            System.out.println("1) ADMIN");
            System.out.println("2) USUARIO");
            System.out.println("0) Salir");
            System.out.print("Seleccione una opción: ");
            
            opcionPrincipal = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            switch (opcionPrincipal) {
                case 1:
                    menuAdministrador(scanner, miServicio);
                    break;
                case 2:
                    menuUsuario(scanner, miServicio);
                    break;
                case 0:
                    System.out.println("\nSaliendo del sistema... ¡Gracias!");
                    break;
                default:
                    System.out.println("\nOpción no válida.");
            }
        }
        scanner.close();
    }

    // Método auxiliar para el Menú Administrador
    private static void menuAdministrador(Scanner scanner, TiendaService miServicio) {
        System.out.println("\n--- Menú Administrador ---");
        System.out.println("1) Listar productos");
        System.out.println("2) Crear nuevo producto");
        System.out.println("3) Buscar producto");
        System.out.println("4) Eliminar producto");
        System.out.println("5) Editar producto");
        System.out.print("Seleccione una opción: ");
        
        int opcionAdmin = scanner.nextInt();
        scanner.nextLine(); 

        if (opcionAdmin == 1) {
            miServicio.mostrarCatalogo();
        } else if (opcionAdmin == 2) {
            System.out.print("ID: "); String id = scanner.nextLine();
            System.out.print("Nombre: "); String nombre = scanner.nextLine();
            System.out.print("Categoría: "); String cat = scanner.nextLine();
            System.out.print("Precio: "); double precio = scanner.nextDouble();
            miServicio.crearProducto(id, nombre, cat, precio);
        } else if (opcionAdmin == 3) {
            System.out.print("Buscar: "); String palabra = scanner.nextLine();
            miServicio.buscarProducto(palabra);
        } else if (opcionAdmin == 4) {
            System.out.print("ID a eliminar: "); String id = scanner.nextLine();
            System.out.print("¿Confirmar 'Si'?: "); String conf = scanner.nextLine();
            if (conf.equalsIgnoreCase("Si")) miServicio.eliminarProducto(id);
        } else if (opcionAdmin == 5) {
            System.out.print("ID a editar: "); String id = scanner.nextLine();
            System.out.print("Nuevo Nombre: "); String nNombre = scanner.nextLine();
            System.out.print("Nueva Categoría: "); String nCat = scanner.nextLine();
            System.out.print("Nuevo Precio: "); double nPrecio = scanner.nextDouble();
            miServicio.editarProducto(id, nNombre, nCat, nPrecio);
        }
    }

    // Método auxiliar para el Menú Usuario
    private static void menuUsuario(Scanner scanner, TiendaService miServicio) {
        int opcionUsuario = -1;
        while (opcionUsuario != 0) {
            System.out.println("\n--- Menú Usuario ---");
            System.out.println("1) Ver productos");
            System.out.println("2) Agregar al carrito");
            System.out.println("3) Ver mi carrito");
            System.out.println("4) Confirmar compra"); // Se me habia olvidado agregarla
            System.out.println("0) Volver");
            System.out.print("Seleccione: ");
            opcionUsuario = scanner.nextInt();
            scanner.nextLine();

            switch (opcionUsuario) {
                case 1: miServicio.mostrarCatalogo(); break;
                case 2:
                    System.out.print("ID: "); String id = scanner.nextLine();
                    System.out.print("Cantidad: "); int cant = scanner.nextInt();
                    miServicio.agregarAlCarrito(id, cant);
                    break;
                case 3: miServicio.verCarrito(); 
                break;
                case 4:
                    // Esta es la nueva opción que completa el flujo de compra
                    miServicio.confirmarCompra();
                    break;             
                case 0: 
                	System.out.println("Volviendo al menú principal...");
                	break;
                default: System.out.println("Opción no válida.");
            }
        }
    }
}


// Como no limitar el buscador? o debe ser preciso???