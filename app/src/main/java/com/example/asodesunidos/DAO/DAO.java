package com.example.asodesunidos.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.example.asodesunidos.Modelos.ahorro;
import com.example.asodesunidos.Modelos.cliente;
import com.example.asodesunidos.Modelos.prestamo;
import com.example.asodesunidos.Modelos.usuario;
import com.example.asodesunidos.SQLiteDB.SQLiteOpenHelperManager;

import java.util.ArrayList;
import java.util.List;

public class DAO {

    public static void insertarUsuario(Context c, usuario usu) {
        SQLiteOpenHelperManager manager = new SQLiteOpenHelperManager(c);
        SQLiteDatabase baseDatos = manager.getWritableDatabase();

        int id_usu = usu.getId();
        String nombre_usu = usu.getNombre();
        String clave_usu = usu.getClave();
        String tipo_usu = "cliente";

        ContentValues registro = new ContentValues();
        registro.put("id_usuario", id_usu);
        registro.put("nombre", nombre_usu);
        registro.put("clave", clave_usu);
        registro.put("tipo", tipo_usu);

        baseDatos.insert("usuario", null, registro);
        baseDatos.close();
    }

    public static void insertarCliente(Context c, cliente cli) {
        SQLiteOpenHelperManager manager = new SQLiteOpenHelperManager(c);
        SQLiteDatabase baseDatos = manager.getWritableDatabase();

        Integer cedula = cli.getCedula();
        String nombre = cli.getNombre();
        Integer salario = cli.getSalario();
        String telefono = cli.getTelefono();
        String fechaNacimiento = cli.getFechaNacimiento();
        String estadoCivil = cli.getEstadoCivil();
        String direccion = cli.getDireccion();

        ContentValues registro = new ContentValues();
        registro.put("cedula", cedula);
        registro.put("nombre", nombre);
        registro.put("salario", salario);
        registro.put("telefono", telefono);
        registro.put("Fecha_nacimiento", fechaNacimiento);
        registro.put("estado_civil", estadoCivil);
        registro.put("direccion", direccion);

        baseDatos.insert("cliente", null, registro);
        baseDatos.close();
    }

    public static void insertarPrestamo(Context c, prestamo p){
        SQLiteOpenHelperManager manager = new SQLiteOpenHelperManager(c);
        SQLiteDatabase baseDatos = manager.getWritableDatabase();
        Integer cedula = p.getCedula();
        String tipoPrestamo = p.getTipoPrestamo();
        Integer montoPrestamo = p.getMontoPrestamo();
        String plazoPrestamo = p.getPeriodoPrestamo();
        Integer cantidadCuotas = p.getCantidadCuotas();
        Integer montoCuota = p.getMontoCuota();

        ContentValues registro = new ContentValues();
        registro.put("cedula_cliente", cedula);
        registro.put("tipo_credito", tipoPrestamo);
        registro.put("monto_prestamo", montoPrestamo);
        registro.put("monto_pagado", 0);
        registro.put("plazo_prestamo", plazoPrestamo);
        registro.put("cantidad_cuotas", cantidadCuotas);
        registro.put("cuota", montoCuota);

        baseDatos.insert("prestamo", null, registro);
        List<prestamo> n=retornarPrestamos(c);
        baseDatos.close();
    }

    public static List<usuario> retornarUsuarios(Context c) {
        SQLiteOpenHelperManager manager = new SQLiteOpenHelperManager(c);
        SQLiteDatabase baseDatos = manager.getReadableDatabase();

        Cursor cur = baseDatos.rawQuery("SELECT * FROM usuario", null);

        List<usuario> usuarios = new ArrayList<>();

        usuario usu;
        if (cur.getCount() != 0) {
            cur.moveToFirst();
            do {
                usu = new usuario();
                usu.setId(cur.getInt(0));
                usu.setNombre(cur.getString(1));
                usu.setClave(cur.getString(2));
                usu.setTipo(cur.getString(3));
                usuarios.add(usu);
            } while (cur.moveToNext());
        }
        baseDatos.close();

        return usuarios;
    }
    public static ArrayList<prestamo> mostrarPrestamos(Context c, cliente cli){
        SQLiteOpenHelperManager manager = new SQLiteOpenHelperManager(c);
        SQLiteDatabase baseDatos = manager.getReadableDatabase();

        Cursor cur = baseDatos.rawQuery("SELECT * FROM prestamo where cedula_cliente=" + cli.getCedula(), null);

        ArrayList<prestamo> prestamos = new ArrayList<>();

        prestamo pres;
        if (cur.getCount() != 0) {
            cur.moveToFirst();
            do {
                pres = new prestamo();
                pres.setCedula(cur.getInt(1));
                pres.setTipoPrestamo(cur.getString(2));
                pres.setMontoPrestamo(cur.getInt(3));
                pres.setMontoCuota(cur.getInt(7));
                pres.setMontoPagado(cur.getInt(6));
                prestamos.add(pres);
            } while (cur.moveToNext());
        }
        baseDatos.close();

        return prestamos;
    }

