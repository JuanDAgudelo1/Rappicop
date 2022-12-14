package com.proyecto.rappicop.modelos;

import java.io.Serializable;

public class Direccion implements Serializable {

    public String nombre;
    public String direccion;

    public Direccion(String nombre, String direccion) {
        this.nombre = nombre;
        this.direccion = direccion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}
