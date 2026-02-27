/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pizzaexpress.backend.db;

import com.mycompany.pizzaexpress.backend.crudIntefaces.CreacionEntidad;
import com.mycompany.pizzaexpress.backend.crudIntefaces.EdicionEntidad;
import com.mycompany.pizzaexpress.backend.crudIntefaces.EliminacionEntidad;
import com.mycompany.pizzaexpress.backend.crudIntefaces.LecturaEntidad;
import com.mycompany.pizzaexpress.backend.exceptions.ExceptionGenerica;
import com.mycompany.pizzaexpress.backend.modelos.Sucursal;

/**
 * claseDB va servir para realizar las querys en la db unicamente desupés que hayan sido verificados los valores
 * @author edu
 */
public class SucursalesDB extends GenericDB implements CreacionEntidad<Sucursal>,EdicionEntidad<Sucursal>,EliminacionEntidad,LecturaEntidad <Sucursal> {

    @Override
    public void crearEntidad(Sucursal entidad) throws ExceptionGenerica {
       
    }

    @Override
    public void editarEntidad(Sucursal entidad) throws ExceptionGenerica {
        
    }

    @Override
    public void eliminarEntidad(int id) throws ExceptionGenerica {
        
    }

    @Override
    public Sucursal leerEntidad(int id) throws ExceptionGenerica {
        return null;
    }
    


}
