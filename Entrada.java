package campovet.vista;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/** Métodos auxiliares para leer datos por consola con validación. */
public class Entrada {

    private Entrada() { }

    public static String leerTexto(Scanner scanner, String mensaje) {
        System.out.print(mensaje);
        return scanner.nextLine().trim();
    }

    public static int leerEntero(Scanner scanner, String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Debes ingresar un número entero válido.");
            }
        }
    }

    public static double leerDecimal(Scanner scanner, String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                return Double.parseDouble(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Debes ingresar un número válido (ej: 12500.50).");
            }
        }
    }

    /** Lee una fecha AAAA-MM-DD; si se deja vacío devuelve la fecha de hoy. */
    public static LocalDate leerFecha(Scanner scanner, String mensaje) {
        while (true) {
            String texto = leerTexto(scanner, mensaje);
            if (texto.isEmpty()) {
                return LocalDate.now();
            }
            try {
                return LocalDate.parse(texto);
            } catch (DateTimeParseException e) {
                System.out.println("Formato inválido. Usa AAAA-MM-DD.");
            }
        }
    }
}
