/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pizzaexpress.backend.modelos.partida;

/**
 *
 * @author edu
 */
public class Reloj implements Runnable {

    private int tiempoRestante;

    public Reloj(int tiempoRestante) {
        this.tiempoRestante = tiempoRestante;
    }

    @Override
    public void run() {
        while (tiempoRestante > 0) {
            try {
                tiempoRestante -= 1000;
                // actualizar panel de reloj
                Thread.sleep(1000); // dormir el hilo 1 segundo

            } catch (InterruptedException ex) {
            }
        }
    }
    
    public int getTiempoRestante() {
        return tiempoRestante;
    }
}
