package com.proyecto.rappicop.modelos;

public class DestacadoModelo {

    int imagen;
    String nombre;
    String desc;

    public DestacadoModelo(int imagen, String nombre, String desc) {
        this.imagen = imagen;
        this.nombre = nombre;
        this.desc = desc;
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}

