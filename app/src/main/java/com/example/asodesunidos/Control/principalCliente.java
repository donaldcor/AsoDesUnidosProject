package com.example.asodesunidos.Control;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.asodesunidos.Control.administrativo;
import com.example.asodesunidos.Control.calcularCuota;
import com.example.asodesunidos.Modelos.cliente;
import com.example.asodesunidos.R;
import com.example.asodesunidos.SessionManagement;

public class principalCliente extends AppCompatActivity {

    Button verPrestamos, gestionarAhorros, calculaCuota, infoPersonal;
    Button logout;
    Bundle bundle;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal_cliente);

        setSupportActionBar(findViewById(R.id.my_toolbar));

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);

        verPrestamos = findViewById(R.id.ver_prestamos_btn);
        gestionarAhorros = findViewById(R.id.gestionar_ahorros_btn);
        calculaCuota = findViewById(R.id.calcular_cuota_btn);
        infoPersonal = findViewById(R.id.informacion_personal_btn);
        logout = findViewById(R.id.log_out_btn);

        verPrestamos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivityVerPrestamos();
            }
        });

        gestionarAhorros.setOnClickListener(view -> openActivityGestionarAhorros());

        calculaCuota.setOnClickListener(view -> openActivityCalcularCuota());

        infoPersonal.setOnClickListener(view -> openActivityInformacionPersonal());

        logout.setOnClickListener(view -> logout());
    }

    private void openActivityVerPrestamos(){
        Intent intent = new Intent(this, misPrestamos.class);
        intent.putExtra("CLIENTE", clienteLogueado());
        startActivity(intent);
    }

    private void openActivityGestionarAhorros(){
        Intent intent = new Intent(this, gestionAhorros.class);
        intent.putExtra("CLIENTE", clienteLogueado());
        startActivity(intent);
    }

    private void openActivityCalcularCuota(){
        Intent intent = new Intent(this, calcularCuota.class);
        startActivity(intent);
    }

    private void openActivityInformacionPersonal(){
        Intent intent = new Intent(this, informacionPersonal.class);
        intent.putExtra("CLIENTE", clienteLogueado());
        startActivity(intent);
    }

    private void logout(){
        SessionManagement sessionManagement = new SessionManagement(principalCliente.this);
        sessionManagement.closeSession();
        finish();
    }

    private cliente clienteLogueado(){
        bundle = getIntent().getExtras();
        cliente cli = (cliente) bundle.getSerializable("CLIENTE");

        return cli;
    }
}