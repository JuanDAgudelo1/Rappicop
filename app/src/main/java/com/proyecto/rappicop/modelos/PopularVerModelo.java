package com.proyecto.rappicop.modelos;

public class PopularVerModelo {

    int imagen;
    String nombre;
    String descrpcion;
    String calificacion;
    String tiempo;

    public PopularVerModelo(int imagen, String nombre, String descrpcion, String calificacion, String tiempo) {
        this.imagen = imagen;
        this.nombre = nombre;
        this.descrpcion = descrpcion;
        this.calificacion = calificacion;
        this.tiempo = tiempo;
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

    public String getDescrpcion() {
        return descrpcion;
    }

    public void setDescrpcion(String descrpcion) {
        this.descrpcion = descrpcion;
    }

    public String getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(String calificacion) {
        this.calificacion = calificacion;
    }

    public String getTiempo() {
        return tiempo;
    }

    public void setTiempo(String tiempo) {
        this.tiempo = tiempo;
    }
}
