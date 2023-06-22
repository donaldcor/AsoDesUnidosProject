package com.example.asodesunidos.Control;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.asodesunidos.Control.administrativo;
import com.example.asodesunidos.DAO.DAO;
import com.example.asodesunidos.Modelos.cliente;
import com.example.asodesunidos.Modelos.usuario;
import com.example.asodesunidos.R;
import com.example.asodesunidos.SQLiteDB.SQLiteOpenHelperManager;
import com.example.asodesunidos.SessionManagement;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    private Button ingresarBtn, salirBtn;
    private EditText userEditText, passEditText;
    private usuario usuario = new usuario();
    private cliente cliente = new cliente();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setSupportActionBar(findViewById(R.id.my_toolbar));

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);

        userEditText = findViewById(R.id.user_ET);
        passEditText = findViewById(R.id.pass_ET);


        ingresarBtn = findViewById(R.id.ingresar_btn);
        salirBtn = findViewById(R.id.salir_btn);

        ingresarBtn.setOnClickListener(v -> retornarUsuario());

        salirBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        SQLiteOpenHelperManager manager = new SQLiteOpenHelperManager(this);
        SQLiteDatabase baseDatos = manager.getWritableDatabase();
        checkSession();
    }

    @Override
    protected void onResume() {
        super.onResume();
        userEditText.setText("");
        passEditText.setText("");
    }

    private void checkSession(){
        SessionManagement sessionManagement = new SessionManagement(MainActivity.this);
        int userID = sessionManagement.getUser();
        String userPass = sessionManagement.getPass();

        if(userID !=-1 && userPass !=null){
            openActivityPrincipalCliente();
        }
    }

    private void openActivityPrincipalAdmin(){
        Intent intent = new Intent(this, administrativo.class);
        startActivity(intent);
    }

    private void openActivityPrincipalCliente() {
        SessionManagement sessionManagement = new SessionManagement(MainActivity.this);
        int userID = sessionManagement.getUser();
        cliente = DAO.retornarClienteCed(this, userID);
        Intent intent = new Intent(this, principalCliente.class);
        intent.putExtra("CLIENTE", cliente);
        startActivity(intent);
    }

    private void retornarUsuario() {
        if (!userEditText.getText().toString().isEmpty()) {
            if (!passEditText.getText().toString().isEmpty()) {
                Integer usu = Integer.parseInt(userEditText.getText().toString());
                String pass = passEditText.getText().toString();
                String tipo;
                usuario = DAO.retornarUsuarioCed(this, usu);
                tipo = usuario.getTipo().toString();
                if (Objects.equals(usuario.getId(), usu) && Objects.equals(usuario.getClave(), pass)) {

                    switch (tipo) {
                        case "cliente":
                            SessionManagement sessionManagement = new SessionManagement(MainActivity.this);
                            sessionManagement.saveSession(usuario);
                            openActivityPrincipalCliente();
                            break;
                        case "admin":
                            openActivityPrincipalAdmin();
                            break;
                        default:
                            break;
                    }
                } else {
                    if (!Objects.equals(usuario.getId(), usu)) {
                        userEditText.setError("Usuario incorrecto");
                    }
                    if (!Objects.equals(usuario.getClave(), pass)) {
                        passEditText.setError("Contraseña incorrecta");
                    }
                }
            } else {
                passEditText.setError("Debe digitar una contraseña");
            }
        }else{
            userEditText.setError("Debe digitar un nombre de usuario");
        }
    }



}