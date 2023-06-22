package com.example.asodesunidos.SQLiteDB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SQLiteOpenHelperManager extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "ASODESUNIDOS";
    private static final int DATABASE_VERSION = 1;

    public SQLiteOpenHelperManager(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(
                "create table usuario(" +
                        "id_usuario int primary key," +
                        "nombre text not null," +
                        "clave text not null," +
                        "tipo text not null)");

        sqLiteDatabase.execSQL(
                "create table cliente(" +
                        "id INTEGER primary key AUTOINCREMENT," +
                        "cedula int not null UNIQUE," +
                        "nombre text not null," +
                        "salario int not null, " +
                        "telefono text not null," +
                        "Fecha_nacimiento text not null," +
                        "estado_civil text not null, " +
                        "direccion text not null," +
                        "FOREIGN KEY (cedula) REFERENCES usuario(id_usu))");

        sqLiteDatabase.execSQL(
                "create table administrador(" +
                        "id INTEGER primary key AUTOINCREMENT," +
                        "cedula int not null UNIQUE," +
                        "nombre text not null," +
                        "FOREIGN KEY (cedula) REFERENCES usuario(id_usu))");

        sqLiteDatabase.execSQL(
                "create table ahorro(" +
                        "id INTEGER primary key AUTOINCREMENT," +
                        "cedula_cliente int not null," +
                        "tipo_ahorro text not null," +
                        "monto_ahorrado real not null," +
                        "cuota real not null," +
                        "FOREIGN KEY (cedula_cliente) REFERENCES cliente(cedula))");

        sqLiteDatabase.execSQL(
                "create table prestamo(" +
                        "id INTEGER primary key AUTOINCREMENT," +
                        "cedula_cliente int not null," +
                        "tipo_credito text not null," +
                        "monto_prestamo real not null," +
                        "plazo_prestamo real not null," +
                        "cantidad_cuotas real not null," +
                        "monto_pagado real not null," +
                        "cuota real not null," +
                        "FOREIGN KEY (cedula_cliente) REFERENCES cliente(cedula))");

        burnedData(sqLiteDatabase);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

    }


    private void burnedData(SQLiteDatabase sqLiteDatabase) {


        //USUARIOS----------------------------------------------------------------------------------
        sqLiteDatabase.execSQL("insert into usuario (id_usuario,nombre, clave, tipo) values (105660957,'Lorena Mora',1111, 'admin')");
        sqLiteDatabase.execSQL("insert into usuario (id_usuario,nombre, clave, tipo) values (106080441,'Ivan Quesada',2222, 'admin')");
        sqLiteDatabase.execSQL("insert into usuario (id_usuario,nombre, clave, tipo) values (120950736,'Isabella Araujo',3333, 'admin')");
        sqLiteDatabase.execSQL("insert into usuario (id_usuario,nombre, clave, tipo) values (121760184,'Arianna Araujo',4444, 'admin')");
        sqLiteDatabase.execSQL("insert into usuario (id_usuario,nombre, clave, tipo) values (109230952,'Rafael Araujo',5555, 'admin')");
        sqLiteDatabase.execSQL("insert into usuario (id_usuario,nombre, clave, tipo) values (401660592,'Patricia Alfaro',6666, 'admin')");
        sqLiteDatabase.execSQL("insert into usuario (id_usuario,nombre, clave, tipo) values (202740538,'Orlando Alfaro',7777, 'admin')");
        sqLiteDatabase.execSQL("insert into usuario (id_usuario,nombre, clave, tipo) values (401770749,'Josue Alfaro',8888, 'admin')");
        sqLiteDatabase.execSQL("insert into usuario (id_usuario,nombre, clave, tipo) values (401600748,'Andrea Alfaro',9999, 'admin')");
        sqLiteDatabase.execSQL("insert into usuario (id_usuario,nombre, clave, tipo) values (108930353,'Alejandro Gamboa',1010, 'admin')");
        sqLiteDatabase.execSQL("insert into usuario (id_usuario,nombre, clave, tipo) values (118480995,'Esteban Salas',111, 'cliente')");
        sqLiteDatabase.execSQL("insert into usuario (id_usuario,nombre, clave, tipo) values (109420410,'Fabian Salas',222, 'cliente')");
        sqLiteDatabase.execSQL("insert into usuario (id_usuario,nombre, clave, tipo) values (108610762,'Randall Salas',333, 'cliente')");
        sqLiteDatabase.execSQL("insert into usuario (id_usuario,nombre, clave, tipo) values (110370409,'Paul Salas',444, 'cliente')");
        sqLiteDatabase.execSQL("insert into usuario (id_usuario,nombre, clave, tipo) values (107270537,'Walter Salas',555, 'cliente')");
        sqLiteDatabase.execSQL("insert into usuario (id_usuario,nombre, clave, tipo) values (117190303,'Luis Salas',666, 'cliente')");
        sqLiteDatabase.execSQL("insert into usuario (id_usuario,nombre, clave, tipo) values (400950690,'Zoila Molina',777, 'cliente')");
        sqLiteDatabase.execSQL("insert into usuario (id_usuario,nombre, clave, tipo) values (400880827,'Walter Enrique Salas',888, 'cliente')");
        sqLiteDatabase.execSQL("insert into usuario (id_usuario,nombre, clave, tipo) values (118860882,'Juan Pablo Salas',999, 'cliente')");
        sqLiteDatabase.execSQL("insert into usuario (id_usuario,nombre, clave, tipo) values (108570894,'Diana Fonseca',101, 'cliente')");


        //ADMINISTRADORES---------------------------------------------------------------------------
        sqLiteDatabase.execSQL("insert into administrador (cedula,nombre) values (105660957,'Lorena Mora')");
        sqLiteDatabase.execSQL("insert into administrador (cedula,nombre) values (106080441,'Ivan Quesada')");
        sqLiteDatabase.execSQL("insert into administrador (cedula,nombre) values (120950736,'Isabella Araujo')");
        sqLiteDatabase.execSQL("insert into administrador (cedula,nombre) values (121760184,'Arianna Araujo')");
        sqLiteDatabase.execSQL("insert into administrador (cedula,nombre) values (109230952,'Rafael Araujo')");
        sqLiteDatabase.execSQL("insert into administrador (cedula,nombre) values (401660592,'Patricia Alfaro')");
        sqLiteDatabase.execSQL("insert into administrador (cedula,nombre) values (202740538,'Orlando Alfaro')");
        sqLiteDatabase.execSQL("insert into administrador (cedula,nombre) values (401770749,'Josue Alfaro')");
        sqLiteDatabase.execSQL("insert into administrador (cedula,nombre) values (401600748,'Andrea Alfaro')");
        sqLiteDatabase.execSQL("insert into administrador (cedula,nombre) values (108930353,'Alejandro Gamboa')");


        //CLIENTES----------------------------------------------------------------------------------
        sqLiteDatabase.execSQL("insert into cliente (cedula, nombre, salario, telefono, Fecha_nacimiento, estado_civil, direccion) values (118480995," +
                "'Esteban Salas', 100000, '87647291','21/07/2002', 'soltero', '25mts Sur de la Iglesia Católica')");
        sqLiteDatabase.execSQL("insert into cliente (cedula, nombre, salario, telefono, Fecha_nacimiento, estado_civil, direccion) values (109420410," +
                "'Fabian Salas',  200000, '88614031', '25/09/1975', 'soltero', 'Frente a la cancha de San Bartolomé')");
        sqLiteDatabase.execSQL("insert into cliente (cedula, nombre, salario, telefono, Fecha_nacimiento, estado_civil, direccion) values (108610762," +
                "'Randall Salas', 300000, '88614032','07/09/1973', 'casado', 'Frente a la cancha de San Bartolomé')");
        sqLiteDatabase.execSQL("insert into cliente (cedula, nombre, salario, telefono, Fecha_nacimiento, estado_civil, direccion) values (110370409," +
                "'Paul Salas',  400000, '88614033', '04/06/1979', 'casado', '100mts Norte y 50mts Este del Papa Johns')");
        sqLiteDatabase.execSQL("insert into cliente (cedula, nombre, salario, telefono, Fecha_nacimiento, estado_civil, direccion) values (107270537," +
                "'Walter Salas', 500000, '88614034','12/08/1968', 'casado', '25mts Norte del Ebais')");
        sqLiteDatabase.execSQL("insert into cliente (cedula, nombre, salario, telefono, Fecha_nacimiento, estado_civil, direccion) values (117190303," +
                "'Luis Salas',  600000, '88614035', '27/08/1998', 'soltero', '25mts Norte del Ebais')");
        sqLiteDatabase.execSQL("insert into cliente (cedula, nombre, salario, telefono, Fecha_nacimiento, estado_civil, direccion) values (400950690," +
                "'Zoila Molina', 700000, '88614036','19/03/1948', 'casado', '25mts Este del Ebais')");
        sqLiteDatabase.execSQL("insert into cliente (cedula, nombre, salario, telefono, Fecha_nacimiento, estado_civil, direccion) values (400880827," +
                "'Walter Enrique Salas',  800000, '88614037', '11/06/1945', 'casado', '25mts del Ebais')");
        sqLiteDatabase.execSQL("insert into cliente (cedula, nombre, salario, telefono, Fecha_nacimiento, estado_civil, direccion) values (118860882," +
                "'Juan Pablo Salas', 900000, '88614038','30/09/2003', 'soltero', '25mts Norte del Ebais')");
        sqLiteDatabase.execSQL("insert into cliente (cedula, nombre, salario, telefono, Fecha_nacimiento, estado_civil, direccion) values (108570894," +
                "'Diana Fonseca',  1000000, '88614039', '17/08/1973', 'casado', '25mts Norte del Ebais')");


        //AHORROS----------------------------------------------------------------------------------
        sqLiteDatabase.execSQL("insert into ahorro (cedula_cliente, tipo_ahorro, monto_ahorrado, cuota) values (118480995, 'Navideño', 5000, 5000)");
        sqLiteDatabase.execSQL("insert into ahorro (cedula_cliente, tipo_ahorro, monto_ahorrado, cuota) values (118480995, 'Escolar', 10000, 10000)");
        sqLiteDatabase.execSQL("insert into ahorro (cedula_cliente, tipo_ahorro, monto_ahorrado, cuota) values (109420410, 'Marchamo', 15000, 15000)");
        sqLiteDatabase.execSQL("insert into ahorro (cedula_cliente, tipo_ahorro, monto_ahorrado, cuota) values (108610762, 'Extraordinario', 20000, 20000)");
        sqLiteDatabase.execSQL("insert into ahorro (cedula_cliente, tipo_ahorro, monto_ahorrado, cuota) values (108610762, 'Navideño', 25000, 25000)");
        sqLiteDatabase.execSQL("insert into ahorro (cedula_cliente, tipo_ahorro, monto_ahorrado, cuota) values (110370409, 'Escolar', 30000, 30000)");
        sqLiteDatabase.execSQL("insert into ahorro (cedula_cliente, tipo_ahorro, monto_ahorrado, cuota) values (110370409, 'Marchamo', 35000, 35000)");
        sqLiteDatabase.execSQL("insert into ahorro (cedula_cliente, tipo_ahorro, monto_ahorrado, cuota) values (110370409, 'Extraordinario', 40000, 40000)");
        sqLiteDatabase.execSQL("insert into ahorro (cedula_cliente, tipo_ahorro, monto_ahorrado, cuota) values (107270537, 'Navideño', 45000, 45000)");
        sqLiteDatabase.execSQL("insert into ahorro (cedula_cliente, tipo_ahorro, monto_ahorrado, cuota) values (107270537, 'Escolar', 50000, 50000)");
        sqLiteDatabase.execSQL("insert into ahorro (cedula_cliente, tipo_ahorro, monto_ahorrado, cuota) values (117190303, 'Marchamo', 55000, 55000)");
        sqLiteDatabase.execSQL("insert into ahorro (cedula_cliente, tipo_ahorro, monto_ahorrado, cuota) values (117190303, 'Extraordinario', 60000, 60000)");
        sqLiteDatabase.execSQL("insert into ahorro (cedula_cliente, tipo_ahorro, monto_ahorrado, cuota) values (117190303, 'Navideño', 65000, 65000)");
        sqLiteDatabase.execSQL("insert into ahorro(cedula_cliente, tipo_ahorro, monto_ahorrado, cuota) values(400950690, 'Escolar', 70000, 70000)");
        sqLiteDatabase.execSQL("insert into ahorro(cedula_cliente, tipo_ahorro, monto_ahorrado, cuota) values(400950690, 'Marchamo', 75000, 75000)");
        sqLiteDatabase.execSQL("insert into ahorro(cedula_cliente, tipo_ahorro, monto_ahorrado, cuota) values(400950690, 'Extraordinario', 80000, 80000)");
        sqLiteDatabase.execSQL("insert into ahorro(cedula_cliente, tipo_ahorro, monto_ahorrado, cuota) values(400880827, 'Navideño', 85000, 85000)");
        sqLiteDatabase.execSQL("insert into ahorro(cedula_cliente, tipo_ahorro, monto_ahorrado, cuota) values(400880827, 'Escolar', 90000, 90000)");
        sqLiteDatabase.execSQL("insert into ahorro(cedula_cliente, tipo_ahorro, monto_ahorrado, cuota) values(118860882, 'Marchamo', 95000, 95000)");
        sqLiteDatabase.execSQL("insert into ahorro(cedula_cliente, tipo_ahorro, monto_ahorrado, cuota) values(118860882, 'Extraordinario', 100000, 100000)");
    }
}
