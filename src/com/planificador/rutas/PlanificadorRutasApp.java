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
        mostrarEncabezadoAñadirParada();
        
        String nombre = solicitarNombreParada();
        String direccion = solicitarDireccionParada();
        int tiempoPermanencia = solicitarTiempoPermanencia();
        
        Geocodificador.Coordenadas coordenadas = obtenerCoordenadasParada(direccion);
        if (coordenadas == null) {
            mostrarErrorObtencionCoordenadas();
            return;
        }

        Parada nuevaParada = crearNuevaParada(nombre, direccion, coordenadas, tiempoPermanencia);
        gestor.agregarParada(nuevaParada);
        mostrarConfirmacionParadaAñadida();
    }

    private static void mostrarEncabezadoAñadirParada() {
        System.out.println("\n--- AÑADIR PARADA ---");
    }

    private static String solicitarNombreParada() {
        System.out.print("Nombre de la parada: ");
        return scanner.nextLine();
    }

    private static String solicitarDireccionParada() {
        System.out.print("Dirección: ");
        return scanner.nextLine();
    }

    private static int solicitarTiempoPermanencia() {
        System.out.print("Tiempo de permanencia (minutos): ");
        int tiempo = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea
        return tiempo;
    }

    private static Geocodificador.Coordenadas obtenerCoordenadasParada(String direccion) {
        System.out.println("Obteniendo coordenadas...");
        return Geocodificador.obtenerCoordenadas(direccion);
    }

    private static void mostrarErrorObtencionCoordenadas() {
        System.out.println("No se pudo obtener la ubicación. Intente con una dirección más específica.");
    }

    private static Parada crearNuevaParada(String nombre, String direccion, 
                                        Geocodificador.Coordenadas coordenadas, int tiempoPermanencia) {
        return new Parada(nombre, direccion, coordenadas.latitud, coordenadas.longitud, tiempoPermanencia);
    }

    private static void mostrarConfirmacionParadaAñadida() {
        System.out.println("Parada añadida correctamente.");
    }

    private static void mostrarRuta() {
        mostrarEncabezadoRutaActual();
        
        var paradas = gestor.obtenerTodasLasParadas();
        if (paradas.isEmpty()) {
            mostrarMensajeRutaVacia();
            return;
        }

        listarParadas(paradas);
    }

    private static void mostrarEncabezadoRutaActual() {
        System.out.println("\n--- RUTA ACTUAL ---");
    }

    private static void mostrarMensajeRutaVacia() {
        System.out.println("No hay paradas en la ruta.");
    }

    private static void listarParadas(java.util.ArrayList<Parada> paradas) {
        for (int i = 0; i < paradas.size(); i++) {
            System.out.println((i + 1) + ". " + paradas.get(i));
        }
    }

    private static void eliminarParada() {
        mostrarEncabezadoEliminarParada();
        
        var paradas = gestor.obtenerTodasLasParadas();
        if (paradas.isEmpty()) {
            mostrarMensajeNoHayParadas();
            return;
        }

        mostrarRuta();
        int indice = solicitarIndiceParadaAEliminar();
        
        if (gestor.eliminarParadaPorIndice(indice)) {
            mostrarConfirmacionParadaEliminada();
        } else {
            mostrarErrorIndiceNoValido();
        }
    }

    private static void mostrarEncabezadoEliminarParada() {
        System.out.println("\n--- ELIMINAR PARADA ---");
    }

    private static void mostrarMensajeNoHayParadas() {
        System.out.println("No hay paradas para eliminar.");
    }

    private static int solicitarIndiceParadaAEliminar() {
        System.out.print("Seleccione el número de la parada a eliminar: ");
        int indice = scanner.nextInt() - 1;
        scanner.nextLine(); // Consumir el salto de línea
        return indice;
    }

    private static void mostrarConfirmacionParadaEliminada() {
        System.out.println("Parada eliminada correctamente.");
    }

    private static void mostrarErrorIndiceNoValido() {
        System.out.println("No se pudo eliminar la parada. Índice no válido.");
    }

    private static void calcularDistanciaTotal() {
        mostrarEncabezadoDistanciaTotal();
        
        double distancia = gestor.calcularDistanciaTotalRuta();
        mostrarDistanciaCalculada(distancia);
    }

    private static void mostrarEncabezadoDistanciaTotal() {
        System.out.println("\n--- DISTANCIA TOTAL ---");
    }

    private static void mostrarDistanciaCalculada(double distancia) {
        System.out.printf("La distancia total de la ruta es: %.2f km\n", distancia);
    }
}
