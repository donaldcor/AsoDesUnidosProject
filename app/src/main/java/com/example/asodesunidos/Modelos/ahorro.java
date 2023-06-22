package com.example.asodesunidos.Modelos;

public class ahorro {

    private int cedulaCliente;
    private String tipoAhorro;
    private int montoAhorrado;
    private int cuota;

    public ahorro() {
    }

    public ahorro(int cedulaCliente, String tipoAhorro, int montoAhorrado, int cuota) {
        this.cedulaCliente = cedulaCliente;
        this.tipoAhorro = tipoAhorro;
        this.montoAhorrado = montoAhorrado;
        this.cuota = cuota;
    }

    public int getCedulaCliente() {
        return cedulaCliente;
    }

    public void setCedulaCliente(int cedulaCliente) {
        this.cedulaCliente = cedulaCliente;
    }

    public String getTipoAhorro() {
        return tipoAhorro;
    }

    public void setTipoAhorro(String tipoAhorro) {
        this.tipoAhorro = tipoAhorro;
    }

    public int getMontoAhorrado() {
        return montoAhorrado;
    }

    public void setMontoAhorrado(int montoAhorrado) {
        this.montoAhorrado = montoAhorrado;
    }

    public int getCuota() {
        return cuota;
    }

    public void setCuota(int cuota) {
        this.cuota = cuota;
    }

    @Override
    public String toString() {
        return "ahorro{" +
                "cedulaCliente=" + cedulaCliente +
                ", tipoAhorro=" + tipoAhorro +
                ", montoAhorrado=" + montoAhorrado +
                ", cuota=" + cuota +
                '}';
    }
}
