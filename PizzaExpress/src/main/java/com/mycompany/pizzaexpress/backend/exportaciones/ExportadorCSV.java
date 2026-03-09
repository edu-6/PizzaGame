/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pizzaexpress.backend.exportaciones;

import com.mycompany.pizzaexpress.backend.exceptions.ExceptionGenerica;
import com.mycompany.pizzaexpress.backend.modelos.reportes.PartidaReporte;
import com.mycompany.pizzaexpress.backend.modelos.reportes.ReporteGlobal;
import com.mycompany.pizzaexpress.backend.modelos.reportes.UsuarioReporte;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author edu
 */
public class ExportadorCSV {

    public void exportarReportePartidas(ArrayList<PartidaReporte> lista, File archivo) throws ExceptionGenerica {
        try (FileWriter writer = new FileWriter(archivo)) {

            writer.write("SUCURSAL,Jugador,Nickname,Nivel max,Penalizaciones,Entregados,Incompletos,Cancelados,Bonos eficiencia,Total,Fecha\n");

            for (PartidaReporte r : lista) {
                writer.write(r.getNombreSucursal() + ","
                        + r.getNombreUsuario() + ","
                        + r.getNickname() + ","
                        + r.getNivelAlcanzado() + ","
                        + r.getPenalizaciones() + ","
                        + r.getPedidosExitosos() + ","
                        + r.getPedidosIncompletos() + ","
                        + r.getPedidosCancelados() + ","
                        + r.getBonosEficiencia() + ","
                        + r.getPuntosTotales() + ","
                        + r.getFecha() + "\n");
            }
        } catch (IOException e) {
            throw new ExceptionGenerica("Error al crear csv, no ingrese comas");
        }
    }

    public void exportarReporteUsuarios(ArrayList<UsuarioReporte> lista, File archivo) throws ExceptionGenerica {
        try (FileWriter writer = new FileWriter(archivo)) {

            writer.write("Nickname,Nombre,Partidas,Puntos Totales,Promedio,Max Nivel,Sucursal\n");

            for (UsuarioReporte r : lista) {
                writer.write(r.getNickname() + ","
                        + r.getNombre() + ","
                        + r.getPartidasJugadas() + ","
                        + r.getPuntosAcumulados() + ","
                        + r.getPromedioPuntos() + ","
                        + r.getMaxNivel() + ","
                        + r.getNombreSucursal() + "\n");
            }
        } catch (IOException e) {
            throw new ExceptionGenerica("Error al crear csv, no ingrese comas");
        }
    }

    public void exportarEstadisticasGlobales(ReporteGlobal reporte, File archivo) throws ExceptionGenerica {
        try (FileWriter writer = new FileWriter(archivo)) {

            writer.write("Sucursal mas Activa,Total Partidas,Producto Mas Vendido,Cantidad\n");
            writer.write(reporte.getSucursalMasActiva() + ","
                    + reporte.getTotalPartidasSucursal() + ","
                    + reporte.getProductoMasVendido() + ","
                    + reporte.getCantidadProductoVendido() + "\n");

        } catch (IOException e) {
            throw new ExceptionGenerica("Error al crear csv, no ingrese comas");
        }
    }

}
