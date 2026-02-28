/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pizzaexpress.backend.modelos;

/**
 *
 * @author edu
 */
public class Sucursal {

    private String ubicacion;
    private String nombre;
    private int id;

    public Sucursal(String ubicacion, String nombre) {
        this.ubicacion = ubicacion;
        this.nombre = nombre;
    }

    public Sucursal(String ubicacion, String nombre, int id) {
        this.ubicacion = ubicacion;
        this.nombre = nombre;
        this.id = id;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public String getNombre() {
        return nombre;
    }

    public int getId() {
        return id;
    }

    public boolean datosCompletos() {
        return nombre != null && !nombre.trim().isEmpty()
                && ubicacion != null && !ubicacion.trim().isEmpty();
    }

    public boolean esValido() {
        return nombre.length() <= 100
                && ubicacion.length() <= 255;
    }

}
