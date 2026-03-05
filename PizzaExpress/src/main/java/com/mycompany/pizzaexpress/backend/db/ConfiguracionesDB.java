/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pizzaexpress.backend.db;

import com.mycompany.pizzaexpress.backend.exceptions.ExceptionGenerica;
import com.mycompany.pizzaexpress.backend.modelos.ConfiguracionDePartida;
import com.mycompany.pizzaexpress.backend.modelos.ConfiguracionPunteos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author edu
 */
public class ConfiguracionesDB {
    private static final String EDITAR_GLOBAL = "UPDATE configuracion_global SET tiempo_partida = ?, tiempo_nivel_1 = ?, tiempo_nivel_2 = ?, tiempo_nivel_3 = ?, puntos_nivel_2 = ?, puntos_nivel_3 = ?, tiempo_entre_pedidos = ? WHERE id_configuracion = 1";
    private static final String BUSCAR_GLOBAL = "SELECT * FROM configuracion_global WHERE id_configuracion = 1";
    private static final String EDITAR_PUNTEOS = "UPDATE configuracion_punteos SET bono_pedido_correcto = ?, bono_tiempo_optimo = ?, bono_eficiencia = ?, pedido_cancelado = ?, pedido_incompleto = ? WHERE id_punteo = 1";
    private static final String BUSCAR_PUNTEOS = "SELECT * FROM configuracion_punteos WHERE id_punteo = 1";


    public void editarReglasPartida(ConfiguracionDePartida config) throws ExceptionGenerica {
        try (Connection conn = ConexionDB.getConnection(); PreparedStatement stm = conn.prepareStatement(EDITAR_GLOBAL)) {
            stm.setInt(1, config.getTiempoPartida());
            stm.setInt(2, config.getTiempoNivel1());
            stm.setInt(3, config.getTiempoNivel2());
            stm.setInt(4, config.getTiempoNivel3());
            stm.setInt(5, config.getPuntosNivel2());
            stm.setInt(6, config.getPuntosNivel3());
            stm.setInt(7, config.getTiempoEntrePedidos());
            stm.executeUpdate();
        } catch (SQLException e) {
            throw new ExceptionGenerica("Error al actualizar configuración global: " + e.getMessage());
        }
    }

    public ConfiguracionDePartida obtenerReglasPartida() throws ExceptionGenerica {
        try (Connection conn = ConexionDB.getConnection(); PreparedStatement stm = conn.prepareStatement(BUSCAR_GLOBAL); ResultSet rs = stm.executeQuery()) {
            if (rs.next()) {
                return new ConfiguracionDePartida(
                    rs.getInt("tiempo_partida"),
                    rs.getInt("tiempo_nivel_1"),
                    rs.getInt("tiempo_nivel_2"),
                    rs.getInt("tiempo_nivel_3"),
                    rs.getInt("puntos_nivel_2"),
                    rs.getInt("puntos_nivel_3"),
                    rs.getInt("tiempo_entre_pedidos")
                );
            }
        } catch (SQLException e) {
            throw new ExceptionGenerica("Error al obtener configuración global: " + e.getMessage());
        }
        return null;
    }

    public void editarReglasPunteos(ConfiguracionPunteos punteo) throws ExceptionGenerica {
        try (Connection conn = ConexionDB.getConnection(); PreparedStatement stm = conn.prepareStatement(EDITAR_PUNTEOS)) {
            stm.setInt(1, punteo.getBonoPedidoCorrecto());
            stm.setInt(2, punteo.getBonoTiempoOptimo());
            stm.setInt(3, punteo.getBonoEficiencia());
            stm.setInt(4, punteo.getPedidoCancelado());
            stm.setInt(5, punteo.getPedidoIncompleto());
            stm.executeUpdate();
        } catch (SQLException e) {
            throw new ExceptionGenerica("Error al actualizar configuración de punteos: " + e.getMessage());
        }
    }

    public ConfiguracionPunteos obtenerReglasPunteos() throws ExceptionGenerica {
        try (Connection conn = ConexionDB.getConnection(); PreparedStatement stm = conn.prepareStatement(BUSCAR_PUNTEOS); ResultSet rs = stm.executeQuery()) {
            if (rs.next()) {
                return new ConfiguracionPunteos(
                    rs.getInt("bono_pedido_correcto"),
                    rs.getInt("bono_tiempo_optimo"),
                    rs.getInt("bono_eficiencia"),
                    rs.getInt("pedido_cancelado"),
                    rs.getInt("pedido_incompleto")
                );
            }
        } catch (SQLException e) {
            throw new ExceptionGenerica("Error al obtener configuración de punteos: " + e.getMessage());
        }
        return null;
    }
    
}
