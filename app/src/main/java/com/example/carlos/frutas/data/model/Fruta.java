package com.example.carlos.frutas.data.model;

/**
 * Created by carlos on 19/02/18.
 */

public class Fruta {
    int id;
    String nombre;
    int peso;
    String ciudad;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Fruta(int id, String nombre, int peso, String ciudad) {
        this.id = id;

        this.nombre = nombre;
        this.peso = peso;
        this.ciudad = ciudad;
    }
}
