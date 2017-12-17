/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unique;

import Tabelas.Aluno;
import Tabelas.Matricula;
import Tabelas.Mensalidade;
import Tabelas.PagtoMaterial;
import java.awt.Container;
import static java.awt.Frame.MAXIMIZED_BOTH;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.swing.JRViewer;
import org.hibernate.Session;
import org.hibernate.Transaction;
import unique.Cadastros.JTextFieldLimit;
import util.HibernateUtil;

/**
 *
 * @author CarlosEduardo
 */
public class GerarMensalidades extends javax.swing.JFrame {

    /**
     * Creates new form GerarMensalidades
     */
    Session conexao;
    Transaction tx;
    Aluno aluno;
    private static GerarMensalidades instance = null;
    
    private GerarMensalidades() {
        initComponents();
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("Logo.png")));
        
        txtMesAno.requestFocusInWindow();
        
        txtValorPagoMaterial.setDocument(new JTextFieldLimit(8, false));
        txtValorPagoMaterial.setText("0.000,00");
        
        comboMaterialEscola.addActionListener((ActionEvent e) -> {
            MudouItemComboBox(e); 
        });
        
        comboDinheiroCheque.addActionListener((ActionEvent e) -> {
            MudouItemComboBoxCheque(e); 
        });
        
        //Escondendo oalguns campos:
        txtNroIdentCheque.setVisible(false);
        txtNroIdentCheque.setEnabled(false);

        txtBoxDataCompensacao.setVisible(false);
        txtBoxDataCompensacao.setEnabled(false);

        lblNroIdentCheque.setVisible(false);
        lblDatComCheque.setVisible(false);
        txtValorPagoMaterial.setEnabled(false);
        
        this.setLocationRelativeTo(null);
    }
    
    public static GerarMensalidades getInstance() {
        if (instance == null)
            instance = new GerarMensalidades();
        return instance;
    }
    
    private void MudouItemComboBox(ActionEvent e) {
        if(comboMaterialEscola.getSelectedIndex() > 0) {
            txtValorPagoMaterial.setEnabled(true);
            comboDinheiroCheque.setEnabled(true);
        } else{            
            txtNroIdentCheque.setVisible(false);
            txtNroIdentCheque.setEnabled(false);
            
            txtBoxDataCompensacao.setVisible(false);
            txtBoxDataCompensacao.setEnabled(false);
            
            lblNroIdentCheque.setVisible(false);
            lblDatComCheque.setVisible(false);
            txtValorPagoMaterial.setEnabled(false);
            
            comboDinheiroCheque.setEnabled(false);
            //comboDinheiroCheque.setSelectedIndex(0);
        }
    }
    
    private void MudouItemComboBoxCheque(ActionEvent e) {
        if(comboDinheiroCheque.getSelectedIndex() == 0) {   //Em dinheiro
            txtValorPagoMaterial.setEnabled(true);
            
            txtNroIdentCheque.setVisible(false);
            txtNroIdentCheque.setEnabled(false);
            
            txtBoxDataCompensacao.setVisible(false);
            txtBoxDataCompensacao.setEnabled(false);
            
            lblNroIdentCheque.setVisible(false);
            lblDatComCheque.setVisible(false);
        } else{                                             //Em Cheque
            txtNroIdentCheque.setVisible(true);
            txtNroIdentCheque.setEnabled(true);
            
            txtBoxDataCompensacao.setVisible(true);
            txtBoxDataCompensacao.setEnabled(true);
            
            lblNroIdentCheque.setVisible(true);
            lblDatComCheque.setVisible(true);
            txtValorPagoMaterial.setEnabled(true);
        }
    }
    
    public void preencherCampos(Aluno aluno){
        this.aluno = aluno;
        
        txtNome.setText(aluno.getNome());
        txtNivel.setText(aluno.getNivelAtual().getNome());
        
        txtValorAulas.setText(aluno.getNivelAtual().getValorAulas().toString());
        if(aluno.getNivelAtual().getValorAulas().intValue() - aluno.getNivelAtual().getValorAulas() == 0){
            txtValorAulas.setText(txtValorAulas.getText()+"0");
        }
        
        txtValorMaterial.setText(aluno.getNivelAtual().getValorMaterial().toString());
        if(aluno.getNivelAtual().getValorMaterial().intValue() - aluno.getNivelAtual().getValorMaterial()== 0){
            txtValorMaterial.setText(txtValorMaterial.getText()+"0");
        }
        txtDuracao.setText(aluno.getNivelAtual().getDuracao().toString());
        
        for(int i = 1; i <= aluno.getNivelAtual().getDuracao(); i++)
            comboAulas.addItem(i);
        
        int mesAtual = Calendar.getInstance().get(Calendar.MONTH) + 2;
        String mes = "";
        if(mesAtual < 10)
            mes = "0"+String.valueOf(mesAtual);
        
        int anoAtual = Calendar.getInstance().get(Calendar.YEAR);
        
        txtMesAno.setText(mes+String.valueOf(anoAtual));
        
        lblInfoVlrTotalMaterial.setText("Valor total do material: R$ " + aluno.getNivelAtual().getValorMaterial());
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
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtValorAulas = new javax.swing.JTextField();
        txtNome = new javax.swing.JTextField();
        txtDuracao = new javax.swing.JTextField();
        txtValorMaterial = new javax.swing.JTextField();
        txtNivel = new javax.swing.JTextField();
        comboAulas = new javax.swing.JComboBox();
        txtMesAno = new javax.swing.JFormattedTextField();
        btnProcessar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel10 = new javax.swing.JLabel();
        comboMaterialEscola = new javax.swing.JComboBox();
        txtValorPagoMaterial = new javax.swing.JFormattedTextField();
        lblInfoVlrTotalMaterial = new javax.swing.JLabel();
        comboDinheiroCheque = new javax.swing.JComboBox();
        jLabel11 = new javax.swing.JLabel();
        lblNroIdentCheque = new javax.swing.JLabel();
        txtNroIdentCheque = new javax.swing.JTextField();
        lblDatComCheque = new javax.swing.JLabel();
        txtBoxDataCompensacao = new javax.swing.JFormattedTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Gerar Mensalidades");
        setModalExclusionType(java.awt.Dialog.ModalExclusionType.APPLICATION_EXCLUDE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("Aluno:");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Nível:");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Valor das Aulas (R$):");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("Valor do Material (R$):");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("Parcelas Aulas:");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setText("Duração (Meses):");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setText("Valor pago do Material (Entrada):");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setText("Mês/Ano da Primeira Parcela:");

        txtValorAulas.setEditable(false);
        txtValorAulas.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        txtNome.setEditable(false);
        txtNome.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        txtDuracao.setEditable(false);
        txtDuracao.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        txtValorMaterial.setEditable(false);
        txtValorMaterial.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        txtNivel.setEditable(false);
        txtNivel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        comboAulas.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "à vista" }));
        comboAulas.setNextFocusableComponent(comboMaterialEscola);
        comboAulas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboAulasActionPerformed(evt);
            }
        });

        try {
            txtMesAno.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtMesAno.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtMesAno.setNextFocusableComponent(comboAulas);

        btnProcessar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/unique/Imagens/dialog-ok.png"))); // NOI18N
        btnProcessar.setText("Processar");
        btnProcessar.setNextFocusableComponent(btnCancelar);
        btnProcessar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProcessarActionPerformed(evt);
            }
        });

        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/unique/Imagens/list-remove.png"))); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.setNextFocusableComponent(txtMesAno);
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Segoe UI Light", 0, 36)); // NOI18N
        jLabel9.setText("Geração de Mensalidades");
        jLabel9.setToolTipText("");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel10.setText("Material Comprado na Escola:");

        comboMaterialEscola.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Não", "Sim" }));

        txtValorPagoMaterial.setColumns(8);
        try {
            txtValorPagoMaterial.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("#.###,##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtValorPagoMaterial.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtValorPagoMaterial.setText("");
        txtValorPagoMaterial.setEnabled(false);
        txtValorPagoMaterial.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtValorPagoMaterial.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtValorPagoMaterialFocusGained(evt);
            }
        });
        txtValorPagoMaterial.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtValorPagoMaterialMouseClicked(evt);
            }
        });
        txtValorPagoMaterial.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtValorPagoMaterialKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtValorPagoMaterialKeyTyped(evt);
            }
        });

        lblInfoVlrTotalMaterial.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N

        comboDinheiroCheque.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Dinheiro", "Cheque" }));
        comboDinheiroCheque.setEnabled(false);

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel11.setText("Forma de Pagamento do Material:");

        lblNroIdentCheque.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblNroIdentCheque.setText("Número de Identificação do Cheque:");

        txtNroIdentCheque.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        lblDatComCheque.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblDatComCheque.setText("Data de Compensação do Cheque:");

        try {
            txtBoxDataCompensacao.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtBoxDataCompensacao.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jSeparator1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel4))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(txtMesAno, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel11))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(txtValorMaterial, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel10))))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGap(64, 64, 64)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel2))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(4, 4, 4)
                                        .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 371, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 215, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(txtNivel, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addGap(4, 4, 4)
                                                .addComponent(txtValorAulas, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lblNroIdentCheque)
                                    .addComponent(jLabel7)
                                    .addComponent(lblDatComCheque))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtDuracao)
                                    .addComponent(comboAulas, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(comboMaterialEscola, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtValorPagoMaterial, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)
                                    .addComponent(comboDinheiroCheque, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addComponent(lblInfoVlrTotalMaterial, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtNroIdentCheque, javax.swing.GroupLayout.PREFERRED_SIZE, 354, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtBoxDataCompensacao, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnProcessar)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addGap(4, 4, 4)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel6)
                    .addComponent(txtDuracao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNivel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtValorAulas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel5)
                    .addComponent(comboAulas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtValorMaterial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(comboMaterialEscola, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(comboDinheiroCheque, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel8)
                        .addComponent(txtMesAno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblInfoVlrTotalMaterial, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtValorPagoMaterial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNroIdentCheque)
                    .addComponent(txtNroIdentCheque, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDatComCheque)
                    .addComponent(txtBoxDataCompensacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnProcessar)
                    .addComponent(btnCancelar))
                .addGap(10, 10, 10))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void comboAulasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboAulasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboAulasActionPerformed

    private void btnProcessarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProcessarActionPerformed
        //Realizando algumas verificações:
        String[] mesAno = txtMesAno.getText().split("/");
        int mes = Integer.parseInt(mesAno[0]);
        int ano = Integer.parseInt(mesAno[1]);

        if(!(mes > 0 && mes < 13) || (mes < Calendar.getInstance().get(Calendar.MONTH) 
                                        && ano <= Calendar.getInstance().get(Calendar.YEAR))){
            JOptionPane.showMessageDialog(this, "Informar um mês válido!", "Erro", JOptionPane.ERROR_MESSAGE);
            txtMesAno.requestFocusInWindow();
            return;
        }
        
        if(ano < Calendar.getInstance().get(Calendar.YEAR)){
            JOptionPane.showMessageDialog(this, "Informar um ano válido!", "Erro", JOptionPane.ERROR_MESSAGE);
            txtMesAno.requestFocusInWindow();
            return;
        }
        
        //O valor indicado no campo da parcela não pode ser superior ao valor do material inteiro:
        String valorTemp = txtValorPagoMaterial.getText();
        valorTemp = valorTemp.replaceAll("\\.", "");
        valorTemp = valorTemp.replaceAll("\\,", ".");
        if(comboMaterialEscola.getSelectedIndex() > 0 && Double.parseDouble(valorTemp) > aluno.getNivelAtual().getValorMaterial()) {
            JOptionPane.showMessageDialog(this, "O valor informado para pagamento do material é maior que o valor total do material nesse nível (R$ " + aluno.getNivelAtual().getValorMaterial() + ")", "Erro", JOptionPane.ERROR_MESSAGE);
            txtValorPagoMaterial.requestFocusInWindow();
            return;
        }
        
        //Se estamos pagando o material em cheque, validando a data do mesmo:
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date dataCompen = null;
        
        if(comboDinheiroCheque.isEnabled() && comboDinheiroCheque.getSelectedIndex() == 1) {
            try {
                dataCompen = DateFormat.getDateInstance().parse(txtBoxDataCompensacao.getText());
            } catch (ParseException ex) {
                JOptionPane.showMessageDialog(this, "Data de compensação do cheque é inválida!", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        
        //Se estamos pagando o material em cheque, validando a data do mesmo:
        if(comboDinheiroCheque.isEnabled() && comboDinheiroCheque.getSelectedIndex() == 1 && "".equals(txtNroIdentCheque.getText())) {
            JOptionPane.showMessageDialog(this, "Número de identificação do cheque não pode ficar vazio!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        //Buscando a confirmação:
        if(JOptionPane.showConfirmDialog(this, "Confirma geração de mensalidades para o aluno " + aluno.getNome() + ", no nível " + aluno.getNivelAtual().getCodigo() + "?", "Confirmação", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            //Estamos pagando o curso à vista?
            if(comboAulas.getSelectedIndex() == 0) {
                conexao = HibernateUtil.openSession();
                tx = conexao.beginTransaction();
                
                try{
                    //Gerando apenas UMA mensalidade, com o status PAGA:
                    Mensalidade temp = new Mensalidade();
                    
                    //Se for à vista, pagar com 5% de desconto.
                    double valorAulas = aluno.getNivelAtual().getValorAulas();
                    valorAulas *= 0.95;
                    
                    String valorTmp = txtValorPagoMaterial.getText();
                    valorTmp = valorTemp.replaceAll("\\.", "");
                    valorTmp = valorTemp.replaceAll("\\,", ".");
                    double valorMaterial = Double.parseDouble(valorTmp);
                    
                    temp.setAluno(aluno);
                    temp.setNivel(aluno.getNivelAtual());
                    temp.setPaga(true);
                    temp.setValorAulas(aluno.getNivelAtual().getValorAulas());
                    temp.setValorMaterial(aluno.getNivelAtual().getValorMaterial());
                    //temp.setValorPago(valorAulas + valorMaterial);
                    temp.setValorPago(valorAulas);
                    temp.setMesVencto(mes);
                    temp.setAnoVencto(ano);
                    
                    String strPagamento = dateFormat.format(Calendar.getInstance().getTime());
                    Date dataPagamento = null;
                    try {
                        dataPagamento = DateFormat.getDateInstance().parse(strPagamento);
                    } catch (ParseException ex) {
                        Logger.getLogger(GerarMensalidades.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    temp.setDataPagto(dataPagamento);
                    
                    conexao.saveOrUpdate(temp);
                    tx.commit();
                }catch (Exception e) {
                    conexao.close();
                    JOptionPane.showMessageDialog(this, "Operação mal sucedida. Motivo: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
                
                conexao.close();
                
                //Feedback:
                JOptionPane.showMessageDialog(this, "Registro de pagamento À VISTA gerado com sucesso!", "INFO", JOptionPane.INFORMATION_MESSAGE);
            } else {    //Estamos parcelando o curso:
                for(int x = 0; x < comboAulas.getSelectedIndex(); x++) {
                    conexao = HibernateUtil.openSession();
                    tx = conexao.beginTransaction();

                    try{
                        //Gerando apenas UMA mensalidade, com o status PAGA:
                        Mensalidade temp = new Mensalidade();

                        temp.setAluno(aluno);
                        temp.setNivel(aluno.getNivelAtual());
                        temp.setPaga(false);
                        temp.setValorAulas(aluno.getNivelAtual().getValorAulas() / comboAulas.getSelectedIndex());
                        temp.setValorMaterial(0d);
                        temp.setValorPago(0d);
                        temp.setMesVencto(mes);
                        temp.setAnoVencto(ano);

                        temp.setDataPagto(null);

                        conexao.saveOrUpdate(temp);
                        tx.commit();
                        
                        mes++;
                        
                        if(mes > 12) {
                            mes = 1;
                            ano++;
                        }
                    }catch (Exception e) {
                        conexao.close();
                        JOptionPane.showMessageDialog(this, "Operação mal sucedida. Motivo: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                    }

                    conexao.close();
                }
                
                //Feedback:
                JOptionPane.showMessageDialog(this, "Foram geradas " + comboAulas.getSelectedIndex() + " mensalidades com sucesso!", "INFO", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        
        //Estamos comprando o material aqui na escola?
        if(comboMaterialEscola.getSelectedIndex() > 0) {
            //Salvando o valor pago na tabela:
            PagtoMaterial material = new PagtoMaterial();
            
            material.setAluno(aluno);
            material.setNivel(aluno.getNivelAtual());
            material.setValorPago(Double.parseDouble(valorTemp));
            
            //Estamos pagando em cheque?
            if(comboDinheiroCheque.getSelectedIndex() == 1) {
                material.setNumeroCheque(txtNroIdentCheque.getText());
                material.setDataCompensacao(dataCompen);
            } else {
                material.setNumeroCheque(null);
                material.setDataCompensacao(null);
            }
            
            String strDataHoje = dateFormat.format(Calendar.getInstance().getTime());
            Date dataHoje = null;
            try {
                dataHoje = DateFormat.getDateInstance().parse(strDataHoje);
            } catch (ParseException ex) {
                Logger.getLogger(GerarMensalidades.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            material.setDataPagto(dataHoje);
            
            //Salvando no banco:
            try {
                conexao = HibernateUtil.openSession();
                tx = conexao.beginTransaction();

                conexao.saveOrUpdate(material);
                tx.commit();

                conexao.close();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Operação mal sucedida. Motivo: " + e.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
            }
        }
        
        //Gerando a matrícula do Aluno:
        Matricula matricula = new Matricula();
        
        long qtdeParcelasAulas = 1;
        
        matricula.setAluno(aluno);
        matricula.setNivel(aluno.getNivelAtual());
        if(comboAulas.getSelectedIndex() > 0)
            qtdeParcelasAulas = (long)comboAulas.getSelectedIndex();
        matricula.setQtdeParcelasAulas(qtdeParcelasAulas);
        matricula.setQtdeParcelasMaterial(999L);
        matricula.setValorAulas(aluno.getNivelAtual().getValorAulas());
        matricula.setValorMaterial(aluno.getNivelAtual().getValorMaterial());
        
        if(comboMaterialEscola.getSelectedIndex() > 0)
            matricula.setMaterialEscola(true);
        else
            matricula.setMaterialEscola(false);
        
        String strDataHoje = dateFormat.format(Calendar.getInstance().getTime());
        Date dataHoje = null;
        try {
            dataHoje = DateFormat.getDateInstance().parse(strDataHoje);
        } catch (ParseException ex) {
            Logger.getLogger(GerarMensalidades.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        matricula.setDataMatricula(dataHoje);
        
        //Salvando no banco:
        try {
            conexao = HibernateUtil.openSession();
            tx = conexao.beginTransaction();

            conexao.saveOrUpdate(matricula);
            tx.commit();

            conexao.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Operação mal sucedida. Motivo: " + e.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
        }
        
        //Se Chegamos até aqui, perguntando sobre a impressão dos boletos:
        if(JOptionPane.showConfirmDialog(this, "Deseja imprimir o carnê de pagamento?", "Confirmação", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
            //Conecatando com o hibernate:
            conexao = HibernateUtil.openSession();
            Transaction tx = conexao.beginTransaction();

            //Gerando relatorio:
            HashMap map = new HashMap();
            JasperPrint jasperPrint = null;
            Connection connection = null;
            try {
                connection = DriverManager.getConnection("jdbc:firebirdsql:localhost:C:\\Banco\\UNIQUE.FDB","sysdba","1123581321");
            } catch (SQLException ex) {
                Logger.getLogger(GerarMensalidades.class.getName()).log(Level.SEVERE, null, ex);
            }

            map.put("AlunoID", aluno.getID());
            try {
                JasperReport compiled = JasperCompileManager.compileReport("C:\\Banco\\Relatorios\\Carne.jrxml");
                jasperPrint = JasperFillManager.fillReport(compiled, map, connection);
                JRViewer viewer = new JRViewer(jasperPrint);
                JFrame report = new JFrame();
                report.setExtendedState(MAXIMIZED_BOTH);
                report.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                report.setTitle("Carnê de Pagamento");
                Container c = report.getContentPane();
                c.add(viewer);
                report.setVisible(true);

            } catch (JRException ex) {
                Logger.getLogger(GerarMensalidades.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(this, "Não foi possível listar o carnê de pagamento. Motivo: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
        
        //Perguntando sobre a impressão do contrato:
        if(JOptionPane.showConfirmDialog(this, "Deseja imprimir o contrato do aluno?", "Confirmação", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
            //Conecatando com o hibernate:
            conexao = HibernateUtil.openSession();
            Transaction tx = conexao.beginTransaction();

            //Gerando relatorio:
            HashMap map = new HashMap();
            JasperPrint jasperPrint = null;
            Connection connection = null;
            try {
                connection = DriverManager.getConnection("jdbc:firebirdsql:localhost:C:\\Banco\\UNIQUE.FDB","sysdba","1123581321");
            } catch (SQLException ex) {
                Logger.getLogger(GerarMensalidades.class.getName()).log(Level.SEVERE, null, ex);
            }

            map.put("AlunoID", aluno.getID());
            try {
                JasperReport compiled = JasperCompileManager.compileReport("C:\\Banco\\Relatorios\\Contrato.jrxml");
                jasperPrint = JasperFillManager.fillReport(compiled, map, connection);
                JRViewer viewer = new JRViewer(jasperPrint);
                JFrame report = new JFrame();
                report.setExtendedState(MAXIMIZED_BOTH);
                report.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                report.setTitle("Contrato de Matrícula");
                Container c = report.getContentPane();
                c.add(viewer);
                report.setVisible(true);
            } catch (JRException ex) {
                Logger.getLogger(GerarMensalidades.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(this, "Não foi possível listar o contrato. Motivo: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
        
        //Fechando a janela:
        this.dispose();
    }//GEN-LAST:event_btnProcessarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        comboAulas.setSelectedIndex(0);
        comboMaterialEscola.setSelectedIndex(0);
        txtValorPagoMaterial.setText("0.000,00");
        txtMesAno.setText("");
        
        txtMesAno.requestFocusInWindow();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void txtValorPagoMaterialFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtValorPagoMaterialFocusGained
        // TODO add your handling code here:
        //txtValorAulas.select(0, txtValorAulas.getColumns());
        txtValorPagoMaterial.select(txtValorAulas.getColumns(), txtValorAulas.getColumns());
    }//GEN-LAST:event_txtValorPagoMaterialFocusGained

    private void txtValorPagoMaterialMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtValorPagoMaterialMouseClicked
        // TODO add your handling code here:
        txtValorPagoMaterial.selectAll();
    }//GEN-LAST:event_txtValorPagoMaterialMouseClicked

    private void txtValorPagoMaterialKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtValorPagoMaterialKeyPressed
        // TODO add your handling code here:
        if(!((evt.getKeyChar() == '0') || (evt.getKeyChar() == '1') || (evt.getKeyChar() == '2') ||
            (evt.getKeyChar() == '3') || (evt.getKeyChar() == '4') || (evt.getKeyChar() == '5') ||
            (evt.getKeyChar() == '6') || (evt.getKeyChar() == '7') || (evt.getKeyChar() == '8') ||
            (evt.getKeyChar() == '9')))
            return;
    }//GEN-LAST:event_txtValorPagoMaterialKeyPressed

    private void txtValorPagoMaterialKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtValorPagoMaterialKeyTyped
        // TODO add your handling code here:
        String temp = txtValorPagoMaterial.getText();

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

            txtValorPagoMaterial.setText(temp);
        }
        else if(evt.getKeyChar() == 8) //Backspace
        {
            temp = temp.replaceAll("\\,|\\.", "");
            temp = '0' + temp;

            StringBuilder builder = new StringBuilder(temp);
            builder.insert(1, '.');
            builder.insert(5, ',');
            temp = builder.toString();

            txtValorPagoMaterial.setText(temp);
        }
    }//GEN-LAST:event_txtValorPagoMaterialKeyTyped

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
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
            java.util.logging.Logger.getLogger(GerarMensalidades.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GerarMensalidades.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GerarMensalidades.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GerarMensalidades.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new GerarMensalidades().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnProcessar;
    private javax.swing.JComboBox comboAulas;
    private javax.swing.JComboBox comboDinheiroCheque;
    private javax.swing.JComboBox comboMaterialEscola;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblDatComCheque;
    private javax.swing.JLabel lblInfoVlrTotalMaterial;
    private javax.swing.JLabel lblNroIdentCheque;
    private javax.swing.JFormattedTextField txtBoxDataCompensacao;
    private javax.swing.JTextField txtDuracao;
    private javax.swing.JFormattedTextField txtMesAno;
    private javax.swing.JTextField txtNivel;
    private javax.swing.JTextField txtNome;
    private javax.swing.JTextField txtNroIdentCheque;
    private javax.swing.JTextField txtValorAulas;
    private javax.swing.JTextField txtValorMaterial;
    private javax.swing.JFormattedTextField txtValorPagoMaterial;
    // End of variables declaration//GEN-END:variables
}
