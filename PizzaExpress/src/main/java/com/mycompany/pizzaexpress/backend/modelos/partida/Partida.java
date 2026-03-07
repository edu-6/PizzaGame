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
    private ArrayList<Pedido> listaPedidos = new ArrayList();

    private int pedidosActivos;
    private int nivelActual = 1;

    // atributos para la db
    private int puntosPositivos;
    private int pedidosExitosos;
    private int pedidosIncompletos;
    private int pedidosCancelados;
    private int bonosPorEficiencia;
    private int penalizaciones;
    private int puntosTotales;

    public Partida(ConfiguracionDePartida reglasPartida, ConfiguracionPunteos reglasPunteo) {
        this.reglasPartida = reglasPartida;
        this.reglasPunteo = reglasPunteo;
    }

    @Override
    public void run() {
        Reloj reloj = new Reloj(reglasPartida.getTiempoPartida());
        hiloReloj = new Thread(reloj);
        generadorPedidos = new GeneradorDePedidos(hiloReloj, this, reglasPartida);
        hiloReloj.start();
        Thread hiloGenerador = new Thread(generadorPedidos);
        hiloGenerador.start();
    }

    public void agregarNuevoPedido(Pedido pedido) {
        this.listaPedidos.add(pedido);
    }

    public int getCantidadPedidosActivos() {
        return this.pedidosActivos;
    }

    public int getNivelActual() {
        return nivelActual;
    }

    /**
     * Actualiza el nivel segun los puntos actuales
     */
    public void actualizarNivel() {
        if (puntosTotales >= reglasPartida.getPuntosNivel2() && puntosTotales < reglasPartida.getPuntosNivel3()) {
            this.nivelActual = 2;
        } else if (puntosTotales >= reglasPartida.getPuntosNivel3()) {
            this.nivelActual = 3;
        }
    }

    /**
     * Actualiza todos los puntos y estadisticas cuando un pedido termina / es
     * cancelado etc además llama a actualizarNivel con el nuevo punteo
     *
     * @param pedido
     */
    public void ProcesarPedidoFinalizado(Pedido pedido) {
        if (pedido.isCancelado()) {
            pedidosCancelados++;
            penalizaciones += reglasPunteo.getPedidoCancelado();
        } else if (pedido.isNoEntregado()) {
            pedidosIncompletos++;
            penalizaciones += reglasPunteo.getPedidoIncompleto();
        } else if (pedido.isEntregado()) {
            puntosPositivos += reglasPunteo.getBonoPedidoCorrecto();
            pedidosExitosos++;
        }

        if (pedido.aplicaBonoAntesDeTiempo()) {
            puntosPositivos += reglasPunteo.getBonoTiempoOptimo();
        }
        if (pedido.aplicaBonoEficiencia()) {
            bonosPorEficiencia += reglasPunteo.getBonoEficiencia();
            puntosPositivos += reglasPunteo.getBonoEficiencia();
        }

        this.puntosTotales = puntosPositivos - penalizaciones;
        this.actualizarNivel();
    }

    public int getPuntosPositivos() {
        return puntosPositivos;
    }

    public int getPedidosExitosos() {
        return pedidosExitosos;
    }

    public int getPedidosIncompletos() {
        return pedidosIncompletos;
    }

    public int getPedidosCancelados() {
        return pedidosCancelados;
    }

    public int getBonosPorEficiencia() {
        return bonosPorEficiencia;
    }

    public int getPenalizaciones() {
        return penalizaciones;
    }

    public int getPuntosTotales() {
        return puntosTotales;
    }
}
