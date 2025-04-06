# Planificador de Rutas

Aplicación Java para planificar rutas con múltiples paradas, cálculo de distancias y optimización de rutas.

## Documentación
- [Documentación del Proyecto](proyecto.md)
- [Guía de GitHub Codespace](codespace.md)

## Características Principales
- Añadir/eliminar paradas con nombre, dirección y tiempo de permanencia
- Cálculo automático de coordenadas usando OpenStreetMap
- Cálculo de distancias entre paradas usando la fórmula Haversine
- Interfaz de consola interactiva
- Gestión completa de rutas

## Estructura del Proyecto
```
src/
└── com/
    └── planificador/
        └── rutas/
            ├── Geocodificador.java
            ├── GestorRutas.java
            ├── Parada.java
            └── PlanificadorRutasApp.java
lib/
└── json.jar
```

## Requisitos
- Java 11 o superior
- Conexión a internet (para geocodificación)

## Instalación
1. Clona el repositorio
2. Descarga json.jar y colócalo en la carpeta lib/
3. Compila el proyecto:
   ```bash
   javac -cp lib/json.jar src/com/planificador/rutas/*.java
   ```

## Ejecución
```bash
java -cp "src;lib/json.jar" com.planificador.rutas.PlanificadorRutasApp
```

## Ejemplo de Uso
1. Añadir parada:
   - Nombre: "Casa"
   - Dirección: "Calle Principal 123, Madrid"
   - Tiempo de permanencia: 10 minutos

2. Añadir otra parada:
   - Nombre: "Oficina"
   - Dirección: "Avenida Secundaria 456, Madrid"

3. Calcular distancia total entre paradas

4. Ver ruta completa

## Configuración de la Consola (Windows)
Para mostrar correctamente caracteres especiales:
1. Abre el símbolo del sistema como administrador
2. Ejecuta: `chcp 65001`
3. Cambia la fuente a Consolas o Lucida Console

## Dependencias
- json.org (incluido en lib/json.jar) para manejo de JSON
- OpenStreetMap Nominatim API para geocodificación

## Licencia
MIT License
