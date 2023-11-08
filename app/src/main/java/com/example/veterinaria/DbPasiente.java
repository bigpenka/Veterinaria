package com.example.veterinaria;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class DbPasiente {
    SQLiteDatabase db;
    ArrayList<pasiente>lista= new ArrayList<pasiente>();
    pasiente p;
    Context ct;
    String nombreDB = "DBPasiente";
    String tabla ="create table if not exists pasiente(id integer primary key autoincrement, nombre text, especie text, telefono text, ciudad text)";

    public DbPasiente(Context c){
        this.ct=c;
        db=c.openOrCreateDatabase(nombreDB, Context.MODE_PRIVATE, null);
        db.execSQL(tabla);

    }
    public boolean insertar(pasiente p){
        ContentValues contenedor = new ContentValues();
        contenedor.put("nombre", p.getNombre());
        contenedor.put("especie", p.getEspecie());
        contenedor.put("telefono",p.getTelefono());
        contenedor.put("ciudad",p.getCiudad());
        return (db.insert("pasiente",null, contenedor))>0;
    }
    public boolean eliminar(int id){
        return (db.delete("pasiente", "id"+id, null))>0;
    }
    public boolean editar(pasiente p){
        ContentValues contenedor = new ContentValues();
        contenedor.put("nombre", p.getNombre());
        contenedor.put("especie", p.getEspecie());
        contenedor.put("telefono",p.getTelefono());
        contenedor.put("ciudad",p.getCiudad());
        return (db.update("pasiente", contenedor,"id"+p.getId(),null))>0;
        
    }
    public ArrayList<pasiente>vertodo(){
        lista.clear();
        Cursor cursor = db.rawQuery("select * from pasiente", null);
        if (cursor!=null && cursor.getCount()>0){
            cursor.moveToFirst();
            do{
                lista.add(new pasiente(cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5)));
            }while (cursor.moveToNext());
        }
        
          return lista;
    }
    
}