    public static int modificarCliente(Context c, cliente cli){

        SQLiteOpenHelperManager manager = new SQLiteOpenHelperManager(c);
        SQLiteDatabase baseDatos = manager.getWritableDatabase();
        int cantidad = 0;

        Integer cedula_cli = cli.getCedula();
        String nombre_cli = cli.getNombre();
        String tel_cli = cli.getTelefono();
        Integer sal_cli = cli.getSalario();
        String est_civ_cli = cli.getEstadoCivil();
        String dir_cli = cli.getDireccion();
        String fec_nac_cli = cli.getFechaNacimiento();

        ContentValues registro = new ContentValues();
        registro.put("cedula",cedula_cli);
        registro.put("nombre",nombre_cli);
        registro.put("telefono",tel_cli);
        registro.put("salario",sal_cli);
        registro.put("estado_civil",est_civ_cli);
        registro.put("direccion",dir_cli);
        registro.put("Fecha_nacimiento",fec_nac_cli);

        cantidad = baseDatos.update("cliente",registro,"cedula=" + cedula_cli,null);
        baseDatos.close();
        return cantidad;
    }

    public static cliente consultarCliente(Context c, cliente cli){
        SQLiteOpenHelperManager manager = new SQLiteOpenHelperManager(c);
        SQLiteDatabase baseDatos = manager.getWritableDatabase();

        Cursor fila = baseDatos.rawQuery("select * from cliente where cedula=" + cli.getCedula(), null);

        cliente cli1=null;

        if(fila.moveToFirst()){
            cli1=new cliente();
            cli1.setCedula(fila.getInt(1));
            cli1.setNombre(fila.getString(2));
            cli1.setSalario(fila.getInt(3));
            cli1.setTelefono(fila.getString(4));
            cli1.setFechaNacimiento(fila.getString(5));
            cli1.setEstadoCivil(fila.getString(6));
            cli1.setDireccion(fila.getString(7));


            baseDatos.close();
            return cli1;
        }else{
            baseDatos.close();
            return cli1;
        }
    }

    public static usuario retornarUsuarioCed(Context c, Integer ced) {
        SQLiteOpenHelperManager manager = new SQLiteOpenHelperManager(c);
        SQLiteDatabase baseDatos = manager.getReadableDatabase();

        Cursor cur = baseDatos.rawQuery("SELECT id_usuario, nombre, clave, tipo FROM usuario WHERE id_usuario = " +ced, null);

        usuario usu = new usuario();
        if (cur.moveToFirst()) {
            usu.setId(cur.getInt(0));
            usu.setNombre(cur.getString(1));
            usu.setClave(cur.getString(2));
            usu.setTipo(cur.getString(3));
        }else{
            Toast.makeText(c, "No existe ese usuario", Toast.LENGTH_SHORT).show();
        }
        baseDatos.close();
        return usu;
    }

    public static boolean retornarUsuarioBool(Context c, Integer ced) {
        SQLiteOpenHelperManager manager = new SQLiteOpenHelperManager(c);
        SQLiteDatabase baseDatos = manager.getReadableDatabase();

        Cursor cur = baseDatos.rawQuery("SELECT id_usuario FROM usuario WHERE id_usuario = " + ced, null);

        usuario usu = new usuario();
        if (cur.moveToFirst()) {
            usu.setId(cur.getInt(0));
        }else{
            Toast.makeText(c, "No existe ese usuario", Toast.LENGTH_SHORT).show();
            return false;
        }
        baseDatos.close();
        return true;
    }

    public static cliente retornarClienteCed(Context c, Integer ced) {
        SQLiteOpenHelperManager manager = new SQLiteOpenHelperManager(c);
        SQLiteDatabase baseDatos = manager.getReadableDatabase();

        Cursor cur = baseDatos.rawQuery("SELECT cedula, nombre, salario, telefono, Fecha_nacimiento, estado_civil, direccion FROM cliente WHERE cedula =" + ced, null);

        cliente cli = new cliente();
        if (cur.moveToFirst()) {
            cli.setCedula(cur.getInt(0));
            cli.setNombre(cur.getString(1));
            cli.setSalario(cur.getInt(2));
            cli.setTelefono(cur.getString(3));
            cli.setFechaNacimiento(cur.getString(4));
            cli.setEstadoCivil(cur.getString(5));
            cli.setDireccion(cur.getString(6));
        }else{
            Toast.makeText(c, "No existe ese cliente", Toast.LENGTH_SHORT).show();
        }
        baseDatos.close();
        return cli;
    }

    public static List<cliente> retornarClientes(Context c) {
        SQLiteOpenHelperManager manager = new SQLiteOpenHelperManager(c);
        SQLiteDatabase baseDatos = manager.getReadableDatabase();

        Cursor cur = baseDatos.rawQuery("SELECT * FROM cliente", null);

        List<cliente> clientes = new ArrayList<>();

        cliente client;
        if (cur.getCount() != 0) {
            cur.moveToFirst();
            do {
                client = new cliente();
                client.setCedula(cur.getInt(1));
                client.setNombre(cur.getString(2));
                client.setTelefono(cur.getString(3));
                client.setSalario(cur.getInt(4));
                client.setEstadoCivil(cur.getString(5));
                client.setDireccion(cur.getString(6));
                client.setFechaNacimiento(cur.getString(7));
                clientes.add(client);
            } while (cur.moveToNext());
        }
        baseDatos.close();

        return clientes;
    }

