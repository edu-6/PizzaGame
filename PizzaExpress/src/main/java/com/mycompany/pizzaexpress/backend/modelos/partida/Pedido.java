/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pizzaexpress.backend.modelos.partida;

import java.util.ArrayList;

/**
 *
 * @author edu
 */
public class Pedido implements Runnable{
    private Thread relojPartida;
    
    private boolean activo;
    private Partida partida;
    private int tiempoLimite;
    private ArrayList<ProductoPedido> productos;

    public Pedido(int tiempoLimite, ArrayList<ProductoPedido> productos) {
        this.tiempoLimite = tiempoLimite;
        this.productos = productos;
    }

    @Override
    public void run() {
        while(pedidoEstaVivo()){
            try {
                Thread.sleep(1000);
                tiempoLimite-= 1000;
                // actualizar el panel con el tiempo limite
            } catch (InterruptedException ex) {
            }
        }
        
        // avisar a la partida que ya terminó
        partida.ProcesarPedidoFinalizado(this);
    }

    public void setReloj(Thread reloj) {
        this.relojPartida = reloj;
    }

    public void setPartida(Partida partida) {
        this.partida = partida;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
    
    private boolean pedidoEstaVivo(){
        return relojPartida.isAlive()
        && tiempoLimite>0
        && activo;
    }
    
    
    
    
    

}
