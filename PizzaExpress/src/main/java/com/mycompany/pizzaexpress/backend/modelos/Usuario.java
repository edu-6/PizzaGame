/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pizzaexpress.backend.modelos;

/**
 *
 * @author edu
 */
public class Usuario {
    private String nombre;
    private String rol;
    private String nickname;
    private String nombreSucursal;
    private int idSucursal;
    public Usuario(String rol, String nickname, String nombreSucursal, int idSucrusal) {
        this.rol = rol;
        this.nickname = nickname;
        this.nombreSucursal = nombreSucursal;
        this.idSucursal = idSucrusal;
    }

    public Usuario(String rol, String nickname) {
        this.rol = rol;
        this.nickname = nickname;
    }

    public String getRol() {
        return rol;
    }

    public String getNickname() {
        return nickname;
    }

    public String getNombreSucursal() {
        return nombreSucursal;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setNombreSucursal(String nombreSucursal) {
        this.nombreSucursal = nombreSucursal;
    }

    public void setIdSucursal(int idSucursal) {
        this.idSucursal = idSucursal;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
    
    
    
    
    
}
