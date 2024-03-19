/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unique;

import Tabelas.Aluno;
import Tabelas.Mensalidade;
import java.awt.Container;
import java.awt.Cursor;
import static java.awt.Frame.MAXIMIZED_BOTH;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.swing.JRViewer;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import unique.Consultas.AlunosCadastrados;
import util.ConnectionUtil;
import util.HibernateUtil;
import util.ReportUtil;

/**
 *
 * @author arlorencini
 */
public class GerarMensalidadeVIP extends javax.swing.JFrame {
    Session conexao = null;
    
    /**
     * Creates new form GerarMensalidadeVIP
     */
    public GerarMensalidadeVIP() {
        initComponents();
        
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("Logo.png")));
        
        this.setLocationRelativeTo(null);  
        
        LimparCampos();
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
        jLabel4 = new javax.swing.JLabel();
        txtBoxNome = new javax.swing.JTextField();
        lblInfoNome = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableMensalidades = new javax.swing.JTable();
        jLabel21 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel22 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        comboMes = new javax.swing.JComboBox();
        comboAno = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        lblInfoMensalidade = new javax.swing.JLabel();
        btnSalvar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        txtBoxAulasExtras = new javax.swing.JTextField();
        btnReimprimir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Gerar Mensalidade para Aluno VIP");

        jLabel1.setFont(new java.awt.Font("Segoe UI Light", 0, 36)); // NOI18N
        jLabel1.setText("Gerar Mensalidade para Aluno VIP");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("Nome:");

        txtBoxNome.setColumns(50);
        txtBoxNome.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtBoxNome.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBoxNomeKeyReleased(evt);
            }
        });

        lblInfoNome.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        lblInfoNome.setIcon(new javax.swing.ImageIcon(getClass().getResource("/unique/Imagens/package-installed-outdated.png"))); // NOI18N
        lblInfoNome.setText(" ");
        lblInfoNome.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblInfoNomeMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblInfoNomeMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblInfoNomeMouseExited(evt);
            }
        });

        tableMensalidades.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mês de Referência", "Valor da Parcela", "Total de Aulas Extras", "Pago"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableMensalidades.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tableMensalidadesMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tableMensalidades);

        jLabel21.setFont(new java.awt.Font("Segoe UI Light", 0, 24)); // NOI18N
        jLabel21.setText("Parcelas Geradas para o Aluno:");

        jSeparator3.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jLabel22.setFont(new java.awt.Font("Segoe UI Light", 0, 24)); // NOI18N
        jLabel22.setText("Gerar Nova Parcela:");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Mês de Referência:");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Ano de Referência:");

        comboMes.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        comboMes.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro" }));

        comboAno.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        comboAno.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "2015", "2016", "2017", "2018", "2019", "2020" }));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("Qtd de Aulas Extras:");

        lblInfoMensalidade.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N

        btnSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/unique/Imagens/dialog-ok.png"))); // NOI18N
        btnSalvar.setText("Salvar");
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });

        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/unique/Imagens/list-remove.png"))); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        txtBoxAulasExtras.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        btnReimprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/unique/Imagens/gtk-edit.png"))); // NOI18N
        btnReimprimir.setText("Reimprimir");
        btnReimprimir.setEnabled(false);
        btnReimprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReimprimirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jSeparator1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtBoxNome, javax.swing.GroupLayout.PREFERRED_SIZE, 440, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblInfoNome, javax.swing.GroupLayout.DEFAULT_SIZE, 469, Short.MAX_VALUE))
                    .addComponent(jLabel1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 466, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel21)
                            .addComponent(btnReimprimir, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(18, 18, 18)
                        .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel22)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel2))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(comboAno, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(comboMes, 0, 122, Short.MAX_VALUE)
                                    .addComponent(txtBoxAulasExtras))
                                .addGap(18, 18, 18)
                                .addComponent(lblInfoMensalidade, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(btnCancelar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnSalvar)))))
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
                    .addComponent(txtBoxNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblInfoNome, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel21)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                        .addComponent(btnReimprimir))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel22)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(comboMes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(comboAno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(lblInfoMensalidade, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtBoxAulasExtras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnSalvar)
                            .addComponent(btnCancelar)))
                    .addComponent(jSeparator3))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtBoxNomeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBoxNomeKeyReleased
        //Pesquisando no banco:
        if(txtBoxNome.getText().length() != 0){
            //Passando para UPPERCASE:
            txtBoxNome.setText(txtBoxNome.getText().toUpperCase());

            try{
                //Verificando se existe o nome cadastrado no banco:
                conexao = HibernateUtil.openSession();
                Criteria select = conexao.createCriteria(Aluno.class);
                select.add(Restrictions.like("Nome", txtBoxNome.getText(), MatchMode.ANYWHERE));

                if(select.list().size() > 0){
                    lblInfoNome.setText("Foram encontrados " + select.list().size() + " resultados semelhantes já cadastrados. Clique para visualizar.");
                    lblInfoNome.setVisible(true);
                }else{
                    lblInfoNome.setVisible(false);
                }

                conexao.close();
            } catch(Exception e){
                JOptionPane.showMessageDialog(this, "Operação mal sucedida. Motivo: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }else{
            lblInfoNome.setVisible(false);
        }
    }//GEN-LAST:event_txtBoxNomeKeyReleased

    private void lblInfoNomeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblInfoNomeMouseClicked
        //Abrindo a janela de consulta:
        AlunosCadastrados consultaAlunos = AlunosCadastrados.getInstance(txtBoxNome.getText(), AlunosCadastrados.CALLER.CALLER_GERARMENSALIDADEVIP);
        consultaAlunos.SetJanelaPai(this);

        consultaAlunos.setVisible(true);
    }//GEN-LAST:event_lblInfoNomeMouseClicked

    private void lblInfoNomeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblInfoNomeMouseEntered
        lblInfoNome.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_lblInfoNomeMouseEntered

    private void lblInfoNomeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblInfoNomeMouseExited
        lblInfoNome.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_lblInfoNomeMouseExited

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        //Limpando os campos:
        LimparCampos();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        //Verificando se os campos são válidos:
        boolean okToGo = true;
        Double valorTotal = 0d;
        
        if(!mensalidades.equals(null)){
            for(Mensalidade mens : mensalidades) {
                //Mês e ano é igual?
                if((comboMes.getSelectedIndex() + 1 == mens.getMesVencto()) && (Integer.valueOf(comboAno.getSelectedItem().toString()) == mens.getAnoVencto())) {
                    JOptionPane.showMessageDialog(this, "Já existe uma parcela gerada para o mês e ano informados!", "Erro", JOptionPane.ERROR_MESSAGE);
                    okToGo = false;
                }
            }
        }
        
        try {
            String txtAulas = txtBoxAulasExtras.getText().trim();
            txtAulas = txtAulas.replaceAll(",", ".");
            valorTotal = (matricula.getValorMensal() + (Double.parseDouble(txtAulas) * matricula.getValorAulaExcedente()));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Nro de aulas extras inválido!", "Erro", JOptionPane.ERROR_MESSAGE);
            txtBoxAulasExtras.requestFocusInWindow();
            txtBoxAulasExtras.selectAll();
            okToGo = false;
        }
        
        if(okToGo && JOptionPane.showConfirmDialog(this, "Confirma a geração da parcela no valor de R$" + valorTotal + "?" , "Confirmação", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
            //Preparando as informações para salvar a mensalidade:
            Mensalidade mens = new Mensalidade();

            mens.setAluno(alunoSelecionado);
            mens.setNivel(alunoSelecionado.getNivelAtual());
            mens.setAnoVencto(Integer.valueOf(comboAno.getSelectedItem().toString()));
            mens.setMesVencto(comboMes.getSelectedIndex() + 1);
            mens.setPaga(false);
            String txtAulas = txtBoxAulasExtras.getText().trim();
            txtAulas = txtAulas.replaceAll(",", ".");
            mens.setQtdAulasExtras(Double.valueOf(txtAulas));
            mens.setValorAulas(matricula.getValorMensal() + (mens.getQtdAulasExtras() * matricula.getValorAulaExcedente()));
            mens.setValorMaterial(0d);
            mens.setValorPago(0d);

            conexao = HibernateUtil.openSession();

            Transaction tx = conexao.beginTransaction();
            conexao.saveOrUpdate(mens);

            try{
                tx.commit();

                //Sucesso:
                if(JOptionPane.showConfirmDialog(this, "Mensalidade gerada com sucesso! Deseja imprimir o boleto?", "Mensagem", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
                    //Gerando relatorio:
                    HashMap map = new HashMap();
                    JasperPrint jasperPrint = null;
<<<<<<< HEAD
                    Connection connection = null;
                    try {
                        connection = DriverManager.getConnection("jdbc:firebirdsql:/home/cadumancini/Unique/UNIQUE.FDB","sysdba","1123581321");
                    } catch (SQLException ex) {
                        Logger.getLogger(GerarMensalidades.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    map.put("MensalidadeID", mens.getID());
                    try {
                        JasperReport compiled = JasperCompileManager.compileReport("/home/cadumancini/Unique/Unique/Relatorios/CarneVip.jrxml");
=======
                    Connection connection = ConnectionUtil.getConnection();

                    map.put("MensalidadeID", mens.getID());
                    try {
                        JasperReport compiled = ReportUtil.getReport("CarneVip");
>>>>>>> 57b4a00a2fad96d49b1df3fd6dbb3dae5190fa93
                        jasperPrint = JasperFillManager.fillReport(compiled, map, connection);
                        JRViewer viewer = new JRViewer(jasperPrint);
                        JFrame report = new JFrame();
                        report.setExtendedState(MAXIMIZED_BOTH);
                        report.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                        report.setTitle("Boleto de Aluno VIP");
                        Container c = report.getContentPane();
                        c.add(viewer);
                        report.setVisible(true);

                    } catch (JRException ex) {
                        JOptionPane.showMessageDialog(this, "Não foi possível imprimir o relatório. Motivo: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                }
                LoadInfoForStudent(alunoSelecionado.getID());
            } catch(Exception e){
                JOptionPane.showMessageDialog(this, "Operação mal sucedida. Motivo: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }

            if(conexao.isOpen())
                conexao.close();
        }
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void btnReimprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReimprimirActionPerformed
        // TODO add your handling code here:
        if(tableMensalidades.getSelectedRow() >= 0){
            if(JOptionPane.showConfirmDialog(this, "Deseja reimprimir esse boleto?", "Confirmação", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
                conexao = HibernateUtil.openSession();

                String periodo = tableMensalidades.getValueAt(tableMensalidades.getSelectedRow(), 0).toString();
                String periodos[] = periodo.split("/");
                int mes = Integer.parseInt(periodos[0]);
                int ano = Integer.parseInt(periodos[1]);

                Criteria crit = conexao.createCriteria(Mensalidade.class);
                crit.add(Restrictions.eq("Aluno", alunoSelecionado));
                crit.add(Restrictions.eq("MesVencto", mes));
                crit.add(Restrictions.eq("AnoVencto", ano));
                Mensalidade mens = (Mensalidade) crit.list().get(0);

                //Gerando relatorio:
                HashMap map = new HashMap();
                JasperPrint jasperPrint = null;
<<<<<<< HEAD
                Connection connection = null;
                try {
                    connection = DriverManager.getConnection("jdbc:firebirdsql:/home/cadumancini/Unique/UNIQUE.FDB","sysdba","1123581321");
                } catch (SQLException ex) {
                    Logger.getLogger(GerarMensalidades.class.getName()).log(Level.SEVERE, null, ex);
                }

                map.put("MensalidadeID", mens.getID());
                try {
                    JasperReport compiled = JasperCompileManager.compileReport("/home/cadumancini/Unique/Unique/Relatorios/CarneVip.jrxml");
=======
                Connection connection = ConnectionUtil.getConnection();

                map.put("MensalidadeID", mens.getID());
                try {
                    JasperReport compiled = ReportUtil.getReport("CarneVip");
>>>>>>> 57b4a00a2fad96d49b1df3fd6dbb3dae5190fa93
                    jasperPrint = JasperFillManager.fillReport(compiled, map, connection);
                    JRViewer viewer = new JRViewer(jasperPrint);
                    JFrame report = new JFrame();
                    report.setExtendedState(MAXIMIZED_BOTH);
                    report.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                    report.setTitle("Boleto de Aluno VIP");
                    Container c = report.getContentPane();
                    c.add(viewer);
                    report.setVisible(true);

                } catch (JRException ex) {
                    JOptionPane.showMessageDialog(this, "Não foi possível imprimir o relatório. Motivo: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }

            } else{
                JOptionPane.showMessageDialog(this, "Operação cancelada!", "Informação", JOptionPane.INFORMATION_MESSAGE);
            }
        } else{
            JOptionPane.showMessageDialog(this, "Nenhuma mensalidade selecionada!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
        
    }//GEN-LAST:event_btnReimprimirActionPerformed

    private void tableMensalidadesMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMensalidadesMousePressed
        // TODO add your handling code here:
        btnReimprimir.setEnabled(true);
    }//GEN-LAST:event_tableMensalidadesMousePressed

    private void LimparCampos() {
        txtBoxNome.setText("");
        txtBoxNome.requestFocusInWindow();
        lblInfoNome.setText("");
        lblInfoNome.setVisible(false);
        lblInfoMensalidade.setText("");
        txtBoxAulasExtras.setText("");
        
        //Desabilitando os campos:
        comboAno.setEnabled(false);
        comboMes.setEnabled(false);
        txtBoxAulasExtras.setEnabled(false);
        btnSalvar.setEnabled(false);
    }
    
    public void LoadInfoForStudent(Long id){
        LimparCampos();

        try{
            //Verificando se existe o nome cadastrado no banco:
            conexao = HibernateUtil.openSession();
            Criteria select = conexao.createCriteria(Aluno.class);
            select.add(Restrictions.eq("ID", id));

            if(select.list().size() > 0){
                //Adicionando os valores na tabela:
                List<Aluno> temp = select.list();

                temp.stream().forEach((Aluno list) -> {
                    txtBoxNome.setText(list.getNome());
                    alunoSelecionado = list;
                });
            }
            
            //Esse aluno é VIP?
            if(!alunoSelecionado.isVip()) {
                JOptionPane.showMessageDialog(this, "O(a) aluno(a) " + alunoSelecionado.getNome() + " não está cadastrado como VIP!", "ERRO", JOptionPane.ERROR_MESSAGE);
                LimparCampos();
                conexao.close();
                return;
            }
            
            //Buscando as mensalidades do aluno:
            Criteria selectMensalidade = conexao.createCriteria(Mensalidade.class);
            selectMensalidade.add(Restrictions.eq("Aluno", alunoSelecionado));
            selectMensalidade.addOrder(Order.asc("AnoVencto"));
            selectMensalidade.addOrder(Order.asc("MesVencto"));
            
            if(selectMensalidade.list().size() > 0) {
                mensalidades = selectMensalidade.list();
                
                //Preenchendo a tabela:
                DefaultTableModel newModel = (DefaultTableModel) tableMensalidades.getModel();
                newModel.setRowCount(0);
                
                mensalidades.stream().forEach((Mensalidade mens) -> {
                    //PReparando a data:
                    String data = mens.getMesVencto() + "/" + mens.getAnoVencto();
                    String pago = "Não";
                    
                    if(mens.isPaga())
                        pago = "Sim";
                    
                    newModel.addRow(new Object[]{ data, mens.getValorAulas(), mens.getQtdAulasExtras(), pago });
                });
            }
            
            //Buscando a matricula do aluno:
            Criteria critMatri = conexao.createCriteria(Tabelas.MatriculaVip.class);
            critMatri.add(Restrictions.eq("Aluno", alunoSelecionado));
            critMatri.add(Restrictions.eq("Nivel", alunoSelecionado.getNivelAtual()));
            
            matricula = (Tabelas.MatriculaVip)critMatri.list().get(0);
            
            lblInfoMensalidade.setText("Custo por Aula: R$" + matricula.getValorAulaExcedente());
            
            comboAno.setEnabled(true);
            comboMes.setEnabled(true);
            txtBoxAulasExtras.setEnabled(true);
            btnSalvar.setEnabled(true);

            //Finalizando o Hibernate:
            conexao.close();
        } catch(Exception e){
            JOptionPane.showMessageDialog(this, "Operação mal sucedida. Motivo: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }

        txtBoxNome.requestFocusInWindow();
    }
    
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
            java.util.logging.Logger.getLogger(GerarMensalidadeVIP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GerarMensalidadeVIP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GerarMensalidadeVIP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GerarMensalidadeVIP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GerarMensalidadeVIP().setVisible(true);
            }
        });
    }
    
    //Membros:
    Aluno alunoSelecionado = null;
    List<Mensalidade> mensalidades = new ArrayList<Mensalidade>();
    Tabelas.MatriculaVip matricula = null;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnReimprimir;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JComboBox comboAno;
    private javax.swing.JComboBox comboMes;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JLabel lblInfoMensalidade;
    private javax.swing.JLabel lblInfoNome;
    private javax.swing.JTable tableMensalidades;
    private javax.swing.JTextField txtBoxAulasExtras;
    private javax.swing.JTextField txtBoxNome;
    // End of variables declaration//GEN-END:variables
}
