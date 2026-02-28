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
public interface BuscarTodos <T>{
    public ArrayList<T> readAll() throws ExceptionGenerica;
}
