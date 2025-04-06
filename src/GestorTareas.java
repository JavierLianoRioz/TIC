// GestorTareas.java
import java.util.ArrayList;

public class GestorTareas {
    private final ArrayList<Tarea> tareas = new ArrayList<>();

    public void añadirTarea(String descripcion) {
        tareas.add(new Tarea(descripcion));
    }

    public boolean borrarTarea(int indice) {
        if (esIndiceValido(indice)) {
            tareas.remove(indice);
            return true;
        }
        return false;
    }

    public void editarTarea(int indice, String nuevaDescripcion) {
        if (esIndiceValido(indice)) {
            tareas.get(indice).setDescripcion(nuevaDescripcion);
        }
    }

    public ArrayList<Tarea> obtenerTareas() {
        return new ArrayList<>(tareas);
    }

    public ArrayList<Tarea> obtenerUltimasTareas(int cantidad) {
        int inicio = Math.max(tareas.size() - cantidad, 0);
        return new ArrayList<>(tareas.subList(inicio, tareas.size()));
    }

    public boolean estaVacia() {
        return tareas.isEmpty();
    }

    public Tarea obtenerTarea(int indice) {
        if (esIndiceValido(indice)) {
            return tareas.get(indice);
        }
        return null;
    }

    private boolean esIndiceValido(int indice) {
        return indice >= 0 && indice < tareas.size();
    }
}
