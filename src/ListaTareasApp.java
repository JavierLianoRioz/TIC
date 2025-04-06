// ListaTareasApp.java
import java.util.ArrayList;
import java.util.Scanner;

public class ListaTareasApp {
    private static final Scanner scanner = new Scanner(System.in);
    private static final GestorTareas gestor = new GestorTareas();

    public static void main(String[] args) {
        mostrarMenu();
    }

    static void mostrarMenu() {
        while (true) {
            System.out.println("\n--- MENÚ PRINCIPAL ---");
            System.out.println("Últimas 3 tareas:");
            var ultimas = gestor.obtenerUltimasTareas(3);
            for (int i = 0; i < ultimas.size(); i++) {
                // Los números de las tareas deben comenzar desde 1
                System.out.println((i + 1) + ". " + ultimas.get(i));
            }
            System.out.println("\nOpciones:");
            System.out.println("[A]ñadir tarea");
            System.out.println("[B]orrar tarea");
            System.out.println("[M]ostrar más");
            System.out.println("[S]alir");
            System.out.print("Selecciona una opción: ");

            String opcion = scanner.nextLine().trim();
            switch (opcion.toUpperCase()) {
                case "A" -> añadirTarea();
                case "B" -> borrarTarea();
                case "M" -> mostrarListaCompleta();
                case "S" -> {
                    System.out.println("¡Hasta luego! ✨");
                    return;
                }
                default -> {
                    if (opcion.matches("\\d+")) {
                        editarTareaDesdeMenu(Integer.parseInt(opcion) - 1);
                    } else {
                        System.out.println("Opción no válida. 😅");
                    }
                }
            }
        }
    }

    static void añadirTarea() {
        System.out.println("\n--- AÑADIR TAREA ---");
        System.out.print("Introduce la nueva tarea: ");
        String tarea = scanner.nextLine();
        gestor.añadirTarea(tarea);
        System.out.println("Tarea añadida correctamente ✅");
        volverAlMenu();
    }

    static void mostrarListaCompleta() {
        System.out.println("\n--- LISTA COMPLETA DE TAREAS ---");
        var lista = gestor.obtenerTareas();
        if (lista.isEmpty()) {
            System.out.println("No hay tareas todavía. 💤");
        } else {
            for (int i = 0; i < lista.size(); i++) {
                // Aquí también corregimos el índice para que sea coherente con la lista completa
                System.out.println((i + 1) + ". " + lista.get(i));
            }
            System.out.print("¿Deseas editar alguna tarea? Escribe su número o pulsa ENTER para volver: ");
            String entrada = scanner.nextLine().trim();
            if (entrada.matches("\\d+")) {
                int indice = Integer.parseInt(entrada) - 1;
                editarTareaDesdeMenu(indice);
            }
        }
        volverAlMenu();
    }

    static void editarTareaDesdeMenu(int indice) {
        Tarea tarea = gestor.obtenerTarea(indice);
        if (tarea == null) {
            System.out.println("No existe esa tarea. ❌");
            return;
        }
        System.out.println("Tarea actual: " + tarea);
        System.out.print("Nueva descripción: ");
        String nueva = scanner.nextLine();
        gestor.editarTarea(indice, nueva);
        System.out.println("Tarea actualizada ✏️");
    }

    static void borrarTarea() {
        while (true) {
            System.out.println("\n--- BORRAR TAREA ---");
            var lista = gestor.obtenerTareas();
            if (lista.isEmpty()) {
                System.out.println("No hay tareas para borrar. 🧹");
                volverAlMenu();
                return;
            }
            for (int i = 0; i < lista.size(); i++) {
                // Mantenemos la numeración correcta también aquí
                System.out.println((i + 1) + ". " + lista.get(i));
            }
            System.out.print("Selecciona el número de la tarea a borrar (o 0 para volver): ");
            try {
                int seleccion = Integer.parseInt(scanner.nextLine());
                if (seleccion == 0) return;
                if (seleccion < 1 || seleccion > lista.size()) {
                    System.out.println("Selección inválida. ❌");
                    continue;
                }
                Tarea tareaSeleccionada = gestor.obtenerTarea(seleccion - 1);
                System.out.println("¿Seguro que quieres borrar: '" + tareaSeleccionada + "'? (S/N): ");
                String confirmacion = scanner.nextLine();
                if (confirmacion.equalsIgnoreCase("S")) {
                    gestor.borrarTarea(seleccion - 1);
                    System.out.println("Tarea borrada con éxito 🗑️");
                } else {
                    System.out.println("Operación cancelada ❎");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada no válida. 😵");
            }
        }
    }

    static void volverAlMenu() {
        System.out.println("\nPulsa ENTER para volver al menú principal...");
        scanner.nextLine();
    }
}
