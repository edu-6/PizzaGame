/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pizzaexpress.backend.modelos;

/**
 *
 * @author edu
 */
public class ConfiguracionPunteos {
    private int bonoPedidoCorrecto;
    private int bonoTiempoOptimo;
    private int bonoEficiencia;
    private int pedidoCancelado;
    private int pedidoIncompleto;

    
    public ConfiguracionPunteos( int bonoPedidoCorrecto, int bonoTiempoOptimo, int bonoEficiencia, int pedidoCancelado, int pedidoIncompleto) {
        this.bonoPedidoCorrecto = bonoPedidoCorrecto;
        this.bonoTiempoOptimo = bonoTiempoOptimo;
        this.bonoEficiencia = bonoEficiencia;
        this.pedidoCancelado = pedidoCancelado;
        this.pedidoIncompleto = pedidoIncompleto;
    }
    
    public int getBonoPedidoCorrecto() {
        return bonoPedidoCorrecto;
    }

    public int getBonoTiempoOptimo() {
        return bonoTiempoOptimo;
    }

    public int getBonoEficiencia() {
        return bonoEficiencia;
    }

    public int getPedidoCancelado() {
        return pedidoCancelado;
    }

    public int getPedidoIncompleto() {
        return pedidoIncompleto;
    }
    
    
    
}
