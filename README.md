# Reproductor MP3

## Introduccion

Esta practica es una tarea para la materia de Tópicos Avanzados de Programación con el profesor Amaro Flores Alejandro, consiste en realizar un reproductor de musica mp3 con una interfaz visual, por lo que se decidió que para fines practicos se haría uso de Vaadin.

## Base URL

La url sobre la que se estará trabajando es la siguiente `https://localhost:8080`

## Recursos disponibles

La interfaz visual cuenta con dos partes:
 - Izquierda: Con el div y los controles principales de las canciones
 - Derecha: Con la tabla(grid) que contendra la lista de canciones

Una vez que se termino el programa se opto por decidir que el grid tambien pudiera intervenir con la seleccion de canciones, por lo que si se decide seleccionar algun item del mismo, reproducira la cancion correspondiente.

## Detalles

Algunos detalles que me gustarian recalcar que no parecen tan obvios cuando estas viendo la pura interfas visual son:
 - Se creo la clase `nodoDto` para poder llenar la tabla del grid, de haberla llenado con la pura entidad del nodo habria tenido mas informacion de la necesaria y con ella un par de complicaciones que no convienen.
 - Se trabajo con la libreria `Clip` y con `AudioInputStream`, recomiendo revisar el metodo `reproducirCancion(File)` en el `MainView`
 - Al principio se estuvo manejando la libreria `AdvancedPlayer`, sin embargo esta libreria traia complicaciones, como el hecho de que al momento de reproducir una cancion, todas las demas funcoiones se detenian hasta que la cancion se detuviera, lo que posteriormente me llevo a trabajar con `Thread`, sin embargo el inconveniente con esto es que si reproducia la cancion y la detenia, pero no volvia a reproducirla, opte por buscar otra libreria.
 - Tenia pensado tambien agregar un controlador de volumen, pero la libreria del `Slider` no parecia estar disponible, si llegara a encontrar otra version, lo actualizare 
