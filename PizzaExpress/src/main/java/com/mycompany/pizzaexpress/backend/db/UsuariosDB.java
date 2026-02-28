/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pizzaexpress.backend.db;
import com.mycompany.pizzaexpress.backend.crudIntefaces.CreacionEntidad;
import com.mycompany.pizzaexpress.backend.crudIntefaces.EdicionEntidad;
import com.mycompany.pizzaexpress.backend.crudIntefaces.EliminacionEntidad;
import com.mycompany.pizzaexpress.backend.exceptions.ExceptionGenerica;
import com.mycompany.pizzaexpress.backend.modelos.Usuario;

/**
 *
 * @author edu
 */
public class UsuariosDB implements CreacionEntidad<Usuario>, EdicionEntidad<Usuario>, EliminacionEntidad{

    @Override
    public void create(Usuario entidad) throws ExceptionGenerica {

    }

    @Override
    public void update(Usuario entidad) throws ExceptionGenerica {
    }

    @Override
    public void delete(int id) throws ExceptionGenerica {
        
    }

}
