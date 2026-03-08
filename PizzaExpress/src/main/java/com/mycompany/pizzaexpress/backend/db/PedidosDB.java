/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pizzaexpress.backend.db;

import com.mycompany.pizzaexpress.backend.exceptions.ExceptionGenerica;
import com.mycompany.pizzaexpress.backend.modelos.Producto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author edu
 */
public class PedidosDB {

    private static final String  HAY_PRODUCTOS = "select id_sucursal FROM producto WHERE id_sucursal = ?";
   
    private static final String SELECIONAR_N_PRODUCTOS_ACTIVOS = "SELECT * FROM producto WHERE id_sucursal = ? AND activo = TRUE ORDER BY RAND() LIMIT ?";

    public ArrayList<Producto> obtenerProductosAleatorios(int idSucursal) throws ExceptionGenerica {
        ArrayList<Producto> listaProductos = new ArrayList<>();
        Random random = new Random();
         int cantidad = random.nextInt(1, 4);
        try (Connection conn = ConexionDB.getConnection(); PreparedStatement pstmt = conn.prepareStatement(SELECIONAR_N_PRODUCTOS_ACTIVOS)) {
            pstmt.setInt(1, idSucursal);
            pstmt.setInt(2, cantidad);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                listaProductos.add(new Producto(
                        rs.getString("nombre"),
                        rs.getBoolean("activo"),
                        rs.getString("descripcion"),
                        rs.getInt("id_sucursal"),
                        rs.getInt("id_producto")
                ));
            }
            
            if(listaProductos.isEmpty()){
                throw new ExceptionGenerica("No hay productos en la sucursal, no se pueden generar pedidos");
            }
            
            return listaProductos;
        } catch (SQLException e) {
            throw new ExceptionGenerica("Error al obtener productos");
        }
    }
    
    public boolean  hayProductos(int idSucursal) throws ExceptionGenerica{
        try (Connection conn = ConexionDB.getConnection(); PreparedStatement pstmt = conn.prepareStatement(HAY_PRODUCTOS)) {
            pstmt.setInt(1, idSucursal);
            ResultSet rs = pstmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            throw new ExceptionGenerica("Error al verificar existencia de productos");
        }
    }

}
