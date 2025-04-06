package com.planificador.rutas;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

public class Geocodificador {
    private static final String NOMINATIM_URL = "https://nominatim.openstreetmap.org/search?format=json&q=";

    public static Coordenadas obtenerCoordenadas(String direccion) {
        try {
            String urlStr = NOMINATIM_URL + java.net.URLEncoder.encode(direccion, "UTF-8");
            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("User-Agent", "PlanificadorRutasApp");

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuilder content = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
            conn.disconnect();

            JSONObject json = new JSONObject(content.toString().substring(1, content.length() - 1));
            double lat = json.getDouble("lat");
            double lon = json.getDouble("lon");
            return new Coordenadas(lat, lon);
        } catch (Exception e) {
            System.out.println("Error al obtener coordenadas: " + e.getMessage());
            return null;
        }
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
