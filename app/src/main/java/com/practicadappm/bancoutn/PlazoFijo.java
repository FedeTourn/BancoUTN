package com.practicadappm.bancoutn;

public class PlazoFijo {

    Float tasaNominalAnual;
    Float tasaEfectivaAnual;
    Float capital;
    Integer meses, dias;
    Float interesesGanados;
    Float montoTotal;
    Float montoTotalAnual;

    public boolean setearYChequear(Float tNominal, Float tEfectiva, Float cap, Integer meses) {
        if (tNominal > 0 && tNominal < 100 && tEfectiva > 0 && tEfectiva < 100 && cap > 0 && meses >= 1) {
            this.tasaNominalAnual = tNominal;
            this.tasaEfectivaAnual = tEfectiva;
            this.capital = cap;
            this.meses = meses;
            this.dias = meses*30;
            return true;
        } else {
            return false;
        }
    }

    public void simular(){
        this.interesesGanados = this.capital*(this.tasaNominalAnual/Float.valueOf(100)/((Float.valueOf(12))/Float.valueOf(meses)));
        this.montoTotal = this.capital + this.interesesGanados;
        this.montoTotalAnual = this.capital*(1 + this.tasaNominalAnual/Float.valueOf(100));
    }

    public Float getInteresesGanados() {
        return interesesGanados;
    }

    public Integer getDias(){
        return dias;
    }

    public Float getCapital() {
        return capital;
    }

    public Float getMontoTotal() {
        return montoTotal;
    }

    public Float getMontoTotalAnual() {
        return montoTotalAnual;
    }
}


