/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pizzaexpress.backend.servicios;

import com.mycompany.pizzaexpress.backend.crudIntefaces.BusquedaParametrica;
import com.mycompany.pizzaexpress.backend.crudIntefaces.BusquedaPorString;
import com.mycompany.pizzaexpress.backend.crudIntefaces.CreacionEntidad;
import com.mycompany.pizzaexpress.backend.crudIntefaces.EdicionEntidad;
import com.mycompany.pizzaexpress.backend.crudIntefaces.EliminacionEntidad;
import com.mycompany.pizzaexpress.backend.db.UsuariosDB;
import com.mycompany.pizzaexpress.backend.exceptions.CamposVaciosException;
import com.mycompany.pizzaexpress.backend.exceptions.DatosMuyLargosException;
import com.mycompany.pizzaexpress.backend.exceptions.EntidadDuplicadaException;
import com.mycompany.pizzaexpress.backend.exceptions.ExceptionGenerica;
import com.mycompany.pizzaexpress.backend.modelos.BusquedaUsuarios;
import com.mycompany.pizzaexpress.backend.modelos.Usuario;
import java.util.ArrayList;

/**
 *
 * @author edu
 */
public class UsuariosCrudService implements CreacionEntidad<Usuario>, EdicionEntidad<Usuario>,
        EliminacionEntidad, BusquedaPorString<Usuario>,BusquedaParametrica <BusquedaUsuarios,Usuario> {

    private final UsuariosDB db = new UsuariosDB();

    @Override
    public void crear(Usuario entidad) throws ExceptionGenerica {
        if (!entidad.datosCompletos()){
            throw new CamposVaciosException();
        }
        if (!entidad.tamañoDedatosValidos()){
            throw new DatosMuyLargosException();
        }
        
        if (db.nicknameYaExiste(entidad.getNickname())) {
            throw new EntidadDuplicadaException("El nickname '" + entidad.getNickname() + "' ya está ocupado.");
        }
        db.crear(entidad);
    }

    @Override
    public void editar(Usuario entidad) throws ExceptionGenerica {
        if (!entidad.datosCompletos()){
            throw new CamposVaciosException();
        }
        if (!entidad.tamañoDedatosValidos()){
            throw new DatosMuyLargosException();
        }

        // si el nickname no cambió, se busca si  reamente le pertenecía 
        Usuario anterior = db.buscar(entidad.getId());
        if (!anterior.getNickname().equals(entidad.getNickname())) {
            if (db.nicknameYaExiste(entidad.getNickname())) {
                throw new ExceptionGenerica("El nickname " + entidad.getNickname() + " ya está en uso");
            }
        }

        db.editar(entidad);
    }

    @Override
    public void eliminar(int id) throws ExceptionGenerica {
        db.eliminar(id);
    }

    @Override
    public ArrayList<Usuario> buscarPorString(String parametro) throws ExceptionGenerica {
       if(parametro.isBlank()){
           throw new ExceptionGenerica("Ingrese una cadena para buscar");
       }
       return this.db.buscarPorString(parametro);
    }

    @Override
    public ArrayList<Usuario> buscarVariosConFiltro(BusquedaUsuarios busqueda) throws ExceptionGenerica {
        return this.db.buscarVariosConFiltro(busqueda);
    }
    
    
    
}
