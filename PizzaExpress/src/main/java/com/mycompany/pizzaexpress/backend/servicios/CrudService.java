/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pizzaexpress.backend.servicios;

import com.mycompany.pizzaexpress.modelos.Entidad;

/**
 *
 * @author edu
 */
public abstract  class CrudService {
    
    public abstract void crear(Entidad entidad);
    public abstract void eliminar(int id);
    public abstract void buscar(int id);
    public abstract void actualizar(Entidad entidad);
    
    
}
