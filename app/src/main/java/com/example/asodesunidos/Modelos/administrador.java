package com.example.asodesunidos.Modelos;

public class administrador {
    private Integer cedula;
    private String nombre;

    public administrador() {
    }

    public administrador(Integer cedula, String nombre) {
        this.cedula = cedula;
        this.nombre = nombre;
    }

    public Integer getCedula() {
        return cedula;
    }

    public void setCedula(Integer cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
