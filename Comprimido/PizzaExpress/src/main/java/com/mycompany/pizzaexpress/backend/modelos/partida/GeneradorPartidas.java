/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pizzaexpress.backend.modelos.partida;

import com.mycompany.pizzaexpress.backend.exceptions.ExceptionGenerica;
import com.mycompany.pizzaexpress.backend.servicios.ConfiguracionesCrudService;

/**
 *
 * @author edu
 */
public class GeneradorPartidas {
    private ConfiguracionesCrudService crudService = new ConfiguracionesCrudService();
    
    public Partida generarNuevaPartida() throws ExceptionGenerica{
        return new Partida(crudService.obtenerReglasPartida(), crudService.obtenerReglasPunteos());
    }
}
