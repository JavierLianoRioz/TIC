package com.planificador.rutas;

import java.util.Scanner;
import com.planificador.rutas.GestorRutas;
import com.planificador.rutas.Parada;
import com.planificador.rutas.Geocodificador.Coordenadas;

public class PlanificadorRutasApp {
    private static final Scanner scanner = new Scanner(System.in);
    private static final GestorRutas gestor = new GestorRutas();

    public static void main(String[] args) {
        mostrarMenuPrincipal();
    }

    private static void mostrarMenuPrincipal() {
        while (true) {
            System.out.println("\n=== PLANIFICADOR DE RUTAS ===");
            System.out.println("1. Añadir parada");
            System.out.println("2. Mostrar ruta actual");
            System.out.println("3. Eliminar parada");
            System.out.println("4. Calcular distancia total");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            switch (opcion) {
                case 1 -> añadirParada();
                case 2 -> mostrarRuta();
                case 3 -> eliminarParada();
                case 4 -> calcularDistanciaTotal();
                case 5 -> {
                    System.out.println("¡Hasta luego!");
                    return;
                }
                default -> System.out.println("Opción no válida. Intente nuevamente.");
            }
        }
    }

    private static void añadirParada() {
        System.out.println("\n--- AÑADIR PARADA ---");
        System.out.print("Nombre de la parada: ");
        String nombre = scanner.nextLine();
        
        System.out.print("Dirección: ");
        String direccion = scanner.nextLine();
        
        System.out.print("Tiempo de permanencia (minutos): ");
        int tiempo = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea

        System.out.println("Obteniendo coordenadas...");
        Geocodificador.Coordenadas coords = Geocodificador.obtenerCoordenadas(direccion);
        if (coords == null) {
            System.out.println("No se pudo obtener la ubicación. Intente con una dirección más específica.");
            return;
        }

        Parada nuevaParada = new Parada(nombre, direccion, coords.latitud, coords.longitud, tiempo);
        gestor.añadirParada(nuevaParada);
        System.out.println("Parada añadida correctamente.");
    }

    private static void mostrarRuta() {
        System.out.println("\n--- RUTA ACTUAL ---");
        var paradas = gestor.obtenerParadas();
        
        if (paradas.isEmpty()) {
            System.out.println("No hay paradas en la ruta.");
            return;
        }

        for (int i = 0; i < paradas.size(); i++) {
            System.out.println((i + 1) + ". " + paradas.get(i));
        }
    }

    private static void eliminarParada() {
        System.out.println("\n--- ELIMINAR PARADA ---");
        var paradas = gestor.obtenerParadas();
        
        if (paradas.isEmpty()) {
            System.out.println("No hay paradas para eliminar.");
            return;
        }

        mostrarRuta();
        System.out.print("Seleccione el número de la parada a eliminar: ");
        int indice = scanner.nextInt() - 1;
        scanner.nextLine(); // Consumir el salto de línea

        if (gestor.borrarParada(indice)) {
            System.out.println("Parada eliminada correctamente.");
        } else {
            System.out.println("No se pudo eliminar la parada. Índice no válido.");
        }
    }

    private static void calcularDistanciaTotal() {
        double distancia = gestor.calcularDistanciaTotal();
        System.out.println("\n--- DISTANCIA TOTAL ---");
        System.out.printf("La distancia total de la ruta es: %.2f km\n", distancia);
    }
}
