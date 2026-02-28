/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pizzaexpress.backend.db;

import com.mycompany.pizzaexpress.backend.exceptions.ExceptionGenerica;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author edu
 */
public class GenericDB {
    
    public boolean existeEntidadByString(String param, String query) throws ExceptionGenerica{
        try (Connection conn = ConexionDB.getConnection(); PreparedStatement statment = conn.prepareStatement(query)) {
            statment.setString(1, param);
            ResultSet result = statment.executeQuery();
            return result.next();
        } catch (SQLException e) {
            throw new ExceptionGenerica("Error al buscar entidad: " + e.getMessage());
        }
    }
}
