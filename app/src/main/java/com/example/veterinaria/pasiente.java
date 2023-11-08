package com.example.veterinaria;

public class pasiente {
    int id;
    String nombre;
    String especie;
    String telefono;
    String ciudad;

    public  pasiente(int anInt, String string, String cursorString, String s, String string1, String cursorString1){}

    public pasiente(String nombre, String especie, String telefono, String ciudad) {
        this.nombre = nombre;
        this.especie=especie;
        this.telefono=telefono;
        this.ciudad=ciudad;
    }
    public pasiente(int id, String nombre, String especie, String telefono, String ciudad){
        this.id=id;
        this.nombre = nombre;
        this.especie=especie;
        this.telefono=telefono;
        this.ciudad=ciudad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }
}
