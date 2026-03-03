/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.pizzaexpress.frontend.usuarios;

import com.mycompany.pizzaexpress.backend.db.SucursalesDB;
import com.mycompany.pizzaexpress.backend.exceptions.ExceptionGenerica;
import com.mycompany.pizzaexpress.backend.modelos.Sucursal;
import com.mycompany.pizzaexpress.backend.modelos.Usuario;
import com.mycompany.pizzaexpress.backend.servicios.UsuariosCrudService;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author edu
 */
public class UsuarioForm extends javax.swing.JPanel {
    private UsuariosPanel panelPadre;
    private JPanel parent;
    private Usuario usuario;
    private ArrayList<Sucursal> listaSucursales;
    private SucursalesDB sucursalesDB = new SucursalesDB();

    /**
     * Creates new form NuevaSucursalPanel
     */
    public UsuarioForm(JPanel panel, UsuariosPanel panelPadre) {
        this.iniciarPanel(panel);
        this.panelPadre = panelPadre;
        this.ocularComboBoxSucursales();
        this.cargarSucursales();
    }
    public UsuarioForm(JPanel panel,UsuariosPanel panelPadre ,Usuario usuario) {
        this.iniciarPanel(panel);
        this.panelPadre = panelPadre;
        this.usuario = usuario;
        this.ocularComboBoxSucursales();
        this.cargarSucursales();
        this.rellenarCampos(usuario);
    }
    
    private void iniciarPanel(JPanel panel) {
        initComponents();
        this.parent = panel;
        this.setSize(new Dimension(830, 590));
    }

    public void ocularComboBoxSucursales() {
        this.SucursalesBox.setEnabled(false);
        this.SucursalesBox.setVisible(false);
        this.sucursalLbl.setVisible(false);
    }
    
    private void desparecer() {
        this.parent.removeAll();
        this.parent.revalidate();
        this.parent.repaint();
    }

    

    private void cargarSucursales() {
        try {
            // cargar todas las sucursales
            this.listaSucursales = sucursalesDB.seleccionarTodos();
            // cambiando de sucursales a nombres
            String[] nombres = this.listaSucursales.stream().map(s -> s.getNombre()).toArray(String[]::new);
            // ondiendoselo al combo box 
            DefaultComboBoxModel modelo = new DefaultComboBoxModel(nombres);
            this.SucursalesBox.setModel(modelo);
        } catch (ExceptionGenerica ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }

    }
    private void rellenarCampos(Usuario usuario) {
        this.nombreTexto.setText(usuario.getNombre());
        this.nicknameTxt.setText(usuario.getNickname());
        this.contraseñaTxt.setText(usuario.getContraseña());
        
        // poner el tipo de usuario que era antes
        this.setRolAnterior(usuario);
        // poner la sucursal que era antes
        if(usuario.getIdSucursal() > 0){
            this.SucursalesBox.setSelectedIndex(encontrarIndexSucursal(usuario.getIdSucursal(), listaSucursales));
        }
        
       
    }
    
    private void setRolAnterior(Usuario usuario){
         switch (usuario.getRol()) {
            case "ADMIN_SISTEMA":
                this.rolesBox.setSelectedIndex(0);
                break;
            case "TENDERO":
                this.rolesBox.setSelectedIndex(1);
                break;
            case "COCINERO":
                this.rolesBox.setSelectedIndex(2);
                break;
        }
    }
    
    
    private String setRolSegunIndice(int indice){
        switch (indice) {
            case 0:
               return "ADMIN_SISTEMA";
            case 1:
                return "TENDERO";
            case 2:
                return "COCINERO";
        }
        return null;
    }

    private int encontrarIndexSucursal(int idSucursal, ArrayList<Sucursal> lista) {
        for (int i = 0; i < lista.size(); i++) {
            if (idSucursal == lista.get(i).getId()) {
                return i;
            }
        }
        return 0;
    }

    


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField2 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        nombreTexto = new javax.swing.JTextField();
        contraseñaTxt = new javax.swing.JTextField();
        guardarBtn = new javax.swing.JButton();
        cancelarBtn = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        nicknameTxt = new javax.swing.JTextField();
        rolesBox = new javax.swing.JComboBox<>();
        SucursalesBox = new javax.swing.JComboBox<>();
        sucursalLbl = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        jTextField2.setText("jTextField1");

