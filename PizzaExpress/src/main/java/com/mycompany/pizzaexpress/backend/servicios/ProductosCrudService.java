/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pizzaexpress.backend.servicios;

import com.mycompany.pizzaexpress.backend.crudIntefaces.BusquedaConParametroInt;
import com.mycompany.pizzaexpress.backend.crudIntefaces.CreacionEntidad;
import com.mycompany.pizzaexpress.backend.crudIntefaces.EdicionEntidad;
import com.mycompany.pizzaexpress.backend.exceptions.CamposVaciosException;
import com.mycompany.pizzaexpress.backend.exceptions.DatosMuyLargosException;
import com.mycompany.pizzaexpress.backend.exceptions.EntidadDuplicadaException;
import com.mycompany.pizzaexpress.backend.exceptions.ExceptionGenerica;
import com.mycompany.pizzaexpress.backend.db.ProductosDB;
import com.mycompany.pizzaexpress.backend.modelos.Producto;
import java.util.ArrayList;

/**
 *
 * @author edu
 */
public class ProductosCrudService implements CreacionEntidad<Producto>,
        EdicionEntidad<Producto>, BusquedaConParametroInt<Producto>{
    
    private final ProductosDB db = new ProductosDB();

    @Override
    public ArrayList buscarConUnParametroInt(int param) throws ExceptionGenerica {
        return db.buscarConUnParametroInt(param);
    }

    @Override
    public void crear(Producto entidad) throws ExceptionGenerica {
        if (!entidad.datosCompletos()) {
            throw new CamposVaciosException();
        }
        if (!entidad.esValido()) {
            throw new DatosMuyLargosException();
        }

        Producto producto = this.db.buscarProductoEnSucursal(entidad.getNombre(), entidad.getId_sucursal());

        if (producto != null) {
            throw new EntidadDuplicadaException("ya existe el product" + entidad.getNombre() + " en esta sucursal");
        }

        db.crear(entidad);
    }

    @Override
    public void editar(Producto entidad) throws ExceptionGenerica {
        if (!entidad.datosCompletos()) {
            throw new CamposVaciosException();
        }

        if (!entidad.esValido()) {
            throw new DatosMuyLargosException();
        }

        /**
         * Se verifica que el nombre no sea el de algun producto anterior
         */
        Producto anterior = this.db.buscarProductoEnSucursal(entidad.getNombre(), entidad.getId_sucursal());
        if (anterior != null) {// Si exite  un nombre así dentro de la sucursal
            // si existe dentro de la sucursal un nombre así
            // y no es del mismo id
            if (anterior.getId() != entidad.getId()) {
                throw new EntidadDuplicadaException("ya existe la sucursal con nombre " + entidad.getNombre());
            }
        }

        db.editar(entidad);
    } 
    
    public Producto buscarProductoEnSucursal (String nombre, int idSucursal) throws ExceptionGenerica{
        return db.buscarProductoEnSucursal(nombre, idSucursal);
    }

}
