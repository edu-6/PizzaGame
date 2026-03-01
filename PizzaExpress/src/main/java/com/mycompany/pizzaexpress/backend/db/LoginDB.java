/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pizzaexpress.backend.db;

import com.mycompany.pizzaexpress.backend.exceptions.CamposVaciosException;
import com.mycompany.pizzaexpress.backend.exceptions.ExceptionGenerica;
import com.mycompany.pizzaexpress.backend.modelos.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author edu
 */
public class LoginDB extends GenericDB {

   private static final String LOGIN_USUARIO = "SELECT nickname, nombre, rol, id_sucursal FROM usuario WHERE nickname = ? AND contraseña = ?";
    private static final String NOMBRE_SUCURSAL = "SELECT nombre FROM sucursal WHERE id = ?";

    public Usuario loguear(String nickname, String contraseña) throws ExceptionGenerica {
        
        if(nickname.isBlank() || contraseña.isBlank()){
            throw new CamposVaciosException();
        }
        Usuario usuario = null;

        try (Connection conn = ConexionDB.getConnection(); PreparedStatement stmt = conn.prepareStatement(LOGIN_USUARIO)) {

            stmt.setString(1, nickname);
            stmt.setString(2, contraseña);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String rol = rs.getString("rol");
                    String nick = rs.getString("nickname");
                    String nombreReal = rs.getString("nombre");
                    int idSucursal = rs.getInt("id_sucursal");
                    boolean tieneSucursal = !rs.wasNull();

                    usuario = new Usuario(rol, nick);
                    usuario.setNombre(nombreReal);

                    if (tieneSucursal) {
                        usuario.setIdSucursal(idSucursal);
                        usuario.setNombreSucursal(obtenerNombreSucursal(idSucursal));
                    }
                }
            }
        } catch (SQLException e) {
            throw new ExceptionGenerica("Error al hacer login en la base de datos");
        }

        if (usuario == null) {
            throw new ExceptionGenerica("Credenciales incorrectas");
        }

        return usuario;
    }

    private String obtenerNombreSucursal(int id) throws ExceptionGenerica {
        try (Connection conn = ConexionDB.getConnection(); PreparedStatement stmt = conn.prepareStatement(NOMBRE_SUCURSAL)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("nombre");
                }
            }
        } catch (SQLException e) {
            throw new ExceptionGenerica("Error al buscar el nombre de la sucursal");
        }
        return null;
    }
}
