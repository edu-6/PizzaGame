/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pizzaexpress.backend.modelos.partida;

import com.mycompany.pizzaexpress.backend.modelos.ConfiguracionDePartida;

/**
 *
 * @author edu
 */
public class GeneradorDePedidos implements Runnable {
    
     private ConfiguracionDePartida reglasPartida;
    private int tiempoEntrePedidos;
    private Thread hiloReloj;
    private Partida partida;

    public GeneradorDePedidos(Thread hiloReloj, Partida partida,ConfiguracionDePartida reglasPartida  ) {
        this.hiloReloj = hiloReloj;
        this.partida = partida;
        this.reglasPartida = reglasPartida;
        this.tiempoEntrePedidos = reglasPartida.getTiempoEntrePedidos();
    }
   
    @Override
    public void run() {
        while(hiloReloj.isAlive()){
            intentarGenerarPedido();
            try {
                Thread.sleep(tiempoEntrePedidos);
            } catch (InterruptedException ex) {
            }
        }
    }
    
    
    private void intentarGenerarPedido(){
        if(partida.getCantidadPedidosActivos() < reglasPartida.getLimitePedidosActivos()){
            Pedido pedido = new Pedido(0, null); // temporal
            this.partida.agregarNuevoPedido(pedido);
        }
    }
    
    
    
    
}
