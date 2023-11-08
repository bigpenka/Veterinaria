package com.example.veterinaria;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.net.PortUnreachableException;
import java.util.ArrayList;

public class Adaptador extends BaseAdapter {
    ArrayList<pasiente> lista;
    DbPasiente dbPasiente;
    pasiente p;
    Activity a;
    int id=0;
    public Adaptador(Activity a, ArrayList<pasiente> lista, DbPasiente dbPasiente){
        this.lista=lista;
        this.a=a;
        this.dbPasiente=dbPasiente;
    }
    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id=id;
    }
    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public Object getItem(int i) {
        p=lista.get(i);
        return null;
    }

    @Override
    public long getItemId(int i) {
        p=lista.get(i);
        return p.getId();
    }

    @Override
    public View getView(int posicion,View view, ViewGroup viewGroup) {
        View v = view;
        if (v == null){
            LayoutInflater li = (LayoutInflater) a.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v=li.inflate(R.layout.item, null);
        }
        p=lista.get(posicion);
        TextView nombre=v.findViewById(R.id.nombre);
        TextView especie=v.findViewById(R.id.especie);
        TextView telefono=v.findViewById(R.id.telefono);
        TextView ciudad=v.findViewById(R.id.ciudad);
        Button editar= v.findViewById(R.id.btn_editar);
        Button eliminar= v.findViewById(R.id.btn_eliminar);
        nombre.setText(p.getNombre());
        especie.setText(p.getEspecie());
        telefono.setText(p.getTelefono());
        ciudad.setText(p.getCiudad());
        editar.setTag(posicion);
        eliminar.setTag(posicion);
        editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               int pos = Integer.parseInt(view.getTag().toString()) ;
               final Dialog dialog = new Dialog(a);
               dialog.setTitle("Editar Pasiente");
               dialog.setCancelable(true);
               dialog.setContentView(R.layout.dialogo);
               dialog.show();
               final EditText nombre = dialog.findViewById(R.id.nombre);
                final EditText especie = dialog.findViewById(R.id.especie);
                final EditText telefono = dialog.findViewById(R.id.telefono);
                final EditText ciudad = dialog.findViewById(R.id.ciudad);
                Button guardar = dialog.findViewById(R.id.btn_agregar);
                Button cancelar = dialog.findViewById(R.id.btn_cancelar);
                p=lista.get(pos);
                setId(p.getId());
                nombre.setText(p.getNombre());
                especie.setText(p.getEspecie());
                telefono.setText(p.getTelefono());
                ciudad.setText(p.getCiudad());
                guardar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        try{
                            p= new pasiente(getId(), nombre.getText().toString(),
                                    especie.getText().toString(),
                                    telefono.getText().toString(),
                                    ciudad.getText().toString());
                            dbPasiente.editar(p);
                            lista=dbPasiente.vertodo();
                            notifyDataSetChanged();
                            dialog.dismiss();

                        }catch (Exception e){
                            Toast.makeText(a,"Error",Toast.LENGTH_SHORT).show();
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
        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos=Integer.parseInt(view.getTag().toString());
                p=lista.get(posicion);
                setId(p.getId());
                AlertDialog.Builder del = new AlertDialog.Builder(a);
                del.setMessage("Estas seguro de eliminar ?");
                del.setPositiveButton("si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dbPasiente.eliminar(getId());
                        lista=dbPasiente.vertodo();
                        notifyDataSetChanged();
                    }
                });
                del.setNegativeButton("no", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                del.show();
            }


        });
        return v;
    }
}
