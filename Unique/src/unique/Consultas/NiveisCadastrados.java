/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unique.Consultas;

import unique.Cadastros.CadastroNiveis;
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
import org.hibernate.criterion.Order;
import util.HibernateUtil;

/**
 *
 * @author CarlosEduardo
 */
public class NiveisCadastrados extends javax.swing.JFrame {

    /**
     * Creates new form NiveisCadastrados
     */
    
    CadastroNiveis cadastroNiveis;
    Session conexao;
    private static NiveisCadastrados instance = null;
    
    private NiveisCadastrados() {
        initComponents();
        
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("Logo.png")));
        //Buscar todos os niveis
        
        try{
            conexao = HibernateUtil.openSession();

            Criteria crit = conexao.createCriteria(Nivel.class);
            crit.addOrder(Order.asc("Codigo"));
            List<Nivel> results = crit.list(); 
            DefaultTableModel model = (DefaultTableModel) tblNiveis.getModel();

            String codigo, nome, descricao, valorAulas, valorMaterial, duracao, cargaHoraria, horasSemanais, cargaMin, cargaMax, gotIt, idioma, online;

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

                cargaHoraria = String.valueOf(n.getQtdHoras());
                String horasStr, minutosStr;
                int horas, minutos;

                horas = (int)n.getMinutosSemanais() / 60;
                if(horas < 10)
                    horasStr = "0" + String.valueOf(horas);
                else
                    horasStr = String.valueOf(horas);

                minutos = n.getMinutosSemanais() % 60;
                if(minutos < 10)
                    minutosStr = "0" + String.valueOf(minutos);
                else
                    minutosStr = String.valueOf(minutos);

                horasSemanais = horasStr + ":" + minutosStr;
                
                String vip = "Não";
                if(n.isVIP())
                    vip = "Sim";
                
                gotIt = "Não";
                if(n.isGotIt())
                    gotIt = "Sim";
                
                online = "Não";
                if(n.isOnline())
                    online = "Sim";
                
                String prorrogavel = "Não";
                if(n.isProrrogavel())
                    prorrogavel = "Sim";
                
                cargaMin = Integer.toString(n.getCargaHorMin());
                cargaMax = Integer.toString(n.getCargaHorMax());
                idioma = n.getIdioma();

                String[] linha = new String[]{n.getID().toString(), codigo, nome, descricao, valorAulas, valorMaterial, 
                    duracao, cargaHoraria, horasSemanais, vip, prorrogavel, cargaMin, cargaMax, gotIt, idioma, online};

                model.addRow(linha);
            } 

            conexao.close();
        } catch(Exception e){
            JOptionPane.showMessageDialog(this, "Operação mal sucedida. Motivo: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        
        resizeColumnWidth(tblNiveis);
        this.setLocationRelativeTo(null);   
    }
    
    public static NiveisCadastrados getInstance() {
      if (instance == null)
         instance = new NiveisCadastrados();
      return instance;
   }
    
    public void setCadastroNiveis(CadastroNiveis niveis){
        this.cadastroNiveis = niveis;
    }
    
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnOk = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblNiveis = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Níveis Cadastrados");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        btnOk.setIcon(new javax.swing.ImageIcon(getClass().getResource("/unique/Imagens/dialog-ok.png"))); // NOI18N
        btnOk.setText("Importar");
        btnOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOkActionPerformed(evt);
            }
        });

        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/unique/Imagens/list-remove.png"))); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        tblNiveis.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Código", "Nome", "Descrição", "Valor Aulas (R$)", "Valor Material (R$)", "Duração (meses)", "Carga Horária", "Horas Semanais", "VIP", "Prorrogável", "Carga Mín.", "Carga Máx.", "Got It!", "Idioma", "Online"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tblNiveis);

        jLabel1.setFont(new java.awt.Font("Segoe UI Light", 0, 36)); // NOI18N
        jLabel1.setText("Níveis Cadastrados");

        jLabel2.setFont(new java.awt.Font("Tahoma", 2, 12)); // NOI18N
        jLabel2.setText("Por favor, selecione um nível e clique em \"Importar\", ou selecione \"Voltar\" para continuar o cadastro.");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnCancelar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnOk))
                    .addComponent(jScrollPane2)
                    .addComponent(jSeparator1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(0, 512, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(1, 1, 1)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnOk, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOkActionPerformed
        int linha = tblNiveis.getSelectedRow();
        String[] tempo = tblNiveis.getValueAt(linha, 8).toString().split(":");
        int totalMinutos = (Integer.parseInt(tempo[0]) * 60) + (Integer.parseInt(tempo[1]));
        if(linha > -1){
            boolean vip = false;
            boolean prorrogavel = false;
            boolean gotIt = false;
            boolean online = false;
            
            System.out.println(tblNiveis.getValueAt(linha, 9));
            
            if(tblNiveis.getValueAt(linha, 9) == "Sim")
                vip = true;
            
            if(tblNiveis.getValueAt(linha, 10) == "Sim")
                prorrogavel = true;
            
            if(tblNiveis.getValueAt(linha, 13) == "Sim")
                gotIt = true;
            
            if(tblNiveis.getValueAt(linha, 15) == "Sim")
                online = true;
            
            cadastroNiveis.preencherCampos(Long.parseLong(tblNiveis.getValueAt(linha, 0).toString()),
                                            tblNiveis.getValueAt(linha, 1).toString(), 
                                            tblNiveis.getValueAt(linha, 2).toString(),
                                            tblNiveis.getValueAt(linha, 3).toString(), 
                                            Double.parseDouble(tblNiveis.getValueAt(linha, 4).toString()), 
                                            Double.parseDouble(tblNiveis.getValueAt(linha, 5).toString()),
                                            Long.parseLong(tblNiveis.getValueAt(linha, 6).toString()),
                                            Integer.parseInt(tblNiveis.getValueAt(linha, 7).toString()),
                                            totalMinutos, vip, prorrogavel, 
                                            Integer.parseInt(tblNiveis.getValueAt(linha, 11).toString()),
                                            Integer.parseInt(tblNiveis.getValueAt(linha, 12).toString()), 
                                            gotIt, tblNiveis.getValueAt(linha, 14).toString(), online);
        }
        this.dispose();
    }//GEN-LAST:event_btnOkActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        instance = null;
    }//GEN-LAST:event_formWindowClosed

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
            java.util.logging.Logger.getLogger(NiveisCadastrados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NiveisCadastrados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NiveisCadastrados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NiveisCadastrados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NiveisCadastrados().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnOk;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable tblNiveis;
    // End of variables declaration//GEN-END:variables
}
