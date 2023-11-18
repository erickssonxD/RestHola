/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.iplacex.resthola;

/**
 *
 * @author Erick
 */
import java.io.Serializable;

public class Insumo implements Serializable {
    private int codigo;
    private String nombre;
    private int valor;

    // Empty constructor
    public Insumo() {
    }

    // Constructor with all parameters
    public Insumo(int codigo, String nombre, int valor) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.valor = valor;
    }

    // Getter and Setter methods for 'codigo'
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    // Getter and Setter methods for 'nombre'
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Getter and Setter methods for 'valor'
    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }
}
