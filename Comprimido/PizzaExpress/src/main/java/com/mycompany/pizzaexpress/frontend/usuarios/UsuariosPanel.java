/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.pizzaexpress.frontend.usuarios;

import com.mycompany.pizzaexpress.backend.db.SucursalesDB;
import com.mycompany.pizzaexpress.backend.exceptions.ExceptionGenerica;
import com.mycompany.pizzaexpress.backend.modelos.BusquedaUsuarios;
import com.mycompany.pizzaexpress.backend.modelos.Sucursal;
import com.mycompany.pizzaexpress.backend.modelos.Usuario;
import com.mycompany.pizzaexpress.backend.servicios.UsuariosCrudService;
import com.mycompany.pizzaexpress.frontend.panels_por_rol.super_admin.SuperAdminBase;
import java.awt.BorderLayout;
import java.util.ArrayList;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author edu
 */
public class UsuariosPanel extends javax.swing.JPanel {
    private boolean ultimaTieneFiltros;
    private JPanel panelContenedor;
    private SuperAdminBase padre;
    private ArrayList<Sucursal> lista;
    private UsuariosCrudService usuariosCrudService = new UsuariosCrudService();
    private SucursalesDB sucursalesDB = new SucursalesDB();

    /**
     * Creates new form UsuariosPanel
     */
    public UsuariosPanel(SuperAdminBase padre) {
        initComponents();
        this.padre = padre;
        this.cargarSucursales();
        this.cambiarVisivilidadSucursales(); // ocultar al inicio las sucursales

    }

