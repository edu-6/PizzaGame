/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pizzaexpress.backend.modelos;

/**
 *
 * @author edu
 */
public class Sesion {

    public static Usuario usuarioSession;

    private Sesion() {
    }

    public static  void guardarSesion(Usuario usuario) {
        usuarioSession = usuario;
    }

    public static Usuario getUsuario() {
        return usuarioSession;
    }

}
