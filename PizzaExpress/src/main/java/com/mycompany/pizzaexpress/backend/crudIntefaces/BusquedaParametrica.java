/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.pizzaexpress.backend.crudIntefaces;

import com.mycompany.pizzaexpress.backend.exceptions.ExceptionGenerica;
import java.util.ArrayList;

/**
 *
 * @author edu
 */
// es una iterefaz que permite buscar armando una clase  con parametros y luego usandola para buscarlo 
public interface BusquedaParametrica <B,A> {
    
     public ArrayList<A> buscarVariosConFiltro(B busqueda) throws ExceptionGenerica;
    
}
