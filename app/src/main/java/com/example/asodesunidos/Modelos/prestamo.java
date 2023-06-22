package com.example.asodesunidos.Modelos;
import com.example.asodesunidos.Modelos.cliente;

import java.io.Serializable;

public class prestamo implements Serializable {
    private Integer cedula;
    private String tipoPrestamo;
    private Integer montoPrestamo;
    private Integer monto_pagado;
    private String periodoPrestamo;
    private Integer cantidadCuotas;
    private Integer montoCuota;



    public prestamo() {
        cedula = 0;
        tipoPrestamo = "";
        montoPrestamo = 0;
        periodoPrestamo = "";
        cantidadCuotas = 0;
        montoCuota = 0;
    }

    public prestamo(Integer cedula, String tipoPrestamo, Integer montoPrestamo,Integer monto_pagado, String periodoPrestamo, Integer cantidadCuotas, Integer montoCuota) {
        this.cedula = cedula;
        this.tipoPrestamo = tipoPrestamo;
        this.montoPrestamo = montoPrestamo;
        this.monto_pagado=monto_pagado;
        this.periodoPrestamo = periodoPrestamo;
        this.cantidadCuotas = cantidadCuotas;
        this.montoCuota = montoCuota;
    }

    public String getTipoPrestamo() {
        return tipoPrestamo;
    }

    public void setTipoPrestamo(String tipoPrestamo) {
        this.tipoPrestamo = tipoPrestamo;
    }

    public String getPeriodoPrestamo() {
        return periodoPrestamo;
    }

    public void setPeriodoPrestamo(String periodoPrestamo) {
        this.periodoPrestamo = periodoPrestamo;
    }

    public Integer getMontoPrestamo() {
        return montoPrestamo;
    }

    public void setMontoPrestamo(Integer montoPrestamo) {
        this.montoPrestamo = montoPrestamo;
    }

    public Integer getMontoPagado() {
        return monto_pagado;
    }

    public void setMontoPagado(Integer montoPagado) {
        this.monto_pagado = montoPagado;
    }

    public Integer getMontoCuota() {
        return montoCuota;
    }

    public void setMontoCuota(Integer montoCuota) {
        this.montoCuota = montoCuota;
    }

    public Integer getCedula() {
        return cedula;
    }

    public void setCedula(Integer cedula) {
        this.cedula = cedula;
    }

    public Integer getCantidadCuotas() {
        return cantidadCuotas;
    }

    public void setCantidadCuotas(Integer cantidadCuotas) {
        this.cantidadCuotas = cantidadCuotas;
    }
}
