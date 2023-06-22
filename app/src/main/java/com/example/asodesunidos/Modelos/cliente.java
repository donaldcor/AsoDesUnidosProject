package com.example.asodesunidos.Modelos;

import java.io.Serializable;

public class cliente implements Serializable{
    private Integer cedula;
    private String nombre;
    private Integer salario;
    private String telefono;
    private String fechaNacimiento;
    private String estadoCivil;
    private String direccion;


    public cliente() {
        cedula = 0;
        nombre = "";
        salario = 0;
        telefono = "";
        fechaNacimiento = "";
        estadoCivil = "";
        direccion = "";
    }

    public cliente(Integer cedula, String nombre, Integer salario, String telefono, String fechaNacimiento, String estadoCivil, String direccion) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.salario = salario;
        this.telefono = telefono;
        this.fechaNacimiento = fechaNacimiento;
        this.estadoCivil = estadoCivil;
        this.direccion = direccion;
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

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Integer getSalario() {
        return salario;
    }

    public void setSalario(Integer salario) {
        this.salario = salario;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
}
