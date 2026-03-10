/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pizzaexpress.backend.modelos.partida;

import com.mycompany.pizzaexpress.backend.modelos.EstadoPedido;
import com.mycompany.pizzaexpress.backend.modelos.Producto;
import com.mycompany.pizzaexpress.frontend.partida.PanelPedido;
import java.util.ArrayList;

/**
 *
 * @author edu
 */
public class Pedido implements Runnable {

    private PanelPedido panelPedido; // interfaz

    private Thread relojPartida;
    private Partida partida;
    private ArrayList<Producto> productos;

    private boolean activo = true;
    private int tiempoLimite;
    private int tiempoRestante;
    private int numeroPedido;

    // estados del pedido
    private int ultimoEstado = 1;
    private boolean recibido = true;  //1
    private boolean preparando; //2
    private boolean enHorno;   //3
    private boolean entregado; //4
    private boolean cancelado; //5
    private boolean noEntregado; //6

    public Pedido(int tiempoLimite, ArrayList<Producto> productos,
            Thread relojPartida, Partida partida) {
        this.tiempoLimite = tiempoLimite;
        this.tiempoRestante = tiempoLimite;
        this.productos = productos;
        this.relojPartida = relojPartida;
        this.partida = partida;
    }

    @Override
    public void run() {
        while (pedidoEstaVivo()) {
            //System.out.println("No."+ this.getNumeroPedido()+ "vivo");
            try {
                Thread.sleep(1000);
                tiempoRestante -= 1000;
                this.panelPedido.actualizarTiempo(tiempoRestante);
            } catch (InterruptedException ex) {
            }
        }

        //si no fue cancelado o entregado
        if ((!cancelado && !entregado)) {
            this.noEntregado = true;
        }
        //avisar al panel que ya terminó
        this.panelPedido.eliminarPedidoVisual();

        //avisar a la partida que ya terminó
        partida.ProcesarPedidoFinalizado(this);
    }

    private boolean pedidoEstaVivo() {
        return partida.isPartidaEnCurso() 
            && tiempoRestante > 0 
            && activo;
    }

    /**
     * Cambia los estados actuales
     */
    public void avanzar() {
        if (ultimoEstado < 4) {
            ultimoEstado++;
            switch (ultimoEstado) {
                case 2:
                    preparando = true;
                    break;
                case 3:
                    enHorno = true;
                    break;
                case 4:
                    entregado = true;
                    activo = false; // termina el pedido
                    break;
            }
        }
    }

    public EstadoPedido consultarEstado() {
        switch (ultimoEstado) {
            case 1:
                return EstadoPedido.RECIBIDO;
            case 2:
                return EstadoPedido.PREPARANDO;
            case 3:
                return EstadoPedido.EN_HORNO;
            case 4:
                return EstadoPedido.ENTREGADO;
            case 5:
                return EstadoPedido.CANCELADO;
            case 6:
                return EstadoPedido.NO_ENTREGADO;
        }
        return null;
    }

    public boolean sePuedeCancelar() {
        return ultimoEstado < 3;
    }

    public void cancelar() {
        this.activo = false;
        this.cancelado = true;
        this.ultimoEstado = 5;
    }

    public boolean aplicaBonoAntesDeTiempo() {
        return (entregado && tiempoRestante > 0);
    }

    public boolean aplicaBonoEficiencia() {
        return (entregado && tiempoRestante >= tiempoLimite / 2);
    }

    public boolean isEntregado() {
        return entregado;
    }

    public boolean isCancelado() {
        return cancelado;
    }

    public boolean isNoEntregado() {
        return noEntregado;
    }

    public void setPanelPedido(PanelPedido panelPedido) {
        this.panelPedido = panelPedido;
    }

    public int getNumeroPedido() {
        return numeroPedido;
    }

    public void setNumeroPedido(int numeroPedido) {
        this.numeroPedido = numeroPedido;
    }

    public ArrayList<Producto> getProductos() {
        return productos;
    }

    public Partida getPartida() {
        return partida;
    }

    public boolean isRecibido() {
        return recibido;
    }

    public boolean isPreparando() {
        return preparando;
    }

    public boolean isEnHorno() {
        return enHorno;
    }
    
    
    
    

}
