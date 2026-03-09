/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pizzaexpress.backend.modelos;

/**
 *
 * @author edu
 */
public class BusquedaUsuarios {
    
    private String rol;
    private String sucursal;

    public BusquedaUsuarios(String rol, String sucursal) {
        this.rol = rol;
        this.sucursal = sucursal;
    }

    public String getRol() {
        return rol;
    }

    public String getSucursal() {
        return sucursal;
    }
    
}
