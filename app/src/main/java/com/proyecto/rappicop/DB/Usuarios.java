package com.proyecto.rappicop.DB;

public class Usuarios {
    private String nombreCompleto;
    private String correo;
    private String usuario;
    private String contrasena;
    private String confirmarcontrasena;
    private String sexo;



    public Usuarios(String nombreCompleto, String correo, String usuario, String contrasena, String confirmarcontrasena,String sexo) {
        this.nombreCompleto = nombreCompleto;
        this.correo = correo;
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.confirmarcontrasena = confirmarcontrasena;
        this.sexo = sexo;

    }



    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getConfirmarcontrasena() {
        return confirmarcontrasena;
    }

    public void setConfirmarcontrasena(String confgirmarcontrasena) {
        this.confirmarcontrasena = confgirmarcontrasena;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }


    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }


}
