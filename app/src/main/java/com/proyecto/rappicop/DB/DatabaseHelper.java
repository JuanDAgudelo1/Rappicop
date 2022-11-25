package com.proyecto.rappicop.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;


public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Rappicop";
    public static final int DATABASE_VERSION = 20;
    private static final String TABLE_NAME = "t_usuarios";
    private static final String TABLE_OFERTA = "t_ofertas";
    private static final String TABLE_UBICACIONES = "t_ubicaciones";
    private static final String TABLE_CARRITO = "t_carrito";
    private static final String TABLE_ACEPTADO = "t_aceptados";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + "(id INTEGER PRIMARY KEY AUTOINCREMENT,usuario TEXT,"
                + "nombre TEXT,correo TEXT,contraseña TEXT,confirmarcontraseña TEXT, rol TEXT)");

        db.execSQL("create table " + TABLE_OFERTA + "(id INTEGER PRIMARY KEY AUTOINCREMENT,usuario TEXT,"
                + "nombre TEXT,categoria TEXT,precio INTEGER,ubicacion TEXT,descripcion TEXT,imagen BLOB)");

        db.execSQL("create table " + TABLE_CARRITO + "(id INTEGER PRIMARY KEY AUTOINCREMENT,vendedor TEXT,"
                + "cliente TEXT,oferta TEXT,cantidad INTEGER,imagen BLOB)");

        db.execSQL("create table " + TABLE_UBICACIONES + "(id INTEGER PRIMARY KEY AUTOINCREMENT,usuario TEXT,"
                + "nombre TEXT,direccion TEXT)");

        db.execSQL("create table " + TABLE_ACEPTADO + "(id INTEGER PRIMARY KEY AUTOINCREMENT,oferta TEXT,"
                + "cliente TEXT,ubicacion TEXT,cantidad INTEGER, precio INTEGER,domiciliario TEXT, estado TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_OFERTA);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CARRITO);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ACEPTADO);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_UBICACIONES);

        onCreate(db);
    }

    public String getTableName() {
        return TABLE_NAME;
    }

    public String getTableOferta() {
        return TABLE_OFERTA;
    }

    public String getTableCarrito() {
        return TABLE_CARRITO;
    }

    public String getTableAceptado() {
        return TABLE_ACEPTADO;
    }

    public String getTableUbicaciones() {
        return TABLE_UBICACIONES;
    }

}