    private void cargarSucursales() {
        try {
            // cargar todas las sucursales
            this.lista = sucursalesDB.seleccionarTodos();
            // cambiando de sucursales a nombres
            String[] nombres = lista.stream().map(s -> s.getNombre()).toArray(String[]::new);
            // ondiendoselo al combo box 
            DefaultComboBoxModel modelo = new DefaultComboBoxModel(nombres);
            this.sucursalesBox.setModel(modelo);
        } catch (ExceptionGenerica ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }

    private void cambiarVisivilidadSucursales() {
        boolean valorDeVisidibilidad = false;
        if (this.rolesBox.getSelectedIndex() > 0) {
            valorDeVisidibilidad = true;
        }
        this.sucursalLbl.setVisible(valorDeVisidibilidad);
        this.sucursalesBox.setVisible(valorDeVisidibilidad);
        this.sucursalesBox.setEnabled(valorDeVisidibilidad);
        this.revalidate();
        this.repaint();
    }
    
    
    public void mostrarEdicionForm(Usuario usuario){
        this.panelNuevoUsuario.setLayout(new BorderLayout());
        this.panelNuevoUsuario.add(new UsuarioForm(this.panelNuevoUsuario,this, usuario), BorderLayout.CENTER);
        this.revalidate();
        this.repaint();
    }
    
    
    private void mostrarResultados(ArrayList<Usuario> lista){
        this.panelResultados.removeAll();
        this.panelResultados.revalidate();
        this.panelResultados.repaint();
        JPanel contenedor = new JPanel();
            contenedor.setLayout(new BoxLayout(contenedor, BoxLayout.Y_AXIS));
            for (Usuario usuario : lista) {
                UsuarioInfo tarjeta = new UsuarioInfo(usuario, this, contenedor);
                contenedor.add(tarjeta);
                contenedor.add(Box.createVerticalStrut(10));
            }

            contenedor.revalidate();
            contenedor.repaint();

            JScrollPane scrollPane = new JScrollPane(contenedor);
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            scrollPane.getVerticalScrollBar().setUnitIncrement(16);
            
            panelResultados.setLayout(new BorderLayout());
            panelResultados.add(scrollPane, BorderLayout.CENTER);
            panelResultados.revalidate();
            panelResultados.repaint();
            this.revalidate();
            this.repaint();
    }
    
    
    public void actualizarUltimaBusqueda(){
        if(this.ultimaTieneFiltros){
            this.buscarConFiltros();
        }else{
            this.buscarPorNombreONickname();
        }
    }
    
    public void buscarPorNombreONickname(){
        this.ultimaTieneFiltros = false;
        
        if(this.barraBusqeuda.getText().isBlank()){
            return;
        }
        try {
            ArrayList<Usuario> usuarios = this.usuariosCrudService.buscarPorString(this.barraBusqeuda.getText());
            mostrarResultados(usuarios);
        } catch (ExceptionGenerica ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }
    
    public void buscarConFiltros(){
        this.ultimaTieneFiltros = true;
        int rol = this.rolesBox.getSelectedIndex();
        String rolElegido = "";
        switch (rol) {
            case 0:
                rolElegido = "ADMIN_SISTEMA";
                break;
            case 1:
                rolElegido = "TENDERO";
                break;
            case 2:
                rolElegido = "COCINERO";
                break;
        }
        BusquedaUsuarios busqueda = new BusquedaUsuarios(rolElegido, (String) this.sucursalesBox.getSelectedItem());
        try {
            ArrayList<Usuario> usuarios = this.usuariosCrudService.buscarVariosConFiltro(busqueda);
            this.mostrarResultados(usuarios);
        } catch (ExceptionGenerica ex) {
           JOptionPane.showMessageDialog(this, ex.getMessage());
        }
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        nuevoUsuarioBtn = new javax.swing.JButton();
        busquedaFiltrosBtn = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        barraBusqeuda = new javax.swing.JTextField();
        sucursalesBox = new javax.swing.JComboBox<>();
        rolesBox = new javax.swing.JComboBox<>();
        panelResultados = new javax.swing.JPanel();
        panelNuevoUsuario = new javax.swing.JPanel();
        buscarPorString = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        sucursalLbl = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 204, 153));

        nuevoUsuarioBtn.setBackground(new java.awt.Color(51, 102, 255));
        nuevoUsuarioBtn.setFont(new java.awt.Font("Fira Sans", 0, 36)); // NOI18N
        nuevoUsuarioBtn.setForeground(new java.awt.Color(0, 0, 0));
        nuevoUsuarioBtn.setText("Nuevo usuario");
        nuevoUsuarioBtn.addActionListener(this::nuevoUsuarioBtnActionPerformed);

        busquedaFiltrosBtn.setBackground(new java.awt.Color(153, 153, 153));
        busquedaFiltrosBtn.setFont(new java.awt.Font("Fira Sans", 0, 24)); // NOI18N
        busquedaFiltrosBtn.setForeground(new java.awt.Color(0, 0, 0));
        busquedaFiltrosBtn.setText("Buscar( Filtros)");
        busquedaFiltrosBtn.addActionListener(this::busquedaFiltrosBtnActionPerformed);

        jButton2.setBackground(new java.awt.Color(255, 51, 51));
        jButton2.setFont(new java.awt.Font("Fira Sans", 0, 36)); // NOI18N
        jButton2.setForeground(new java.awt.Color(0, 0, 0));
        jButton2.setText("Regresar");
        jButton2.addActionListener(this::jButton2ActionPerformed);

        barraBusqeuda.setFont(new java.awt.Font("Fira Sans", 0, 18)); // NOI18N
        barraBusqeuda.addActionListener(this::barraBusqeudaActionPerformed);

        sucursalesBox.setFont(new java.awt.Font("Fira Sans", 0, 24)); // NOI18N
        sucursalesBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        rolesBox.setFont(new java.awt.Font("Fira Sans", 0, 24)); // NOI18N
        rolesBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Admin sistema", "Tendero", "Cocinero" }));
        rolesBox.addActionListener(this::rolesBoxActionPerformed);

        panelResultados.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout panelResultadosLayout = new javax.swing.GroupLayout(panelResultados);
        panelResultados.setLayout(panelResultadosLayout);
        panelResultadosLayout.setHorizontalGroup(
            panelResultadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 510, Short.MAX_VALUE)
        );
        panelResultadosLayout.setVerticalGroup(
            panelResultadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 372, Short.MAX_VALUE)
        );

        panelNuevoUsuario.setBackground(new java.awt.Color(255, 255, 51));

        javax.swing.GroupLayout panelNuevoUsuarioLayout = new javax.swing.GroupLayout(panelNuevoUsuario);
        panelNuevoUsuario.setLayout(panelNuevoUsuarioLayout);
        panelNuevoUsuarioLayout.setHorizontalGroup(
            panelNuevoUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panelNuevoUsuarioLayout.setVerticalGroup(
            panelNuevoUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        buscarPorString.setBackground(new java.awt.Color(153, 153, 153));
        buscarPorString.setFont(new java.awt.Font("Fira Sans", 0, 24)); // NOI18N
        buscarPorString.setForeground(new java.awt.Color(0, 0, 0));
        buscarPorString.setText("Buscar");
        buscarPorString.addActionListener(this::buscarPorStringActionPerformed);

        jLabel1.setBackground(new java.awt.Color(0, 0, 0));
        jLabel1.setFont(new java.awt.Font("Fira Sans", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("ROL:");

        sucursalLbl.setFont(new java.awt.Font("Fira Sans", 0, 24)); // NOI18N
        sucursalLbl.setForeground(new java.awt.Color(0, 0, 0));
        sucursalLbl.setText("Sucursal:");

        jLabel2.setFont(new java.awt.Font("Fira Sans", 0, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("nombre / nickname");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelResultados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelNuevoUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(nuevoUsuarioBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8))
                    .addComponent(rolesBox, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sucursalesBox, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(busquedaFiltrosBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(barraBusqeuda, javax.swing.GroupLayout.PREFERRED_SIZE, 363, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buscarPorString, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(sucursalLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(309, 309, 309)
                        .addComponent(jLabel2)))
                .addGap(0, 26, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nuevoUsuarioBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(sucursalLbl)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sucursalesBox, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rolesBox, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(barraBusqeuda, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(busquedaFiltrosBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buscarPorString, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(60, 60, 60)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelResultados, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelNuevoUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void nuevoUsuarioBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nuevoUsuarioBtnActionPerformed
        this.panelNuevoUsuario.setLayout(new BorderLayout());
        this.panelNuevoUsuario.add(new UsuarioForm(this.panelNuevoUsuario, this), BorderLayout.CENTER);
        this.revalidate();
        this.repaint();
    }//GEN-LAST:event_nuevoUsuarioBtnActionPerformed

    private void busquedaFiltrosBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_busquedaFiltrosBtnActionPerformed
         this.buscarConFiltros();
    }//GEN-LAST:event_busquedaFiltrosBtnActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.padre.regresar();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void barraBusqeudaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_barraBusqeudaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_barraBusqeudaActionPerformed

    private void buscarPorStringActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarPorStringActionPerformed
        
        this.buscarPorNombreONickname();

    }//GEN-LAST:event_buscarPorStringActionPerformed

    private void rolesBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rolesBoxActionPerformed
        this.cambiarVisivilidadSucursales();
    }//GEN-LAST:event_rolesBoxActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField barraBusqeuda;
    private javax.swing.JButton buscarPorString;
    private javax.swing.JButton busquedaFiltrosBtn;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JButton nuevoUsuarioBtn;
    private javax.swing.JPanel panelNuevoUsuario;
    private javax.swing.JPanel panelResultados;
    private javax.swing.JComboBox<String> rolesBox;
    private javax.swing.JLabel sucursalLbl;
    private javax.swing.JComboBox<String> sucursalesBox;
    // End of variables declaration//GEN-END:variables
}
