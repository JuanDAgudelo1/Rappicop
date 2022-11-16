package com.proyecto.rappicop.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.proyecto.rappicop.domiciliario.OfertaAceptada;
import com.proyecto.rappicop.domiciliario.listadirecciones;
import com.proyecto.rappicop.modelos.CarritoModelo;
import com.proyecto.rappicop.vendedor.Oferta;

import java.util.ArrayList;

public class Logica extends DatabaseHelper {

    Context context;

    public Logica(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    /**
     * Usuario
     */
    public long nuevoUsuario(String Usuario, String Nombres, String Correo, String Contrasena, String ConfirmarContrasena, String rol) {
        long id = 0;

        try {
            DatabaseHelper rapidb = new DatabaseHelper(context);
            SQLiteDatabase db = rapidb.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("nombre", Nombres);
            values.put("correo", Correo);
            values.put("usuario", Usuario);
            values.put("contraseña", Contrasena);
            values.put("confirmarcontraseña", ConfirmarContrasena);
            values.put("rol", rol);

            id = db.insert(getTableName(), null, values);
        } catch (Exception ex) {
            ex.toString();
        }

        return id;
    }

    /**
     * Ofertas
     */
    public long nuevaOferta(String usuario, String nombre, String categoria, int precio, String ubicacion, String descripcion, byte[] imagen) {
        long id = 0;

        try {
            DatabaseHelper rapidb = new DatabaseHelper(context);

            ContentValues values = new ContentValues();
            values.put("usuario", usuario);
            values.put("nombre", nombre);
            values.put("categoria", categoria);
            values.put("precio", precio);
            values.put("ubicacion", ubicacion);
            values.put("descripcion", descripcion);
            values.put("imagen", imagen);

            SQLiteDatabase db = rapidb.getWritableDatabase();
            id = db.insert(getTableOferta(), null, values);
        } catch (Exception ex) {
            ex.toString();
        }

        return id;
    }

    public boolean eliminarOferta(String vendedor, String nombreRestaurante) {
        boolean correct = false;
        DatabaseHelper rapidb = new DatabaseHelper(context);

        try (SQLiteDatabase db = rapidb.getWritableDatabase()) {
            Cursor cursorUsuarios = db.rawQuery("DELETE FROM " + getTableOferta() + " WHERE usuario LIKE '" + vendedor + "' AND nombre LIKE '" + nombreRestaurante + "'", null);
            cursorUsuarios.moveToFirst();
            correct = true;
        } catch (Exception ex) {
            ex.toString();
        }

        return correct;
    }

    public long aceptarOferta (String oferta, String cliente, String ubicacion, int cantidad, int precio, String estado){

        long id = 0;

        try {

            DatabaseHelper rapidb = new DatabaseHelper(context);
            SQLiteDatabase db = rapidb.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("oferta", oferta);
            values.put("cliente", cliente);
            values.put("ubicacion", ubicacion);
            values.put("cantidad", cantidad);
            values.put("precio", precio);
            values.put("estado", estado);

            id = db.insert(TABLE_ACEPTADO, null, values);
        }catch (Exception ex){
            ex.toString();
        }

        return id;
    }

    /**
     * Ofertas
     */
    public ArrayList<Oferta> consultaOfertas() {
        DatabaseHelper rapidb = new DatabaseHelper(context);
        SQLiteDatabase db = rapidb.getWritableDatabase();

        ArrayList<Oferta> listaofertas = new ArrayList<Oferta>();

        Cursor cursoroferta = db.rawQuery("SELECT * FROM " + getTableOferta(), null);

        if (cursoroferta.moveToFirst()) {
            do {
                Oferta oferta = new Oferta();
                oferta.setUsuario(cursoroferta.getString(1));
                oferta.setNombre(cursoroferta.getString(2));
                oferta.setPrecio(cursoroferta.getInt(4));
                oferta.setUbicacion(cursoroferta.getString(5));
                oferta.setImagen(cursoroferta.getBlob(7));

                listaofertas.add(oferta);
            } while (cursoroferta.moveToNext());
        }

        cursoroferta.close();

        return listaofertas;
    }

    public ArrayList<Oferta> consultaOfertasPorUsuario(String usuario) {
        DatabaseHelper rapidb = new DatabaseHelper(context);
        SQLiteDatabase db = rapidb.getWritableDatabase();

        ArrayList<Oferta> listaofertas = new ArrayList<Oferta>();

        Cursor cursoroferta = db.rawQuery("SELECT * FROM " + getTableOferta() + " WHERE usuario LIKE '" + usuario + "'", null);

        if (cursoroferta.moveToFirst()) {
            do {
                Oferta oferta = new Oferta();
                oferta.setUsuario(cursoroferta.getString(1));
                oferta.setNombre(cursoroferta.getString(2));
                oferta.setPrecio(cursoroferta.getInt(4));
                oferta.setUbicacion(cursoroferta.getString(5));
                oferta.setImagen(cursoroferta.getBlob(7));

                listaofertas.add(oferta);
            } while (cursoroferta.moveToNext());
        }

        cursoroferta.close();

        return listaofertas;
    }

    public Oferta consultaOfertaPorNombre(String nombre) {
        DatabaseHelper rapidb = new DatabaseHelper(context);
        SQLiteDatabase db = rapidb.getWritableDatabase();

        Oferta oferta = null;

        Cursor cursoroferta = db.rawQuery("SELECT * FROM " + getTableOferta() + " WHERE nombre LIKE '" + nombre + "'", null);

        if (cursoroferta.moveToFirst()) {
            // TODO: no se necesita while, ya que usa solo un resultado
            do {
                oferta = new Oferta();
                oferta.setUsuario(cursoroferta.getString(1));
                oferta.setNombre(cursoroferta.getString(2));
                oferta.setDescripcion(cursoroferta.getString(6));
                oferta.setPrecio(cursoroferta.getInt(4));
                oferta.setUbicacion(cursoroferta.getString(5));
                oferta.setImagen(cursoroferta.getBlob(7));
            } while (cursoroferta.moveToNext());
        }

        cursoroferta.close();

        return oferta;
    }

    /**
     * Carro compras
     */
    public long nuevoCarrito(String usuario, String vendedor, String nombre, int cantidad) {
        long id = 0;

        try {
            DatabaseHelper rapidb = new DatabaseHelper(context);

            ContentValues values = new ContentValues();
            values.put("vendedor", vendedor);
            values.put("cliente", usuario);
            values.put("oferta", nombre);
            values.put("cantidad", cantidad);

            SQLiteDatabase db = rapidb.getWritableDatabase();
            id = db.insert(getTableCarrito(), null, values);
        } catch (Exception ex) {
            ex.toString();
        }

        return id;
    }
    public CarritoModelo consultacarro(int id){

        DatabaseHelper rapidb = new DatabaseHelper(context);
        SQLiteDatabase db = rapidb.getWritableDatabase();

        CarritoModelo carro = new CarritoModelo();
        Cursor cursoroferta = null;

        cursoroferta = db.rawQuery("SELECT * FROM " + TABLE_CARRITO + " WHERE id = '" + id + "'",null);

        if (cursoroferta.moveToFirst()){
            do {
                carro.setVendedor(cursoroferta.getString(1));
                carro.setConsumidor(cursoroferta.getString(2));
                carro.setProducto(cursoroferta.getString(3));
                carro.setCantidad(cursoroferta.getInt(4));
            }   while (cursoroferta.moveToNext());
        }

        cursoroferta.close();

        return carro;
    }

    public ArrayList<CarritoModelo> consultaCarroPorUsuario(String usuario) {
        DatabaseHelper rapidb = new DatabaseHelper(context);
        SQLiteDatabase db = rapidb.getWritableDatabase();

        ArrayList<CarritoModelo> listacarrito = new ArrayList<>();

        Cursor cursoroferta = db.rawQuery("SELECT * FROM " + getTableCarrito() + " WHERE cliente LIKE '" + usuario + "'", null);

        if (cursoroferta.moveToFirst()) {
            do {
                CarritoModelo carro = new CarritoModelo();
                carro.setVendedor(cursoroferta.getString(1));
                carro.setConsumidor(cursoroferta.getString(2));
                carro.setProducto(cursoroferta.getString(3));
                carro.setCantidad(cursoroferta.getInt(4));
                listacarrito.add(carro);
            } while (cursoroferta.moveToNext());
        }

        cursoroferta.close();

        return listacarrito;
    }

    public boolean aumentarCantidadCarrito(String cliente, String vendedor, String oferta) {
        boolean correct = false;

        DatabaseHelper rapidb = new DatabaseHelper(context);

        try (SQLiteDatabase db = rapidb.getWritableDatabase()) {
            Cursor cursorUsuarios = db.rawQuery("SELECT * FROM " + getTableCarrito() + " WHERE vendedor LIKE '" + vendedor + "' AND cliente LIKE '" + cliente + "' AND oferta LIKE '" + oferta + "'", null);

            if (cursorUsuarios.moveToFirst()) {
                int ident = cursorUsuarios.getInt(0);
                int cant = cursorUsuarios.getInt(4) + 1;

                db.execSQL("UPDATE " + getTableCarrito() + " SET cantidad = '" + cant + "' WHERE id = '" + ident + "'");
                correct = true;
            }
        } catch (Exception ex) {
            ex.toString();
        }

        return correct;
    }

    public boolean disminuirCantidadCarrito(String cliente, String vendedor, String oferta) {
        boolean correct = false;

        DatabaseHelper rapidb = new DatabaseHelper(context);

        try (SQLiteDatabase db = rapidb.getWritableDatabase()) {
            Cursor cursorUsuarios = db.rawQuery("SELECT * FROM " + getTableCarrito() + " WHERE vendedor LIKE '" + vendedor + "' AND cliente LIKE '" + cliente + "' AND oferta LIKE '" + oferta + "'", null);

            if (cursorUsuarios.moveToFirst()) {
                int ident = cursorUsuarios.getInt(0);
                int cant = cursorUsuarios.getInt(4);

                if (cant <= 1) {
                    db.execSQL("DELETE FROM " + getTableCarrito() + " WHERE id = '" + ident + "'");
                    return true;
                }

                cant--;

                db.execSQL("UPDATE " + getTableCarrito() + " SET cantidad = '" + cant + "' WHERE id = '" + ident + "'");
                correct = true;
            }
        } catch (Exception ex) {
            ex.toString();
        }

        return correct;
    }

    public Boolean verificarCarrito(String cliente, String vendedor, String oferta) {
        DatabaseHelper rapidb = new DatabaseHelper(context);
        SQLiteDatabase db = rapidb.getWritableDatabase();

        Cursor cursorUsuarios = db.rawQuery("SELECT * FROM " + getTableCarrito() + " WHERE vendedor LIKE '" + vendedor + "' AND cliente LIKE '" + cliente + "' AND oferta LIKE '" + oferta + "'", null);

        return cursorUsuarios.moveToFirst();
    }
    public boolean eliminarcarrito (String usu, String name){

        DatabaseHelper rapidb = new DatabaseHelper(context);
        SQLiteDatabase db = rapidb.getWritableDatabase();
        boolean correct = false;
        Cursor cursorUsuarios = null;

        try {
            cursorUsuarios = db.rawQuery("SELECT * FROM " + TABLE_CARRITO + " WHERE cliente LIKE '" + usu + "' AND oferta LIKE '" + name + "'",null);



            if (cursorUsuarios.moveToFirst()){
                do {
                    int ident = cursorUsuarios.getInt(0);
                    db.execSQL("DELETE FROM " + TABLE_CARRITO + " WHERE id = '" + ident + "'");
                    return true;
                }   while (cursorUsuarios.moveToNext());
            }

        }catch (Exception ex){
            ex.toString();
        }finally {
            db.close();
        }

        return correct;
    }


    public String idbusca(int id){

        DatabaseHelper rapidb = new DatabaseHelper(context);
        SQLiteDatabase db = rapidb.getWritableDatabase();
        Cursor cursorUsuarios = null;


        cursorUsuarios = db.rawQuery("SELECT * FROM " + TABLE_CARRITO + " WHERE id = '" + id + "'",null);

        cursorUsuarios.moveToFirst();

        return cursorUsuarios.getString(2);
    }
    /**
     * Consutlas
     */

    public ArrayList<OfertaAceptada> consultaaceptadas( String usu ){

        DatabaseHelper rapidb = new DatabaseHelper(context);
        SQLiteDatabase db = rapidb.getWritableDatabase();

        ArrayList<OfertaAceptada> listaofertas = new ArrayList<OfertaAceptada>();

        OfertaAceptada oferta = null;
        Cursor cursoroferta = null;

        cursoroferta = db.rawQuery("SELECT * FROM " + TABLE_ACEPTADO + " WHERE cliente LIKE '" + usu + "'",null);

        if (cursoroferta.moveToFirst()){
            do {
                oferta = new OfertaAceptada();
                oferta.setId(cursoroferta.getInt(0));
                oferta.setOferta(cursoroferta.getString(1));
                oferta.setCliente(cursoroferta.getString(2));
                oferta.setUbicacion(cursoroferta.getString(3));
                oferta.setCantidad(cursoroferta.getInt(4));
                oferta.setPrecio(cursoroferta.getInt(5));
                oferta.setDomiciliario(cursoroferta.getString(6));
                oferta.setEstado(cursoroferta.getString(7));
                listaofertas.add(oferta);
            }   while (cursoroferta.moveToNext());
        }

        cursoroferta.close();

        return listaofertas;
    }

    public ArrayList<listadirecciones> consultaubi(String usu){

        DatabaseHelper rapidb = new DatabaseHelper(context);
        SQLiteDatabase db = rapidb.getWritableDatabase();

        listadirecciones listadir = null;
        Cursor cursoroferta = null;

        ArrayList<listadirecciones> lista = new ArrayList<listadirecciones>();

        cursoroferta = db.rawQuery("SELECT * FROM " + TABLE_UBICACIONES + " WHERE usuario LIKE '" + usu + "'",null);

        if (cursoroferta.moveToFirst()){
            do {
                listadir = new listadirecciones();
                listadir.setNombre(cursoroferta.getString(2));
                listadir.setDireccion(cursoroferta.getString(3));
                lista.add(listadir);
            }   while (cursoroferta.moveToNext());
        }

        cursoroferta.close();

        return lista;
    }

    public Oferta consultanombre(String usu){

        DatabaseHelper rapidb = new DatabaseHelper(context);
        SQLiteDatabase db = rapidb.getWritableDatabase();

        Oferta oferta = null;
        Cursor cursoroferta = null;

        cursoroferta = db.rawQuery("SELECT * FROM " + TABLE_OFERTA + " WHERE nombre LIKE '" + usu + "'",null);

        if (cursoroferta.moveToFirst()){
            do {
                oferta = new Oferta();
                oferta.setUsuario(cursoroferta.getString(1));
                oferta.setNombre(cursoroferta.getString(2));
                oferta.setDescripcion(cursoroferta.getString(6));
                oferta.setPrecio(cursoroferta.getInt(4));
                oferta.setUbicacion(cursoroferta.getString(5));
                oferta.setImagen(cursoroferta.getBlob(7));
            }   while (cursoroferta.moveToNext());
        }

        cursoroferta.close();

        return oferta;
    }

    public ArrayList<OfertaAceptada> consultaespera(){

        DatabaseHelper rapidb = new DatabaseHelper(context);
        SQLiteDatabase db = rapidb.getWritableDatabase();

        ArrayList<OfertaAceptada> listaofertas = new ArrayList<OfertaAceptada>();

        OfertaAceptada oferta = null;
        Cursor cursoroferta = null;

        cursoroferta = db.rawQuery("SELECT * FROM " + TABLE_ACEPTADO + " WHERE estado LIKE '" + "espera" + "'",null);

        if (cursoroferta.moveToFirst()){
            do {
                oferta = new OfertaAceptada();
                oferta.setId(cursoroferta.getInt(0));
                oferta.setOferta(cursoroferta.getString(1));
                oferta.setCliente(cursoroferta.getString(2));
                oferta.setUbicacion(cursoroferta.getString(3));
                oferta.setCantidad(cursoroferta.getInt(4));
                oferta.setPrecio(cursoroferta.getInt(5));
                oferta.setDomiciliario(cursoroferta.getString(6));
                oferta.setEstado(cursoroferta.getString(7));
                listaofertas.add(oferta);
            }   while (cursoroferta.moveToNext());
        }

        cursoroferta.close();

        return listaofertas;
    }
    /**
     * Ubicación
     */

    public long agregaUbi (String usuario, String nombre, String ubi){

        long id = 0;

        try {

            DatabaseHelper rapidb = new DatabaseHelper(context);
            SQLiteDatabase db = rapidb.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("usuario", usuario);
            values.put("nombre", nombre);
            values.put("direccion", ubi);

            id = db.insert(TABLE_UBICACIONES, null, values);
        }catch (Exception ex){
            ex.toString();
        }

        return id;
    }

}
