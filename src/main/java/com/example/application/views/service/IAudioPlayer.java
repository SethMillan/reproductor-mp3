package com.example.application.views.service;

import com.example.application.views.objeto.nodo;
import com.vaadin.flow.component.grid.Grid;

public interface IAudioPlayer {
    boolean isEmpty();
    void insertar(String nom,String dir);
    int getTamanio();
    nodo getCancion(int index);
    void mostrarCanciones(Grid<nodo> grid);

}
