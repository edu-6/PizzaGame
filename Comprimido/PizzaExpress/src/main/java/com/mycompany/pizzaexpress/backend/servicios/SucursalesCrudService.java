/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pizzaexpress.backend.servicios;

import com.mycompany.pizzaexpress.backend.crudIntefaces.BuscarTodos;
import com.mycompany.pizzaexpress.backend.crudIntefaces.CreacionEntidad;
import com.mycompany.pizzaexpress.backend.crudIntefaces.EdicionEntidad;
import com.mycompany.pizzaexpress.backend.crudIntefaces.EliminacionEntidad;
import com.mycompany.pizzaexpress.backend.crudIntefaces.LecturaEntidad;
import com.mycompany.pizzaexpress.backend.db.SucursalesDB;
import com.mycompany.pizzaexpress.backend.exceptions.CamposVaciosException;
import com.mycompany.pizzaexpress.backend.exceptions.DatosMuyLargosException;
import com.mycompany.pizzaexpress.backend.exceptions.EntidadDuplicadaException;
import com.mycompany.pizzaexpress.backend.exceptions.ExceptionGenerica;
import com.mycompany.pizzaexpress.backend.exceptions.NotFoundException;
import com.mycompany.pizzaexpress.backend.modelos.Sucursal;
import java.util.ArrayList;

/**
 * la clase crud service va servir para hacer las validaciones antes de proceder
 * a enviarlos a la db
 *
 * @author edu
 */
public class SucursalesCrudService implements CreacionEntidad<Sucursal>, EdicionEntidad<Sucursal>, EliminacionEntidad,
        BuscarTodos<Sucursal>, LecturaEntidad<Sucursal> {

    private final SucursalesDB db = new SucursalesDB();

    @Override
    public void crear(Sucursal entidad) throws ExceptionGenerica {
        if (!entidad.datosCompletos()) {
            throw new CamposVaciosException();
        }
        if (!entidad.esValido()) {
            throw new DatosMuyLargosException();
        }

        if (db.yaExiste(entidad.getNombre())) {
            throw new EntidadDuplicadaException("ya existe la sucursal con nombre " + entidad.getNombre());
        }

        db.crear(entidad);
    }

    @Override
    public void editar(Sucursal entidad) throws ExceptionGenerica {
        if (!entidad.datosCompletos()) {
            throw new ExceptionGenerica("Rellene los campos ");
        }

        if (!entidad.esValido()) {
            throw new ExceptionGenerica("Los campos exceden el numero de caracteres.");
        }

        /**
         * Se verifica que el nombre no sea el de alguna sucursal anterior
         */
        Sucursal anterior = db.buscar(entidad.getId());
        if (!anterior.getNombre().equals(entidad.getNombre())) { // si no son iguales los nombres
            if (db.yaExiste(entidad.getNombre())) {
                throw new EntidadDuplicadaException("ya existe la sucursal con nombre " + entidad.getNombre());
            }
        }

        db.editar(entidad);
    }

    @Override
    public void eliminar(int id) throws ExceptionGenerica {
        db.eliminar(id);
    }

    @Override
    public Sucursal buscar(int id) throws ExceptionGenerica {
        Sucursal sucursal = db.buscar(id);
        if (sucursal == null) {
            throw new NotFoundException("No se encontró ninguna sucursal con el ID: " + id);
        }
        return sucursal;

    }
    
    
    public Sucursal getSucursalByName(String nombre) throws ExceptionGenerica {
        Sucursal sucursal = db.selectByName(nombre);
        if (sucursal == null) {
            throw new NotFoundException("No se encontró ninguna sucursal con nombre: " + nombre);
        }
        return sucursal;

    }

    @Override
    public ArrayList<Sucursal> seleccionarTodos() throws ExceptionGenerica {
        return db.seleccionarTodos();
    }
}
