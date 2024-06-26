/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unique;

import Tabelas.Funcionario;
import Tabelas.Nivel;
import java.awt.Toolkit;
import unique.Consultas.ConsultaAlunos;
import unique.Consultas.ConsultaNiveis;
import unique.Consultas.ConsultaMensalidades;
import unique.Cadastros.CadastroAlunos;
import unique.Cadastros.CadastroNiveis;
import unique.Cadastros.CadastroFuncionarios;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import unique.Cadastros.CadastroTurmas;
import unique.Consultas.ConsultaFuncionarios;
import unique.Consultas.ConsultaTurmas;
import util.HibernateUtil;

/**
 *
 * @author arlorencini
 */
public class JanelaPrincipal extends javax.swing.JFrame {
    
    JFrame cadastroAlunos = null;
    JFrame cadastroNiveis = null;
    JFrame consultaNiveis = null;
    JFrame consultaMensalidades = null;
    JFrame consultaTurmas = null;

    /**
     * Creates new form JanelaPrincipal
     */
    public JanelaPrincipal() {
        initComponents();
        
        //Maximizando:
        this.setExtendedState(this.getExtendedState() | MAXIMIZED_BOTH);
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("Logo.png")));
        
        btnConsultaMensalidades1.setVisible(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnCadastroAlunos = new javax.swing.JButton();
        btnConsultaAlunos = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        btnConsultaNiveis = new javax.swing.JButton();
        btnCadastroNiveis = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        btnPagamento = new javax.swing.JButton();
        btnConsultaMensalidades = new javax.swing.JButton();
        btnConfiguracoes = new javax.swing.JButton();
        btnSair = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        btnCadastroFuncionarios = new javax.swing.JButton();
        btnConsultaFuncionarios = new javax.swing.JButton();
        btnCadastroTurmas = new javax.swing.JButton();
        btnConsultaTurmas = new javax.swing.JButton();
        btnPagamento1 = new javax.swing.JButton();
        btnAniversariantes = new javax.swing.JButton();
        btnControleHotas = new javax.swing.JButton();
        btnConsultaMensalidades1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("UNIQUE - Gerenciamento Escolar");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        btnCadastroAlunos.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        btnCadastroAlunos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/unique/Imagens/alunosDone.png"))); // NOI18N
        btnCadastroAlunos.setText("Cadastro");
        btnCadastroAlunos.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnCadastroAlunos.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnCadastroAlunos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadastroAlunosActionPerformed(evt);
            }
        });

        btnConsultaAlunos.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        btnConsultaAlunos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/unique/Imagens/alunosConsulta.png"))); // NOI18N
        btnConsultaAlunos.setText("Consulta");
        btnConsultaAlunos.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnConsultaAlunos.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnConsultaAlunos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultaAlunosActionPerformed(evt);
            }
        });

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jLabel2.setFont(new java.awt.Font("Segoe UI Light", 0, 36)); // NOI18N
        jLabel2.setText("Níveis e Turmas");

        jLabel3.setFont(new java.awt.Font("Segoe UI Light", 0, 36)); // NOI18N
        jLabel3.setText("Alunos");

        jSeparator3.setOrientation(javax.swing.SwingConstants.VERTICAL);

        btnConsultaNiveis.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        btnConsultaNiveis.setIcon(new javax.swing.ImageIcon(getClass().getResource("/unique/Imagens/nivelConsulta.png"))); // NOI18N
        btnConsultaNiveis.setText("<html><center>Consulta de<br>Níveis</center></html>");
        btnConsultaNiveis.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnConsultaNiveis.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnConsultaNiveis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultaNiveisActionPerformed(evt);
            }
        });

        btnCadastroNiveis.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        btnCadastroNiveis.setIcon(new javax.swing.ImageIcon(getClass().getResource("/unique/Imagens/nivelDone.png"))); // NOI18N
        btnCadastroNiveis.setText("<html><center>Cadastro de<br>Níveis</center></html>");
        btnCadastroNiveis.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnCadastroNiveis.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnCadastroNiveis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadastroNiveisActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI Light", 0, 36)); // NOI18N
        jLabel4.setText("Institucional");

        btnPagamento.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        btnPagamento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/unique/Imagens/pagamentoDone.png"))); // NOI18N
        btnPagamento.setText("<html><center>Pagamento de<br>Mensalidades (Aulas)</center></html>");
        btnPagamento.setToolTipText("");
        btnPagamento.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnPagamento.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnPagamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPagamentoActionPerformed(evt);
            }
        });

        btnConsultaMensalidades.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        btnConsultaMensalidades.setIcon(new javax.swing.ImageIcon(getClass().getResource("/unique/Imagens/mensalidadesDone.png"))); // NOI18N
        btnConsultaMensalidades.setText("<html><center>Consulta de<br>Mensalidades</center></html>");
        btnConsultaMensalidades.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnConsultaMensalidades.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnConsultaMensalidades.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultaMensalidadesActionPerformed(evt);
            }
        });

        btnConfiguracoes.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        btnConfiguracoes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/unique/Imagens/system-run.png"))); // NOI18N
        btnConfiguracoes.setText("Configurações");
        btnConfiguracoes.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnConfiguracoes.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnConfiguracoes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfiguracoesActionPerformed(evt);
            }
        });

        btnSair.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        btnSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/unique/Imagens/gtk-no.png"))); // NOI18N
        btnSair.setText("Fechar");
        btnSair.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnSair.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSairActionPerformed(evt);
            }
        });

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/unique/Imagens/logoUniqueAlfa.png"))); // NOI18N

        btnCadastroFuncionarios.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        btnCadastroFuncionarios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/unique/Imagens/professorDone.png"))); // NOI18N
        btnCadastroFuncionarios.setText("<html><center>Cadastro de<br>Funcionários</center></html>");
        btnCadastroFuncionarios.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnCadastroFuncionarios.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnCadastroFuncionarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadastroFuncionariosActionPerformed(evt);
            }
        });

        btnConsultaFuncionarios.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        btnConsultaFuncionarios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/unique/Imagens/professorConsulta.png"))); // NOI18N
        btnConsultaFuncionarios.setText("<html><center>Consulta de<br>Funcionários</center></html>");
        btnConsultaFuncionarios.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnConsultaFuncionarios.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnConsultaFuncionarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultaFuncionariosActionPerformed(evt);
            }
        });

        btnCadastroTurmas.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        btnCadastroTurmas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/unique/Imagens/turmaDone.png"))); // NOI18N
        btnCadastroTurmas.setText("<html><center>Cadastro de<br>Turmas</center></html>");
        btnCadastroTurmas.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnCadastroTurmas.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnCadastroTurmas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadastroTurmasActionPerformed(evt);
            }
        });

        btnConsultaTurmas.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        btnConsultaTurmas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/unique/Imagens/turmaConsulta.png"))); // NOI18N
        btnConsultaTurmas.setText("<html><center>Consulta de<br>Turmas</center></html>");
        btnConsultaTurmas.setToolTipText("");
        btnConsultaTurmas.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnConsultaTurmas.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnConsultaTurmas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultaTurmasActionPerformed(evt);
            }
        });

        btnPagamento1.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        btnPagamento1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/unique/Imagens/pagamentoDone.png"))); // NOI18N
        btnPagamento1.setText("<html><center>Pagamento de<br>Mensalidades (Material)</center></html>");
        btnPagamento1.setToolTipText("");
        btnPagamento1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnPagamento1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnPagamento1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPagamento1ActionPerformed(evt);
            }
        });

        btnAniversariantes.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        btnAniversariantes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/unique/Imagens/rsz_birthday.png"))); // NOI18N
        btnAniversariantes.setText("<html><center>Aniversários<br>do Mês</center></html>");
        btnAniversariantes.setToolTipText("");
        btnAniversariantes.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnAniversariantes.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnAniversariantes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAniversariantesActionPerformed(evt);
            }
        });

        btnControleHotas.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        btnControleHotas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/unique/Imagens/notaDone.png"))); // NOI18N
        btnControleHotas.setText("<html><center>Controle de<br>Faltas e Históricos</center></html>");
        btnControleHotas.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnControleHotas.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnControleHotas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnControleHotasActionPerformed(evt);
            }
        });

        btnConsultaMensalidades1.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        btnConsultaMensalidades1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/unique/Imagens/vip.png"))); // NOI18N
        btnConsultaMensalidades1.setText("<html><center>Geração de<br>Mensalidades VIP</center></html>");
        btnConsultaMensalidades1.setEnabled(false);
        btnConsultaMensalidades1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnConsultaMensalidades1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnConsultaMensalidades1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultaMensalidades1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnCadastroAlunos, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnConsultaAlunos, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnAniversariantes, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnControleHotas, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnCadastroTurmas, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnConsultaTurmas, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel2)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnCadastroNiveis, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnConsultaNiveis, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(18, 18, 18)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnSair))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnPagamento1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnConsultaMensalidades, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnCadastroFuncionarios, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnConsultaFuncionarios, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnConfiguracoes, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnConsultaMensalidades1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 191, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator3)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnCadastroAlunos, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnConsultaAlunos, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnConsultaNiveis, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnCadastroNiveis, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(btnPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnPagamento1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(btnConsultaMensalidades, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnCadastroFuncionarios, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnConsultaFuncionarios, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnConfiguracoes, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(btnConsultaMensalidades1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 117, Short.MAX_VALUE)
                                .addComponent(btnSair))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnConsultaTurmas, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnCadastroTurmas, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnAniversariantes, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnControleHotas, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jSeparator2)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel6)))
                .addContainerGap())
        );

        btnCadastroFuncionarios.getAccessibleContext().setAccessibleName("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCadastroAlunosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastroAlunosActionPerformed
        //Temos Níveis cadastrados?
        Session conexao = HibernateUtil.openSession();
        Criteria select = conexao.createCriteria(Nivel.class);
        select.addOrder(Order.asc("Codigo"));
        
        if(select.list().size() <= 0){
            if(JOptionPane.showConfirmDialog(this, "Não há um nivel cadastrado ainda. Deseja cadastrar um agora?", "Aviso", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION){
                btnCadastroNiveisActionPerformed(null);
            }
        }else {
            //Chamando a tela de acadastro de alunos:
            cadastroAlunos = CadastroAlunos.getInstance();
            cadastroAlunos.setVisible(true);
            cadastroAlunos.setExtendedState(this.getExtendedState() | MAXIMIZED_BOTH);
        }
        
        conexao.close();        
    }//GEN-LAST:event_btnCadastroAlunosActionPerformed

    private void btnConsultaAlunosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultaAlunosActionPerformed
        ConsultaAlunos consultaAlunos = ConsultaAlunos.getInstance();
        consultaAlunos.setVisible(true);
        consultaAlunos.setExtendedState(this.getExtendedState() | MAXIMIZED_BOTH);
    }//GEN-LAST:event_btnConsultaAlunosActionPerformed

    private void btnCadastroNiveisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastroNiveisActionPerformed
        //Chamando cadastro de Niveis:
        cadastroNiveis = CadastroNiveis.getInstance();
        cadastroNiveis.setVisible(rootPaneCheckingEnabled);
        cadastroNiveis.setExtendedState(this.getExtendedState() | MAXIMIZED_BOTH);
    }//GEN-LAST:event_btnCadastroNiveisActionPerformed

    private void btnConsultaNiveisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultaNiveisActionPerformed
        // TODO add your handling code here:
        consultaNiveis = ConsultaNiveis.getInstance();
        consultaNiveis.setVisible(true);
        consultaNiveis.setExtendedState(this.getExtendedState() | MAXIMIZED_BOTH);
    }//GEN-LAST:event_btnConsultaNiveisActionPerformed

    private void btnPagamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPagamentoActionPerformed
        // TODO add your handling code here:
        PagamentoParcela pagamentoParcela = PagamentoParcela.getInstance();
        pagamentoParcela.setVisible(true);
        pagamentoParcela.setExtendedState(this.getExtendedState() | MAXIMIZED_BOTH);
    }//GEN-LAST:event_btnPagamentoActionPerformed

    private void btnConsultaMensalidadesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultaMensalidadesActionPerformed
        // TODO add your handling code here:
        consultaMensalidades = ConsultaMensalidades.getInstance(-1);
        consultaMensalidades.setVisible(true);
        consultaMensalidades.setExtendedState(this.getExtendedState() | MAXIMIZED_BOTH);
    }//GEN-LAST:event_btnConsultaMensalidadesActionPerformed

    private void btnConfiguracoesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfiguracoesActionPerformed
        Configuracoes configuracoes = Configuracoes.getInstance();
        configuracoes.setVisible(true);
        configuracoes.setExtendedState(this.getExtendedState() | MAXIMIZED_BOTH);
    }//GEN-LAST:event_btnConfiguracoesActionPerformed

    private void btnSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSairActionPerformed
        System.exit(0);
    }//GEN-LAST:event_btnSairActionPerformed

    private void btnCadastroFuncionariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastroFuncionariosActionPerformed
        CadastroFuncionarios cadastro = CadastroFuncionarios.getInstance();
        cadastro.setVisible(true);
        cadastro.setExtendedState(this.getExtendedState() | MAXIMIZED_BOTH);
    }//GEN-LAST:event_btnCadastroFuncionariosActionPerformed

    private void btnConsultaFuncionariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultaFuncionariosActionPerformed
        ConsultaFuncionarios consulta = ConsultaFuncionarios.getInstance();
        consulta.setVisible(true);
        consulta.setExtendedState(this.getExtendedState() | MAXIMIZED_BOTH);
    }//GEN-LAST:event_btnConsultaFuncionariosActionPerformed

    private void btnCadastroTurmasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastroTurmasActionPerformed
        //Temos professores cadastrados?
        //Temos Níveis cadastrados?
        Session conexao = HibernateUtil.openSession();
        Criteria select = conexao.createCriteria(Funcionario.class);
        select.add(Restrictions.like("Tipo", "Profess", MatchMode.START));
        
        if(select.list().size() <= 0){
            if(JOptionPane.showConfirmDialog(this, "Não há um professor cadastrado ainda. Deseja cadastrar um agora?", "Aviso", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION){
                btnCadastroFuncionariosActionPerformed(null);
            }
        }else {
            //Chamando cadastro de Turmas:
            CadastroTurmas cadastroTurmas = CadastroTurmas.getInstance();
            cadastroTurmas.setVisible(true);
            cadastroTurmas.setExtendedState(this.getExtendedState() | MAXIMIZED_BOTH);
        }
        
        conexao.close();  
    }//GEN-LAST:event_btnCadastroTurmasActionPerformed

    private void btnConsultaTurmasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultaTurmasActionPerformed
        // TODO add your handling code here:
        ConsultaTurmas consultaTurma = ConsultaTurmas.getInstance();
        consultaTurma.setVisible(true);
        consultaTurma.setExtendedState(this.getExtendedState() | MAXIMIZED_BOTH);
    }//GEN-LAST:event_btnConsultaTurmasActionPerformed

    private void btnPagamento1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPagamento1ActionPerformed
        PagamentoMaterial pagamentoParcela = PagamentoMaterial.getInstance();
        pagamentoParcela.setVisible(true);
        pagamentoParcela.setExtendedState(this.getExtendedState() | MAXIMIZED_BOTH);
    }//GEN-LAST:event_btnPagamento1ActionPerformed

    private void btnAniversariantesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAniversariantesActionPerformed
        // TODO add your handling code here:
        Aniversariantes aniverariantes = Aniversariantes.getInstance();
        aniverariantes.setVisible(rootPaneCheckingEnabled);
    }//GEN-LAST:event_btnAniversariantesActionPerformed

    private void btnControleHotasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnControleHotasActionPerformed
        ControleDeNotas controleNotas = ControleDeNotas.getInstance();
        controleNotas.setVisible(true);
        controleNotas.setExtendedState(this.getExtendedState() | MAXIMIZED_BOTH);
    }//GEN-LAST:event_btnControleHotasActionPerformed

    private void btnConsultaMensalidades1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultaMensalidades1ActionPerformed
        GerarMensalidadeVIP tela = new GerarMensalidadeVIP();
        tela.setVisible(true);
        tela.setExtendedState(this.getExtendedState() | MAXIMIZED_BOTH);
    }//GEN-LAST:event_btnConsultaMensalidades1ActionPerformed

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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JanelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new JanelaPrincipal().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAniversariantes;
    private javax.swing.JButton btnCadastroAlunos;
    private javax.swing.JButton btnCadastroFuncionarios;
    private javax.swing.JButton btnCadastroNiveis;
    private javax.swing.JButton btnCadastroTurmas;
    private javax.swing.JButton btnConfiguracoes;
    private javax.swing.JButton btnConsultaAlunos;
    private javax.swing.JButton btnConsultaFuncionarios;
    private javax.swing.JButton btnConsultaMensalidades;
    private javax.swing.JButton btnConsultaMensalidades1;
    private javax.swing.JButton btnConsultaNiveis;
    private javax.swing.JButton btnConsultaTurmas;
    private javax.swing.JButton btnControleHotas;
    private javax.swing.JButton btnPagamento;
    private javax.swing.JButton btnPagamento1;
    private javax.swing.JButton btnSair;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    // End of variables declaration//GEN-END:variables
}
