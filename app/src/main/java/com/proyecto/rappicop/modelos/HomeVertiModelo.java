package com.proyecto.rappicop.modelos;

public class HomeVertiModelo {
    int image;
    String name;
    String tiempo;
    String calificacion;
    String precio;

    public HomeVertiModelo(int image, String name, String tiempo, String calificacion, String precio) {
        this.image = image;
        this.name = name;
        this.tiempo = tiempo;
        this.calificacion = calificacion;
        this.precio = precio;

    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTiempo() {
        return tiempo;
    }

    public void setTiempo(String tiempo) {
        this.tiempo = tiempo;
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
}
