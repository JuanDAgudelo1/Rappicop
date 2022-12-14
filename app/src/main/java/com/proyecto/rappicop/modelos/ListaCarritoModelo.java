package com.proyecto.rappicop.modelos;

import android.graphics.Bitmap;

import java.io.ByteArrayOutputStream;
import java.io.Serializable;

public class ListaCarritoModelo implements Serializable {

    private Bitmap img;
    private byte[] imagen;
    public String nproducto;
    public String precio;
    public String descripcion;
    public String cantidad;
    public String consumidor;
    public String vendedor;

    public ListaCarritoModelo(Bitmap img, String nproducto, String precio, String descripcion, String cantidad, String consumidor, String vendedor) {
        this.img = img;
        this.nproducto = nproducto;
        this.precio = precio;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.consumidor = consumidor;
        this.vendedor = vendedor;

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        img.compress(Bitmap.CompressFormat.PNG, 100, stream);
        this.imagen = stream.toByteArray();
    }

    public String getnproducto() {
        return nproducto;
    }

    public void setnproducto(String nproducto) {
        this.nproducto = nproducto;
    }

    public String getprecio() {
        return precio;
    }

    public void setprecio(String precio) {
        this.precio = precio;
    }

    public String getdescripcion() {
        return descripcion;
    }

    public void setdescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Bitmap getImg() {
        return img;
    }

    public void setImg(Bitmap img) {
        this.img = img;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getConsumidor() {
        return consumidor;
    }

    public void setConsumidor(String consumidor) {
        this.consumidor = consumidor;
    }

    public String getVendedor() {
        return vendedor;
    }

    public void setVendedor(String vendedor) {
        this.vendedor = vendedor;
    }
}
