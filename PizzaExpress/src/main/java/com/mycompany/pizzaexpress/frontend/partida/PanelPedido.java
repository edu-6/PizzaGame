/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.pizzaexpress.frontend.partida;

import com.mycompany.pizzaexpress.backend.modelos.partida.Pedido;
import java.awt.Dimension;
import javax.swing.JPanel;

/**
 *
 * @author edu
 */
public class PanelPedido extends javax.swing.JPanel {

    private JPanel panelContenedor;
    private Pedido pedido;

    /**
     * Creates new form NewJPanel
     */
    public PanelPedido(Pedido pedido, JPanel panelContenedor) {
        initComponents();
        this.setSize(new Dimension(293, 246));
        this.pedido = pedido;
        this.panelContenedor = panelContenedor;
        this.numeroPedido.setText( "No."+pedido.getNumeroPedido());
        Thread pedidoHilo = new Thread(pedido);
        actualizarEstado();
        pedido.setPanelPedido(this);
        pedidoHilo.start();
    }

    public void eliminarPedidoVisual() {
        this.panelContenedor.remove(this);
        this.panelContenedor.revalidate();
        this.panelContenedor.repaint();
    }

    public void actualizarEstado() {
        if(!pedido.sePuedeCancelar()){ // si el pedido no se puede cancelar
            this.btnCancelar.setEnabled(false);
        }
        String path = "/com/mycompany/pizzaexpress/imagenes/";
        String nombre = "";

        switch (pedido.consultarEstado()) {
            case RECIBIDO:
                nombre = "tomada.png";
                break;
            case PREPARANDO:
                nombre = "cocinero.png";
                break;
            case EN_HORNO:
                nombre = "horno.png";
                break;
            case ENTREGADO:
                this.btnAvanzar.setEnabled(false);
                nombre = "entregado.png";
                break;
            case CANCELADO:
                nombre = "cancelar.png";
                break;
            case NO_ENTREGADO:
                nombre = "reporte.png";
                break;
        }

        if (!nombre.isEmpty()) {
            java.net.URL imgURL = getClass().getResource(path + nombre);
            if (imgURL != null) {
                this.estadoLbl.setIcon(new javax.swing.ImageIcon(imgURL));
            }
        }
        this.revalidate();
        this.repaint();
    }

    public void actualizarTiempo(int tiempoRestante) {
        this.tiempoLbl.setText(String.valueOf(tiempoRestante));
        this.revalidate();
        this.repaint();
    }

    private void avanzarPedido() {
        pedido.avanzar();
    }

    private void cancelarPedido() {
        pedido.setActivo(false);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToggleButton2 = new javax.swing.JToggleButton();
        btnEstado = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnAvanzar = new javax.swing.JButton();
        numeroPedido = new javax.swing.JLabel();
        tiempoLbl = new javax.swing.JLabel();
        estadoLbl = new javax.swing.JLabel();

        jToggleButton2.setText("jToggleButton1");

        btnEstado.setForeground(new java.awt.Color(102, 102, 102));
        btnEstado.setFocusable(false);

        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 4));

        btnCancelar.setBackground(new java.awt.Color(0, 0, 0));
        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mycompany/pizzaexpress/imagenes/cancelar.png"))); // NOI18N
        btnCancelar.addActionListener(this::btnCancelarActionPerformed);

        btnAvanzar.setBackground(new java.awt.Color(0, 0, 0));
        btnAvanzar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mycompany/pizzaexpress/imagenes/siguente.png"))); // NOI18N
        btnAvanzar.addActionListener(this::btnAvanzarActionPerformed);

        numeroPedido.setFont(new java.awt.Font("Fira Sans", 0, 24)); // NOI18N
        numeroPedido.setText("numero");

        tiempoLbl.setFont(new java.awt.Font("Fira Sans", 1, 24)); // NOI18N
        tiempoLbl.setText("tiempo");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(btnAvanzar, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(numeroPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(tiempoLbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addComponent(estadoLbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(numeroPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tiempoLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(estadoLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAvanzar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        this.cancelarPedido();
        this.actualizarEstado();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnAvanzarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAvanzarActionPerformed
        this.avanzarPedido();
        this.actualizarEstado();
    }//GEN-LAST:event_btnAvanzarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAvanzar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEstado;
    private javax.swing.JLabel estadoLbl;
    private javax.swing.JToggleButton jToggleButton2;
    private javax.swing.JLabel numeroPedido;
    private javax.swing.JLabel tiempoLbl;
    // End of variables declaration//GEN-END:variables
}
