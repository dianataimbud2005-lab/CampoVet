package campovet.vista;

import campovet.dao.ProductoDAO;
import campovet.modelo.Producto;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

/** Menú de consola del módulo de gestión de inventario (productos). */
public class MenuProducto {

    private final ProductoDAO productoDAO = new ProductoDAO();
    private final Scanner scanner;

    public MenuProducto(Scanner scanner) {
        this.scanner = scanner;
    }

    public void mostrar() {
        int opcion;
        do {
            System.out.println("\n--- GESTIÓN DE INVENTARIO ---");
            System.out.println("1. Registrar producto");
            System.out.println("2. Consultar inventario");
            System.out.println("3. Actualizar producto");
            System.out.println("4. Eliminar producto");
            System.out.println("0. Volver");
            opcion = Entrada.leerEntero(scanner, "Elige una opción: ");

            switch (opcion) {
                case 1 -> registrar();
                case 2 -> consultar();
                case 3 -> actualizar();
                case 4 -> eliminar();
                case 0 -> System.out.println("Volviendo al menú principal...");
                default -> System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
    }

    private void registrar() {
        String nombre = Entrada.leerTexto(scanner, "Nombre: ");
        String descripcion = Entrada.leerTexto(scanner, "Descripción: ");
        int cantidad = Entrada.leerEntero(scanner, "Cantidad disponible: ");
        double precio = Entrada.leerDecimal(scanner, "Precio de venta: ");

        Producto producto = new Producto(nombre, descripcion, cantidad, precio, LocalDate.now());
        System.out.println(productoDAO.insertar(producto)
                ? "Producto registrado correctamente."
                : "No se pudo registrar el producto.");
    }

    private void consultar() {
        List<Producto> productos = productoDAO.listar();
        if (productos.isEmpty()) {
            System.out.println("No hay productos en el inventario.");
            return;
        }
        productos.forEach(System.out::println);
    }

    private void actualizar() {
        int id = Entrada.leerEntero(scanner, "ID del producto a actualizar: ");
        Producto producto = productoDAO.buscarPorId(id);
        if (producto == null) {
            System.out.println("No existe un producto con ese ID.");
            return;
        }
        System.out.println("Datos actuales: " + producto);
        producto.setNombre(Entrada.leerTexto(scanner, "Nuevo nombre: "));
        producto.setDescripcion(Entrada.leerTexto(scanner, "Nueva descripción: "));
        producto.setCantidadDisponible(Entrada.leerEntero(scanner, "Nueva cantidad disponible: "));
        producto.setPrecioVenta(Entrada.leerDecimal(scanner, "Nuevo precio de venta: "));

        System.out.println(productoDAO.actualizar(producto)
                ? "Producto actualizado correctamente."
                : "No se pudo actualizar el producto.");
    }

    private void eliminar() {
        int id = Entrada.leerEntero(scanner, "ID del producto a eliminar: ");
        System.out.println(productoDAO.eliminar(id)
                ? "Producto eliminado del inventario."
                : "No se encontró el producto o no se pudo eliminar.");
    }
}
