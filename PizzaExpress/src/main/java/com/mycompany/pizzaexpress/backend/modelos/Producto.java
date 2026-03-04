/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pizzaexpress.backend.modelos;

/**
 *
 * @author edu
 */
public class Producto {

    private String nombre;
    private boolean activo;
    private String descripcion;
    private int id;
    private int id_sucursal;

    public Producto(String nombre, boolean activo, String descripcion, int idSucursal) {
        this.nombre = nombre;
        this.activo = activo;
        this.descripcion = descripcion;
        this.id_sucursal = idSucursal;
    }

    public Producto(String nombre, boolean activo, String descripcion, int idSucursal, int id) {
        this.nombre = nombre;
        this.id_sucursal = id;
        this.activo = activo;
        this.descripcion = descripcion;
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_sucursal() {
        return id_sucursal;
    }

    public void setId_sucursal(int id_sucursal) {
        this.id_sucursal = id_sucursal;
    }

    public boolean datosCompletos() {
        return nombre != null && !nombre.trim().isEmpty()
                && descripcion != null && !descripcion.trim().isEmpty()
                && id_sucursal > 0;
    }

    public boolean esValido() {
        return nombre.length() <= 100
                && descripcion.length() <= 200;
    }
}
