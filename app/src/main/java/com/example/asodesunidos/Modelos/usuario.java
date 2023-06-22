package com.example.asodesunidos.Modelos;

public class usuario {
    private Integer id;
    private String nombre;
    private String clave;
    private String tipo;

    public usuario(Integer id, String nombre, String clave, String tipo) {
        this.id = id;
        this.nombre = nombre;
        this.clave = clave;
        this.tipo = tipo;
    }

    public usuario() {
        id=0;
        nombre="";
        clave="";
        tipo="";
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
