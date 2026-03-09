/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pizzaexpress.backend.db.reportes;

import com.mycompany.pizzaexpress.backend.db.ConexionDB;
import com.mycompany.pizzaexpress.backend.exceptions.ExceptionGenerica;
import com.mycompany.pizzaexpress.backend.modelos.reportes.ReporteGlobal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author edu
 */
public class ReportesGlobalesDB {
    private static final String SUCURSAL_MAS_ACTIVA = "SELECT s.nombre, COUNT(p.id_partida) AS total "
            + "FROM sucursal s JOIN partida p ON s.id_sucursal = p.id_sucursal "
            + "GROUP BY s.id_sucursal ORDER BY total DESC LIMIT 1";
    private static final String PRODUCTO_MAS_VENDIDO = "SELECT nombre, veces_pedidas "
            + "FROM producto ORDER BY veces_pedidas DESC LIMIT 1";

    public ReporteGlobal obtenerEstadisticasGlobales() throws ExceptionGenerica {
        String sucursal = "";
        int partidas = 0;
        String producto = "";
        int cantidad = 0;

        try (Connection conexion = ConexionDB.getConnection()) {
            
            try (PreparedStatement psS = conexion.prepareStatement(SUCURSAL_MAS_ACTIVA);
                 ResultSet rsS = psS.executeQuery()) {
                if (rsS.next()) {
                    sucursal = rsS.getString("nombre");
                    partidas = rsS.getInt("total");
                }
            }

            try (PreparedStatement psP = conexion.prepareStatement(PRODUCTO_MAS_VENDIDO);
                 ResultSet rsP = psP.executeQuery()) {
                if (rsP.next()) {
                    producto = rsP.getString("nombre");
                    cantidad = rsP.getInt("veces_pedidas");
                }
            }

            return new ReporteGlobal(sucursal, partidas, producto, cantidad);

        } catch (SQLException e) {
            throw new ExceptionGenerica("Error en el reporte global: " + e.getMessage());
        }
    }
    
}
