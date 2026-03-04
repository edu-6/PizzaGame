/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pizzaexpress.backend.db;

import com.mycompany.pizzaexpress.backend.crudIntefaces.BusquedaConParametroInt;
import com.mycompany.pizzaexpress.backend.crudIntefaces.CreacionEntidad;
import com.mycompany.pizzaexpress.backend.crudIntefaces.EdicionEntidad;
import com.mycompany.pizzaexpress.backend.exceptions.ExceptionGenerica;
import com.mycompany.pizzaexpress.backend.modelos.Producto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author edu
 */public class ProductosDB extends GenericDB implements CreacionEntidad<Producto>,
        EdicionEntidad<Producto>, BusquedaConParametroInt<Producto> {

    private static final String CREAR = "INSERT INTO producto (nombre, descripcion, id_sucursal, activo) VALUES (?, ?,?,?) ";
    private static final String EDITAR = "UPDATE  producto SET nombre = ?, descripcion = ?, activo = ? WHERE  id_producto = ?";
    private static final String BUSCAR_TODOS_DE_SUCURSAL = "SELECT * FROM   producto WHERE id_sucursal = ?";
    private static final String BUSCAR_PRODUCTO_DENTRO_DE_SUCURSAL = " SELECT"
            + " * FROM producto WHERE producto_nombre = ? AND id_sucursal = ?";

    @Override
    public void crear(Producto entidad) throws ExceptionGenerica {
        try (Connection conn = ConexionDB.getConnection(); PreparedStatement stm = conn.prepareStatement(CREAR)) {
            stm.setString(1, entidad.getNombre());
            stm.setString(2, entidad.getDescripcion());
            stm.setInt(3, entidad.getId());
            stm.setBoolean(4, entidad.isActivo());

            stm.executeUpdate();

        } catch (SQLException e) {
            throw new ExceptionGenerica(" Error al crear el producto");

        }
    }

    @Override
    public void editar(Producto entidad) throws ExceptionGenerica {
        try (Connection conn = ConexionDB.getConnection(); PreparedStatement stm = conn.prepareStatement(EDITAR)) {
            stm.setString(1, entidad.getNombre());
            stm.setString(2, entidad.getDescripcion());
            stm.setBoolean(3, entidad.isActivo());
            stm.setInt(4, entidad.getId());
            stm.executeUpdate();

        } catch (SQLException e) {
            throw new ExceptionGenerica(" Error al editar al producto");
        }

    }

    @Override
    /**
     * Sirve para buscar todos los productos de una sucursal
     */
    public ArrayList<Producto> buscarConUnParametroInt(int param) throws ExceptionGenerica {
        ArrayList<Producto> lista = new ArrayList();
        try (Connection conn = ConexionDB.getConnection(); PreparedStatement stm = conn.prepareStatement(BUSCAR_TODOS_DE_SUCURSAL)) {
            stm.setInt(1, param);
            ResultSet rs = stm.executeQuery();
            
            while(rs.next()){
                lista.add(extraer(rs));
            }
        } catch (SQLException e) {
            throw new ExceptionGenerica("Error al buscar productos");
        }
        return lista;
    }
    
    
    private  Producto extraer (ResultSet rs) throws ExceptionGenerica{
        try {
            return new Producto(
                    rs.getString("nombre"),
                    rs.getBoolean("activo"),
                    rs.getString("descripcion"),
                    rs.getInt("id")
            );
        } catch (SQLException e) {
            throw new ExceptionGenerica("Error al obtener el producto");
        }
    }
    
    
    public Producto buscarProductoEnSucursal(String nombre, int idSucursal) throws ExceptionGenerica{
        try (Connection conn = ConexionDB.getConnection(); PreparedStatement stm = conn.prepareStatement(BUSCAR_PRODUCTO_DENTRO_DE_SUCURSAL)) {
            stm.setString(1, nombre);
            stm.setInt(2, idSucursal);
            
            ResultSet rs = stm.executeQuery();
            if(rs.next()){
                return extraer(rs);
            }
        } catch (SQLException e) {
            throw new ExceptionGenerica(" Error al buscar el producto");
        }
        return null;
    }

}

