package com.proyecto.rappicop.modelos;

import java.io.Serializable;

public class CarritoModelo implements Serializable {

    private String vendedor;
    private String consumidor;
    private String producto;
    private int cantidad;

    public String getVendedor() {
        return vendedor;
    }

    public void setVendedor(String vendedor) {
        this.vendedor = vendedor;
    }

    public String getConsumidor() {
        return consumidor;
    }

    public void setConsumidor(String consumidor) {
        this.consumidor = consumidor;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

}
