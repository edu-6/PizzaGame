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
 * @param <T>
 */
public interface LecturaEntidad <T>{
    public  T leerEntidad(int id) throws ExceptionGenerica;
    /*
    public ArrayList<T> buscarEntidades(L busqueda) throws ExceptionGenerica;*/
}
