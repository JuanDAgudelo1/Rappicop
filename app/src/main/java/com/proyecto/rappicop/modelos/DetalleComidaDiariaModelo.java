package com.proyecto.rappicop.modelos;

public class DetalleComidaDiariaModelo {

    int image;
    String nombre;
    String descripcion;
    String calificacion;
    String precio;
    String tiempo;

    public DetalleComidaDiariaModelo(int image, String name, String descripcion, String calificacion, String precio, String tiempo) {
        this.image = image;
        this.nombre = name;
        this.descripcion = descripcion;
        this.calificacion = calificacion;
        this.precio = precio;
        this.tiempo = tiempo;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return nombre;
    }

    public void setName(String name) {
        this.nombre = name;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(String calificacion) {
        this.calificacion = calificacion;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getTiempo() {
        return tiempo;
    }

    public void setTiempo(String tiempo) {
        this.tiempo = tiempo;
    }
}
