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
    private String contraseña;
    private String nombre;
    private String rol;
    private int id;
    private String nickname;
    private String nombreSucursal;
    private int idSucursal;

    public Usuario(String contraseña, String nombre, String rol, String nickname, String nombreSucursal, int idSucursal) {
        this.contraseña = contraseña;
        this.nombre = nombre;
        this.rol = rol;
        this.nickname = nickname;
        this.nombreSucursal = nombreSucursal;
        this.idSucursal = idSucursal;
    }
    
    // usado para devolver el id
    
    public Usuario(int id, String contraseña, String nombre, String rol, String nickname, String nombreSucursal, int idSucursal) {
        this.id = id;
        this.contraseña = contraseña;
        this.nombre = nombre;
        this.rol = rol;
        this.nickname = nickname;
        this.nombreSucursal = nombreSucursal;
        this.idSucursal = idSucursal;
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

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public int getIdSucursal() {
        return idSucursal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
    public boolean datosCompletos() {
        return nombre != null && !nombre.trim().isEmpty()
                && nickname != null && !nickname.trim().isEmpty()
                && contraseña != null && !contraseña.trim().isEmpty()
                && rol != null && !rol.trim().isEmpty();
    }
    
    
    public boolean tamañoDedatosValidos() {
        return nickname.length() <= 50
                && nombre.length() <= 100
                && contraseña.length() <= 255
                && rol.length() <= 50;
    }
    
    
    
    
    
}
