package com.example.asodesunidos.Control;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asodesunidos.DAO.DAO;
import com.example.asodesunidos.Modelos.ahorro;
import com.example.asodesunidos.Modelos.cliente;
import com.example.asodesunidos.R;
import com.example.asodesunidos.SQLiteDB.SQLiteOpenHelperManager;
import com.google.android.material.switchmaterial.SwitchMaterial;

import java.util.List;
import java.util.Objects;


public class gestionAhorros extends AppCompatActivity {

    private Switch s1, s2, s3, s4;
    private TextView tv_Navidennio, tv_Escolar, tv_Marchamo, tv_Extra;
    private EditText etNavidennio, etEscolar, etMarchamo, etExtra;
    private Button btn_Navidennio, btn_Escolar, btn_Marchamo, btn_Extra, regresar;
    private List<ahorro> ahorrosCliente;

    private cliente cli;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestion_ahorros);

        setSupportActionBar(findViewById(R.id.my_toolbar));

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);
        Bundle bundle = getIntent().getExtras();
        cli = (cliente) bundle.getSerializable("CLIENTE");
        ahorrosCliente = DAO.retornaAhorros(this, cli.getCedula());
        initComponents();
        init();
        Listeners();
        regresar.setOnClickListener(view -> finish());
    }

    @Override
    protected void onStart() {
        super.onStart();
        try {
            SQLiteOpenHelperManager manager = new SQLiteOpenHelperManager(this);
            SQLiteDatabase baseDatos = manager.getWritableDatabase();
        } catch (Exception errorMessage) {
            Toast.makeText(this, errorMessage.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public void init() {
        muestraAhorrosExistentes();
    }

    public void initComponents() {
        //ASIGNA LOS TEXTVIEW
        tv_Navidennio = findViewById(R.id.tv_navidennio);
        tv_Escolar = findViewById(R.id.tv_escolar);
        tv_Marchamo = findViewById(R.id.tv_marchamo);
        tv_Extra = findViewById(R.id.tv_extra);

        //ASIGNA LOS EDITEXT
        etNavidennio = findViewById(R.id.et_navidennio);
        etEscolar = findViewById(R.id.et_escolar);
        etMarchamo = findViewById(R.id.et_marchamo);
        etExtra = findViewById(R.id.et_extra);

        //ASIGNA LOS SWITCHES
        s1 = findViewById(R.id.switchAhorroNavidennio);
        s2 = findViewById(R.id.switchAhorroEscolar);
        s3 = findViewById(R.id.switchAhorroMarchamo);
        s4 = findViewById(R.id.switchAhorroExtra);

        //ASIGNA LOS BOTONES
        btn_Navidennio = findViewById(R.id.btn_Ahorro_Navidennio);
        btn_Escolar = findViewById(R.id.btn_Ahorro_Escolar);
        btn_Marchamo = findViewById(R.id.btn_Ahorro_Marchamo);
        btn_Extra = findViewById(R.id.btn_Ahorro_Extra);
        regresar = findViewById(R.id.regresar_gest_ahorr_btn);
    }

    public void Listeners() {

        //Switches listerners
        switchNavidennio();
        switchEscolar();
        switchMarchamo();
        switchExtra();

        //Buttons listerners

    }

    @SuppressLint("SetTextI18n")
    public void switchNavidennio() {

        s1.setOnCheckedChangeListener((compoundButton, isChecked) -> {
            if (isChecked) {
                findViewById(R.id.et_navidennio).setEnabled(true);
                etNavidennio.setHintTextColor(Color.parseColor("#33FF33"));
                btn_Navidennio.setVisibility(View.VISIBLE);
                etNavidennio.setFocusableInTouchMode(true);

            } else {
                findViewById(R.id.et_navidennio).setEnabled(false);
                etNavidennio.setHintTextColor(Color.parseColor("#83101112"));
                btn_Navidennio.setVisibility(View.INVISIBLE);
                etNavidennio.setError(null);
            }
        });
    }

    @SuppressLint("SetTextI18n")
    public void switchEscolar() {

        s2.setOnCheckedChangeListener((compoundButton, isChecked) -> {
            if (isChecked) {
                findViewById(R.id.et_escolar).setEnabled(true);
                etEscolar.setHintTextColor(Color.parseColor("#33FF33"));
                btn_Escolar.setVisibility(View.VISIBLE);
                etEscolar.setFocusableInTouchMode(true);
            } else {
                findViewById(R.id.et_escolar).setEnabled(false);
                etEscolar.setHintTextColor(Color.parseColor("#83101112"));
                btn_Escolar.setVisibility(View.INVISIBLE);
                etEscolar.setError(null);
            }
        });
    }

    @SuppressLint("SetTextI18n")
    public void switchMarchamo() {

        s3.setOnCheckedChangeListener((compoundButton, isChecked) -> {
            if (isChecked) {
                findViewById(R.id.et_marchamo).setEnabled(true);
                etMarchamo.setHintTextColor(Color.parseColor("#33FF33"));
                btn_Marchamo.setVisibility(View.VISIBLE);
                etMarchamo.setFocusableInTouchMode(true);
            } else {
                findViewById(R.id.et_marchamo).setEnabled(false);
                etMarchamo.setHintTextColor(Color.parseColor("#83101112"));
                btn_Marchamo.setVisibility(View.INVISIBLE);
                etMarchamo.setError(null);
            }
        });
    }

    @SuppressLint("SetTextI18n")
    public void switchExtra() {

        s4.setOnCheckedChangeListener((compoundButton, isChecked) -> {
            if (isChecked) {
                findViewById(R.id.et_extra).setEnabled(true);
                etExtra.setHintTextColor(Color.parseColor("#33FF33"));
                btn_Extra.setVisibility(View.VISIBLE);
                etExtra.setFocusableInTouchMode(true);
            } else {
                findViewById(R.id.et_extra).setEnabled(false);
                etExtra.setHintTextColor(Color.parseColor("#83101112"));
                btn_Extra.setVisibility(View.INVISIBLE);
                etExtra.setError(null);
            }
        });
    }


    public void guardarAhorroNavidennio(View view) {
        try {
            if (!etNavidennio.getText().toString().isEmpty()) {
                if (Integer.parseInt(String.valueOf(etNavidennio.getText())) >= 5000) {
                    int montoAhorrado = Integer.parseInt(etNavidennio.getText().toString());
                    int cuota = Integer.parseInt(etNavidennio.getText().toString());
                    if (!exiteAhorro(tv_Navidennio.getText().toString())) {

                        ahorro a1 = new ahorro(cli.getCedula(), "Navideño", montoAhorrado, cuota);
                        DAO.insertarAhorro(this, a1);
                        ahorrosCliente = DAO.retornaAhorros(this, cli.getCedula());
                        Toast.makeText(this, "Ahorro Ingresado Con Éxito!", Toast.LENGTH_LONG).show();
                    } else {
                        ahorro a1 = new ahorro(cli.getCedula(), "Navideño", montoAhorrado, cuota);
                        DAO.modificarAhorro(this, a1);
                        Toast.makeText(this, "Ahorro Actualizado Con Éxito!", Toast.LENGTH_LONG).show();
                    }

                } else {
                    throw new Exception("Monto Invalido");
                }

            } else {
                throw new Exception("Empty");
            }
        } catch (Exception e) {
            if (Objects.equals(e.getMessage(), "Empty")) {
                etNavidennio.setError("Ingrese La Cuota");
                Toast.makeText(this, "Debe ingresar la cuota!", Toast.LENGTH_LONG).show();
            } else {
                etNavidennio.setError("La cuota debe ser mayor a 5mil");
                Toast.makeText(this, "La cuota debe ser mayor a 5mil!", Toast.LENGTH_LONG).show();
            }
        }
    }

    public void guardarAhorroEscolar(View view) {
        try {
            if (!etEscolar.getText().toString().isEmpty()) {
                if (Integer.parseInt(String.valueOf(etEscolar.getText())) >= 5000) {

                    int montoAhorrado = Integer.parseInt(etEscolar.getText().toString());
                    int cuota = Integer.parseInt(etEscolar.getText().toString());
                    if (!exiteAhorro(tv_Escolar.getText().toString())) {
                        ahorro a1 = new ahorro(cli.getCedula(), "Escolar", montoAhorrado, cuota);
                        DAO.insertarAhorro(this, a1);
                        ahorrosCliente = DAO.retornaAhorros(this, cli.getCedula());
                        Toast.makeText(this, "Ahorro Ingresado Con Éxito!", Toast.LENGTH_LONG).show();
                    } else {
                        ahorro a1 = new ahorro(cli.getCedula(), "Escolar", montoAhorrado, cuota);
                        DAO.modificarAhorro(this, a1);
                        Toast.makeText(this, "Ahorro Actualizado Con Éxito!", Toast.LENGTH_LONG).show();
                    }

                } else {
                    throw new Exception("Monto Invalido");
                }

            } else {
                throw new Exception("Empty");
            }
        } catch (Exception e) {
            if (Objects.equals(e.getMessage(), "Empty")) {
                etEscolar.setError("Ingrese La Cuota");
                Toast.makeText(this, "Debe ingresar la cuota!", Toast.LENGTH_LONG).show();
            } else {
                etEscolar.setError("La cuota debe ser mayor a 5mil");
                Toast.makeText(this, "La cuota debe ser mayor a 5mil!", Toast.LENGTH_LONG).show();
            }
        }

    }

    public void guardarAhorroMarchamo(View view) {
        try {
            if (!etMarchamo.getText().toString().isEmpty()) {
                if (Integer.parseInt(String.valueOf(etMarchamo.getText())) >= 5000) {
                    int montoAhorrado = Integer.parseInt(etMarchamo.getText().toString());
                    int cuota = Integer.parseInt(etMarchamo.getText().toString());

                    if (!exiteAhorro(tv_Marchamo.getText().toString())) {
                        ahorro a1 = new ahorro(cli.getCedula(), "Marchamo", montoAhorrado, cuota);
                        DAO.insertarAhorro(this, a1);
                        ahorrosCliente = DAO.retornaAhorros(this, cli.getCedula());
                        Toast.makeText(this, "Ahorro Ingresado Con Éxito!", Toast.LENGTH_LONG).show();
                    } else {
                        ahorro a1 = new ahorro(cli.getCedula(), "Marchamo", montoAhorrado, cuota);
                        DAO.modificarAhorro(this, a1);
                        Toast.makeText(this, "Ahorro Actualizado Con Éxito!", Toast.LENGTH_LONG).show();
                    }
                } else {
                    throw new Exception("Monto Invalido");
                }

            } else {
                throw new Exception("Empty");
            }
        } catch (Exception e) {
            if (Objects.equals(e.getMessage(), "Empty")) {
                etMarchamo.setError("Ingrese La Cuota");
                Toast.makeText(this, "Debe ingresar la cuota!", Toast.LENGTH_LONG).show();
            } else {
                etMarchamo.setError("La cuota debe ser mayor a 5mil");
                Toast.makeText(this, "La cuota debe ser mayor a 5mil!", Toast.LENGTH_LONG).show();
            }
        }
    }

    public void guardarAhorroExtra(View view) {
        try {
            if (!etExtra.getText().toString().isEmpty()) {
                if (Integer.parseInt(String.valueOf(etExtra.getText())) >= 5000) {
                    int montoAhorrado = Integer.parseInt(etExtra.getText().toString());
                    int cuota = Integer.parseInt(etExtra.getText().toString());

                    if (!exiteAhorro(tv_Extra.getText().toString())) {
                        ahorro a1 = new ahorro(cli.getCedula(), "Extra", montoAhorrado, cuota);
                        DAO.insertarAhorro(this, a1);
                        ahorrosCliente = DAO.retornaAhorros(this, cli.getCedula());
                        Toast.makeText(this, "Ahorro Ingresado Con Éxito!", Toast.LENGTH_LONG).show();
                    } else {
                        ahorro a1 = new ahorro(cli.getCedula(), "Extra", montoAhorrado, cuota);
                        DAO.modificarAhorro(this, a1);
                        Toast.makeText(this, "Ahorro Actualizado Con Éxito!", Toast.LENGTH_LONG).show();
                    }

                } else {
                    throw new Exception("Monto Invalido");
                }

            } else {
                throw new Exception("Empty");
            }
        } catch (Exception e) {
            if (Objects.equals(e.getMessage(), "Empty")) {
                etExtra.setError("Ingrese La Cuota");
                Toast.makeText(this, "Debe ingresar la cuota!", Toast.LENGTH_LONG).show();
            } else {
                etExtra.setError("La cuota debe ser mayor a 5mil");
                Toast.makeText(this, "La cuota debe ser mayor a 5mil!", Toast.LENGTH_LONG).show();
            }
        }
    }

    public void muestraAhorrosExistentes() {
        if (ahorrosCliente != null) {

            for (ahorro a1 : ahorrosCliente) {
                switch (a1.getTipoAhorro()) {
                    case "Navideño":
                        etNavidennio.setText(String.valueOf(a1.getCuota()));
                        etNavidennio.setEnabled(true);
                        etNavidennio.setFocusable(false);
                        s1.setChecked(true);
                        btn_Navidennio.setVisibility(View.INVISIBLE);
                        break;
                    case "Escolar":
                        etEscolar.setText(String.valueOf(a1.getCuota()));
                        etEscolar.setEnabled(true);
                        etEscolar.setFocusable(false);
                        s2.setChecked(true);
                        btn_Escolar.setVisibility(View.INVISIBLE);
                        break;
                    case "Marchamo":
                        etMarchamo.setText(String.valueOf(a1.getCuota()));
                        etMarchamo.setEnabled(true);
                        etMarchamo.setFocusable(false);
                        s3.setChecked(true);
                        btn_Marchamo.setVisibility(View.INVISIBLE);
                        break;
                    case "Extra":
                        etExtra.setText(String.valueOf(a1.getCuota()));
                        etExtra.setEnabled(true);
                        etExtra.setFocusable(false);
                        s4.setChecked(true);
                        btn_Extra.setVisibility(View.INVISIBLE);
                        break;
                }
            }
        }
    }

    public boolean exiteAhorro(String tipoAhorro) {
        if (ahorrosCliente != null) {
            for (ahorro a1 : ahorrosCliente) {
                if (a1.getTipoAhorro().equals(tipoAhorro)) {
                    return true;
                }
            }
        }
        return false;
    }


}