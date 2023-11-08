package com.example.veterinaria;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    DbPasiente dbp;
    Adaptador adaptador;
    ArrayList<pasiente>lista;
    pasiente p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnGPS = findViewById(R.id.btn_gps);
        dbp = new DbPasiente(MainActivity.this);
        lista=dbp.vertodo();
        adaptador=new Adaptador(this, lista, dbp);
        ListView list= findViewById(R.id.lista);
        Button insertar = findViewById(R.id.btn_insertar);
        list.setAdapter(adaptador);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Dialog dialog = new Dialog(MainActivity.this);
                dialog.setTitle("Nuevo Pasiente");
                dialog.setCancelable(true);
                dialog.setContentView(R.layout.dialogo);
                dialog.show();
                final EditText nombre = dialog.findViewById(R.id.nombre);
                final EditText especie = dialog.findViewById(R.id.especie);
                final EditText telefono = dialog.findViewById(R.id.telefono);
                final EditText ciudad = dialog.findViewById(R.id.ciudad);
                Button guardar = dialog.findViewById(R.id.btn_agregar);
                guardar.setText("Agregar nuevo pasiente");
                Button cancelar = dialog.findViewById(R.id.btn_cancelar);
                guardar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        try{
                            p = new pasiente(nombre.getText().toString(),
                                    especie.getText().toString(),
                                    telefono.getText().toString(),
                                    ciudad.getText().toString());
                            dbp.insertar(p);
                            lista=dbp.vertodo();
                            adaptador.notifyDataSetChanged();
                            dialog.dismiss();
                        }catch (Exception e){
                            Toast.makeText(getApplication(),"Error", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                cancelar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });

            }
        });

        btnGPS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, gps.class);
                startActivity(intent);
            }
        });
    }
}