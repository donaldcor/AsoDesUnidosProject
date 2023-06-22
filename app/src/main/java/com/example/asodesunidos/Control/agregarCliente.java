package com.example.asodesunidos.Control;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.asodesunidos.DAO.DAO;
import com.example.asodesunidos.Modelos.cliente;
import com.example.asodesunidos.Modelos.usuario;
import com.example.asodesunidos.R;

public class agregarCliente extends AppCompatActivity {

    EditText cedula, contrasena, nombre, salario, telefono, fechaNacimiento, estadoCivil, direccion;
    Button crearBtn, regresar;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_cliente);

        setSupportActionBar(findViewById(R.id.my_toolbar));

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);

        crearBtn = findViewById(R.id.consultar_inf_cliente_btn);
        cedula = findViewById(R.id.cedula_ET);
        contrasena = findViewById(R.id.contrasena_ET);
        nombre = findViewById(R.id.nombre_ET);
        salario = findViewById(R.id.salario_ET);
        telefono = findViewById(R.id.telefono_ET);
        fechaNacimiento = findViewById(R.id.fecha_nac_ET);
        estadoCivil = findViewById(R.id.estado_civil_ET);
        direccion = findViewById(R.id.direccion_ET);
        regresar = findViewById(R.id.regresar_btn);


        crearBtn.setOnClickListener(view -> agregarCliente());
        regresar.setOnClickListener(view -> finish());

    }

    private void agregarCliente(){
        String nom, tel, fecha, estado, dir, contra;
        Integer ced, sal;
        cliente cli;
        usuario usu;

        if (cedula.getText().toString().isEmpty()) {
            cedula.setError("Ingrese una cédula");
        }
        if (contrasena.getText().toString().isEmpty()) {
            contrasena.setError("Ingrese una contraseña");
        }
        if (nombre.getText().toString().isEmpty()) {
            nombre.setError("Ingrese una contraseña");
        }
        if (salario.getText().toString().isEmpty()) {
            salario.setError("Ingrese una contraseña");
        }
        if (telefono.getText().toString().isEmpty()) {
            telefono.setError("Ingrese una contraseña");
        }
        if (fechaNacimiento.getText().toString().isEmpty()) {
            fechaNacimiento.setError("Ingrese una contraseña");
        }
        if (estadoCivil.getText().toString().isEmpty()) {
            estadoCivil.setError("Ingrese una contraseña");
        }
        if (direccion.getText().toString().isEmpty()) {
            direccion.setError("Ingrese una contraseña");
        }

        if(!cedula.getText().toString().isEmpty() && !contrasena.getText().toString().isEmpty() && !nombre.getText().toString().isEmpty()
                && !salario.getText().toString().isEmpty() && !telefono.getText().toString().isEmpty() && !fechaNacimiento.getText().toString().isEmpty()
                &&!estadoCivil.getText().toString().isEmpty() && !direccion.getText().toString().isEmpty()) {
            ced = Integer.parseInt(cedula.getText().toString());
            contra = contrasena.getText().toString();
            nom = nombre.getText().toString();
            sal = Integer.parseInt(salario.getText().toString());
            tel = telefono.getText().toString();
            fecha = fechaNacimiento.getText().toString();
            estado = estadoCivil.getText().toString();
            dir = direccion.getText().toString();

            if (!DAO.retornarUsuarioBool(this, ced)) {

                cli = new cliente(ced, nom, sal, tel, fecha, estado, dir);
                usu = new usuario(ced, nom, contra, "cliente");

                DAO.insertarCliente(this, cli);
                DAO.insertarUsuario(this, usu);
                Toast.makeText(this, "Cliente creado correctamente.", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Este cliente ya existe", Toast.LENGTH_SHORT).show();
            }
        }
    }
}