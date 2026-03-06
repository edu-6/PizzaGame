/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pizzaexpress.backend.modelos;

/**
 *
 * @author edu
 */
public class ConfiguracionDePartida {
    
    private int tiempoPartida;
    private int tiempoNivel1;
    private int tiempoNivel2;
    private int tiempoNivel3;
    private int puntosNivel2;
    private int puntosNivel3;
    private int tiempoEntrePedidos;
    private int limitePedidosActivos;
    
    
    public ConfiguracionDePartida( int tiempoPartida, int tiempoNivel1, int tiempoNivel2, int tiempoNivel3, int puntosNivel2,
            int puntosNivel3, int tiempoEntrePedidos, int limitePedidosActivos) {
        this.tiempoPartida = tiempoPartida;
        this.tiempoNivel1 = tiempoNivel1;
        this.tiempoNivel2 = tiempoNivel2;
        this.tiempoNivel3 = tiempoNivel3;
        this.puntosNivel2 = puntosNivel2;
        this.puntosNivel3 = puntosNivel3;
        this.tiempoEntrePedidos = tiempoEntrePedidos;
        this.limitePedidosActivos = limitePedidosActivos;
    }

    public int getTiempoPartida() {
        return tiempoPartida;
    }

    public int getTiempoNivel1() {
        return tiempoNivel1;
    }

    public int getTiempoNivel2() {
        return tiempoNivel2;
    }

    public int getTiempoNivel3() {
        return tiempoNivel3;
    }

    public int getPuntosNivel2() {
        return puntosNivel2;
    }

    public int getPuntosNivel3() {
        return puntosNivel3;
    }

    public int getTiempoEntrePedidos() {
        return tiempoEntrePedidos;
    }

    public int getLimitePedidosActivos() {
        return limitePedidosActivos;
    }
    

  
}
