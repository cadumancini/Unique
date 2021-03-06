/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unique.Consultas;

import Tabelas.Nivel;
import java.awt.Component;
import java.awt.Toolkit;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import util.HibernateUtil;

/**
 *
 * @author CarlosEduardo
 */
public class ConsultaNiveis extends javax.swing.JFrame {

    /**
     * Creates new form ConsultaNiveis
     */
    Session conexao;
    Transaction tx;
    private static ConsultaNiveis instance = null;
    
    private ConsultaNiveis() {
        initComponents();
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("Logo.png")));
        
        txtCodigo.requestFocusInWindow();
        txtValorAulas.setText("0.000,00");
        txtValorMaterial.setText("0.000,00");
    }
    
    public static ConsultaNiveis getInstance() {
        if (instance == null)
            instance = new ConsultaNiveis();
        return instance;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        txtNome = new javax.swing.JTextField();
        comboAulas = new javax.swing.JComboBox();
        comboMaterial = new javax.swing.JComboBox();
        txtValorAulas = new javax.swing.JFormattedTextField();
        txtValorMaterial = new javax.swing.JFormattedTextField();
        btnConsultar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblNiveis = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Pesquisa de Níveis");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("Código:");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Nome:");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Valor Aulas (R$):");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("Valor Material (R$):");

        txtCodigo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtCodigo.setNextFocusableComponent(txtNome);
        txtCodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCodigoKeyReleased(evt);
            }
        });

        txtNome.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtNome.setNextFocusableComponent(comboAulas);
        txtNome.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNomeKeyReleased(evt);
            }
        });

        comboAulas.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        comboAulas.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "menor que", "igual a", "maior que" }));
        comboAulas.setNextFocusableComponent(txtValorAulas);

        comboMaterial.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        comboMaterial.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "menor que", "igual a", "maior que" }));
        comboMaterial.setName(""); // NOI18N
        comboMaterial.setNextFocusableComponent(txtValorMaterial);

        txtValorAulas.setColumns(8);
        try {
            txtValorAulas.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("#.###,##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtValorAulas.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtValorAulas.setText("");
        txtValorAulas.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtValorAulas.setNextFocusableComponent(comboMaterial);
        txtValorAulas.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtValorAulasFocusGained(evt);
            }
        });
        txtValorAulas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtValorAulasMouseClicked(evt);
            }
        });
        txtValorAulas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtValorAulasKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtValorAulasKeyTyped(evt);
            }
        });

        txtValorMaterial.setColumns(8);
        try {
            txtValorMaterial.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("#.###,##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtValorMaterial.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtValorMaterial.setText("");
        txtValorMaterial.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtValorMaterial.setNextFocusableComponent(btnCancelar);
        txtValorMaterial.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtValorMaterialFocusGained(evt);
            }
        });
        txtValorMaterial.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtValorMaterialMouseClicked(evt);
            }
        });
        txtValorMaterial.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtValorMaterialKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtValorMaterialKeyTyped(evt);
            }
        });

        btnConsultar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/unique/Imagens/edit-find.png"))); // NOI18N
        btnConsultar.setText("Consultar");
        btnConsultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultarActionPerformed(evt);
            }
        });

        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/unique/Imagens/list-remove.png"))); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.setNextFocusableComponent(btnConsultar);
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        tblNiveis.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Código", "Nome", "Descrição", "Valor Aulas (R$)", "Valor Material (R$)", "Duração (Meses)", "VIP", "Got It!", "Idioma", "Online"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblNiveis);

        jLabel6.setFont(new java.awt.Font("Segoe UI Light", 0, 36)); // NOI18N
        jLabel6.setText("Pesquisa de Níveis");
        jLabel6.setToolTipText("");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 847, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(comboAulas, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(comboMaterial, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtValorAulas, javax.swing.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE)
                                            .addComponent(txtValorMaterial))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnCancelar))
                                    .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnConsultar))))
                    .addComponent(jSeparator1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jSeparator2))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(comboAulas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtValorAulas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(comboMaterial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtValorMaterial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnConsultar)
                    .addComponent(btnCancelar))
                .addGap(18, 18, 18)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 201, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtValorAulasFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtValorAulasFocusGained
        // TODO add your handling code here:
        //txtValorAulas.select(0, txtValorAulas.getColumns());
        txtValorAulas.selectAll();
    }//GEN-LAST:event_txtValorAulasFocusGained

    private void txtValorAulasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtValorAulasMouseClicked
        // TODO add your handling code here:
        txtValorAulas.selectAll();
    }//GEN-LAST:event_txtValorAulasMouseClicked

    private void txtValorAulasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtValorAulasKeyPressed
        // TODO add your handling code here:
        if(!((evt.getKeyChar() == '0') || (evt.getKeyChar() == '1') || (evt.getKeyChar() == '2') ||
            (evt.getKeyChar() == '3') || (evt.getKeyChar() == '4') || (evt.getKeyChar() == '5') ||
            (evt.getKeyChar() == '6') || (evt.getKeyChar() == '7') || (evt.getKeyChar() == '8') ||
            (evt.getKeyChar() == '9')))
        return;
    }//GEN-LAST:event_txtValorAulasKeyPressed

    private void txtValorAulasKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtValorAulasKeyTyped
        // TODO add your handling code here:
        String temp = txtValorAulas.getText();

        if(((evt.getKeyChar() == '0') || (evt.getKeyChar() == '1') || (evt.getKeyChar() == '2') ||
            (evt.getKeyChar() == '3') || (evt.getKeyChar() == '4') || (evt.getKeyChar() == '5') ||
            (evt.getKeyChar() == '6') || (evt.getKeyChar() == '7') || (evt.getKeyChar() == '8') ||
            (evt.getKeyChar() == '9')) && (temp.startsWith("0")))
        {
            temp = temp.replaceAll("\\,|\\.", "");
            temp = temp.substring(1);

            StringBuilder builder = new StringBuilder(temp);
            builder.insert(1, '.');
            builder.insert(5, ',');
            temp = builder.toString();

            txtValorAulas.setText(temp);
        }
        else if(evt.getKeyChar() == 8) //Backspace
        {
            temp = temp.replaceAll("\\,|\\.", "");
            temp = '0' + temp;

            StringBuilder builder = new StringBuilder(temp);
            builder.insert(1, '.');
            builder.insert(5, ',');
            temp = builder.toString();

            txtValorAulas.setText(temp);
        }
    }//GEN-LAST:event_txtValorAulasKeyTyped

    private void txtValorMaterialFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtValorMaterialFocusGained
        // TODO add your handling code here:
        txtValorMaterial.selectAll();
    }//GEN-LAST:event_txtValorMaterialFocusGained

    private void txtValorMaterialMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtValorMaterialMouseClicked
        // TODO add your handling code here:
        txtValorAulas.selectAll();
    }//GEN-LAST:event_txtValorMaterialMouseClicked

    private void txtValorMaterialKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtValorMaterialKeyPressed
        // TODO add your handling code here:
        if(!((evt.getKeyChar() == '0') || (evt.getKeyChar() == '1') || (evt.getKeyChar() == '2') ||
            (evt.getKeyChar() == '3') || (evt.getKeyChar() == '4') || (evt.getKeyChar() == '5') ||
            (evt.getKeyChar() == '6') || (evt.getKeyChar() == '7') || (evt.getKeyChar() == '8') ||
            (evt.getKeyChar() == '9')))
        return;
    }//GEN-LAST:event_txtValorMaterialKeyPressed

    private void txtValorMaterialKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtValorMaterialKeyTyped
        // TODO add your handling code here:
        String temp = txtValorAulas.getText();

        if(((evt.getKeyChar() == '0') || (evt.getKeyChar() == '1') || (evt.getKeyChar() == '2') ||
            (evt.getKeyChar() == '3') || (evt.getKeyChar() == '4') || (evt.getKeyChar() == '5') ||
            (evt.getKeyChar() == '6') || (evt.getKeyChar() == '7') || (evt.getKeyChar() == '8') ||
            (evt.getKeyChar() == '9')) && (temp.startsWith("0")))
        {
            temp = temp.replaceAll("\\,|\\.", "");
            temp = temp.substring(1);

            StringBuilder builder = new StringBuilder(temp);
            builder.insert(1, '.');
            builder.insert(5, ',');
            temp = builder.toString();

            txtValorAulas.setText(temp);
        }
        else if(evt.getKeyChar() == 8) //Backspace
        {
            temp = temp.replaceAll("\\,|\\.", "");
            temp = '0' + temp;

            StringBuilder builder = new StringBuilder(temp);
            builder.insert(1, '.');
            builder.insert(5, ',');
            temp = builder.toString();

            txtValorAulas.setText(temp);
        }
    }//GEN-LAST:event_txtValorMaterialKeyTyped

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        txtCodigo.setText("");
        txtNome.setText("");
        comboAulas.setSelectedIndex(0);
        comboMaterial.setSelectedIndex(0);
        txtValorAulas.setText("0.000,00");
        txtValorMaterial.setText("0.000,00");
        
        int x = tblNiveis.getRowCount();
        DefaultTableModel model = (DefaultTableModel) tblNiveis.getModel();
        for(int i = 0; i < x; i++){
            model.removeRow(0);
        }
        
        txtCodigo.requestFocusInWindow();
    }//GEN-LAST:event_btnCancelarActionPerformed

    public void resizeColumnWidth(JTable table) {
        final TableColumnModel columnModel = table.getColumnModel();
        for (int column = 0; column < table.getColumnCount(); column++) {
            int width = 50; // Min width
            for (int row = 0; row < table.getRowCount(); row++) {
                TableCellRenderer renderer = table.getCellRenderer(row, column);
                Component comp = table.prepareRenderer(renderer, row, column);
                width = Math.max(comp.getPreferredSize().width, width);
            }
            columnModel.getColumn(column).setPreferredWidth(width);
        }
    }
    
    private void btnConsultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultarActionPerformed
        // TODO add your handling code here:
        int x = tblNiveis.getRowCount();
        DefaultTableModel model = (DefaultTableModel) tblNiveis.getModel();
        for(int i = 0; i < x; i++){
            model.removeRow(0);
        }
        
        try{
            conexao = HibernateUtil.openSession();
            Criteria crit = conexao.createCriteria(Nivel.class);
            if(!txtCodigo.getText().isEmpty())
                crit.add(Restrictions.eq("Codigo", txtCodigo.getText()));

            if(!txtNome.getText().isEmpty())
                crit.add(Restrictions.eq("Nome", txtNome.getText()));

            String temp = txtValorAulas.getText();
            temp = temp.replaceAll("\\.", "");
            temp = temp.replaceAll("\\,", ".");
            Double valor = Double.parseDouble(temp);

            if(valor > 0){
                switch(comboAulas.getSelectedIndex()){
                    case 0:
                        crit.add(Restrictions.lt("ValorAulas", valor)); //less than
                    case 1:
                        crit.add(Restrictions.eq("ValorAulas", valor)); //equals
                    case 2:
                        crit.add(Restrictions.gt("ValorAulas", valor)); //greater than
                }
            }

            temp = txtValorMaterial.getText();
            temp = temp.replaceAll("\\.", "");
            temp = temp.replaceAll("\\,", ".");
            valor = Double.parseDouble(temp);

            if(valor > 0){
                switch(comboMaterial.getSelectedIndex()){
                    case 0:
                        crit.add(Restrictions.lt("ValorMaterial", valor)); //less than
                    case 1:
                        crit.add(Restrictions.eq("ValorMaterial", valor)); //equals
                    case 2:
                        crit.add(Restrictions.gt("ValorMaterial", valor)); //greater than
                }
            }

            crit.addOrder(Order.asc("Codigo"));
            List<Nivel> results = crit.list(); 
            String codigo, nome, descricao, valorAulas, valorMaterial, duracao;

            for(Nivel n : results)
            {
                if(n.getCodigo() == null)
                    codigo = " ";
                else
                    codigo = n.getCodigo();

                if(n.getNome()== null)
                    nome = " ";
                else
                    nome = n.getNome();

                if(n.getDescricao()== null)
                    descricao = " ";
                else
                    descricao = n.getDescricao();

                if(n.getValorAulas()== null)
                    valorAulas = "0.000,00";
                else
                    valorAulas = n.getValorAulas().toString();

                if(n.getValorMaterial()== null)
                    valorMaterial = "0.000,00";
                else
                    valorMaterial = n.getValorMaterial().toString();

                if(n.getDuracao()== null)
                    duracao = "0";
                else
                    duracao = n.getDuracao().toString();
                
                String vip = "Não";
                if(n.isVIP())
                    vip = "Sim";
                
                String gotIt = "Não";
                if(n.isGotIt())
                    gotIt = "Sim";
                
                String online = "Não";
                if(n.isOnline())
                    online = "Sim";
                
                String idioma = n.getIdioma();

                String[] linha = new String[]{n.getID().toString(), codigo, nome, 
                    descricao, valorAulas, valorMaterial, duracao, vip, gotIt, 
                    idioma, online};

                model.addRow(linha);
            }

            conexao.close();
        } catch(Exception e){
            JOptionPane.showMessageDialog(this, "Operação mal sucedida. Motivo: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        
        resizeColumnWidth(tblNiveis);
    }//GEN-LAST:event_btnConsultarActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
        instance = null;
    }//GEN-LAST:event_formWindowClosed

    private void txtCodigoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoKeyReleased
        // TODO add your handling code here:
        txtCodigo.setText(txtCodigo.getText().toUpperCase());
    }//GEN-LAST:event_txtCodigoKeyReleased

    private void txtNomeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNomeKeyReleased
        // TODO add your handling code here:
        txtNome.setText(txtNome.getText().toUpperCase());
    }//GEN-LAST:event_txtNomeKeyReleased

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ConsultaNiveis.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ConsultaNiveis.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ConsultaNiveis.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ConsultaNiveis.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ConsultaNiveis().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnConsultar;
    private javax.swing.JComboBox comboAulas;
    private javax.swing.JComboBox comboMaterial;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTable tblNiveis;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtNome;
    private javax.swing.JFormattedTextField txtValorAulas;
    private javax.swing.JFormattedTextField txtValorMaterial;
    // End of variables declaration//GEN-END:variables
}
