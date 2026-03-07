/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pizzaexpress.backend.modelos.partida;

import java.time.Duration;

/**
 *
 * @author edu
 */
public class ConvertidorDeTiempo {

    public String formatearTiempo(int milisegundos) {
        long ms = (long) milisegundos;
        Duration d = Duration.ofMillis(ms);

        long horas = d.toHours();
        long minutos = d.toMinutesPart();
        long segundos = d.toSecondsPart();

        if (horas > 0) {
            return String.format("%02d:%02d:%02d", horas, minutos, segundos);
        } else {
            return String.format("%02d:%02d", minutos, segundos);
        }
    }
}
