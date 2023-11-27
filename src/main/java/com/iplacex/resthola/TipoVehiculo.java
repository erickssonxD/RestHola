/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.iplacex.resthola;

/**
 *
 * @author Erick
 */
public class TipoVehiculo {
    private int IdTipoVehiculo;
    private String Descripcion;

    public TipoVehiculo(int IdTipoVehiculo, String Descripcion) {
        this.IdTipoVehiculo = IdTipoVehiculo;
        this.Descripcion = Descripcion;
    }

    public int getIdTipoVehiculo() {
        return IdTipoVehiculo;
    }

    public void setIdTipoVehiculo(int IdTipoVehiculo) {
        this.IdTipoVehiculo = IdTipoVehiculo;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }
}
