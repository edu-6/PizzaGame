/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pizzaexpress.backend.modelos.reportes;

/**
 *
 * @author edu
 */
public class ReporteGlobal {
    private String sucursalMasActiva;
    private int totalPartidasSucursal;
    private String productoMasVendido;
    private int cantidadProductoVendido;

    public ReporteGlobal(String sucursalMasActiva, int totalPartidasSucursal, String productoMasVendido, int cantidadProductoVendido) {
        this.sucursalMasActiva = sucursalMasActiva;
        this.totalPartidasSucursal = totalPartidasSucursal;
        this.productoMasVendido = productoMasVendido;
        this.cantidadProductoVendido = cantidadProductoVendido;
    }

    public String getSucursalMasActiva() {
        return sucursalMasActiva;
    }

    public int getTotalPartidasSucursal() {
        return totalPartidasSucursal;
    }

    public String getProductoMasVendido() {
        return productoMasVendido;
    }

    public int getCantidadProductoVendido() {
        return cantidadProductoVendido;
    }

    
    
    
}
