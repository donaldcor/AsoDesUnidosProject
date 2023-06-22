package com.example.asodesunidos.Control;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.asodesunidos.DAO.DAO;
import com.example.asodesunidos.Modelos.cliente;
import com.example.asodesunidos.Modelos.prestamo;
import com.example.asodesunidos.R;

public class asignarPrestamos extends AppCompatActivity {

    EditText cedula, nombre, salario, cuotaPagar, montoSolicitado;
    Button consulta, asignar, revisarMonto, regresar;
    private String [] opciones = {"Hipotecario", "Educación", "Personal", "Viajes"};
    private String [] opciones2 = {"3 años", "5 años", "10 años"};
    private Spinner plazoPrestamo;
    private Spinner tipoPrestamo;
    private cliente cliente = new cliente();
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asignar_prestamos);

        setSupportActionBar(findViewById(R.id.my_toolbar));

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);

        cedula = findViewById(R.id.consultaCedula_ET);
        salario = findViewById(R.id.consultaSalario_ET);
        nombre = findViewById(R.id.consultaNombre_ET);
        cuotaPagar = findViewById(R.id.monto_pagar_calcula_ET);
        consulta = findViewById(R.id.consultar_btn);
        montoSolicitado = findViewById(R.id.monto_solicitado_calcula_ET);
        asignar = findViewById(R.id.asignar_prestamo_adm_btn);
        revisarMonto = findViewById(R.id.calcular_cuota_btn);
        regresar = findViewById(R.id.regresar_asig_prest_btn);
        asignar.setEnabled(false);
        initSpinners();

        consulta.setOnClickListener(view -> consultarCliente());
        revisarMonto.setOnClickListener(view -> calcularMonto());
        asignar.setOnClickListener(view -> asignarPrestamo());
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

    private void consultarCliente(){
        if(!cedula.getText().toString().isEmpty()) {
            Integer ced = Integer.parseInt(cedula.getText().toString());
            cliente = DAO.retornarClienteCed(this, ced);
            nombre.setText(cliente.getNombre());
            salario.setText(String.valueOf(cliente.getSalario()));
        }else{
            cedula.setError("Digite la cédula del cliente");
        }
    }

    public void calcularMonto(){
        if(!montoSolicitado.getText().toString().isEmpty()) {
            Integer sal = Integer.parseInt(salario.getText().toString());
            double tipoPrestamo = obtenerTipoPrestamo();
            Integer plazo = obtenerPlazoPrestamo();
            Integer montoSolicitud = Integer.parseInt(montoSolicitado.getText().toString());

            Integer cuota = Math.toIntExact(Math.round((montoSolicitud * (1 + (tipoPrestamo / 100))) / plazo));
            cuotaPagar.setText("");
            if (cuota <= (sal * 0.45)) {
                cuotaPagar.setText(String.valueOf(cuota));
                asignar.setEnabled(true);
            } else {
                cuotaPagar.setError("La cuota es muy alta para su salario, ingrese una cantidad menor o un plazo mayor");
                Toast.makeText(this, "La cuota es muy alta para su salario, ingrese una cantidad menor o un plazo mayor", Toast.LENGTH_SHORT).show();
                asignar.setEnabled(false);
            }
        }else{
            montoSolicitado.setError("Ingrese el monto que desea solicitar");
        }
    }

    private void asignarPrestamo(){
        prestamo p;
        Integer ced = Integer.parseInt(cedula.getText().toString());
        String tipoPrest = (String) tipoPrestamo.getSelectedItem();
        Integer montoPrest = Math.round(Integer.parseInt(montoSolicitado.getText().toString()));
        String plazo = (String) plazoPrestamo.getSelectedItem();
        Integer cantidadCuotas = obtenerPlazoPrestamo();
        Integer montoCuota = Integer.parseInt(cuotaPagar.getText().toString());

        p = new prestamo(ced, tipoPrest, montoPrest,0, plazo, cantidadCuotas, montoCuota);
        DAO.insertarPrestamo(this, p);
        Toast.makeText(this, "Prestamo asignado correctamente", Toast.LENGTH_SHORT).show();
        cedula.setText("");
        nombre.setText("");
        salario.setText("");
        montoSolicitado.setText("");
        cuotaPagar.setText("");
    }
}