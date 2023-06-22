package com.example.asodesunidos.Control;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.asodesunidos.R;

public class calcularCuota extends AppCompatActivity {

    EditText cuotaPagar, montoSolicitado;
    Button calculaCuota, regresar;

    private String [] opciones = {"Hipotecario", "Educación", "Personal", "Viajes"};
    private String [] opciones2 = {"3 años", "5 años", "10 años"};
    private Spinner plazoPrestamo;
    private Spinner tipoPrestamo;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calcular_cuota);

        setSupportActionBar(findViewById(R.id.my_toolbar));

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);

        montoSolicitado = findViewById(R.id.monto_solicitado_calcula_ET);
        cuotaPagar = findViewById(R.id.monto_pagar_calcula_ET);
        calculaCuota = findViewById(R.id.calcular_cuota_btn);
        regresar = findViewById(R.id.regresar_calc_cuota_btn);
        initSpinners();

        calculaCuota.setOnClickListener(view -> calcularCuota());
        regresar.setOnClickListener(view -> finish());

    }

    private void initSpinners(){
        tipoPrestamo = findViewById(R.id.tipoPrestamo_spn);
        ArrayAdapter<String> adaptador1 = new ArrayAdapter<String>(this, R.layout.diseno_spinner, opciones);
        tipoPrestamo.setAdapter(adaptador1);

        plazoPrestamo = findViewById(R.id.plazoPrestamo_spn);
        ArrayAdapter<String> adaptador2 = new ArrayAdapter<String>(this, R.layout.diseno_spinner, opciones2);
        plazoPrestamo.setAdapter(adaptador2);
    }

    private double obtenerTipoPrestamo(){
        String item = (String) tipoPrestamo.getSelectedItem();
        double porcentaje = 0.0;
        switch (item){
            case "Hipotecario":
                porcentaje=7.5;
                break;
            case "Educación":
                porcentaje= 8.0;
                break;
            case "Personal":
                porcentaje= 10.0;
                break;
            case "Viajes":
                porcentaje= 12.0;
                break;
            default:
                break;
        }
        return porcentaje;
    }

    public Integer obtenerPlazoPrestamo(){
        String plazo = (String) plazoPrestamo.getSelectedItem();
        int plazoMeses = 0;
        switch(plazo){
            case "3 años":
                plazoMeses = 3*12;
                break;
            case "5 años":
                plazoMeses = 5*12;
                break;
            case "10 años":
                plazoMeses = 10*12;
                break;
            default:
                break;
        }
        return plazoMeses;
    }

    public void calcularCuota(){
        if(!montoSolicitado.getText().toString().isEmpty()) {
            cuotaPagar.setText("");
            double tipoPrestamo = obtenerTipoPrestamo();
            Integer plazo = obtenerPlazoPrestamo();
            Integer montoSolicitud = Integer.parseInt(montoSolicitado.getText().toString());
            Integer cuota = Math.toIntExact(Math.round((montoSolicitud * (1 + (tipoPrestamo / 100))) / plazo));
            cuotaPagar.setText("");
            cuotaPagar.setText("¢" + String.valueOf(cuota));
        }else{
            cuotaPagar.setText("");
            montoSolicitado.setError("Digite un monto para calcular la cuota");
        }
    }
}