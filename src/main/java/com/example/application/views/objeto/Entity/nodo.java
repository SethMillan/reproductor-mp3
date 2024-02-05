package com.example.application.views.objeto.Entity;

import lombok.*;
@Data
public class nodo {
    private String nombre, direccion;
    private nodo siguiente, anterior;

    public nodo(String nombre, String direccion) {
        this.nombre = nombre;
        this.direccion = direccion;
        siguiente = null;
        anterior = null;
    }
}
