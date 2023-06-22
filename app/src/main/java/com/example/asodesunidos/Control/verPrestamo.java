package com.example.asodesunidos.Control;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.asodesunidos.DAO.DAO;
import com.example.asodesunidos.Modelos.cliente;
import com.example.asodesunidos.Modelos.prestamo;
import com.example.asodesunidos.R;
import com.example.asodesunidos.SQLiteDB.SQLiteOpenHelperManager;

public class verPrestamo extends AppCompatActivity {

    EditText txtTipoCredito, txtMonto, txtCuota, txtMontoPagado;
    Button btnPagar, regresar;

    prestamo pres;
    int id = 0;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_prestamo);
        setSupportActionBar(findViewById(R.id.my_toolbar));

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);

        txtTipoCredito = findViewById(R.id.txtTipoCredito);
        txtMonto = findViewById(R.id.txtMonto);
        txtCuota = findViewById(R.id.txtCuota);
        btnPagar = findViewById(R.id.btnPagar);
        txtMontoPagado = findViewById(R.id.txtMontoPagado);
        regresar = findViewById(R.id.regresar_ver_prest_btn);

        prestamo p = null;
        cliente cli = new cliente();
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                id = Integer.parseInt(null);
            } else {
                //Min 12:20
                p = (prestamo) extras.getSerializable("PRESTAMO");
                id = p.getCedula();
                cli.setCedula(id);
            }
        } else {
            id = ((prestamo) savedInstanceState.getSerializable("PRESTAMO")).getCedula();
        }
        //   pres= DAO.verPrestamo(this,cli);
        if (p != null) {
            txtCuota.setText(String.valueOf(p.getMontoCuota()));
            txtMonto.setText(String.valueOf(p.getMontoPrestamo()));
            txtMontoPagado.setText(String.valueOf(p.getMontoPagado()));
            txtTipoCredito.setText(p.getTipoPrestamo());
            prestamo finalP = p;
            btnPagar.setOnClickListener(View1 ->CalculoPago(finalP));
        }

        regresar.setOnClickListener(view -> finish());
    }


    private void CalculoPago(prestamo p) {
        Integer cuota = Integer.parseInt(txtCuota.getText().toString());
        Integer calculo = p.getMontoPagado()+cuota;
        p.setMontoPagado(calculo);
        txtMontoPagado.setText(String.valueOf(calculo));
        DAO.modificarMontoPagado(this,p);
    }
}