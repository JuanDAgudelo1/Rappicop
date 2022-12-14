package com.proyecto.rappicop.modelos;

import java.io.Serializable;

public class Usuario implements Serializable {

    private String usuario;
    private String nombre;
    private String correo;
    private String rol;

    public Usuario(String usuario, String nombre, String correo, String rol) {
        this.usuario = usuario;
        this.nombre = nombre;
        this.correo = correo;
        this.rol = rol;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
}
