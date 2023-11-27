/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.iplacex.resthola;

/**
 *
 * @author Erick
 */
public class TipoAlojamiento {
    private int IdTipoAlojamiento;
    private String Descripcion;

    public TipoAlojamiento(int IdTipoAlojamiento, String Descripcion) {
        this.IdTipoAlojamiento = IdTipoAlojamiento;
        this.Descripcion = Descripcion;
    }

    public int getIdTipoAlojamiento() {
        return IdTipoAlojamiento;
    }

    public void setIdTipoAlojamiento(int IdTipoAlojamiento) {
        this.IdTipoAlojamiento = IdTipoAlojamiento;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }
}
