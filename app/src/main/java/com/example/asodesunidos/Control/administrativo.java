package com.example.asodesunidos.Control;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.asodesunidos.R;

public class administrativo extends AppCompatActivity {

    Button agregarClienteBtn, asignarPrestamoBtn, logoutAdmin;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administrativo);

        setSupportActionBar(findViewById(R.id.my_toolbar));

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);

        agregarClienteBtn = findViewById(R.id.ver_prestamos_btn);
        asignarPrestamoBtn = findViewById(R.id.gestionar_ahorros_btn);
        logoutAdmin=findViewById(R.id.logout_btn);

        agregarClienteBtn.setOnClickListener(view -> openActivityAgregarCliente());

        asignarPrestamoBtn.setOnClickListener(view -> openActivityAsignarPrestamo());

        logoutAdmin.setOnClickListener(View->finish());

    }

    private void openActivityAgregarCliente(){
        Intent intent = new Intent(this, agregarCliente.class);
        startActivity(intent);
    }

    private void openActivityAsignarPrestamo(){
        Intent intent = new Intent(this, asignarPrestamos.class);
        startActivity(intent);
    }
}