package com.planificador.rutas;

import java.util.ArrayList;

public class GestorRutas {
    private final ArrayList<Parada> paradas = new ArrayList<>();

    public void agregarParada(Parada nuevaParada) {
        assert nuevaParada != null : "La parada no puede ser null";
        paradas.add(nuevaParada);
    }

    public boolean eliminarParadaPorIndice(int indiceParada) {
        if (esIndiceValido(indiceParada)) {
            paradas.remove(indiceParada);
            return true;
        }
        return false;
    }

    public void actualizarParada(int indiceParada, Parada paradaActualizada) {
        assert esIndiceValido(indiceParada) : "Índice no válido";
        assert paradaActualizada != null : "La parada actualizada no puede ser null";
        paradas.set(indiceParada, paradaActualizada);
    }

    public ArrayList<Parada> obtenerTodasLasParadas() {
        return new ArrayList<>(paradas);
    }

    public ArrayList<Parada> obtenerUltimasNParadas(int numeroParadas) {
        assert numeroParadas >= 0 : "El número de paradas no puede ser negativo";
        int indiceInicio = Math.max(paradas.size() - numeroParadas, 0);
        return new ArrayList<>(paradas.subList(indiceInicio, paradas.size()));
    }

    public boolean tieneParadas() {
        return !paradas.isEmpty();
    }

    public Parada obtenerParadaPorIndice(int indiceParada) {
        if (esIndiceValido(indiceParada)) {
            return paradas.get(indiceParada);
        }
        return null;
    }

    public double calcularDistanciaTotalRuta() {
        assert paradas.size() >= 2 : "Se necesitan al menos 2 paradas para calcular la distancia";
        
        double distanciaTotal = 0;
        for (int i = 1; i < paradas.size(); i++) {
            distanciaTotal += calcularDistanciaEntreDosParadas(paradas.get(i-1), paradas.get(i));
        }
        return distanciaTotal;
    } 

    private double calcularDistanciaEntreDosParadas(Parada paradaInicio, Parada paradaFin) {
        assert paradaInicio != null : "La parada de inicio no puede ser null";
        assert paradaFin != null : "La parada de fin no puede ser null";
        
        final int RADIO_TIERRA_KM = 6371;
        
        double latitudInicio = convertirGradosARadianes(paradaInicio.getLatitud());
        double longitudInicio = convertirGradosARadianes(paradaInicio.getLongitud());
        double latitudFin = convertirGradosARadianes(paradaFin.getLatitud());
        double longitudFin = convertirGradosARadianes(paradaFin.getLongitud());

        double diferenciaLatitud = latitudFin - latitudInicio;
        double diferenciaLongitud = longitudFin - longitudInicio;

        double haversine = calcularFormulaHaversine(diferenciaLatitud, diferenciaLongitud, latitudInicio, latitudFin);
        double anguloCentral = calcularAnguloCentralHaversine(haversine);
        
        return RADIO_TIERRA_KM * anguloCentral;
    }

    private double convertirGradosARadianes(double grados) {
        return Math.toRadians(grados);
    }

    private double calcularFormulaHaversine(double diferenciaLatitud, double diferenciaLongitud, 
                                          double latitudInicio, double latitudFin) {
        return Math.pow(Math.sin(diferenciaLatitud / 2), 2) +
               Math.cos(latitudInicio) * Math.cos(latitudFin) *
               Math.pow(Math.sin(diferenciaLongitud / 2), 2);
    }

    private double calcularAnguloCentralHaversine(double haversine) {
        return 2 * Math.atan2(Math.sqrt(haversine), Math.sqrt(1 - haversine));
    }

    private boolean esIndiceValido(int indice) {
        assert indice >= 0 : "El índice no puede ser negativo";
        return indice < paradas.size();
    }
}
