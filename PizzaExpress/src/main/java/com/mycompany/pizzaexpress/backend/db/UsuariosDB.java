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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author edu
 */
public class UsuariosDB extends GenericDB implements CreacionEntidad<Usuario>, EdicionEntidad<Usuario>, EliminacionEntidad {

    private static final String CREAR = "INSERT INTO usuario (nickname, nombre, contraseña, rol, id_sucursal) VALUES (?, ?, ?, ?, ?)";
    private static final String ACTUALIZAR = "UPDATE usuario SET nickname = ?, nombre = ?, contraseña = ?, rol = ?, id_sucursal = ? WHERE id = ?";
    private static final String ELIMINAR = "DELETE FROM usuario WHERE id = ?";
    private static final String BUSCAR_POR_ID = "SELECT u.*, s.nombre AS nombre_sucursal FROM usuario u LEFT JOIN sucursal s ON u.id_sucursal = s.id WHERE u.id = ?";

    public boolean nicknameYaExiste(String nickname) throws ExceptionGenerica {
        return this.existeEntidadByString(nickname, "SELECT nickname FROM usuario WHERE nickname = ?");
    }

    @Override
    public void crear(Usuario entidad) throws ExceptionGenerica {
        try (Connection conn = ConexionDB.getConnection(); PreparedStatement stmt = conn.prepareStatement(CREAR)) {
            stmt.setString(1, entidad.getNickname());
            stmt.setString(2, entidad.getNombre());
            stmt.setString(3, entidad.getContraseña());
            stmt.setString(4, entidad.getRol());
            if (entidad.getIdSucursal() > 0) {
                stmt.setInt(5, entidad.getIdSucursal());
            } else {
                stmt.setNull(5, java.sql.Types.INTEGER);
            }
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new ExceptionGenerica("Error al crear usuario: " + e.getMessage());
        }
    }

    @Override
    public void editar(Usuario entidad) throws ExceptionGenerica {
        try (Connection conn = ConexionDB.getConnection(); PreparedStatement stmt = conn.prepareStatement(ACTUALIZAR)) {
            stmt.setString(1, entidad.getNickname());
            stmt.setString(2, entidad.getNombre());
            stmt.setString(3, entidad.getContraseña());
            stmt.setString(4, entidad.getRol());
            if (entidad.getIdSucursal() > 0) {
                stmt.setInt(5, entidad.getIdSucursal());
            } else {
                stmt.setNull(5, java.sql.Types.INTEGER);
            }
            stmt.setInt(6, entidad.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new ExceptionGenerica("Error al editar usuario: " + e.getMessage());
        }
    }

    @Override
    public void eliminar(int id) throws ExceptionGenerica {
        try (Connection conn = ConexionDB.getConnection(); PreparedStatement stmt = conn.prepareStatement(ELIMINAR)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new ExceptionGenerica("Error al eliminar usuario: " + e.getMessage());
        }
    }

    public Usuario buscar(int id) throws ExceptionGenerica {
        try (Connection conn = ConexionDB.getConnection(); PreparedStatement stmt = conn.prepareStatement(BUSCAR_POR_ID)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return extraer(rs);
                }
            }
        } catch (SQLException e) {
            throw new ExceptionGenerica("Error al buscar usuario por ID: " + e.getMessage());
        }
        return null;
    }

    private Usuario extraer(ResultSet rs) throws SQLException {
        return new Usuario(
                rs.getInt("id"),
                rs.getString("contraseña"),
                rs.getString("nombre"),
                rs.getString("rol"),
                rs.getString("nickname"),
                rs.getString("nombre_sucursal"),
                rs.getInt("id_sucursal")
        );
    }
}
