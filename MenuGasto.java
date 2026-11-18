package campovet.vista;

import campovet.dao.GastoDAO;
import campovet.dao.UsuarioDAO;
import campovet.modelo.Gasto;
import campovet.modelo.Usuario;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

/** Menú de consola del módulo de gestión de gastos. */
public class MenuGasto {

    private final GastoDAO gastoDAO = new GastoDAO();
    private final UsuarioDAO usuarioDAO = new UsuarioDAO();
    private final Scanner scanner;

    public MenuGasto(Scanner scanner) {
        this.scanner = scanner;
    }

    public void mostrar() {
        int opcion;
        do {
            System.out.println("\n--- GESTIÓN DE GASTOS ---");
            System.out.println("1. Registrar gasto");
            System.out.println("2. Consultar gastos");
            System.out.println("3. Modificar gasto");
            System.out.println("4. Eliminar gasto");
            System.out.println("0. Volver");
            opcion = Entrada.leerEntero(scanner, "Elige una opción: ");

            switch (opcion) {
                case 1 -> registrar();
                case 2 -> consultar();
                case 3 -> modificar();
                case 4 -> eliminar();
                case 0 -> System.out.println("Volviendo al menú principal...");
                default -> System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
    }

    private void registrar() {
        int idUsuario = Entrada.leerEntero(scanner, "ID del usuario que registra el gasto: ");
        Usuario usuario = usuarioDAO.buscarPorId(idUsuario);
        if (usuario == null) {
            System.out.println("No existe un usuario con ese ID.");
            return;
        }
        String descripcion = Entrada.leerTexto(scanner, "Descripción del gasto: ");
        double monto = Entrada.leerDecimal(scanner, "Monto: ");
        LocalDate fecha = Entrada.leerFecha(scanner, "Fecha (AAAA-MM-DD, Enter = hoy): ");

        Gasto gasto = new Gasto(fecha, monto, descripcion, usuario);
        System.out.println(gastoDAO.insertar(gasto)
                ? "Gasto registrado correctamente."
                : "No se pudo registrar el gasto.");
    }

    private void consultar() {
        List<Gasto> gastos = gastoDAO.listar();
        if (gastos.isEmpty()) {
            System.out.println("No hay gastos registrados.");
            return;
        }
        gastos.forEach(System.out::println);
    }

    private void modificar() {
        int id = Entrada.leerEntero(scanner, "ID del gasto a modificar: ");
        Gasto gasto = gastoDAO.buscarPorId(id);
        if (gasto == null) {
            System.out.println("No existe un gasto con ese ID.");
            return;
        }
        System.out.println("Datos actuales: " + gasto);
        gasto.setDescripcion(Entrada.leerTexto(scanner, "Nueva descripción: "));
        gasto.setMonto(Entrada.leerDecimal(scanner, "Nuevo monto: "));
        gasto.setFecha(Entrada.leerFecha(scanner, "Nueva fecha (AAAA-MM-DD, Enter = hoy): "));

        System.out.println(gastoDAO.actualizar(gasto)
                ? "Gasto modificado correctamente."
                : "No se pudo modificar el gasto.");
    }

    private void eliminar() {
        int id = Entrada.leerEntero(scanner, "ID del gasto a eliminar: ");
        System.out.println(gastoDAO.eliminar(id)
                ? "Gasto eliminado correctamente."
                : "No se encontró el gasto o no se pudo eliminar.");
    }
}
