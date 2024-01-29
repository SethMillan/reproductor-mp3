package com.example.application.views.objeto;

public class nodo {
    private String nombre, direccion;
    private nodo siguiente, anterior;

    public nodo(String nombre, String direccion) {
        this.nombre = nombre;
        this.direccion = direccion;
        siguiente=null;
        anterior=null;
    }

    public nodo getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(nodo siguiente) {
        this.siguiente = siguiente;
    }

    public nodo getAnterior() {
        return anterior;
    }

    public void setAnterior(nodo anterior) {
        this.anterior = anterior;
    }
}
