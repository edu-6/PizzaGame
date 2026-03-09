/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pizzaexpress.backend.db.reportes;

import com.mycompany.pizzaexpress.backend.crudIntefaces.BuscarTodos;
import com.mycompany.pizzaexpress.backend.crudIntefaces.BusquedaConParametroInt;
import com.mycompany.pizzaexpress.backend.db.ConexionDB;
import com.mycompany.pizzaexpress.backend.exceptions.ExceptionGenerica;
import com.mycompany.pizzaexpress.backend.modelos.reportes.UsuarioReporte;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author edu
 */
public class ReportesJugadoresDB implements BuscarTodos<UsuarioReporte>, BusquedaConParametroInt<UsuarioReporte> {

    private static final String RANKING_GLOBAL = "SELECT s.nombre AS nombre_sucursal, u.nickname, u.nombre, "
            + "COUNT(p.id_partida) AS partidas_jugadas, SUM(p.puntos_totales) AS puntos_acumulados, "
            + "AVG(p.puntos_totales) AS promedio_puntos, MAX(p.nivel_alcanzado) AS max_nivel "
            + "FROM usuario u JOIN partida p ON u.id_usuario = p.id_usuario "
            + "JOIN sucursal s ON p.id_sucursal = s.id_sucursal "
            + "WHERE u.rol = 'COCINERO' "
            + "GROUP BY u.id_usuario, s.nombre ORDER BY puntos_acumulados DESC";

    private static final String RANKING_POR_SUCURSAL = "SELECT s.nombre AS nombre_sucursal, u.nickname, u.nombre, "
            + "COUNT(p.id_partida) AS partidas_jugadas, SUM(p.puntos_totales) AS puntos_acumulados, "
            + "AVG(p.puntos_totales) AS promedio_puntos, MAX(p.nivel_alcanzado) AS max_nivel "
            + "FROM usuario u JOIN partida p ON u.id_usuario = p.id_usuario "
            + "JOIN sucursal s ON p.id_sucursal = s.id_sucursal "
            + "WHERE p.id_sucursal = ? AND u.rol = 'COCINERO' "
            + "GROUP BY u.id_usuario, s.nombre ORDER BY puntos_acumulados DESC";

    private UsuarioReporte extraer(ResultSet rs) throws SQLException {
        return new UsuarioReporte(
                rs.getString("nickname"),
                rs.getString("nombre"),
                rs.getInt("partidas_jugadas"),
                rs.getInt("puntos_acumulados"),
                rs.getDouble("promedio_puntos"),
                rs.getInt("max_nivel"),
                rs.getString("nombre_sucursal")
        );
    }
    
    public ArrayList<UsuarioReporte> generarRankingGlobal() throws ExceptionGenerica {
        return this.seleccionarTodos();
    }
    public ArrayList<UsuarioReporte> generarRankingSucursal(int idSucursal) throws ExceptionGenerica {
        return this.buscarConUnParametroInt(idSucursal);
    
    }
    
    

    @Override
    public ArrayList<UsuarioReporte> seleccionarTodos() throws ExceptionGenerica {
        ArrayList<UsuarioReporte> lista = new ArrayList<>();
        try (Connection conexión = ConexionDB.getConnection(); PreparedStatement ps = conexión.prepareStatement(RANKING_GLOBAL); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(extraer(rs));
            }
            return lista;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ExceptionGenerica("Error al obtener el ranking global: " + e.getMessage());
        }
    }

    @Override
    public ArrayList<UsuarioReporte> buscarConUnParametroInt(int idSucursal) throws ExceptionGenerica {
        ArrayList<UsuarioReporte> lista = new ArrayList<>();
        try (Connection conexión = ConexionDB.getConnection(); PreparedStatement ps = conexión.prepareStatement(RANKING_POR_SUCURSAL)) {

            ps.setInt(1, idSucursal);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(extraer(rs));
                }
            }
            return lista;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ExceptionGenerica("Error al obtener el ranking por sucursal: " + e.getMessage());
        }
    }
}
