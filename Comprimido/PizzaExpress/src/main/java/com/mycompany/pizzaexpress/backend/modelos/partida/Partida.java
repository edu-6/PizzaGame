/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pizzaexpress.backend.modelos.partida;

import com.mycompany.pizzaexpress.backend.modelos.ConfiguracionDePartida;
import com.mycompany.pizzaexpress.backend.modelos.ConfiguracionPunteos;
import com.mycompany.pizzaexpress.backend.modelos.Sesion;
import com.mycompany.pizzaexpress.frontend.partida.PanelPartida;
import java.util.ArrayList;

/**
 *
 * @author edu
 */
public class Partida implements Runnable {

    private Reloj reloj;
    private Thread hiloReloj;
    private PanelPartida panelPartida; // frontend

    private ConfiguracionDePartida reglasPartida;
    private ConfiguracionPunteos reglasPunteo;
    private GeneradorDePedidos generadorPedidos;
    private ArrayList<Pedido> listaPedidos = new ArrayList();

    private int numeroPedidos;
    private int pedidosActivos;
    private int nivelActual = 1;

    private int pedidosContados;

    // atributos para la db
    private int id_partida;
    private int idUsuario; 
    private int idSucursal;
    private int puntosPositivos;
    private int pedidosExitosos;
    private int pedidosIncompletos;
    private int pedidosCancelados;
    private int bonosPorEficiencia;
    private int penalizaciones;
    private int puntosTotales;
    
    private volatile boolean partidaEnCurso = true;

    public Partida(ConfiguracionDePartida reglasPartida, ConfiguracionPunteos reglasPunteo) {
        this.reglasPartida = reglasPartida;
        this.reglasPunteo = reglasPunteo;
        this.idUsuario = Sesion.getUsuario().getId();
        this.idSucursal = Sesion.getUsuario().getIdSucursal();
    }

    @Override
    public void run() {
        partidaEnCurso = true;
        reloj = new Reloj(reglasPartida.getTiempoPartida(), this);
        hiloReloj = new Thread(reloj);
        generadorPedidos = new GeneradorDePedidos(hiloReloj, this, reglasPartida);
        hiloReloj.start();
        Thread hiloGenerador = new Thread(generadorPedidos);
        hiloGenerador.start();
    }

    
    public void cancelarPartida() {
        this.partidaEnCurso = false;
        this.reloj.acabarTiempo();
        this.hiloReloj.interrupt();
    }

    public boolean isPartidaEnCurso() {
        return partidaEnCurso;
    }
    public void avisarPartidaTerminada() {
        this.panelPartida.irAlPanelFinPartida();
    }

    public void agregarNuevoPedido(Pedido pedido) {
        pedidosActivos++;
        numeroPedidos++;
        pedido.setNumeroPedido(numeroPedidos);
        this.listaPedidos.add(pedido);
        this.panelPartida.agregarNuevoPedido(pedido);
        this.panelPartida.actualizarEstadisticas();
    }

    public int getCantidadPedidosActivos() {
        return this.pedidosActivos;
    }

    public int getNivelActual() {
        return nivelActual;
    }

    public void actualizarTiempoFrontend(int tiempo) {
        this.panelPartida.actualizarTiempo(tiempo);
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

    public void mostrarErrorEnPedido(String mesaje) {
        panelPartida.mostarErrorEnPedido(mesaje);
    }

    /**
     * Actualiza todos los puntos y estadisticas cuando un pedido termina / es
     * cancelado etc además llama a actualizarNivel con el nuevo punteo
     *
     * @param pedido
     */
    public void ProcesarPedidoFinalizado(Pedido pedido) {
        pedidosContados++;
        pedidosActivos--;
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
        this.panelPartida.actualizarEstadisticas();
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

    public void setPanelPartida(PanelPartida panelPartida) {
        this.panelPartida = panelPartida;
    }

    public int getLimitePedidosActivos() {
        return this.reglasPartida.getLimitePedidosActivos();
    }

    public int getTiempoRestante() {
        return this.reloj.getTiempoRestante();
    }

    public void setPartidaEnCurso(boolean partidaEnCurso) {
        this.partidaEnCurso = partidaEnCurso;
    }

    public int getPedidosActivos() {
        return pedidosActivos;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public int getIdSucursal() {
        return idSucursal;
    }

    public ArrayList<Pedido> getListaPedidos() {
        return listaPedidos;
    }

    public int getId_partida() {
        return id_partida;
    }

    public void setId_partida(int id_partida) {
        this.id_partida = id_partida;
    }
    
}
    

