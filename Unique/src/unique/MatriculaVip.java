/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unique;

import Tabelas.Aluno;
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
 * @author arlorencini
 */
public class MatriculaVip extends javax.swing.JFrame {

    /**
     * Creates new form MatriculaVip
     */
    public MatriculaVip(Aluno a) {
        initComponents();
        
        aluno = null;
        aluno = a;
        
        txtBoxVlrMensalidade.setDocument(new JTextFieldLimit(8, false));
        
        String temp = Double.toString(aluno.getNivelAtual().getValorAulas());
        if(aluno.getNivelAtual().getValorAulas() < 10){
            temp = "000" + temp;
        }
        else if(aluno.getNivelAtual().getValorAulas() < 100){
            temp = "00" + temp;
        }
        else if(aluno.getNivelAtual().getValorAulas() < 1000){
            temp = "0" + temp;
        }
        temp = temp.replaceAll("\\.", "");
        StringBuilder builder = new StringBuilder(temp);
        builder.insert(1, '.');
        builder.insert(5, ',');
        temp = builder.toString();
        if(aluno.getNivelAtual().getValorAulas().intValue() - aluno.getNivelAtual().getValorAulas()== 0){
            temp += "0";
        }
        
        txtBoxVlrMensalidade.setText(temp);
        
        txtBoxValorAulaExtra.setDocument(new JTextFieldLimit(8, false));
        txtBoxValorAulaExtra.setText("0.000,00");
        
        txtValorPagoMaterial.setDocument(new JTextFieldLimit(8, false));
        txtValorPagoMaterial.setText("0.000,00");
        
        //Escondendo alguns campos:
        txtNroIdentCheque.setEnabled(false);
        txtNroIdentCheque.setEnabled(false);

        txtBoxDataCompensacao.setEnabled(false);
        txtBoxDataCompensacao.setEnabled(false);

        txtValorPagoMaterial.setEnabled(false);
        
        //Setando alguns valores padrão:
        txtNome.setText(aluno.getNome());
        txtNivel.setText(aluno.getNivelAtual().getNome());
        
        comboMaterialEscola.addActionListener((ActionEvent e) -> {
            MudouItemComboBox(e); 
        });
        
        comboDinheiroCheque.addActionListener((ActionEvent e) -> {
            MudouItemComboBoxCheque(e); 
        });
        
        this.setLocationRelativeTo(null);
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("Logo.png")));
    }
    
    private void MudouItemComboBox(ActionEvent e) {
        if(comboMaterialEscola.getSelectedIndex() > 0) {
            txtValorPagoMaterial.setEnabled(true);
            comboDinheiroCheque.setEnabled(true);
        } else{            
            txtNroIdentCheque.setEnabled(false);
            txtBoxDataCompensacao.setEnabled(false);
            txtValorPagoMaterial.setEnabled(false);
            comboDinheiroCheque.setEnabled(false);
        }
        
        txtValorPagoMaterial.setText("0.000,00");
        txtNroIdentCheque.setText("");
        txtBoxDataCompensacao.setText("  /  /   ");
    }
    
    private void MudouItemComboBoxCheque(ActionEvent e) {
        if(comboDinheiroCheque.getSelectedIndex() == 0) {   //Em dinheiro
            txtValorPagoMaterial.setEnabled(true);
            txtNroIdentCheque.setEnabled(true);
            txtBoxDataCompensacao.setEnabled(false);
        } else{                                             //Em Cheque
            txtBoxDataCompensacao.setEnabled(true);
            txtNroIdentCheque.setEnabled(true);
            txtValorPagoMaterial.setEnabled(true);
        }
        
        txtBoxDataCompensacao.setText("  /  /    ");
        txtNroIdentCheque.setText("");
        txtValorPagoMaterial.setText("0.000,00");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator1 = new javax.swing.JSeparator();
        jLabel9 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtNome = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtNivel = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtBoxVlrMensalidade = new javax.swing.JFormattedTextField();
        txtBoxValorAulaExtra = new javax.swing.JFormattedTextField();
        jLabel10 = new javax.swing.JLabel();
        comboMaterialEscola = new javax.swing.JComboBox();
        jLabel11 = new javax.swing.JLabel();
        comboDinheiroCheque = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        txtValorPagoMaterial = new javax.swing.JFormattedTextField();
        lblNroIdentCheque = new javax.swing.JLabel();
        txtNroIdentCheque = new javax.swing.JTextField();
        lblDatComCheque = new javax.swing.JLabel();
        txtBoxDataCompensacao = new javax.swing.JFormattedTextField();
        btnSalvar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        txtBoxQtdAulas = new javax.swing.JFormattedTextField();
        jLabel8 = new javax.swing.JLabel();
        txtHorasSemanais = new javax.swing.JFormattedTextField();
        txtMesAno = new javax.swing.JFormattedTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtMesAnoIni = new javax.swing.JFormattedTextField();
        txtMesAnoFim = new javax.swing.JFormattedTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Matrícula de Aluno VIP");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Segoe UI Light", 0, 36)); // NOI18N
        jLabel9.setText("Matrícula de Aluno VIP");
        jLabel9.setToolTipText("");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("Aluno:");

        txtNome.setEditable(false);
        txtNome.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Nível:");

        txtNivel.setEditable(false);
        txtNivel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Valor da Mensalidade:");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("Valor da Aula Extra:");

        txtBoxVlrMensalidade.setColumns(8);
        try {
            txtBoxVlrMensalidade.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("#.###,##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtBoxVlrMensalidade.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtBoxVlrMensalidade.setText("");
        txtBoxVlrMensalidade.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtBoxVlrMensalidade.setNextFocusableComponent(txtMesAno);
        txtBoxVlrMensalidade.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtBoxVlrMensalidadeFocusGained(evt);
            }
        });
        txtBoxVlrMensalidade.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtBoxVlrMensalidadeMouseClicked(evt);
            }
        });
        txtBoxVlrMensalidade.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBoxVlrMensalidadeKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBoxVlrMensalidadeKeyTyped(evt);
            }
        });

        txtBoxValorAulaExtra.setColumns(8);
        try {
            txtBoxValorAulaExtra.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("#.###,##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtBoxValorAulaExtra.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtBoxValorAulaExtra.setText("");
        txtBoxValorAulaExtra.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtBoxValorAulaExtra.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtBoxValorAulaExtraFocusGained(evt);
            }
        });
        txtBoxValorAulaExtra.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtBoxValorAulaExtraMouseClicked(evt);
            }
        });
        txtBoxValorAulaExtra.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBoxValorAulaExtraKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBoxValorAulaExtraKeyTyped(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel10.setText("Material Comprado na Escola:");

        comboMaterialEscola.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Não", "Sim" }));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel11.setText("Forma de Pagamento do Material:");

        comboDinheiroCheque.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Dinheiro", "Cheque" }));
        comboDinheiroCheque.setEnabled(false);

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setText("Valor pago do Material (Entrada):");

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

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("Aulas por Semana:");

        txtBoxQtdAulas.setColumns(8);
        try {
            txtBoxQtdAulas.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("#")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtBoxQtdAulas.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtBoxQtdAulas.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtBoxQtdAulas.setNextFocusableComponent(txtHorasSemanais);
        txtBoxQtdAulas.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtBoxQtdAulasFocusGained(evt);
            }
        });
        txtBoxQtdAulas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtBoxQtdAulasMouseClicked(evt);
            }
        });
        txtBoxQtdAulas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBoxQtdAulasKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBoxQtdAulasKeyTyped(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setText("Horas por Aula:");

        try {
            txtHorasSemanais.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##:##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtHorasSemanais.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtHorasSemanais.setNextFocusableComponent(txtBoxValorAulaExtra);
        txtHorasSemanais.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtHorasSemanaisFocusGained(evt);
            }
        });

        try {
            txtMesAno.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtMesAno.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtMesAno.setNextFocusableComponent(txtMesAnoIni);

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel12.setText("Mês/Ano da Primeira Parcela:");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel13.setText("Mês/Ano Início do Contrato:");

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel14.setText("Mês/Ano Fim do Contrato:");

        try {
            txtMesAnoIni.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtMesAnoIni.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtMesAnoIni.setNextFocusableComponent(txtMesAnoFim);

        try {
            txtMesAnoFim.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtMesAnoFim.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtMesAnoFim.setNextFocusableComponent(txtBoxQtdAulas);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnCancelar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSalvar))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(45, 45, 45)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel12)
                                    .addComponent(jLabel13)
                                    .addComponent(jLabel14))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtMesAno, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txtNome, javax.swing.GroupLayout.DEFAULT_SIZE, 371, Short.MAX_VALUE)
                                        .addComponent(txtBoxVlrMensalidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtNivel))
                                    .addComponent(txtMesAnoFim, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtMesAnoIni, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel7)
                                    .addComponent(lblNroIdentCheque)
                                    .addComponent(lblDatComCheque)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel8))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtBoxValorAulaExtra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(comboMaterialEscola, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(comboDinheiroCheque, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtValorPagoMaterial, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtNroIdentCheque, javax.swing.GroupLayout.PREFERRED_SIZE, 354, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtBoxDataCompensacao, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtBoxQtdAulas, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtHorasSemanais, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 98, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtNivel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtBoxVlrMensalidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMesAno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txtMesAnoIni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(txtMesAnoFim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBoxQtdAulas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtHorasSemanais, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtBoxValorAulaExtra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(comboMaterialEscola, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(comboDinheiroCheque, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtValorPagoMaterial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNroIdentCheque)
                    .addComponent(txtNroIdentCheque, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDatComCheque)
                    .addComponent(txtBoxDataCompensacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSalvar)
                    .addComponent(btnCancelar))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtBoxVlrMensalidadeFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtBoxVlrMensalidadeFocusGained
        txtBoxVlrMensalidade.select(txtBoxVlrMensalidade.getColumns(), txtBoxVlrMensalidade.getColumns());
    }//GEN-LAST:event_txtBoxVlrMensalidadeFocusGained

    private void txtBoxVlrMensalidadeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtBoxVlrMensalidadeMouseClicked
        txtBoxVlrMensalidade.selectAll();
    }//GEN-LAST:event_txtBoxVlrMensalidadeMouseClicked

    private void txtBoxVlrMensalidadeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBoxVlrMensalidadeKeyPressed
        // TODO add your handling code here:
        if(!((evt.getKeyChar() == '0') || (evt.getKeyChar() == '1') || (evt.getKeyChar() == '2') ||
            (evt.getKeyChar() == '3') || (evt.getKeyChar() == '4') || (evt.getKeyChar() == '5') ||
            (evt.getKeyChar() == '6') || (evt.getKeyChar() == '7') || (evt.getKeyChar() == '8') ||
            (evt.getKeyChar() == '9')))
            return;
    }//GEN-LAST:event_txtBoxVlrMensalidadeKeyPressed

    private void txtBoxVlrMensalidadeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBoxVlrMensalidadeKeyTyped
        // TODO add your handling code here:
        String temp = txtBoxVlrMensalidade.getText();

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

            txtBoxVlrMensalidade.setText(temp);
        } else if(evt.getKeyChar() == 8) {
            temp = temp.replaceAll("\\,|\\.", ""); //Backspace
            temp = '0' + temp;

            StringBuilder builder = new StringBuilder(temp);
            builder.insert(1, '.');
            builder.insert(5, ',');
            temp = builder.toString();

            txtBoxVlrMensalidade.setText(temp);
        }
    }//GEN-LAST:event_txtBoxVlrMensalidadeKeyTyped

    private void txtBoxValorAulaExtraFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtBoxValorAulaExtraFocusGained
        txtBoxValorAulaExtra.select(txtBoxValorAulaExtra.getColumns(), txtBoxValorAulaExtra.getColumns());
    }//GEN-LAST:event_txtBoxValorAulaExtraFocusGained

    private void txtBoxValorAulaExtraMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtBoxValorAulaExtraMouseClicked
        txtBoxValorAulaExtra.selectAll();
    }//GEN-LAST:event_txtBoxValorAulaExtraMouseClicked

    private void txtBoxValorAulaExtraKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBoxValorAulaExtraKeyPressed
        // TODO add your handling code here:
        if(!((evt.getKeyChar() == '0') || (evt.getKeyChar() == '1') || (evt.getKeyChar() == '2') ||
            (evt.getKeyChar() == '3') || (evt.getKeyChar() == '4') || (evt.getKeyChar() == '5') ||
            (evt.getKeyChar() == '6') || (evt.getKeyChar() == '7') || (evt.getKeyChar() == '8') ||
            (evt.getKeyChar() == '9')))
            return;
    }//GEN-LAST:event_txtBoxValorAulaExtraKeyPressed

    private void txtBoxValorAulaExtraKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBoxValorAulaExtraKeyTyped
        // TODO add your handling code here:
        String temp = txtBoxValorAulaExtra.getText();

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

            txtBoxValorAulaExtra.setText(temp);
        } else if(evt.getKeyChar() == 8) {
            temp = temp.replaceAll("\\,|\\.", ""); //Backspace
            temp = '0' + temp;

            StringBuilder builder = new StringBuilder(temp);
            builder.insert(1, '.');
            builder.insert(5, ',');
            temp = builder.toString();

            txtBoxValorAulaExtra.setText(temp);
        }
    }//GEN-LAST:event_txtBoxValorAulaExtraKeyTyped

    private void txtValorPagoMaterialFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtValorPagoMaterialFocusGained
        // TODO add your handling code here:
        //txtValorAulas.select(0, txtValorAulas.getColumns());
        txtValorPagoMaterial.select(txtValorPagoMaterial.getColumns(), txtValorPagoMaterial.getColumns());
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
            (evt.getKeyChar() == '9')) && (temp.startsWith("0"))){
            temp = temp.replaceAll("\\,|\\.", "");
            temp = temp.substring(1);

            StringBuilder builder = new StringBuilder(temp);
            builder.insert(1, '.');
            builder.insert(5, ',');
            temp = builder.toString();

            txtValorPagoMaterial.setText(temp);
        }
        else if(evt.getKeyChar() == 8){ //Backspace
            temp = temp.replaceAll("\\,|\\.", "");
            temp = '0' + temp;

            StringBuilder builder = new StringBuilder(temp);
            builder.insert(1, '.');
            builder.insert(5, ',');
            temp = builder.toString();

            txtValorPagoMaterial.setText(temp);
        }
    }//GEN-LAST:event_txtValorPagoMaterialKeyTyped

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        //PAssando o foco para o campo de valor:
        txtMesAno.requestFocusInWindow();
    }//GEN-LAST:event_formWindowOpened

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        //O valor indicado no campo da parcela não pode ser superior ao valor do material inteiro:
        boolean salvou = true;
        String valorTemp = txtValorPagoMaterial.getText();
        valorTemp = valorTemp.replaceAll("\\.", "");
        valorTemp = valorTemp.replaceAll("\\,", ".");
        if(comboMaterialEscola.getSelectedIndex() > 0 && Double.parseDouble(valorTemp) > aluno.getNivelAtual().getValorMaterial()) {
            JOptionPane.showMessageDialog(this, "O valor informado para pagamento do material é maior que o valor total do material nesse nível (R$ " + aluno.getNivelAtual().getValorMaterial() + ")", "Erro", JOptionPane.ERROR_MESSAGE);
            txtValorPagoMaterial.requestFocusInWindow();
            return;
        }
        
        if(txtBoxQtdAulas.getText().equals("") || txtBoxQtdAulas.getText().equals(" ") || 
                txtBoxQtdAulas.getText().equals("0")){
            JOptionPane.showMessageDialog(this, "A quantidade de aulas não foi preenchida. Verifique!", "Erro", JOptionPane.ERROR_MESSAGE);
            txtBoxQtdAulas.requestFocusInWindow();
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
                salvou = false;
                return;
            }
        }
        
        //Se estamos pagando o material em cheque, validando a data do mesmo:
        if(comboDinheiroCheque.isEnabled() && comboDinheiroCheque.getSelectedIndex() == 1 && "".equals(txtNroIdentCheque.getText())) {
            JOptionPane.showMessageDialog(this, "Número de identificação do cheque não pode ficar vazio!", "Erro", JOptionPane.ERROR_MESSAGE);
            salvou = false;
            return;
        }

        if(JOptionPane.showConfirmDialog(this, "Confirma matricula do aluno(a) " + aluno.getNome() + ", no nível " + aluno.getNivelAtual().getCodigo() + "?", "Confirmação", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            String[] mesAnoIni = txtMesAnoIni.getText().split("/");
            int mesIni = Integer.parseInt(mesAnoIni[0]);
            int anoIni = Integer.parseInt(mesAnoIni[1]);

            String[] mesAnoFim = txtMesAnoFim.getText().split("/");
            int mesFim = Integer.parseInt(mesAnoFim[0]);
            int anoFim = Integer.parseInt(mesAnoFim[1]);

            //Salvando a matricula atual:
            Tabelas.MatriculaVip matricula = new Tabelas.MatriculaVip();
            matricula.setAluno(aluno);

            if(comboMaterialEscola.getSelectedIndex() == 0)
                matricula.setMaterialEscola(false);
            else
                matricula.setMaterialEscola(true);

            matricula.setNivel(aluno.getNivelAtual());

            matricula.setMesIni(mesIni);
            matricula.setAnoIni(anoIni);
            matricula.setMesFim(mesFim);
            matricula.setAnoFim(anoFim);

            //Buscando o valor da aula extra:
            String valorAulaExtra = txtBoxValorAulaExtra.getText();
            valorAulaExtra = valorAulaExtra.replaceAll("\\.", "");
            valorAulaExtra = valorAulaExtra.replaceAll("\\,", ".");
            matricula.setValorAulaExcedente(Double.valueOf(valorAulaExtra));

            //Buscando o valor da mensalidade:
            String valorMensalidade = txtBoxVlrMensalidade.getText();
            valorMensalidade = valorMensalidade.replaceAll("\\.", "");
            valorMensalidade = valorMensalidade.replaceAll("\\,", ".");
            matricula.setValorMensal(Double.valueOf(valorMensalidade));

            matricula.setValorMaterial(aluno.getNivelAtual().getValorMaterial());

            //Data da matricula:
            String strDataHoje = dateFormat.format(Calendar.getInstance().getTime());
            Date dataHoje = null;
            try {
                dataHoje = DateFormat.getDateInstance().parse(strDataHoje);
            } catch (ParseException ex) {
                Logger.getLogger(GerarMensalidades.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            //Definindo a duração de cada aula:
            String[] tempo = txtHorasSemanais.getText().split(":");
            int horasSemanais = (Integer.parseInt(tempo[0]) * 60) + (Integer.parseInt(tempo[1]));
            matricula.setMinutosSemanais(horasSemanais);

            matricula.setDataMatricula(dataHoje);
            matricula.setAulasSemanais(Long.parseLong(txtBoxQtdAulas.getText()));

            //Salvando no banco:
            Session conexao;
            Transaction tx;
            
            conexao = HibernateUtil.openSession();
            
            try {
                tx = conexao.beginTransaction();
                conexao.saveOrUpdate(matricula);
                tx.commit();
                conexao.close();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Operação mal sucedida. Motivo: " + e.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
                salvou = false;
            }
            
            if(conexao.isOpen())
                conexao.close();
            
            String[] mesAno = txtMesAno.getText().split("/");
            int mes = Integer.parseInt(mesAno[0]);
            int ano = Integer.parseInt(mesAno[1]);
            
            for(int x = 0; x < 12; x++) {
                conexao = HibernateUtil.openSession();
                tx = conexao.beginTransaction();

                try{
                    Mensalidade temp = new Mensalidade();

                    temp.setAluno(aluno);
                    temp.setNivel(aluno.getNivelAtual());
                    temp.setPaga(false);
                    temp.setValorAulas(aluno.getNivelAtual().getValorAulas());
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

                strDataHoje = dateFormat.format(Calendar.getInstance().getTime());
                dataHoje = null;
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
                    salvou = false;
                }
            }
            
            if(salvou){
                if(JOptionPane.showConfirmDialog(this, "Deseja imprimir o contrato do aluno?", "Confirmação", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
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
                        JasperReport compiled = JasperCompileManager.compileReport("C:\\Banco\\Relatorios\\ContratoVip.jrxml");
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
                if(JOptionPane.showConfirmDialog(this, "Deseja imprimir o carnê de pagamento?", "Confirmação", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
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
                        JasperReport compiled = JasperCompileManager.compileReport("C:\\Banco\\Relatorios\\CarneVip2.jrxml");
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
            }
        }
        
        this.dispose();
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        //Fechando a janela:
        this.dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void txtBoxQtdAulasFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtBoxQtdAulasFocusGained
        txtBoxQtdAulas.select(txtBoxQtdAulas.getColumns(), txtBoxQtdAulas.getColumns());
    }//GEN-LAST:event_txtBoxQtdAulasFocusGained

    private void txtBoxQtdAulasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtBoxQtdAulasMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBoxQtdAulasMouseClicked

    private void txtBoxQtdAulasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBoxQtdAulasKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBoxQtdAulasKeyPressed

    private void txtBoxQtdAulasKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBoxQtdAulasKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBoxQtdAulasKeyTyped

    private void txtHorasSemanaisFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtHorasSemanaisFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHorasSemanaisFocusGained

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
            java.util.logging.Logger.getLogger(MatriculaVip.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MatriculaVip.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MatriculaVip.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MatriculaVip.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MatriculaVip(null).setVisible(true);
            }
        });
    }
    
    //Membros:
    Aluno aluno;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JComboBox comboDinheiroCheque;
    private javax.swing.JComboBox comboMaterialEscola;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblDatComCheque;
    private javax.swing.JLabel lblNroIdentCheque;
    private javax.swing.JFormattedTextField txtBoxDataCompensacao;
    private javax.swing.JFormattedTextField txtBoxQtdAulas;
    private javax.swing.JFormattedTextField txtBoxValorAulaExtra;
    private javax.swing.JFormattedTextField txtBoxVlrMensalidade;
    private javax.swing.JFormattedTextField txtHorasSemanais;
    private javax.swing.JFormattedTextField txtMesAno;
    private javax.swing.JFormattedTextField txtMesAnoFim;
    private javax.swing.JFormattedTextField txtMesAnoIni;
    private javax.swing.JTextField txtNivel;
    private javax.swing.JTextField txtNome;
    private javax.swing.JTextField txtNroIdentCheque;
    private javax.swing.JFormattedTextField txtValorPagoMaterial;
    // End of variables declaration//GEN-END:variables
}
