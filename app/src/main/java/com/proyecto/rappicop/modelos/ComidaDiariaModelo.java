package com.proyecto.rappicop.modelos;

public class ComidaDiariaModelo {

    int image;
    String name;
    String descuento;
    String descripcion;
    String tipo;


    public ComidaDiariaModelo(int image, String name, String descuento, String descripcion, String tipo) {
        this.image = image;
        this.name = name;
        this.descuento = descuento;
        this.descripcion = descripcion;
        this.tipo = tipo;
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

    public String getDescuento() {
        return descuento;
    }

    public void setDescuento(String descuento) {
        this.descuento = descuento;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
