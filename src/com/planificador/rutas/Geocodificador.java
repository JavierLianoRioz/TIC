package com.planificador.rutas;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

public class Geocodificador {
    private static final String NOMINATIM_URL = "https://nominatim.openstreetmap.org/search?format=json&q=";

    public static Coordenadas obtenerCoordenadas(String direccion) {
        assert direccion != null && !direccion.isBlank() : "La dirección no puede estar vacía";
        
        try {
            String urlConsulta = construirURLConsulta(direccion);
            String respuestaJSON = realizarPeticionHTTP(urlConsulta);
            return procesarRespuestaJSON(respuestaJSON);
        } catch (Exception e) {
            System.out.println("Error al obtener coordenadas: " + e.getMessage());
            return null;
        }
    }

    private static String construirURLConsulta(String direccion) throws Exception {
        return NOMINATIM_URL + java.net.URLEncoder.encode(direccion, "UTF-8");
    }

    private static String realizarPeticionHTTP(String urlConsulta) throws Exception {
        URL url = new URL(urlConsulta);
        HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
        conexion.setRequestMethod("GET");
        conexion.setRequestProperty("User-Agent", "PlanificadorRutasApp");

        BufferedReader lector = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
        StringBuilder contenido = new StringBuilder();
        String linea;
        while ((linea = lector.readLine()) != null) {
            contenido.append(linea);
        }
        lector.close();
        conexion.disconnect();
        
        return contenido.toString();
    }

    private static Coordenadas procesarRespuestaJSON(String respuestaJSON) {
        JSONObject json = new JSONObject(respuestaJSON.substring(1, respuestaJSON.length() - 1));
        double latitud = json.getDouble("lat");
        double longitud = json.getDouble("lon");
        return new Coordenadas(latitud, longitud);
    }

    public static class Coordenadas {
        public final double latitud;
        public final double longitud;

        public Coordenadas(double latitud, double longitud) {
            this.latitud = latitud;
            this.longitud = longitud;
        }
    }
}
