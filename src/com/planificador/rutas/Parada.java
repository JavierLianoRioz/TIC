package com.planificador.rutas;

public class Parada {
    private String nombre;
    private String direccion;
    private double latitud;
    private double longitud;
    private int tiempoPermanencia; // en minutos

    public Parada(String nombre, String direccion, double latitud, double longitud, int tiempoPermanencia) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.latitud = latitud;
        this.longitud = longitud;
        this.tiempoPermanencia = tiempoPermanencia;
    }

    // Getters y setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public int getTiempoPermanencia() {
        return tiempoPermanencia;
    }

    public void setTiempoPermanencia(int tiempoPermanencia) {
        this.tiempoPermanencia = tiempoPermanencia;
    }

    @Override
    public String toString() {
        return nombre + " (" + direccion + ")";
    }
}
