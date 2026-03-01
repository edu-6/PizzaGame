/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.pizzaexpress.frontend;

import com.mycompany.pizzaexpress.backend.modelos.Usuario;
import com.mycompany.pizzaexpress.frontend.panels_por_rol.super_admin.SuperAdminBase;
import java.awt.BorderLayout;
import java.awt.Dimension;

/**
 *
 * @author edu
 */
public class FrameBase extends javax.swing.JFrame {

   

    /**
     * Creates new form FrameBase
     */
    public FrameBase() {
        initComponents();
        this.setLocationRelativeTo(null);
        mostrarLogin();
        //this.add(new AdminSucursalPanel(), BorderLayout.CENTER);
    }

    private void reiniciarFrame() {
        this.getContentPane().removeAll();
        this.setLayout(new BorderLayout());
    }
    
    
    public void mostrarLogin(){
        reiniciarFrame();
        this.setSize(new Dimension(700, 400));
        this.setLayout(new BorderLayout());
        this.add(new LoginPanel(this), BorderLayout.CENTER);
        pintar();
    }

    private void pintar() {
        this.revalidate();
        this.repaint();
    }

    public void cambiarPanel(Usuario usuarioLogueado) {
        reiniciarFrame();
        if (usuarioLogueado.getRol().equals("SUPER_ADMIN")) {
            this.add(new SuperAdminBase(this), BorderLayout.CENTER);
            pintar();

        }
    }
    
    
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
