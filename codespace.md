# GitHub Codespace - Guía de Uso

## ¿Qué es GitHub Codespaces?
GitHub Codespaces es un entorno de desarrollo en la nube que permite crear, probar y ejecutar proyectos directamente desde el navegador o Visual Studio Code.

## Características Principales
- **Entornos Preconfigurados**: Usa `devcontainer.json` para configuraciones personalizadas
- **Integración con GitHub**: Acceso directo a repositorios
- **Acceso Remoto**: Desarrolla desde cualquier lugar
- **Contenedores Docker**: Entornos aislados y consistentes
- **Escalabilidad**: Ajusta recursos (CPU, memoria) según necesidades

## Uso con el Planificador de Rutas
1. **Crear Codespace**:
   - Desde el repositorio, haz clic en "Code" > "Open with Codespaces"
   
2. **Configurar Entorno**:
   - El Codespace incluye Java y las herramientas necesarias preinstaladas

3. **Ejecutar Proyecto**:
   ```bash
   javac -cp lib/json.jar src/com/planificador/rutas/*.java
   java -cp "src;lib/json.jar" com.planificador.rutas.PlanificadorRutasApp
   ```

4. **Desarrollo**:
   - Edita archivos directamente en el Codespace
   - Usa la terminal integrada para compilar y ejecutar

5. **Guardar Cambios**:
   ```bash
   git add .
   git commit -m "Descripción de los cambios"
   git push
   ```

## Ventajas
- No requiere configuración local
- Acceso desde cualquier dispositivo
- Entorno consistente para todo el equipo
- Fácil colaboración

[Volver al README principal](../README.md)
