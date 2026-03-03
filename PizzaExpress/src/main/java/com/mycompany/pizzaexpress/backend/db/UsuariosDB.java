/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pizzaexpress.backend.db;

import com.mycompany.pizzaexpress.backend.crudIntefaces.BusquedaParametrica;
import com.mycompany.pizzaexpress.backend.crudIntefaces.BusquedaPorString;
import com.mycompany.pizzaexpress.backend.crudIntefaces.CreacionEntidad;
import com.mycompany.pizzaexpress.backend.crudIntefaces.EdicionEntidad;
import com.mycompany.pizzaexpress.backend.crudIntefaces.EliminacionEntidad;
import com.mycompany.pizzaexpress.backend.exceptions.ExceptionGenerica;
import com.mycompany.pizzaexpress.backend.modelos.BusquedaUsuarios;
import com.mycompany.pizzaexpress.backend.modelos.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author edu
 */
public class UsuariosDB extends GenericDB implements CreacionEntidad<Usuario>, EdicionEntidad<Usuario>, EliminacionEntidad,
        BusquedaPorString<Usuario>, BusquedaParametrica<BusquedaUsuarios, Usuario> {

    private static final String CREAR = "INSERT INTO usuario (nickname, nombre, contraseña, rol, id_sucursal) VALUES (?, ?, ?, ?, ?)";
    private static final String ACTUALIZAR = "UPDATE usuario SET nickname = ?, nombre = ?, contraseña = ?, rol = ?, id_sucursal = ? WHERE id_usuario = ?";
    private static final String ELIMINAR = "DELETE FROM usuario WHERE id_usuario = ?";
    private static final String BUSCAR_POR_ID = "SELECT u.*, s.nombre AS nombre_sucursal FROM usuario u LEFT JOIN sucursal s ON u.id_sucursal = s.id_sucursal WHERE u.id_usuario = ?";
    private static final String BUSQUEDA_POR_NICKNAME_O_NOMBRE = "SELECT u.*, s.nombre  AS nombre_sucursal FROM usuario u  LEFT JOIN sucursal s ON u.id_sucursal = s.id_sucursal "
            + "WHERE (u.nickname = ? OR u.nombre = ?)";
    private static final String BUSQUEDA_POR_ROL = "SELECT u.*, s.nombre  AS nombre_sucursal FROM usuario u  LEFT JOIN sucursal s ON u.id_sucursal = s.id_sucursal "
            + "WHERE u.rol = ?";

    private static final String FILTRO_POR_SUCURSAL = " AND  s.nombre = ?";

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
            if (entidad.getIdSucursal() > 0 && !entidad.getRol().equals("ADMIN_SISTEMA")) {
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
            if (entidad.getIdSucursal() > 0 && !entidad.getRol().equals("ADMIN_SISTEMA")) {
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
                rs.getInt("id_usuario"),
                rs.getString("contraseña"),
                rs.getString("nombre"),
                rs.getString("rol"),
                rs.getString("nickname"),
                rs.getString("nombre_sucursal"),
                rs.getInt("id_sucursal")
        );
    }

    @Override
    public ArrayList<Usuario> buscarPorString(String parametro) throws ExceptionGenerica {
        ArrayList<Usuario> lista = new ArrayList();
        try (Connection conn = ConexionDB.getConnection(); PreparedStatement stm = conn.prepareStatement(BUSQUEDA_POR_NICKNAME_O_NOMBRE)) {
            stm.setString(1, parametro);
            stm.setString(2, parametro);
            try (ResultSet rs = stm.executeQuery()) {
                while (rs.next()) {
                    lista.add(extraer(rs));
                }
            }
        } catch (SQLException e) {
            throw new ExceptionGenerica("Error al realizar la busqueda" + e.getMessage());
        }
        return lista;
    }

    @Override
    /**
     * Genera una busqueda segun los parametros
     */
    public ArrayList<Usuario> buscarVariosConFiltro(BusquedaUsuarios busqueda) throws ExceptionGenerica {
        boolean usaDosFiltros =!busqueda.getRol().equals("ADMIN_SISTEMA");
        String consulta = BUSQUEDA_POR_ROL;
        
        if(usaDosFiltros){
            consulta += FILTRO_POR_SUCURSAL;
        }

        ArrayList<Usuario> lista = new ArrayList();
        try (Connection conn = ConexionDB.getConnection(); PreparedStatement stm = conn.prepareStatement(consulta)) {
            stm.setString(1,  busqueda.getRol());
            if(usaDosFiltros){
                stm.setString(2, busqueda.getSucursal());
            }
            
            try (ResultSet rs = stm.executeQuery()) {
                while (rs.next()) {
                    lista.add(extraer(rs));
                }
            }
        } catch (SQLException e) {
            throw new ExceptionGenerica("Error al realizar la busqueda" + e.getMessage());
        }
        return lista;

    }

}
