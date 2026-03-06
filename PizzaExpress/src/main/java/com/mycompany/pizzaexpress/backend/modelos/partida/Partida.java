/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pizzaexpress.backend.modelos.partida;

import com.mycompany.pizzaexpress.backend.modelos.ConfiguracionDePartida;
import com.mycompany.pizzaexpress.backend.modelos.ConfiguracionPunteos;
import java.util.ArrayList;

/**
 *
 * @author edu
 */
public class Partida implements Runnable {
    
    private Thread hiloReloj;
    // private Panel para mostrar pues
    
    private ConfiguracionDePartida reglasPartida;
    private ConfiguracionPunteos reglasPunteo;
    private GeneradorDePedidos generadorPedidos;

    private int pedidosActivos;
    private int nivelActual = 1;
    private ArrayList<Pedido> listaPedidos = new ArrayList();

    public Partida(ConfiguracionDePartida reglasPartida, ConfiguracionPunteos reglasPunteo) {
        this.reglasPartida = reglasPartida;
        this.reglasPunteo = reglasPunteo;
    }

    @Override
    public void run() {
        Reloj reloj = new Reloj(reglasPartida.getTiempoPartida());
        hiloReloj = new Thread(reloj);
        generadorPedidos = new GeneradorDePedidos(hiloReloj,this, reglasPartida);
        hiloReloj.start();
        Thread hiloGenerador = new Thread(generadorPedidos);
        hiloGenerador.start();
    }

    public void agregarNuevoPedido(Pedido pedido) {
        pedido.setReloj(hiloReloj);
        pedido.setPartida(this);
        this.listaPedidos.add(pedido);
    }
    
    
    
    public int getCantidadPedidosActivos(){
        return this.pedidosActivos;
    }

    public int getNivelActual() {
        return nivelActual;
    }
    
    
    
    public void ProcesarPedidoFinalizado(Pedido pedido){
        // agregar puntos quitar puntos etccccccc
    }
    
    
    
    


}
