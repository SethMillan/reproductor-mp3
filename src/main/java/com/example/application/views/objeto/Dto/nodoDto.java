package com.example.application.views.objeto.Dto;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class nodoDto implements Serializable {
    private String nombre, direccion;
}
