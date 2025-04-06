package com.planificador.rutas;

import java.util.ArrayList;

public class GestorRutas {
    private final ArrayList<Parada> paradas = new ArrayList<>();

    public void añadirParada(Parada parada) {
        paradas.add(parada);
    }

    public boolean borrarParada(int indice) {
        if (esIndiceValido(indice)) {
            paradas.remove(indice);
            return true;
        }
        return false;
    }

    public void editarParada(int indice, Parada paradaActualizada) {
        if (esIndiceValido(indice)) {
            paradas.set(indice, paradaActualizada);
        }
    }

    public ArrayList<Parada> obtenerParadas() {
        return new ArrayList<>(paradas);
    }

    public ArrayList<Parada> obtenerUltimasParadas(int cantidad) {
        int inicio = Math.max(paradas.size() - cantidad, 0);
        return new ArrayList<>(paradas.subList(inicio, paradas.size()));
    }

    public boolean estaVacia() {
        return paradas.isEmpty();
    }

    public Parada obtenerParada(int indice) {
        if (esIndiceValido(indice)) {
            return paradas.get(indice);
        }
        return null;
    }

    public double calcularDistanciaTotal() {
        if (paradas.size() < 2) return 0;
        
        double distanciaTotal = 0;
        for (int i = 1; i < paradas.size(); i++) {
            distanciaTotal += calcularDistancia(paradas.get(i-1), paradas.get(i));
        }
        return distanciaTotal;
    }

    private double calcularDistancia(Parada p1, Parada p2) {
        // Implementación de la fórmula Haversine para calcular distancia entre coordenadas
        final int RADIO_TIERRA = 6371; // en kilómetros
        
        double lat1 = Math.toRadians(p1.getLatitud());
        double lon1 = Math.toRadians(p1.getLongitud());
        double lat2 = Math.toRadians(p2.getLatitud());
        double lon2 = Math.toRadians(p2.getLongitud());

        double dLat = lat2 - lat1;
        double dLon = lon2 - lon1;

        double a = Math.pow(Math.sin(dLat / 2), 2) +
                   Math.cos(lat1) * Math.cos(lat2) *
                   Math.pow(Math.sin(dLon / 2), 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        
        return RADIO_TIERRA * c;
    }

    private boolean esIndiceValido(int indice) {
        return indice >= 0 && indice < paradas.size();
    }
}
