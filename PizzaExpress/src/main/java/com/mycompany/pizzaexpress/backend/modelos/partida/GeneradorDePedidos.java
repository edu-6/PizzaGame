/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pizzaexpress.backend.modelos.partida;

import com.mycompany.pizzaexpress.backend.db.PedidosDB;
import com.mycompany.pizzaexpress.backend.exceptions.ExceptionGenerica;
import com.mycompany.pizzaexpress.backend.modelos.ConfiguracionDePartida;
import com.mycompany.pizzaexpress.backend.modelos.Sesion;

/**
 *
 * @author edu
 */
public class GeneradorDePedidos implements Runnable {

    private PedidosDB pedidosDB = new PedidosDB();

    private ConfiguracionDePartida reglasPartida;
    private int tiempoEntrePedidos;
    private Thread relojPartida;
    private Partida partida;

    public GeneradorDePedidos(Thread hiloReloj, Partida partida, ConfiguracionDePartida reglasPartida) {
        this.relojPartida = hiloReloj;
        this.partida = partida;
        this.reglasPartida = reglasPartida;
        this.tiempoEntrePedidos = reglasPartida.getTiempoEntrePedidos();
    }

    @Override
    public void run() {
        while (relojPartida.isAlive()) {
            try {
                intentarGenerarPedido();
            } catch (ExceptionGenerica ex) {
               partida.mostrarErrorEnPedido(ex.getMessage());
            }
            try {
                Thread.sleep(tiempoEntrePedidos);
            } catch (InterruptedException ex) {
            }
        }
    }

    /**
     * Genera un pedido si los pedidos activos no superan el limite
     * @throws ExceptionGenerica 
     */
    private void intentarGenerarPedido() throws ExceptionGenerica {
        if (partida.getCantidadPedidosActivos() < reglasPartida.getLimitePedidosActivos()) {
            Pedido pedido = new Pedido(obtenerTiempoPedido(),
                    pedidosDB.obtenerProductosAleatorios(Sesion.getUsuario().getIdSucursal()),
                    relojPartida, partida);
            this.partida.agregarNuevoPedido(pedido);
        }
    }

    /**
     * Sirve para obtener el tiempo limite para un pedido segun 
     * el nivel del jugador en la partida
     * @return 
     */
    private int obtenerTiempoPedido() {
        switch (partida.getNivelActual()) {
            case 1:
                return reglasPartida.getTiempoNivel1();
            case 2:
                return reglasPartida.getTiempoNivel1();
            case 3:
                return reglasPartida.getTiempoNivel1();
        }
        return 0;
    }
}
