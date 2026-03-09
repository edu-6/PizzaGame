/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pizzaexpress.backend.db.reportes;

import com.mycompany.pizzaexpress.backend.crudIntefaces.BuscarTodos;
import com.mycompany.pizzaexpress.backend.crudIntefaces.BusquedaConParametroInt;
import com.mycompany.pizzaexpress.backend.crudIntefaces.CreacionEntidad;
import com.mycompany.pizzaexpress.backend.db.ConexionDB;
import com.mycompany.pizzaexpress.backend.exceptions.ExceptionGenerica;
import com.mycompany.pizzaexpress.backend.modelos.partida.Partida;
import com.mycompany.pizzaexpress.backend.modelos.reportes.PartidaReporte;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author edu
 */
public class ReportesPartidasDB implements CreacionEntidad<Partida>, BuscarTodos<PartidaReporte>, BusquedaConParametroInt<PartidaReporte> {

    private static final String GUARDAR_PARTIDA = "INSERT INTO partida (id_usuario, id_sucursal, fecha, nivel_alcanzado, "
            + "pedidos_exitosos, pedidos_incompletos, pedidos_cancelados, "
            + "puntos_bono_eficiencia, penalizaciones, puntos_totales) "
            + "VALUES (?, ?, NOW(), ?, ?, ?, ?, ?, ?, ?)";

    private static final String PARTIDAS_EN_SUCURSAL = "SELECT p.*, u.nickname, u.nombre, s.nombre AS nombre_sucursal "
            + "FROM partida p "
            + "JOIN usuario u ON u.id_usuario = p.id_usuario "
            + "JOIN sucursal s ON p.id_sucursal = s.id_sucursal "
            + "WHERE p.id_sucursal = ? ORDER BY p.puntos_totales DESC";

    private static final String PARTIDAS_GLOBALES = "SELECT p.*, u.nickname, u.nombre, s.nombre AS nombre_sucursal "
            + "FROM partida p "
            + "JOIN usuario u ON u.id_usuario = p.id_usuario "
            + "JOIN sucursal s ON p.id_sucursal = s.id_sucursal "
            + "ORDER BY p.puntos_totales DESC";

    public ArrayList<PartidaReporte> buscarTodasLasPartidas() throws ExceptionGenerica {
        return this.seleccionarTodos();
    }

    public ArrayList<PartidaReporte> buscarPartidasEnSucursal(int idSucursal) throws ExceptionGenerica {
        return this.buscarConUnParametroInt(idSucursal);
    }

    @Override
    public void crear(Partida entidad) throws ExceptionGenerica {
        try (Connection conexión = ConexionDB.getConnection(); PreparedStatement ps = conexión.prepareStatement(GUARDAR_PARTIDA)) {
            ps.setInt(1, entidad.getIdUsuario());
            ps.setInt(2, entidad.getIdSucursal());
            ps.setInt(3, entidad.getNivelActual());
            ps.setInt(4, entidad.getPedidosExitosos());
            ps.setInt(5, entidad.getPedidosIncompletos());
            ps.setInt(6, entidad.getPedidosCancelados());
            ps.setInt(7, entidad.getBonosPorEficiencia());
            ps.setInt(8, entidad.getPenalizaciones());
            ps.setInt(9, entidad.getPuntosTotales());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new ExceptionGenerica("Error al guardar la partida " + e.getMessage());
        }
    }

    @Override
    public ArrayList<PartidaReporte> seleccionarTodos() throws ExceptionGenerica {
        ArrayList<PartidaReporte> lista = new ArrayList<>();
        try (Connection conexión = ConexionDB.getConnection(); PreparedStatement ps = conexión.prepareStatement(PARTIDAS_GLOBALES); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(extraer(rs));
            }
            return lista;
        } catch (SQLException e) {
            throw new ExceptionGenerica("Error al buscar las partidas " + e.getMessage());
        }
    }

    @Override
    public ArrayList<PartidaReporte> buscarConUnParametroInt(int param) throws ExceptionGenerica {
        ArrayList<PartidaReporte> lista = new ArrayList<>();
        try (Connection conexión = ConexionDB.getConnection(); PreparedStatement ps = conexión.prepareStatement(PARTIDAS_EN_SUCURSAL)) {
            ps.setInt(1, param);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(extraer(rs));
                }
            }
            return lista;
        } catch (SQLException e) {
            throw new ExceptionGenerica("Error al buscar las partidas por sucursal " + e.getMessage());
        }
    }

    private PartidaReporte extraer(ResultSet rs) throws SQLException {
        return new PartidaReporte(
                rs.getString("nickname"),
                rs.getString("nombre"),
                rs.getInt("id_partida"),
                rs.getString("nombre_sucursal"),
                rs.getString("fecha"),
                rs.getInt("nivel_alcanzado"),
                rs.getInt("pedidos_exitosos"),
                rs.getInt("pedidos_incompletos"),
                rs.getInt("pedidos_cancelados"),
                rs.getInt("puntos_bono_eficiencia"),
                rs.getInt("penalizaciones"),
                rs.getInt("puntos_totales")
        );
    }

}
