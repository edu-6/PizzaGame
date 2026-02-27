/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.pizzaexpress.backend.crudIntefaces;

import com.mycompany.pizzaexpress.backend.exceptions.ExceptionGenerica;

/**
 *
 * @author edu
 * @param <T>
 */
public interface EliminacionEntidad {
    
    /**
     *
     * @param id
     * @throws com.mycompany.pizzaexpress.backend.exceptions.ExceptionGenerica
     * @throws ExceptionGenerica
     */
    public void eliminarEntidad(int id) throws ExceptionGenerica;
    
}
