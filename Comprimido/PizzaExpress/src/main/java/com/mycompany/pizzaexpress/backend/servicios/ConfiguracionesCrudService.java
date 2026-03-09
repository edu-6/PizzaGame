/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pizzaexpress.backend.servicios;

import com.mycompany.pizzaexpress.backend.db.ConfiguracionesDB;
import com.mycompany.pizzaexpress.backend.exceptions.ExceptionGenerica;
import com.mycompany.pizzaexpress.backend.modelos.ConfiguracionDePartida;
import com.mycompany.pizzaexpress.backend.modelos.ConfiguracionPunteos;

/**
 *
 * @author edu
 */
public class ConfiguracionesCrudService {
    private ConfiguracionesDB db = new ConfiguracionesDB();
    
    
    public ConfiguracionDePartida obtenerReglasPartida() throws ExceptionGenerica {
        return db.obtenerReglasPartida();
    }

    public ConfiguracionPunteos obtenerReglasPunteos () throws ExceptionGenerica {
        return db.obtenerReglasPunteos();
    }
    
    public void editarReglasPunteos (ConfiguracionPunteos config) throws ExceptionGenerica{
        if (config == null) throw new ExceptionGenerica("Objeto nulo");
        validarPunteos(config);
        db.editarReglasPunteos(config);
        
    }
    
    public void editarReglasPartida (ConfiguracionDePartida config) throws ExceptionGenerica{
        if (config == null) throw new ExceptionGenerica("Objeto nulo");
        validarReglasPartida(config);
        db.editarReglasPartida(config);
        
    }
    
    
    private void validarReglasPartida(ConfiguracionDePartida c) throws ExceptionGenerica {
        if (c.getTiempoPartida() <= 0){
            throw new ExceptionGenerica("El tiempo de partida debe ser mayor a 0");
        }
        if (c.getTiempoNivel1() <= 0){
            throw new ExceptionGenerica("El tiempo del nivel 1 debe ser mayor a 0");
        }
        if (c.getTiempoNivel2() <= 0){
            throw new ExceptionGenerica("El tiempo del nivel 2 debe ser mayor a 0");
        }
        if (c.getTiempoNivel3() <= 0){
            throw new ExceptionGenerica("El tiempo del nivel 3 debe ser mayor a 0");
        }

        if (c.getTiempoNivel2() >= c.getTiempoNivel1()){
            throw new ExceptionGenerica("Dificultad inválida: El tiempo del nivel 2 debe ser menor al nivel 1");
        }
        if (c.getTiempoNivel3() >= c.getTiempoNivel2()){ 
            throw new ExceptionGenerica("Dificultad inválida: El tiempo del nivel 3 debe ser menor al nivel 2");
        }

        if (c.getPuntosNivel2() <= 0){
            throw new ExceptionGenerica("Los puntos para el nivel 2 deben ser mayores a 0");
        }
        if (c.getPuntosNivel3() <= 0){
            throw new ExceptionGenerica("Los puntos para el nivel 3 deben ser mayores a 0");
        }
        
        if (c.getPuntosNivel3() <= c.getPuntosNivel2()) {
            throw new ExceptionGenerica("El nivel 3 debe requerir más puntos que el nivel 2");
        }
      
        if (c.getTiempoEntrePedidos() >= c.getTiempoPartida()){
             throw new ExceptionGenerica("El tiempo entre pedidos no puede ser igual ni mayor al tiempo de la partida");
        }
        if (c.getLimitePedidosActivos() <= 0 ){
             throw new ExceptionGenerica("El limite de pedidos activos solo puede ser mayor a 0");
        } 
           
    }


    private void validarPunteos(ConfiguracionPunteos p) throws ExceptionGenerica {
        
        if (p.getBonoPedidoCorrecto() <= 0){
            throw new ExceptionGenerica("El bono de pedido correcto debe ser positivo");
        }
        if (p.getBonoTiempoOptimo() <= 0){
            throw new ExceptionGenerica("El bono de tiempo óptimo debe ser positivo");
        }
        if (p.getBonoEficiencia() <= 0){
            throw new ExceptionGenerica("El bono de eficiencia debe ser positivo");
        }

        
        if (p.getPedidoCancelado() <= 0){
            throw new ExceptionGenerica("La penalización por cancelación debe ser mayor a 0");
        }
        if (p.getPedidoIncompleto() <= 0) {
            throw new ExceptionGenerica("La penalización por pedido incompleto debe ser mayor a 0");
        }
        
        if (p.getPedidoCancelado() >= p.getPedidoIncompleto()) {
            throw new ExceptionGenerica("La penalización por cancelar debe ser menor a la de dejarlo incompleto");
        }
    }
}
