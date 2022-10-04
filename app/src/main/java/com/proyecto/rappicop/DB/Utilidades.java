package com.proyecto.rappicop.DB;

public class Utilidades {
    public final static String TABLA_USUARIO="usuario";
    public final static String CAMPO_NOMBRECOMPLETO="nombreCompleto";
    public final static String CAMPO_CORREO="correo";
    public final static String CAMPO_USUARIOREGISTRADO="usuario";
    public final static String CAMPO_CONTRASENA="contrasena";
    public final static String CAMPO_CONFIRMARCONTRASENA="confirmarcontrasena";
    public final static String CAMPO_SEXO="sexo";



    public static final String CREAR_TABLA_USUARIO = "CREATE TABLE " + TABLA_USUARIO + "("+CAMPO_NOMBRECOMPLETO+" TEXT, "
             +CAMPO_CORREO+" TEXT, "+CAMPO_USUARIOREGISTRADO+" TEXT, " +CAMPO_CONTRASENA+" TEXT, " +CAMPO_CONFIRMARCONTRASENA+" TEXT, "+CAMPO_SEXO+" TEXT)";

}

