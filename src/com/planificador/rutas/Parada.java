package com.planificador.rutas;

public class Parada {
    private String nombre;
    private String direccion;
    private double latitud;
    private double longitud;
    private int tiempoPermanencia; // en minutos

    public Parada(String nombre, String direccion, double latitud, double longitud, int tiempoPermanencia) {
        assert nombre != null && !nombre.isBlank() : "El nombre no puede estar vacío";
        assert direccion != null && !direccion.isBlank() : "La dirección no puede estar vacía";
        assert tiempoPermanencia >= 0 : "El tiempo de permanencia no puede ser negativo";
        
        this.nombre = nombre;
        this.direccion = direccion;
        this.latitud = latitud;
        this.longitud = longitud;
        this.tiempoPermanencia = tiempoPermanencia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nuevoNombre) {
        assert nuevoNombre != null && !nuevoNombre.isBlank() : "El nombre no puede estar vacío";
        this.nombre = nuevoNombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String nuevaDireccion) {
        assert nuevaDireccion != null && !nuevaDireccion.isBlank() : "La dirección no puede estar vacía";
        this.direccion = nuevaDireccion;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double nuevaLatitud) {
        this.latitud = nuevaLatitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double nuevaLongitud) {
        this.longitud = nuevaLongitud;
    }

    public int getTiempoPermanencia() {
        return tiempoPermanencia;
    }

    public void setTiempoPermanencia(int nuevoTiempoPermanencia) {
        assert nuevoTiempoPermanencia >= 0 : "El tiempo de permanencia no puede ser negativo";
        this.tiempoPermanencia = nuevoTiempoPermanencia;
    }

    @Override
    public String toString() {
        return nombre + " (" + direccion + ")";
    }
}
