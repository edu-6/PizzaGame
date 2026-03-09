/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pizzaexpress.backend.modelos.reportes;

/**
 *
 * @author edu
 */
public class UsuarioReporte {
    private String nickname;
    private String nombre;
    private int partidasJugadas;
    private int puntosAcumulados;
    private double promedioPuntos;
    private int maxNivel;
    private String nombreSucursal;

    public UsuarioReporte(String nickname, String nombre, int partidasJugadas, int puntosAcumulados,
            double promedioPuntos, int maxNivel, String nombreSucursal) {
        this.nickname = nickname;
        this.nombre = nombre;
        this.partidasJugadas = partidasJugadas;
        this.puntosAcumulados = puntosAcumulados;
        this.promedioPuntos = promedioPuntos;
        this.maxNivel = maxNivel;
        this.nombreSucursal = nombreSucursal;
    }

    public String getNickname() {
        return nickname;
    }

    public String getNombre() {
        return nombre;
    }

    public int getPartidasJugadas() {
        return partidasJugadas;
    }

    public int getPuntosAcumulados() {
        return puntosAcumulados;
    }

    public double getPromedioPuntos() {
        return promedioPuntos;
    }

    public int getMaxNivel() {
        return maxNivel;
    }

    public String getNombreSucursal() {
        return nombreSucursal;
    }
    
}
