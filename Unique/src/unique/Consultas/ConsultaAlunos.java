/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unique.Consultas;

import unique.Cadastros.CadastroAlunos;
import Tabelas.Aluno;
import java.awt.Toolkit;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import util.HibernateUtil;

/**
 *
 * @author loren
 */
public class ConsultaAlunos extends javax.swing.JFrame {
    Session conexao = null;
    CadastroAlunos cadastroAlunos = null;
    private static ConsultaAlunos instance = null;
    
    /**
     * Creates new form ConsultaAlunos
     */
    private ConsultaAlunos() {
        initComponents();
        
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("Logo.png")));
        //Inicializando a tabela de resultados:
        DefaultTableModel model = new DefaultTableModel(new String[] {"ID", "Nome", "CPF", "RG", "Telefone", "Celular"}, 0);
        tabelaResultados.setModel(model);
    }
    
    public static ConsultaAlunos getInstance() {
        if (instance == null)
            instance = new ConsultaAlunos();
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
        jSeparator1 = new javax.swing.JSeparator();
        txtBoxNome = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtBoxCpf = new javax.swing.JFormattedTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtBoxTelefone = new javax.swing.JFormattedTextField();
        jLabel11 = new javax.swing.JLabel();
        txtBoxCelular = new javax.swing.JFormattedTextField();
        txtBoxId = new javax.swing.JTextField();
        btnPesquisar = new javax.swing.JButton();
        btnLimpar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaResultados = new javax.swing.JTable();
        btnEditar = new javax.swing.JButton();
        btnExibirParcelas = new javax.swing.JToggleButton();
        txtBoxRg = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Consulta de ALunos");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI Light", 0, 36)); // NOI18N
        jLabel1.setText("Pesquisa de Alunos");

        txtBoxNome.setColumns(50);
        txtBoxNome.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtBoxNome.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBoxNomeKeyReleased(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("Nome:");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setText("CPF:");

        txtBoxCpf.setColumns(14);
        try {
            txtBoxCpf.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtBoxCpf.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtBoxCpf.setPreferredSize(new java.awt.Dimension(180, 26));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setText("RG:");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel12.setText("ID:");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Telefone:");

        try {
            txtBoxTelefone.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##) ####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtBoxTelefone.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel11.setText("Celular:");

        try {
            txtBoxCelular.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##) #####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtBoxCelular.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        txtBoxId.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        btnPesquisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/unique/Imagens/edit-find.png"))); // NOI18N
        btnPesquisar.setText("Pesquisar");
        btnPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisarActionPerformed(evt);
            }
        });

        btnLimpar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/unique/Imagens/list-remove.png"))); // NOI18N
        btnLimpar.setText("Limpar");
        btnLimpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimparActionPerformed(evt);
            }
        });

        tabelaResultados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tabelaResultados);

        btnEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/unique/Imagens/gtk-edit.png"))); // NOI18N
        btnEditar.setText("Visualizar Aluno");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        btnExibirParcelas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/unique/Imagens/edit-find.png"))); // NOI18N
        btnExibirParcelas.setText("Exibir Parcelas");
        btnExibirParcelas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExibirParcelasActionPerformed(evt);
            }
        });

        txtBoxRg.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtBoxRg.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBoxRgKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 403, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnExibirParcelas)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEditar))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtBoxNome, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 440, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(txtBoxCpf, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(jLabel6)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(txtBoxRg, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(txtBoxTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(17, 17, 17)
                                                .addComponent(jLabel12)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(txtBoxId))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addGap(0, 0, 0)
                                                .addComponent(jLabel11)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(txtBoxCelular)))))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(btnLimpar)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnPesquisar)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtBoxNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtBoxCpf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel12)
                    .addComponent(txtBoxId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBoxRg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtBoxTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBoxCelular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnPesquisar)
                    .addComponent(btnLimpar))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEditar)
                    .addComponent(btnExibirParcelas))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisarActionPerformed
        try{
            //Preparando a query:
            conexao = HibernateUtil.openSession();
            Criteria select = conexao.createCriteria(Aluno.class);

            //ID:
            if(!txtBoxId.getText().isEmpty()){
                select.add(Restrictions.eq("ID", Long.parseLong(txtBoxId.getText())));
            }

            //Nome:
            if(!txtBoxNome.getText().isEmpty()){
                select.add(Restrictions.like("Nome", txtBoxNome.getText(), MatchMode.ANYWHERE));
            }

            //CPF:
            if(!"   .   .   -  ".equals(txtBoxCpf.getText())){
                select.add(Restrictions.eq("CPF", txtBoxCpf.getText()));
            }

            //RG:
            if(!txtBoxRg.getText().isEmpty()){
                select.add(Restrictions.eq("RG", txtBoxRg.getText()));
            }

            //Telefone:
            if(!"(  )     -    ".equals(txtBoxTelefone.getText())){
                select.add(Restrictions.eq("Telefone", txtBoxTelefone.getText()));
            }

            //Celular:
            if(!"(  )      -    ".equals(txtBoxCelular.getText())){
                select.add(Restrictions.eq("Celular", txtBoxCelular.getText()));
            }

            //Executando a query:
            if(select.list().size() > 0){
                //Adicionando os valores na tabela:
                DefaultTableModel model = new DefaultTableModel(new String[] {"ID", "Nome", "CPF", "RG", "Telefone", "Celular"},0);
                tabelaResultados.setModel(model);

                List<Aluno> temp = select.list();

                temp.stream().forEach((list) -> {
                    model.addRow(new Object[]{ list.getID(), list.getNome(), list.getCPF(), list.getRG(), list.getTelefone(), list.getCelular() });
                });
            } else{
                //Limpando a TAbela:
                DefaultTableModel model = new DefaultTableModel(new String[] {"ID", "Nome", "CPF", "RG", "Telefone", "Celular"}, 0);
                tabelaResultados.setModel(model);

                JOptionPane.showMessageDialog(this, "Nenhuma informação a listar!", "Aviso", JOptionPane.OK_OPTION);

                txtBoxNome.requestFocusInWindow();
                txtBoxNome.selectAll();
            }

            //Fechando:
            conexao.close();
        } catch(Exception e){
            JOptionPane.showMessageDialog(this, "Operação mal sucedida. Motivo: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnPesquisarActionPerformed

    private void txtBoxNomeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBoxNomeKeyReleased
        //Passando para UPPERCASE:
        txtBoxNome.setText(txtBoxNome.getText().toUpperCase());
    }//GEN-LAST:event_txtBoxNomeKeyReleased

    private void btnLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimparActionPerformed
        //Limpando os campos:
        txtBoxNome.setText("");
        txtBoxCpf.setText("");
        txtBoxRg.setText("");
        txtBoxId.setText("");
        txtBoxTelefone.setText("");
        txtBoxCelular.setText("");
        
        //Limpando a TAbela:
        DefaultTableModel model = new DefaultTableModel(new String[] {"ID", "Nome", "CPF", "RG", "Telefone", "Celular"}, 0);
        tabelaResultados.setModel(model);
        
        //PAssando o foco para o nome:
        txtBoxNome.requestFocusInWindow();
    }//GEN-LAST:event_btnLimparActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        if(tabelaResultados.getSelectedRow() >= 0){
            cadastroAlunos = CadastroAlunos.getInstance();
            cadastroAlunos.setVisible(rootPaneCheckingEnabled);
            cadastroAlunos.setExtendedState(this.getExtendedState() | MAXIMIZED_BOTH);
            
            cadastroAlunos.LoadInfoForStudent((Long)tabelaResultados.getValueAt(tabelaResultados.getSelectedRow(), 0));
        }else{
            //Aviso
            JOptionPane.showMessageDialog(this, "Nenhum aluno selecionado!", "Aviso", JOptionPane.OK_OPTION);
            
            //PAssando o foco para o nome:
            txtBoxNome.requestFocusInWindow();
        }
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnExibirParcelasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExibirParcelasActionPerformed
        //Abrindo a janela com as tabelas pagas:
        if(tabelaResultados.getSelectedRow() >= 0){
            ConsultaMensalidades consulta = ConsultaMensalidades.getInstance((Long)tabelaResultados.getValueAt(tabelaResultados.getSelectedRow(), 0));
            consulta.setVisible(true);
            consulta.setExtendedState(this.getExtendedState() | MAXIMIZED_BOTH);
        }else{
            //Aviso
            JOptionPane.showMessageDialog(this, "Nenhum aluno selecionado!", "Aviso", JOptionPane.OK_OPTION);
            
            //PAssando o foco para o nome:
            txtBoxNome.requestFocusInWindow();
        }
    }//GEN-LAST:event_btnExibirParcelasActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
        instance = null;
    }//GEN-LAST:event_formWindowClosed

    private void txtBoxRgKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBoxRgKeyReleased
        // TODO add your handling code here:
        txtBoxRg.setText(txtBoxRg.getText().toUpperCase());
    }//GEN-LAST:event_txtBoxRgKeyReleased

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
            java.util.logging.Logger.getLogger(ConsultaAlunos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ConsultaAlunos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ConsultaAlunos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ConsultaAlunos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new ConsultaAlunos().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEditar;
    private javax.swing.JToggleButton btnExibirParcelas;
    private javax.swing.JButton btnLimpar;
    private javax.swing.JButton btnPesquisar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable tabelaResultados;
    private javax.swing.JFormattedTextField txtBoxCelular;
    private javax.swing.JFormattedTextField txtBoxCpf;
    private javax.swing.JTextField txtBoxId;
    private javax.swing.JTextField txtBoxNome;
    private javax.swing.JTextField txtBoxRg;
    private javax.swing.JFormattedTextField txtBoxTelefone;
    // End of variables declaration//GEN-END:variables
}