# Planificador de Rutas - Documentación del Proyecto

## Descripción
Aplicación Java para planificar rutas con múltiples paradas, cálculo de distancias y optimización de rutas.

## Estructura del Código
```
src/
└── com/
    └── planificador/
        └── rutas/
            ├── Geocodificador.java - Maneja la geocodificación de direcciones
            ├── GestorRutas.java - Lógica principal de gestión de rutas
            ├── Parada.java - Representa una parada en la ruta
            └── PlanificadorRutasApp.java - Interfaz de consola principal
```

## Funcionalidades
- **Geocodificación**: Obtiene coordenadas (latitud, longitud) de direcciones usando OpenStreetMap
- **Cálculo de distancias**: Usa la fórmula Haversine para calcular distancias entre paradas
- **Gestión de rutas**: Permite añadir, eliminar y editar paradas
- **Interfaz de consola**: Menú interactivo para gestionar la ruta

## Dependencias
- json.org (lib/json.jar) para manejo de JSON
- OpenStreetMap Nominatim API para geocodificación

## Ejecución
```bash
javac -cp lib/json.jar src/com/planificador/rutas/*.java
java -cp "src;lib/json.jar" com.planificador.rutas.PlanificadorRutasApp
```

[Volver al README principal](../README.md)