        jButton2.setBackground(new java.awt.Color(255, 204, 0));
        jButton2.setFont(new java.awt.Font("Fira Sans", 0, 18)); // NOI18N
        jButton2.setText("Guardar");
        jButton2.addActionListener(this::jButton2ActionPerformed);

        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setFont(new java.awt.Font("Ubuntu Light", 0, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("nickname:");

        jLabel2.setFont(new java.awt.Font("Ubuntu Light", 0, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Nombre:");

        nombreTexto.setFont(new java.awt.Font("Fira Sans", 0, 18)); // NOI18N

        contraseñaTxt.setFont(new java.awt.Font("Fira Sans", 0, 18)); // NOI18N
        contraseñaTxt.addActionListener(this::contraseñaTxtActionPerformed);

        guardarBtn.setBackground(new java.awt.Color(255, 204, 0));
        guardarBtn.setFont(new java.awt.Font("Fira Sans", 0, 36)); // NOI18N
        guardarBtn.setText("Guardar");
        guardarBtn.addActionListener(this::guardarBtnActionPerformed);

        cancelarBtn.setBackground(new java.awt.Color(255, 102, 102));
        cancelarBtn.setFont(new java.awt.Font("Fira Sans", 0, 36)); // NOI18N
        cancelarBtn.setText("Cancelar");
        cancelarBtn.addActionListener(this::cancelarBtnActionPerformed);

        jLabel3.setFont(new java.awt.Font("Ubuntu Light", 0, 36)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Contraseña:");

        nicknameTxt.setFont(new java.awt.Font("Fira Sans", 0, 18)); // NOI18N
        nicknameTxt.addActionListener(this::nicknameTxtActionPerformed);

        rolesBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Admin Sistema", "Tendero", "Cocinero" }));
        rolesBox.addActionListener(this::rolesBoxActionPerformed);

        SucursalesBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        SucursalesBox.addActionListener(this::SucursalesBoxActionPerformed);

        sucursalLbl.setFont(new java.awt.Font("Ubuntu Light", 0, 36)); // NOI18N
        sucursalLbl.setForeground(new java.awt.Color(0, 0, 0));
        sucursalLbl.setText("Sucursal:");

        jLabel5.setFont(new java.awt.Font("Ubuntu Light", 0, 36)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Rol:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(70, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cancelarBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 431, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(guardarBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 431, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rolesBox, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sucursalLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(SucursalesBox, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(31, 31, 31)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nombreTexto, javax.swing.GroupLayout.PREFERRED_SIZE, 425, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nicknameTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 425, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(contraseñaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 428, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(109, 109, 109))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nombreTexto, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nicknameTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(contraseñaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(46, 46, 46)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SucursalesBox, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rolesBox, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sucursalLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(80, 80, 80)
                .addComponent(guardarBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(cancelarBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void contraseñaTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_contraseñaTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_contraseñaTxtActionPerformed

    private void guardarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarBtnActionPerformed

        UsuariosCrudService crud = new UsuariosCrudService();
        //Extrear el nuevo usuario
        Usuario nuevo = new Usuario(
                this.contraseñaTxt.getText(),
                this.nombreTexto.getText(),
                this.setRolSegunIndice(this.rolesBox.getSelectedIndex()),
                this.nicknameTxt.getText(),
                // buscar el indicex elegido, y luego buscar el id que pertenece a ese index
                this.listaSucursales.get(this.SucursalesBox.getSelectedIndex()).getId()
        );
       
        try {
            if(this.usuario == null){
                crud.crear(nuevo);
            }else{
                nuevo.setId(usuario.getId()); // volver a ponerle el id
                crud.editar(nuevo);
                this.panelPadre.actualizarUltimaBusqueda();// para atualizar el panel 
            }
            
            this.desparecer();
        } catch (ExceptionGenerica ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }//GEN-LAST:event_guardarBtnActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

    }//GEN-LAST:event_jButton2ActionPerformed

    private void cancelarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarBtnActionPerformed
        this.desparecer();
    }//GEN-LAST:event_cancelarBtnActionPerformed

    private void nicknameTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nicknameTxtActionPerformed

    }//GEN-LAST:event_nicknameTxtActionPerformed

    private void rolesBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rolesBoxActionPerformed

        boolean valorDeVisidibilidad = false;
        if (this.rolesBox.getSelectedIndex() > 0) {
            valorDeVisidibilidad = true;
        }
        this.SucursalesBox.setVisible(valorDeVisidibilidad);
        this.SucursalesBox.setEnabled(valorDeVisidibilidad);
        this.sucursalLbl.setVisible(valorDeVisidibilidad);

        this.revalidate();
        this.repaint();
    }//GEN-LAST:event_rolesBoxActionPerformed

    private void SucursalesBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SucursalesBoxActionPerformed

    }//GEN-LAST:event_SucursalesBoxActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> SucursalesBox;
    private javax.swing.JButton cancelarBtn;
    private javax.swing.JTextField contraseñaTxt;
    private javax.swing.JButton guardarBtn;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField nicknameTxt;
    private javax.swing.JTextField nombreTexto;
    private javax.swing.JComboBox<String> rolesBox;
    private javax.swing.JLabel sucursalLbl;
    // End of variables declaration//GEN-END:variables
}
