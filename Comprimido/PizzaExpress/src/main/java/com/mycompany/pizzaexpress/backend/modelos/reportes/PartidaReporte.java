/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pizzaexpress.backend.modelos.reportes;

/**
 *
 * @author edu
 */
public class PartidaReporte {
    private String nickname;
    private String nombreUsuario;
    private int idPartida;
    private String nombreSucursal;
    private String fecha;
    private int nivelAlcanzado;
    private int pedidosExitosos;
    private int pedidosIncompletos;
    private int pedidosCancelados;
    private int bonosEficiencia;
    private int penalizaciones;
    private int puntosTotales;

    public PartidaReporte(String nickname, String nombreUsuario, int idPartida, String nombreSucursal, String fecha, int nivelAlcanzado, int pedidosExitosos, int pedidosIncompletos, int pedidosCancelados, int bonosEficiencia, int penalizaciones, int puntosTotales) {
        this.nickname = nickname;
        this.nombreUsuario = nombreUsuario;
        this.idPartida = idPartida;
        this.nombreSucursal = nombreSucursal;
        this.fecha = fecha;
        this.nivelAlcanzado = nivelAlcanzado;
        this.pedidosExitosos = pedidosExitosos;
        this.pedidosIncompletos = pedidosIncompletos;
        this.pedidosCancelados = pedidosCancelados;
        this.bonosEficiencia = bonosEficiencia;
        this.penalizaciones = penalizaciones;
        this.puntosTotales = puntosTotales;
    }

    public String getNickname() {
        return nickname;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public int getIdPartida() {
        return idPartida;
    }

    public String getNombreSucursal() {
        return nombreSucursal;
    }

    public String getFecha() {
        if (this.fecha != null && this.fecha.length() >= 10) {
            return this.fecha.substring(0, 10);
        }
        return fecha;
    }

    public int getNivelAlcanzado() {
        return nivelAlcanzado;
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

    public int getBonosEficiencia() {
        return bonosEficiencia;
    }

    public int getPenalizaciones() {
        return penalizaciones;
    }

    public int getPuntosTotales() {
        return puntosTotales;
    }

   
    
    

    
}