    public static List<prestamo> retornarPrestamos(Context c) {
        SQLiteOpenHelperManager manager = new SQLiteOpenHelperManager(c);
        SQLiteDatabase baseDatos = manager.getReadableDatabase();

        Cursor cur = baseDatos.rawQuery("SELECT * FROM prestamo", null);

        List<prestamo> prestamos = new ArrayList<>();

        prestamo p;
        if (cur.getCount() != 0) {
            cur.moveToFirst();
            do {
                p = new prestamo();
                p.setCedula(cur.getInt(1));
                p.setTipoPrestamo(cur.getString(2));
                p.setMontoPrestamo(cur.getInt(3));
                p.setPeriodoPrestamo(cur.getString(4));
                p.setCantidadCuotas(cur.getInt(5));
                p.setMontoPagado(cur.getInt(6));
                p.setMontoCuota(cur.getInt(7));
                prestamos.add(p);
            } while (cur.moveToNext());
        }
        baseDatos.close();

        return prestamos;
    }

    public static List<usuario> retornarAhorros(Context c) {
        SQLiteOpenHelperManager manager = new SQLiteOpenHelperManager(c);
        SQLiteDatabase baseDatos = manager.getReadableDatabase();

        Cursor cur = baseDatos.rawQuery("SELECT * FROM usuario", null);

        List<usuario> usuarios = new ArrayList<>();

        usuario usu;
        if (cur.getCount() != 0) {
            cur.moveToFirst();
            do {
                usu = new usuario();
                usu.setId(cur.getInt(0));
                usu.setNombre(cur.getString(1));
                usu.setClave(cur.getString(2));
                usu.setTipo(cur.getString(3));
                usuarios.add(usu);
            } while (cur.moveToNext());
        }
        baseDatos.close();

        return usuarios;
    }


    public static void insertarAhorro(Context c, ahorro a) {
        SQLiteOpenHelperManager manager = new SQLiteOpenHelperManager(c);
        SQLiteDatabase baseDatos = manager.getWritableDatabase();
        ContentValues registro = new ContentValues();

        registro.put("cedula_cliente", a.getCedulaCliente());
        registro.put("tipo_ahorro", a.getTipoAhorro());
        registro.put("monto_ahorrado", a.getMontoAhorrado());
        registro.put("cuota", a.getCuota());

        baseDatos.insert("ahorro", null, registro);
        baseDatos.close();
    }


    public static void modificarAhorro(Context c, ahorro a1) {

        SQLiteOpenHelperManager manager = new SQLiteOpenHelperManager(c);
        SQLiteDatabase baseDatos = manager.getWritableDatabase();

        ContentValues registro = new ContentValues();
        registro.put("cuota", a1.getCuota());
        String condicion = "cedula_cliente = ? AND tipo_ahorro = ?";
        String[] argumentos = { String.valueOf(a1.getCedulaCliente()), a1.getTipoAhorro() };
        baseDatos.update("ahorro", registro,condicion, argumentos);
        baseDatos.close();
    }

    public static void modificarMontoPagado(Context c, prestamo p) {

        SQLiteOpenHelperManager manager = new SQLiteOpenHelperManager(c);
        SQLiteDatabase baseDatos = manager.getWritableDatabase();

        ContentValues registro = new ContentValues();
        registro.put("monto_pagado", p.getMontoPagado());
        String condicion = "cedula_cliente = ? AND tipo_credito = ?";
        String[] argumentos = { String.valueOf(p.getCedula()), p.getTipoPrestamo() };
        baseDatos.update("prestamo", registro,condicion, argumentos);
        baseDatos.close();
    }

    public static List<ahorro> retornaAhorros(Context c, int cedulaCliente) {
        SQLiteOpenHelperManager manager = new SQLiteOpenHelperManager(c);
        SQLiteDatabase baseDatos = manager.getWritableDatabase();

        Cursor cur = baseDatos.rawQuery("select * from ahorro where cedula_cliente=" + cedulaCliente, null);
        List<ahorro> listaAhorros = null;
        ahorro a1;
        if (cur.getCount() != 0) {
            listaAhorros = new ArrayList<>();
            cur.moveToFirst();
            do {
                a1 = new ahorro();
                a1.setCedulaCliente(cur.getInt(1));
                a1.setTipoAhorro(cur.getString(2));
                a1.setMontoAhorrado(cur.getInt(3));
                a1.setCuota(cur.getInt(4));
                listaAhorros.add(a1);
            } while (cur.moveToNext());

            baseDatos.close();
            return listaAhorros;

        } else {
            baseDatos.close();
            return null;
        }
    }
}
