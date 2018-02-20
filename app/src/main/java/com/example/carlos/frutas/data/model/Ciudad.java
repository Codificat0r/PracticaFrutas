package com.example.carlos.frutas.data.model;

/**
 * Created by carlos on 19/02/18.
 */

public class Ciudad {
    int id;
    String nombre;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Ciudad(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }
}
