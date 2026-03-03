/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pizzaexpress.backend.db;

import com.mycompany.pizzaexpress.backend.crudIntefaces.BuscarTodos;
import com.mycompany.pizzaexpress.backend.crudIntefaces.CreacionEntidad;
import com.mycompany.pizzaexpress.backend.crudIntefaces.EdicionEntidad;
import com.mycompany.pizzaexpress.backend.crudIntefaces.EliminacionEntidad;
import com.mycompany.pizzaexpress.backend.crudIntefaces.LecturaEntidad;
import com.mycompany.pizzaexpress.backend.exceptions.ExceptionGenerica;
import com.mycompany.pizzaexpress.backend.modelos.Sucursal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * claseDB va servir para realizar las querys en la db unicamente desupés que
 * hayan sido verificados los valores
 *
 * @author edu
 */
public class SucursalesDB extends GenericDB implements BuscarTodos<Sucursal>, CreacionEntidad<Sucursal>, EdicionEntidad<Sucursal>, EliminacionEntidad, LecturaEntidad<Sucursal> {

    private static final String CREAR = "INSERT INTO sucursal (nombre, ubicacion) VALUES (?, ?)";
    private static final String ACTUALIZAR = "UPDATE sucursal SET nombre = ?, ubicacion = ? WHERE id_sucursal = ?";
    private static final String ELIMINAR = "DELETE FROM sucursal WHERE id_sucursal = ?";
    private static final String BUSCAR_POR_ID = "SELECT * FROM sucursal WHERE id_sucursal = ?";
    private static final String BUSCAR_POR_NOMBRE = "SELECT * FROM sucursal WHERE nombre = ?";
    private static final String SELECCIONAR_TODOS = "SELECT * FROM sucursal ";
    
    public boolean yaExiste(String nombre) throws ExceptionGenerica {
        return this.existeEntidadByString(nombre, BUSCAR_POR_NOMBRE);
    }

    @Override
    public void crear(Sucursal entidad) throws ExceptionGenerica {
        try (Connection conn = ConexionDB.getConnection(); PreparedStatement stmt = conn.prepareStatement(CREAR)) {
            stmt.setString(1, entidad.getNombre());
            stmt.setString(2, entidad.getUbicacion());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new ExceptionGenerica("Error al crear sucursal: " + e.getMessage());
        }
    }

    @Override
    public void editar(Sucursal entidad) throws ExceptionGenerica {
        try (Connection conn = ConexionDB.getConnection(); PreparedStatement stmt = conn.prepareStatement(ACTUALIZAR)) {
            stmt.setString(1, entidad.getNombre());
            stmt.setString(2, entidad.getUbicacion());
            stmt.setInt(3, entidad.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new ExceptionGenerica("Error al editar sucursal: " + e.getMessage());
        }
    }

    @Override
    public void eliminar(int id) throws ExceptionGenerica {
        try (Connection conn = ConexionDB.getConnection(); PreparedStatement stmt = conn.prepareStatement(ELIMINAR)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new ExceptionGenerica("Error al eliminar sucursal: " + e.getMessage());
        }
    }

    @Override
    public Sucursal buscar(int id) throws ExceptionGenerica {
        try (Connection conn = ConexionDB.getConnection(); PreparedStatement stmt = conn.prepareStatement(BUSCAR_POR_ID)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return extraer(rs);
                }
            }
        } catch (SQLException e) {
            throw new ExceptionGenerica("Error al leer sucursal: " + e.getMessage());
        }
        return null;
    }

    private Sucursal extraer(ResultSet result) throws SQLException {
        return new Sucursal(
                result.getString("ubicacion"),
                result.getString("nombre"),
                result.getInt("id_sucursal")
        );
    }
    
    
    
    public Sucursal selectByName(String name) throws ExceptionGenerica {
        try (Connection conn = ConexionDB.getConnection(); PreparedStatement stmt = conn.prepareStatement(BUSCAR_POR_NOMBRE)) {
            stmt.setString(1, name);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return extraer(rs);
                }
            }
        } catch (SQLException e) {
            throw new ExceptionGenerica("Error al leer sucursal: " + e.getMessage());
        }
        return null;
    }

    @Override
    public ArrayList<Sucursal> seleccionarTodos() throws ExceptionGenerica {
        ArrayList<Sucursal>  lista = new ArrayList();
       try (Connection conn = ConexionDB.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(SELECCIONAR_TODOS); 
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                lista.add(extraer(rs));
            }
            
        } catch (SQLException e) {
            throw new ExceptionGenerica("Error al obtener todas las sucursales: " + e.getMessage());
        }
        return lista;
    }
    
}
