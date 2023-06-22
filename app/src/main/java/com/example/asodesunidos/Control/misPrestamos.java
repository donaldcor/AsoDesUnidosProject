package com.example.asodesunidos.Control;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.asodesunidos.Adaptadores.ListaPrestamosAdapter;
import com.example.asodesunidos.DAO.DAO;
import com.example.asodesunidos.Modelos.cliente;
import com.example.asodesunidos.Modelos.prestamo;
import com.example.asodesunidos.R;
import com.example.asodesunidos.SQLiteDB.SQLiteOpenHelperManager;

import java.util.ArrayList;
import java.util.List;

public class misPrestamos extends AppCompatActivity {

    RecyclerView listaPrestamos;
    List<prestamo> listaArrayPrestamos;
    TextView titulo;
    Button regresar;

    private cliente cli;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mis_prestamos);

        setSupportActionBar(findViewById(R.id.my_toolbar));

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);

        titulo = findViewById(R.id.viewMisPrestamos);

        listaPrestamos = findViewById(R.id.listaPrestamos);

        listaPrestamos.setLayoutManager(new LinearLayoutManager(this));

        listaArrayPrestamos = new ArrayList<>();

        regresar = findViewById(R.id.regresar_mis_prest_btn);

        Bundle bundle = getIntent().getExtras();
        cli = (cliente) bundle.getSerializable("CLIENTE");

        ListaPrestamosAdapter adapter = new ListaPrestamosAdapter(DAO.mostrarPrestamos(this,cli));
        listaPrestamos.setAdapter(adapter);

        regresar.setOnClickListener(view -> finish());
    }

    @Override
    protected void onStart() {
        super.onStart();
        SQLiteOpenHelperManager manager = new SQLiteOpenHelperManager(this);
        SQLiteDatabase baseDatos = manager.getWritableDatabase();
    }

}