/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unique.Consultas;

import unique.Cadastros.CadastroAlunos;
import Tabelas.Aluno;
import java.awt.Component;
import java.awt.Toolkit;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import unique.Cadastros.CadastroTurmas;
import unique.ControleDeNotas;
import unique.GerarMensalidadeVIP;
import unique.PagamentoMaterial;
import unique.PagamentoParcela;
import util.HibernateUtil;

/**
 *
 * @author arlorencini
 */
public class AlunosCadastrados extends javax.swing.JFrame {
    public enum CALLER {
        CALLER_CADASTRO,
        CALLER_PAGAMENTO,
        CALLER_PAGAMENTOMATERIAL,
        CALLER_CONSULTAMENSALIDADE,
        CALLER_CADASTROTURMA,
        CALLER_CONTROLENOTAS,
        CALLER_CONSULTATURMA,
        CALLER_GERARMENSALIDADEVIP,
    }
    
    /**
     * Creates new form AlunosCadastrados
     * @param query
     * @param typeOfCaller
     */
    private AlunosCadastrados(String query, CALLER typeOfCaller) {
        initComponents();
        
        this.setLocationRelativeTo(null);    
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("Logo.png")));
        
        searchQuery = query;
        callerType = typeOfCaller;
        
        try{
            //Selecionando os valores:
            conexao = HibernateUtil.openSession();
            Criteria select = conexao.createCriteria(Aluno.class);
            select.add(Restrictions.like("Nome", searchQuery, MatchMode.ANYWHERE));

            if(select.list().size() > 0){
                //Adicionando os valores na tabela:
                DefaultTableModel model = new DefaultTableModel(new String[] { "ID", "Nome do Aluno", "CPF", "Nascimento", "Endereço", "Situação"},0);
                tabelaAlunos.setModel(model);

                List<Aluno> temp = select.list();

                for(Aluno list : temp) {
                    Date data = list.getNascimento();
                    DateFormat df = new SimpleDateFormat("dd/MM/yyyy");    

                    String sit = "Ativo";
                    if(!list.isAtivo()){
                        sit = "Inativo";
                    }

                    model.addRow(new Object[]{list.getID(),  list.getNome(), list.getCPF(), df.format(data), list.getEndereco(), sit });
                }

                resizeColumnWidth(tabelaAlunos);
            }

            conexao.close();
        } catch(Exception e){
            JOptionPane.showMessageDialog(this, "Operação mal sucedida. Motivo: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public static AlunosCadastrados getInstance(String query, CALLER typeOfCaller) {
        if (instance == null)
            instance = new AlunosCadastrados(query, typeOfCaller);
        return instance;
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
    
    public void SetJanelaPai(CadastroAlunos caller) {
        pai = (CadastroAlunos)caller;
    }
    
    public void SetJanelaPai(PagamentoParcela caller) {
        pai = (PagamentoParcela)caller;
    }
    
    public void SetJanelaPai(ConsultaMensalidades caller) {
        pai = (ConsultaMensalidades)caller;
    }
    
    public void SetJanelaPai(ControleDeNotas caller) {
        pai = (ControleDeNotas)caller;
    }
    
    public void SetJanelaPai(CadastroTurmas caller) {
        pai = (CadastroTurmas)caller;
    }
    
    public void SetJanelaPai(PagamentoMaterial caller) {
        pai = (PagamentoMaterial)caller;
    }
    
    public void SetJanelaPai(ConsultaTurmas caller) {
        pai = (ConsultaTurmas)caller;
    }
    
    public void SetJanelaPai(GerarMensalidadeVIP caller) {
        pai = (GerarMensalidadeVIP)caller;
    }
    
    public void SetSearchQuery(String query){
        searchQuery = query;
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
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaAlunos = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jToggleButton1 = new javax.swing.JToggleButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Alunos Cadastrados");
        setAlwaysOnTop(true);
        setModalExclusionType(java.awt.Dialog.ModalExclusionType.APPLICATION_EXCLUDE);
        setType(java.awt.Window.Type.UTILITY);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI Light", 0, 36)); // NOI18N
        jLabel1.setText("Alunos Cadastrados");

        jLabel2.setFont(new java.awt.Font("Tahoma", 2, 12)); // NOI18N
        jLabel2.setText("Por favor, selecione um aluno e clique em \"Importar\", ou selecione \"Voltar\" para continuar o cadastro.");

        tabelaAlunos.setAutoCreateRowSorter(true);
        tabelaAlunos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Nome do Aluno", "CPF", "Nascimento", "Endereço", "Situação"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tabelaAlunos);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/unique/Imagens/list-remove.png"))); // NOI18N
        jButton1.setText("Cancelar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jToggleButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/unique/Imagens/dialog-ok.png"))); // NOI18N
        jToggleButton1.setText("Importar");
        jToggleButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton1ActionPerformed(evt);
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
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(0, 287, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jToggleButton1)))
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
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jToggleButton1))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jToggleButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton1ActionPerformed
        //Temos algo selecionado?
        if(tabelaAlunos.getSelectedRow() >= 0){
            
            if(callerType.equals(CALLER.CALLER_CADASTRO)){
                CadastroAlunos temp = (CadastroAlunos)pai;
                this.setVisible(false);
                temp.LoadInfoForStudent((Long)tabelaAlunos.getValueAt(tabelaAlunos.getSelectedRow(), 0));
            }else if(callerType.equals(CALLER.CALLER_PAGAMENTO)){
                PagamentoParcela temp = (PagamentoParcela)pai;
                this.setVisible(false);
                temp.LoadInfoForStudent((Long)tabelaAlunos.getValueAt(tabelaAlunos.getSelectedRow(), 0));
            }else if(callerType.equals(CALLER.CALLER_CONSULTAMENSALIDADE)){
                ConsultaMensalidades temp = (ConsultaMensalidades)pai;
                this.setVisible(false);
                temp.LoadInfoForStudent((Long)tabelaAlunos.getValueAt(tabelaAlunos.getSelectedRow(), 0));
            }else if(callerType.equals(CALLER.CALLER_CADASTROTURMA)){
                CadastroTurmas temp = (CadastroTurmas)pai;
                this.setVisible(false);
                temp.LoadInfoForStudent((Long)tabelaAlunos.getValueAt(tabelaAlunos.getSelectedRow(), 0));
            }else if(callerType.equals(CALLER.CALLER_PAGAMENTOMATERIAL)){
                PagamentoMaterial temp = (PagamentoMaterial)pai;
                this.setVisible(false);
                temp.LoadInfoForStudent((Long)tabelaAlunos.getValueAt(tabelaAlunos.getSelectedRow(), 0));
            }else if(callerType.equals(CALLER.CALLER_CONTROLENOTAS)){
                ControleDeNotas temp = (ControleDeNotas)pai;
                this.setVisible(false);
                temp.LoadInfoForStudent((Long)tabelaAlunos.getValueAt(tabelaAlunos.getSelectedRow(), 0));
            }else if(callerType.equals(CALLER.CALLER_CONSULTATURMA)){
                ConsultaTurmas temp = (ConsultaTurmas)pai;
                this.setVisible(false);
                temp.LoadInfoForStudent((Long)tabelaAlunos.getValueAt(tabelaAlunos.getSelectedRow(), 0));
            }else if(callerType.equals(CALLER.CALLER_GERARMENSALIDADEVIP)){
                GerarMensalidadeVIP temp = (GerarMensalidadeVIP)pai;
                this.setVisible(false);
                temp.LoadInfoForStudent((Long)tabelaAlunos.getValueAt(tabelaAlunos.getSelectedRow(), 0));
            }
            
            this.dispose();
        } else{
            JOptionPane.showMessageDialog(this, "Nenhum aluno selecionado!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jToggleButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(AlunosCadastrados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AlunosCadastrados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AlunosCadastrados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AlunosCadastrados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new AlunosCadastrados(null, null).setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JTable tabelaAlunos;
    // End of variables declaration//GEN-END:variables
    private String searchQuery;
    Session conexao = null;
    Object pai = null;
    CALLER callerType;
    private static AlunosCadastrados instance = null;
}
