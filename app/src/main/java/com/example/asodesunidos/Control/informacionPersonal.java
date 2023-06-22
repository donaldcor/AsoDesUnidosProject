package com.example.asodesunidos.Control;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.asodesunidos.DAO.DAO;
import com.example.asodesunidos.Modelos.cliente;
import com.example.asodesunidos.R;
import com.example.asodesunidos.SQLiteDB.SQLiteOpenHelperManager;

public class informacionPersonal extends AppCompatActivity {

    EditText cedula, nombre, salario, telefono, fechaNacimiento, estadoCivil, direccion;
    Button btnConsultar;
    Button btnModificar, regresar;
    Bundle bundle;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacion_personal);

        setSupportActionBar(findViewById(R.id.my_toolbar));

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);

        cedula = findViewById(R.id.cedula_ET);
        nombre = findViewById(R.id.nombre_ET);
        salario = findViewById(R.id.salario_ET);
        telefono = findViewById(R.id.telefono_ET);
        fechaNacimiento = findViewById(R.id.fecha_nac_ET);
        estadoCivil = findViewById(R.id.estado_civil_ET);
        direccion = findViewById(R.id.direccion_ET);
        btnConsultar = findViewById(R.id.consultar_inf_cliente_btn);
        btnModificar = findViewById(R.id.modificar_inf_cliente_btn);
        regresar = findViewById(R.id.regresar_info_pers_btn);

        regresar.setOnClickListener(view -> finish());

    }

    @Override
    protected void onStart() {
        super.onStart();
        SQLiteOpenHelperManager manager = new SQLiteOpenHelperManager(this);
        SQLiteDatabase baseDatos = manager.getWritableDatabase();
    }

    public void consultarCliente(View vista) {
        //Integer ced, sal;
        //String nom, tel, fechaNac, estadoCiv, dir;
        //cliente cliente;
        bundle = getIntent().getExtras();
        cliente cli = (cliente) bundle.getSerializable("CLIENTE");

        /*if (cedula.getText().toString().isEmpty()) {
            Toast.makeText(this, "Debe ingresar la cedula del cliente para buscarlo!", Toast.LENGTH_SHORT).show();
        } else {*/

            //ced = Integer.parseInt(cedula.getText().toString());

            //cliente = new cliente();
            //cliente.setCedula(ced);

            cliente clienteFinal = DAO.consultarCliente(this, cli);

            if (cli != null) {
                cedula.setText(String.valueOf(clienteFinal.getCedula()));
                nombre.setText(clienteFinal.getNombre());
                salario.setText(String.valueOf(clienteFinal.getSalario()));
                telefono.setText(clienteFinal.getTelefono());
                fechaNacimiento.setText(clienteFinal.getFechaNacimiento());
                estadoCivil.setText(clienteFinal.getEstadoCivil());
                direccion.setText(clienteFinal.getDireccion());
            } else {
                Toast.makeText(this, "Cliente no encontrado!", Toast.LENGTH_SHORT).show();
            }
        //}
    }

    public void modificarCliente(View vista) {
        Integer ced, sal;
        String nom, tel, fec_Nac, est_civ, dir;
        cliente cliente;
        int cantidad = 0;

        if(!cedula.getText().toString().isEmpty() && !nombre.getText().toString().isEmpty() &&
        !salario.getText().toString().isEmpty() && !telefono.getText().toString().isEmpty() &&
        !fechaNacimiento.getText().toString().isEmpty() && !estadoCivil.getText().toString().isEmpty() &&
        !direccion.getText().toString().isEmpty()){

            ced = Integer.parseInt(cedula.getText().toString());
            sal = Integer.parseInt(salario.getText().toString());
            nom = nombre.getText().toString();
            tel = telefono.getText().toString();
            fec_Nac = fechaNacimiento.getText().toString();
            est_civ = estadoCivil.getText().toString();
            dir = direccion.getText().toString();

            cliente = new cliente(ced, nom, sal, tel, fec_Nac, est_civ, dir);

            cantidad = DAO.modificarCliente(this, cliente);

            if(cantidad == 1){
                Toast.makeText(this, "Datos modificados exitosamente!",Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(this, "Cliente no encontrado!", Toast.LENGTH_SHORT).show();
            }

        }else{
            Toast.makeText(this, "Debe de llenar todos los campos!", Toast.LENGTH_LONG).show();
        }
    }
}
