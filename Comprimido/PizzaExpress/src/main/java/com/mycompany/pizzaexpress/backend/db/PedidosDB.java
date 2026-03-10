/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pizzaexpress.backend.db;

import com.mycompany.pizzaexpress.backend.exceptions.ExceptionGenerica;
import com.mycompany.pizzaexpress.backend.modelos.Producto;
import com.mycompany.pizzaexpress.backend.modelos.partida.Partida;
import com.mycompany.pizzaexpress.backend.modelos.partida.Pedido;
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

    private static final String  HAY_PRODUCTOS = "select id_sucursal FROM producto WHERE id_sucursal = ? AND activo = TRUE";
   
    private static final String SELECIONAR_N_PRODUCTOS_ACTIVOS = "SELECT * FROM producto WHERE id_sucursal = ? AND activo = TRUE ORDER BY RAND() LIMIT ?";
    
    private static final String REGISTRAR_PEDIDO =
    "INSERT INTO pedido (id_partida, recibido, preparando, en_horno, entregado, no_entregado, cancelado) " +
    "VALUES (?, ?, ?, ?, ?, ?, ?)";
    

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
    
    public void  hayProductos(int idSucursal) throws ExceptionGenerica{
        try (Connection conn = ConexionDB.getConnection(); PreparedStatement pstmt = conn.prepareStatement(HAY_PRODUCTOS)) {
            pstmt.setInt(1, idSucursal);
            ResultSet rs = pstmt.executeQuery();
           if(rs.next()){
               
           }else{
               throw new ExceptionGenerica("No hay productos en la sucursal, no se pueden generar pedidos");
           }
        } catch (SQLException e) {
            throw new ExceptionGenerica("Error al verificar existencia de productos");
        }
    }
    
    
    public void registrarPedidosPartida(Partida partida) throws ExceptionGenerica{
        int id_partida = partida.getId_partida();
        ArrayList<Pedido> lista = partida.getListaPedidos();
        for (Pedido pedido : lista) {
            registrarPedido(pedido,id_partida);
        }
    }
    
    
    private void registrarPedido(Pedido pedido, int idPartida) throws ExceptionGenerica{
        try (Connection conn = ConexionDB.getConnection(); PreparedStatement ps = conn.prepareStatement(REGISTRAR_PEDIDO)) {
            ps.setInt(1, idPartida);
            ps.setBoolean(2, true);
            ps.setBoolean(2, pedido.isPreparando());
            ps.setBoolean(2, pedido.isEnHorno());
            ps.setBoolean(2, pedido.isEntregado());
            ps.setBoolean(2, pedido.isNoEntregado());
            ps.setBoolean(2, pedido.isCancelado());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new ExceptionGenerica("Error al guardar el pedido");
        }
    }

}
