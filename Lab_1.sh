printf " /\_/\ \n"
printf "( o.o )  \n"
printf " > ^ <   \n"

#!/bin/bash

# Función para mostrar el gatito con ojos, orejas y manos en un estado dado
mostrar_gatito() {
  local orejas=$1 # Orejas
  local ojos=$2   # Ojos
  local manos=$3  # Manos
  clear
  printf "$orejas \n"
  printf "( $ojos )  \n"
  printf " $manos   \n"
}

# Función de animación de parpadeo, orejas y movimiento de manos
animar_gatito() {
  local duracion=$1  # Duración de cada fotograma (en segundos)
  
  while true; do
    # Ojos abiertos, manos normales, orejas normales
    mostrar_gatito " /\_/\\" "o.o" "> ^ <"
    sleep $duracion

    # Ojos cerrados, manos normales, orejas normales
    mostrar_gatito " /\_/\\" "-.-" "> ^ <"
    sleep $duracion

    # Ojos abiertos, manos arriba, orejas movidas
    mostrar_gatito " |\__/|" "o.o" "/ ^ \\"
    sleep $duracion

    # Ojos abiertos, manos abajo, orejas normales
    mostrar_gatito " /\_/\\" "o.o" "v   v"
    sleep $duracion

    # Ojos abiertos, manos normales, orejas bajadas
    mostrar_gatito " |\__/|" "o.o" "> ^ <"
    sleep $duracion
  done
}

# Iniciar la animación con la duración deseada entre cambios (0.5 segundos en este caso)
animar_gatito 0.5
