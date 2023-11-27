/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.iplacex.resthola;

/**
 *
 * @author Erick
 */
public class Alojamiento {
    private String Rut;
    private String NombreAgrupacion;
    private int IdTipoVehiculo;
    private int IdTipoAlojamiento;
    private String FechaIngreso;
    private int Dias;

    public Alojamiento(String Rut, String NombreAgrupacion, int IdTipoVehiculo,
                       int IdTipoAlojamiento, String FechaIngreso, int Dias) {
        this.Rut = Rut;
        this.NombreAgrupacion = NombreAgrupacion;
        this.IdTipoVehiculo = IdTipoVehiculo;
        this.IdTipoAlojamiento = IdTipoAlojamiento;
        this.FechaIngreso = FechaIngreso;
        this.Dias = Dias;
    }

    public String getRut() {
        return Rut;
    }

    public void setRut(String Rut) {
        this.Rut = Rut;
    }

    public String getNombreAgrupacion() {
        return NombreAgrupacion;
    }

    public void setNombreAgrupacion(String NombreAgrupacion) {
        this.NombreAgrupacion = NombreAgrupacion;
    }

    public int getIdTipoVehiculo() {
        return IdTipoVehiculo;
    }

    public void setIdTipoVehiculo(int IdTipoVehiculo) {
        this.IdTipoVehiculo = IdTipoVehiculo;
    }

    public int getIdTipoAlojamiento() {
        return IdTipoAlojamiento;
    }

    public void setIdTipoAlojamiento(int IdTipoAlojamiento) {
        this.IdTipoAlojamiento = IdTipoAlojamiento;
    }

    public String getFechaIngreso() {
        return FechaIngreso;
    }

    public void setFechaIngreso(String FechaIngreso) {
        this.FechaIngreso = FechaIngreso;
    }

    public int getDias() {
        return Dias;
    }

    public void setDias(int Dias) {
        this.Dias = Dias;
    }
}
