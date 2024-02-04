package com.example.application.views.service;

import com.example.application.views.objeto.nodo;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import java.util.ArrayList;
import java.util.List;

public class AudioPlayer implements IAudioPlayer{
    private nodo inicioLista, finLista;
    private int tamanio;
    //-----Constructor-----------------------
    public AudioPlayer(){
        inicioLista=null;
        finLista=null;
        tamanio=0;
    }
    //------Metodos--------------------------
    @Override
    public boolean isEmpty() {
        if (inicioLista==null&&finLista==null){
            return true;
        }else{
            return false;
        }
    }
    @Override
    public void mostrarCanciones(Grid<nodo> grid){

        List<nodo> canciones=new ArrayList<>();
        try{
            nodo nodoActual = inicioLista;

            while (nodoActual != null) {
                canciones.add(nodoActual);
                nodoActual = nodoActual.getSiguiente();
            }
            grid.setItems(canciones);
        }
        catch(Exception e){
            Notification.show("Errorcito: "+e);
        }
}
    @Override
    public void insertar(String nom, String dir) {
        nodo N=new nodo(nom,dir);
        if(inicioLista==null){
            inicioLista=N;
            inicioLista.setSiguiente(inicioLista);
            N.setAnterior(finLista);
            finLista=N;

        }else{
            finLista.setSiguiente(N);
            N.setSiguiente(inicioLista);
            N.setAnterior(finLista);
            finLista=N;
            inicioLista.setAnterior(finLista);
        }
        tamanio++;
    }

    @Override
    public int getTamanio() {
        if (inicioLista==null&&finLista==null){
            return 0;
        }else{
            return tamanio;
        }
    }

    @Override
    public nodo getCancion(int index) {
        if (index < 0 || index >= tamanio) {
            return null;
        }
        int n = 0;
        nodo aux = inicioLista;
        while (n != index) {
            aux = aux.getSiguiente();
            n++;
        }
        return aux;
    }
}
