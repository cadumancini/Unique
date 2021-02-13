/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unique.Cadastros;

import Tabelas.Nivel;
import java.awt.KeyboardFocusManager;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.util.Collections;
import java.util.List;
import javax.swing.JOptionPane;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import unique.Consultas.NiveisCadastrados;
import util.HibernateUtil;

/**
 *
 * @author cemancini
 */
public class CadastroNiveis extends javax.swing.JFrame {

    Session conexao;
    Transaction tx;
    Long id;
    private static CadastroNiveis instance = null;
    
    NiveisCadastrados buscaNiveis = null;
    /**
     * Creates new form CadastroNiveis
     */
    private CadastroNiveis() {
        initComponents();
        
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("Logo.png")));
        txtCodigo.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.EMPTY_SET); 
        txtCodigo.addKeyListener(new java.awt.event.KeyAdapter(){  
            @Override
            public void keyTyped(java.awt.event.KeyEvent evt){
                txtCodigoKeyTyped(evt);
            }
        });
              
        txtValorAulas.setDocument(new JTextFieldLimit(8));
        txtValorMaterial.setDocument(new JTextFieldLimit(8));
        
        id = -1L;
    }
    
    public static CadastroNiveis getInstance() {
      if (instance == null)
         instance = new CadastroNiveis();
      return instance;
   }

    private void txtCodigoKeyTyped(java.awt.event.KeyEvent evt){
        if(evt.getKeyChar()== KeyEvent.VK_ENTER || evt.getKeyChar() == KeyEvent.VK_TAB){
            if(!txtCodigo.getText().isEmpty()){
                txtNome.setEditable(true);
                txtDescricao.setEditable(true);
                txtValorAulas.setEditable(true);
                txtValorMaterial.setEditable(true);
                txtDuracao.setEditable(true);
                txtTotalHoras.setEditable(true);
                txtHorasSemanais.setEditable(true);
                txtCargaHorMin.setEditable(true);
                txtCargaHorMax.setEditable(true);
                checkBoxVip.setEnabled(true);   
                checkBoxGotIt.setEnabled(true);   
                checkBoxProrrogavel.setEnabled(true);
                txtCargaHorMin.setEnabled(false);
                txtCargaHorMax.setEnabled(false);
                jComboIdioma.setEnabled(true);

                try{
                    conexao = HibernateUtil.openSession();

                    Criteria crit = conexao.createCriteria(Nivel.class);
                    crit.add(Restrictions.eq("Codigo", txtCodigo.getText()));
                    List results = crit.list();
                    if(results.size() > 0)
                    {
                        Nivel n = (Nivel)results.get(0);
                        txtNome.setText(n.getNome());
                        txtDescricao.setText(n.getDescricao());

                        String temp = Double.toString(n.getValorAulas());
                        if(n.getValorAulas() < 10){
                            temp = "000" + temp;
                        }
                        else if(n.getValorAulas() < 100){
                            temp = "00" + temp;
                        }
                        else if(n.getValorAulas() < 1000){
                            temp = "0" + temp;
                        }
                        temp = temp.replaceAll("\\.", "");
                        StringBuilder builder = new StringBuilder(temp);
                        builder.insert(1, '.');
                        builder.insert(5, ',');
                        temp = builder.toString();
                        if(n.getValorAulas().intValue() - n.getValorAulas() == 0){
                            temp += "0";
                        }
                        txtValorAulas.setText(temp);

                        temp = Double.toString(n.getValorMaterial());
                        if(n.getValorMaterial() < 10){
                            temp = "000" + temp;
                        }
                        else if(n.getValorMaterial() < 100){
                            temp = "00" + temp;
                        }
                        else if(n.getValorMaterial() < 1000){
                            temp = "0" + temp;
                        }
                        temp = temp.replaceAll("\\.", "");
                        builder = new StringBuilder(temp);
                        builder.insert(1, '.');
                        builder.insert(5, ',');
                        temp = builder.toString();
                        if(n.getValorMaterial().intValue() - n.getValorMaterial()== 0){
                            temp += "0";
                        }
                        txtValorMaterial.setText(temp);

                        txtDuracao.setText(Long.toString(n.getDuracao()));
                        txtTotalHoras.setText(Long.toString(n.getQtdHoras()));
                        int totalMinutos = n.getMinutosSemanais();
                        int horas, minutos;
                        horas = (int)totalMinutos / 60;
                        minutos = totalMinutos % 60;
                        String horasStr;
                        if(horas < 10)
                            horasStr = "0" + String.valueOf(horas);
                        else
                            horasStr = String.valueOf(horas);

                        String minutosStr;
                        if(minutos < 10)
                            minutosStr = "0" + String.valueOf(minutos);
                        else
                            minutosStr = String.valueOf(minutos);

                        txtHorasSemanais.setText(horasStr + minutosStr);
                        
                        checkBoxVip.setSelected(n.isVIP());
                        checkBoxProrrogavel.setSelected(n.isProrrogavel());
                        
                        if(n.isVIP()){
                            habilitarCamposVip(false);
                            txtCargaHorMin.setText(Integer.toString(n.getCargaHorMin()));
                            txtCargaHorMax.setText(Integer.toString(n.getCargaHorMax()));
                        }
                        else{
                            habilitarCamposVip(true);
                            txtCargaHorMin.setText("");
                            txtCargaHorMax.setText("");
                        }
                        
                        checkBoxGotIt.setSelected(n.isGotIt());

                        id = n.getID();
                        btnExcluir.setEnabled(true);
                    }
                    else
                    {
                        id = -1L;
                        txtNome.setText("");
                        txtDescricao.setText("");
                        txtValorAulas.setText("0.000,00");
                        txtValorMaterial.setText("0.000,00");
                        txtDuracao.setText("");
                        txtTotalHoras.setText("");
                        txtHorasSemanais.setText("");
                        txtCargaHorMin.setText("");
                        txtCargaHorMax.setText("");
                        
                        btnExcluir.setEnabled(false);
                        checkBoxVip.setSelected(false);
                        txtDuracao.setEditable(true);
                        txtHorasSemanais.setEditable(true);
                        txtTotalHoras.setEditable(true);
                        checkBoxVip.setEnabled(true);
                        checkBoxGotIt.setEnabled(true);
                        checkBoxProrrogavel.setEnabled(true);
                        jComboIdioma.setEnabled(true);
                        txtCargaHorMin.setEditable(true);
                        txtCargaHorMax.setEditable(true);
                    }
                    conexao.close();
                } catch(Exception e){
                    JOptionPane.showMessageDialog(this, "Operação mal sucedida. Motivo: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
                txtCodigo.transferFocus();
            }
            else
            {
                JOptionPane.showMessageDialog(this, "O campo de Código não deve ficar vazio!", "Erro", JOptionPane.OK_OPTION);
                txtCodigo.requestFocusInWindow();
            }
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

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        txtNome = new javax.swing.JTextField();
        btnSalvar = new javax.swing.JButton();
        txtDescricao = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        btnCancelar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        txtValorAulas = new javax.swing.JFormattedTextField();
        txtValorMaterial = new javax.swing.JFormattedTextField();
        btnPesquisar = new javax.swing.JButton();
        txtDuracao = new javax.swing.JFormattedTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtHorasSemanais = new javax.swing.JFormattedTextField();
        txtTotalHoras = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel10 = new javax.swing.JLabel();
        checkBoxVip = new javax.swing.JCheckBox();
        jLabel11 = new javax.swing.JLabel();
        checkBoxProrrogavel = new javax.swing.JCheckBox();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtCargaHorMin = new javax.swing.JTextField();
        txtCargaHorMax = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        checkBoxGotIt = new javax.swing.JCheckBox();
        jLabel15 = new javax.swing.JLabel();
        jComboIdioma = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastro de Níveis");
        setType(java.awt.Window.Type.POPUP);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("Código:");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Idioma:");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Descrição:");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("Valor Aulas (R$):");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("Valor Material (R$):");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setText("Duração (meses):");

        txtCodigo.setColumns(10);
        txtCodigo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtCodigo.setNextFocusableComponent(txtNome);
        txtCodigo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtCodigoFocusGained(evt);
            }
        });
        txtCodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCodigoKeyReleased(evt);
            }
        });

        txtNome.setEditable(false);
        txtNome.setColumns(30);
        txtNome.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtNome.setNextFocusableComponent(txtDescricao);
        txtNome.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtNomeFocusGained(evt);
            }
        });
        txtNome.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNomeKeyReleased(evt);
            }
        });

        btnSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/unique/Imagens/dialog-ok.png"))); // NOI18N
        btnSalvar.setText("Salvar");
        btnSalvar.setMaximumSize(new java.awt.Dimension(75, 23));
        btnSalvar.setMinimumSize(new java.awt.Dimension(75, 23));
        btnSalvar.setNextFocusableComponent(btnExcluir);
        btnSalvar.setPreferredSize(new java.awt.Dimension(75, 23));
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });

        txtDescricao.setEditable(false);
        txtDescricao.setColumns(50);
        txtDescricao.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtDescricao.setNextFocusableComponent(txtValorAulas);
        txtDescricao.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtDescricaoFocusGained(evt);
            }
        });
        txtDescricao.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDescricaoKeyReleased(evt);
            }
        });

        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/unique/Imagens/list-remove.png"))); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/unique/Imagens/list-remove.png"))); // NOI18N
        btnExcluir.setText("Excluir");
        btnExcluir.setToolTipText("");
        btnExcluir.setEnabled(false);
        btnExcluir.setNextFocusableComponent(btnCancelar);
        btnExcluir.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                btnExcluirFocusGained(evt);
            }
        });
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });

        txtValorAulas.setEditable(false);
        txtValorAulas.setColumns(8);
        try {
            txtValorAulas.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("#.###,##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtValorAulas.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtValorAulas.setText("");
        txtValorAulas.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtValorAulas.setNextFocusableComponent(txtValorMaterial);
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

        txtValorMaterial.setEditable(false);
        txtValorMaterial.setColumns(8);
        try {
            txtValorMaterial.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("#.###,##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtValorMaterial.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtValorMaterial.setText("");
        txtValorMaterial.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtValorMaterial.setNextFocusableComponent(txtDuracao);
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

        btnPesquisar.setText("...");
        btnPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisarActionPerformed(evt);
            }
        });

        txtDuracao.setEditable(false);
        try {
            txtDuracao.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtDuracao.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtDuracao.setNextFocusableComponent(txtTotalHoras);
        txtDuracao.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtDuracaoFocusGained(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setText("Total de horas:");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setText("Horas Semanais:");

        txtHorasSemanais.setEditable(false);
        try {
            txtHorasSemanais.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##:##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtHorasSemanais.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtHorasSemanais.setNextFocusableComponent(btnSalvar);
        txtHorasSemanais.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtHorasSemanaisFocusGained(evt);
            }
        });

        txtTotalHoras.setEditable(false);
        txtTotalHoras.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtTotalHoras.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtTotalHorasFocusGained(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Segoe UI Light", 0, 36)); // NOI18N
        jLabel9.setText("Cadastro de Níveis");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel10.setText("VIP:");

        checkBoxVip.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        checkBoxVip.setEnabled(false);
        checkBoxVip.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                checkBoxVipMouseClicked(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel11.setText("Prorrogável:");

        checkBoxProrrogavel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        checkBoxProrrogavel.setEnabled(false);
        checkBoxProrrogavel.setNextFocusableComponent(txtCargaHorMin);
        checkBoxProrrogavel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                checkBoxProrrogavelMouseClicked(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel12.setText("Carga Horária Mínima:");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel13.setText("Carga Horária Máxima:");

        txtCargaHorMin.setEditable(false);
        txtCargaHorMin.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtCargaHorMin.setNextFocusableComponent(txtCargaHorMax);
        txtCargaHorMin.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtCargaHorMinFocusGained(evt);
            }
        });
        txtCargaHorMin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCargaHorMinKeyPressed(evt);
            }
        });

        txtCargaHorMax.setEditable(false);
        txtCargaHorMax.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtCargaHorMax.setNextFocusableComponent(btnSalvar);
        txtCargaHorMax.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtCargaHorMaxFocusGained(evt);
            }
        });
        txtCargaHorMax.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCargaHorMaxKeyPressed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel14.setText("Got It!:");

        checkBoxGotIt.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        checkBoxGotIt.setEnabled(false);
        checkBoxGotIt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                checkBoxGotItMouseClicked(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel15.setText("Nome:");

        jComboIdioma.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jComboIdioma.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Inglês", "Espanhol" }));
        jComboIdioma.setEnabled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btnPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(txtDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(39, 39, 39)
                                        .addComponent(jLabel10)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(checkBoxVip)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(checkBoxGotIt))
                                    .addComponent(txtValorMaterial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(txtDuracao, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(jLabel7))
                                            .addComponent(txtHorasSemanais, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtTotalHoras, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(checkBoxProrrogavel)
                                    .addComponent(txtCargaHorMax, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtCargaHorMin, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jComboIdioma, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtValorAulas, javax.swing.GroupLayout.Alignment.LEADING))))
                            .addComponent(jLabel9))
                        .addGap(0, 64, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancelar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnPesquisar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 4, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(checkBoxVip, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel10)
                                .addComponent(jLabel14)
                                .addComponent(jLabel15)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)))
                    .addComponent(checkBoxGotIt, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jComboIdioma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtValorAulas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtValorMaterial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtDuracao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7)
                            .addComponent(txtTotalHoras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(txtHorasSemanais, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel11))
                    .addComponent(checkBoxProrrogavel, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txtCargaHorMin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txtCargaHorMax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        boolean salvar = true;

        if(txtNome.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "O campo Nome não pode ficar vazio!", "Erro", JOptionPane.ERROR_MESSAGE);
            salvar = false;
            txtNome.requestFocusInWindow();
        }
        
        if(checkBoxGotIt.isSelected() && checkBoxVip.isSelected()){
            JOptionPane.showMessageDialog(this, "Os campos de VIP e Got It não podem ficar marcados ao mesmo tempo!", "Erro", JOptionPane.ERROR_MESSAGE);
            salvar = false;
            checkBoxVip.requestFocusInWindow();
        }
        
        if(salvar){
            // Inserindo novo nível:
            conexao = HibernateUtil.openSession();
            tx = conexao.beginTransaction();

            Nivel nivel = new Nivel();
            nivel.setVIP(checkBoxVip.isSelected());
            nivel.setGotIt(checkBoxGotIt.isSelected());
            nivel.setProrrogavel(checkBoxProrrogavel.isSelected());
            
            if(nivel.isVIP()){
                nivel.setQtdHoras(0);
                nivel.setMinutosSemanais(0);
                nivel.setDuracao(0L);
                int cargaMin = 0, cargaMax = 0;
                try{
                    cargaMin = (Integer.parseInt(txtCargaHorMin.getText()));
                    cargaMax = (Integer.parseInt(txtCargaHorMax.getText()));
                    if(cargaMin == 0 || cargaMax == 0){
                        JOptionPane.showMessageDialog(this, "Os campos de carga horária mínima e máxima não podem ser preenchidos com zero!", 
                            "Erro", JOptionPane.ERROR_MESSAGE);
                        txtCargaHorMin.requestFocusInWindow();
                        tx.rollback();
                        conexao.close();
                        return;
                    }
                    nivel.setCargaHorMin(cargaMin);
                    nivel.setCargaHorMax(cargaMax);
                } catch (Exception e){
                    JOptionPane.showMessageDialog(this, "Os campos de carga horária mínima e máxima devem ser preenchidos com valores inteiros!", 
                            "Erro", JOptionPane.ERROR_MESSAGE);
                    txtCargaHorMin.requestFocusInWindow();                    
                    tx.rollback();
                    conexao.close();
                    return;
                }
            } else{
                nivel.setQtdHoras(Integer.parseInt(txtTotalHoras.getText()));
                String[] tempo = txtHorasSemanais.getText().split(":");
                int horasSemanais = (Integer.parseInt(tempo[0]) * 60) + (Integer.parseInt(tempo[1]));
                nivel.setMinutosSemanais(horasSemanais);
                nivel.setDuracao(Long.parseLong(txtDuracao.getText()));
                nivel.setCargaHorMin(0);
                nivel.setCargaHorMax(0);
            }
            
            nivel.setCodigo(txtCodigo.getText());
            nivel.setNome(txtNome.getText());
            nivel.setDescricao(txtDescricao.getText());
            String temp = txtValorAulas.getText();
            temp = temp.replaceAll("\\.", "");
            temp = temp.replaceAll("\\,", ".");
            nivel.setValorAulas(Double.parseDouble(temp));

            temp = txtValorMaterial.getText();
            temp = temp.replaceAll("\\.", "");
            temp = temp.replaceAll("\\,", ".");
            nivel.setValorMaterial(Double.parseDouble(temp));
            nivel.setIdioma(jComboIdioma.getSelectedItem().toString());

            if(id > -1)
                nivel.setID(id);

            try
            {
                conexao.saveOrUpdate(nivel);
                tx.commit();
                txtCodigo.requestFocusInWindow();
                btnExcluir.setEnabled(true);

            }catch(Exception ex){
                tx.rollback();
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Operação mal sucedida. Motivo: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
            conexao.close();
            
            JOptionPane.showMessageDialog(this, "Operação realizada com sucesso!", "Aviso", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void txtNomeFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNomeFocusGained
        txtNome.selectAll();
    }//GEN-LAST:event_txtNomeFocusGained

    private void txtDescricaoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDescricaoFocusGained
        txtDescricao.selectAll();
    }//GEN-LAST:event_txtDescricaoFocusGained

    private void txtCodigoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCodigoFocusGained
        txtCodigo.selectAll();
    }//GEN-LAST:event_txtCodigoFocusGained

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        limparCampos();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void limparCampos(){
        txtCodigo.requestFocusInWindow();
        txtNome.setText("");
        txtDescricao.setText("");
        txtValorAulas.setText("");
        txtValorMaterial.setText("");
        txtDuracao.setText("");
        txtTotalHoras.setText("");
        txtHorasSemanais.setText("");
        txtCargaHorMin.setText("");
        txtCargaHorMax.setText("");
        btnExcluir.setEnabled(false);
        jComboIdioma.setEnabled(false);
        checkBoxVip.setSelected(false);
        checkBoxGotIt.setSelected(false);
        checkBoxProrrogavel.setSelected(false);
    }
    
    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        int opcao = JOptionPane.showConfirmDialog(this, "Tem certeza que deseja excluir o Nível: " + txtCodigo.getText() + "?", "Confirmação", JOptionPane.YES_NO_OPTION);
        
        if(opcao == JOptionPane.YES_OPTION)
        {
            conexao = HibernateUtil.openSession();
            tx = conexao.beginTransaction();
            Criteria crit = conexao.createCriteria(Nivel.class);
            crit.add(Restrictions.eq("Codigo", txtCodigo.getText()));
            List results = crit.list();
            if(results.size() > 0)
            {
                try
                {
                    Nivel n = (Nivel)results.get(0);
                    conexao.delete(n);
                    tx.commit();
                    limparCampos();
                }catch(Exception ex){
                    tx.rollback();
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Operação mal sucedida. Motivo: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
            conexao.close();
        }
        else
        {
            txtCodigo.requestFocusInWindow();
            btnExcluir.setEnabled(false);
        }
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void btnExcluirFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_btnExcluirFocusGained
        if(!btnExcluir.isEnabled())
            btnCancelar.requestFocusInWindow();
    }//GEN-LAST:event_btnExcluirFocusGained

    private void txtValorAulasFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtValorAulasFocusGained
        //txtValorAulas.select(0, txtValorAulas.getColumns());
        txtValorAulas.select(txtValorAulas.getColumns(), txtValorAulas.getColumns());
    }//GEN-LAST:event_txtValorAulasFocusGained

    private void txtValorMaterialFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtValorMaterialFocusGained
        //txtValorMaterial.select(0, txtValorMaterial.getColumns());
        txtValorAulas.select(txtValorAulas.getColumns(), txtValorAulas.getColumns());
    }//GEN-LAST:event_txtValorMaterialFocusGained

    private void txtValorAulasKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtValorAulasKeyTyped
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

    private void txtValorAulasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtValorAulasMouseClicked
        txtValorAulas.selectAll();
    }//GEN-LAST:event_txtValorAulasMouseClicked

    private void txtValorMaterialMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtValorMaterialMouseClicked
        txtValorMaterial.selectAll();
    }//GEN-LAST:event_txtValorMaterialMouseClicked

    private void txtValorAulasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtValorAulasKeyPressed
        if(!((evt.getKeyChar() == '0') || (evt.getKeyChar() == '1') || (evt.getKeyChar() == '2') ||
            (evt.getKeyChar() == '3') || (evt.getKeyChar() == '4') || (evt.getKeyChar() == '5') ||
            (evt.getKeyChar() == '6') || (evt.getKeyChar() == '7') || (evt.getKeyChar() == '8') ||
            (evt.getKeyChar() == '9')))
            return;
    }//GEN-LAST:event_txtValorAulasKeyPressed

    private void txtValorMaterialKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtValorMaterialKeyPressed
        if(!((evt.getKeyChar() == '0') || (evt.getKeyChar() == '1') || (evt.getKeyChar() == '2') ||
            (evt.getKeyChar() == '3') || (evt.getKeyChar() == '4') || (evt.getKeyChar() == '5') ||
            (evt.getKeyChar() == '6') || (evt.getKeyChar() == '7') || (evt.getKeyChar() == '8') ||
            (evt.getKeyChar() == '9')))
            return;
    }//GEN-LAST:event_txtValorMaterialKeyPressed

    private void txtValorMaterialKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtValorMaterialKeyTyped
        String temp = txtValorMaterial.getText();
        
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
            
            txtValorMaterial.setText(temp);
        }
        else if(evt.getKeyChar() == 8) //Backspace
        {
            temp = temp.replaceAll("\\,|\\.", "");
            temp = '0' + temp;
            
            StringBuilder builder = new StringBuilder(temp);
            builder.insert(1, '.');
            builder.insert(5, ',');
            temp = builder.toString();
            
            txtValorMaterial.setText(temp);
        }
    }//GEN-LAST:event_txtValorMaterialKeyTyped

    private void btnPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisarActionPerformed
        buscaNiveis = NiveisCadastrados.getInstance();
        buscaNiveis.setCadastroNiveis(this);
        buscaNiveis.setVisible(rootPaneCheckingEnabled);
    }//GEN-LAST:event_btnPesquisarActionPerformed

    private void txtDuracaoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDuracaoFocusGained
        txtDuracao.selectAll();
    }//GEN-LAST:event_txtDuracaoFocusGained

    private void txtHorasSemanaisFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtHorasSemanaisFocusGained
        txtHorasSemanais.selectAll();
    }//GEN-LAST:event_txtHorasSemanaisFocusGained

    private void txtTotalHorasFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTotalHorasFocusGained
        txtTotalHoras.selectAll();
    }//GEN-LAST:event_txtTotalHorasFocusGained

    private void txtCodigoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoKeyReleased
        if(txtCodigo.getText().length() != 0){
            txtCodigo.setText(txtCodigo.getText().toUpperCase());
        }
    }//GEN-LAST:event_txtCodigoKeyReleased

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        instance = null;
    }//GEN-LAST:event_formWindowClosed

    private void txtNomeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNomeKeyReleased
        txtNome.setText(txtNome.getText().toUpperCase());
    }//GEN-LAST:event_txtNomeKeyReleased

    private void txtDescricaoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescricaoKeyReleased
        txtDescricao.setText(txtDescricao.getText().toUpperCase());
    }//GEN-LAST:event_txtDescricaoKeyReleased

    private void checkBoxVipMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_checkBoxVipMouseClicked
        if(checkBoxVip.isSelected()){
            habilitarCamposVip(false);
            if(checkBoxGotIt.isSelected()){
                JOptionPane.showMessageDialog(this, "Atenção: o campo Got It está marcado, e os dois não podem ficar marcados ao mesmo tempo!", "Erro", JOptionPane.ERROR_MESSAGE);
                checkBoxVip.setSelected(false);
                checkBoxGotIt.requestFocusInWindow();
            }
        }
        else
            habilitarCamposVip(true);
    }//GEN-LAST:event_checkBoxVipMouseClicked

    private void habilitarCamposVip(boolean ativar){
        txtDuracao.setEnabled(ativar);
        txtTotalHoras.setEnabled(ativar);
        txtHorasSemanais.setEnabled(ativar);
        checkBoxProrrogavel.setEnabled(ativar);
        txtCargaHorMin.setEnabled(!ativar); // contrario dos demais
        txtCargaHorMax.setEnabled(!ativar); // contrario dos demais       
    }
    
    private void checkBoxProrrogavelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_checkBoxProrrogavelMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_checkBoxProrrogavelMouseClicked

    private void txtCargaHorMinFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCargaHorMinFocusGained
        txtCargaHorMin.selectAll();
    }//GEN-LAST:event_txtCargaHorMinFocusGained

    private void txtCargaHorMaxFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCargaHorMaxFocusGained
        txtCargaHorMax.selectAll();
    }//GEN-LAST:event_txtCargaHorMaxFocusGained

    private void txtCargaHorMinKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCargaHorMinKeyPressed
        if(!((evt.getKeyChar() == '0') || (evt.getKeyChar() == '1') || (evt.getKeyChar() == '2') ||
            (evt.getKeyChar() == '3') || (evt.getKeyChar() == '4') || (evt.getKeyChar() == '5') ||
            (evt.getKeyChar() == '6') || (evt.getKeyChar() == '7') || (evt.getKeyChar() == '8') ||
            (evt.getKeyChar() == '9')))
            return;
    }//GEN-LAST:event_txtCargaHorMinKeyPressed

    private void txtCargaHorMaxKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCargaHorMaxKeyPressed
        if(!((evt.getKeyChar() == '0') || (evt.getKeyChar() == '1') || (evt.getKeyChar() == '2') ||
            (evt.getKeyChar() == '3') || (evt.getKeyChar() == '4') || (evt.getKeyChar() == '5') ||
            (evt.getKeyChar() == '6') || (evt.getKeyChar() == '7') || (evt.getKeyChar() == '8') ||
            (evt.getKeyChar() == '9')))
            return;
    }//GEN-LAST:event_txtCargaHorMaxKeyPressed

    private void checkBoxGotItMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_checkBoxGotItMouseClicked
        if(checkBoxGotIt.isSelected()){
            if(checkBoxVip.isSelected()){
                JOptionPane.showMessageDialog(this, "Atenção: o campo VIP está marcado, e os dois não podem ficar marcados ao mesmo tempo!", "Erro", JOptionPane.ERROR_MESSAGE);
                checkBoxGotIt.setSelected(false);
                checkBoxVip.requestFocusInWindow();
            }
        }
    }//GEN-LAST:event_checkBoxGotItMouseClicked

    public void preencherCampos(Long id, String codigo, String nome, String descr, Double valorAulas, Double valorMaterial, 
            Long duracao, int totalHoras, int minutosSemanais, boolean vip, boolean prorrogavel, int cargaMin, int cargaMax, boolean gotIt, String idioma){
        this.id = id;
        
        txtCodigo.setText(codigo);
        txtNome.setText(nome);
        txtDescricao.setText(descr);
        
        checkBoxVip.setSelected(vip);
        checkBoxProrrogavel.setSelected(prorrogavel);
        checkBoxGotIt.setSelected(gotIt);
        if(vip){
            habilitarCamposVip(false);
            txtCargaHorMin.setText(Integer.toString(cargaMin));
            txtCargaHorMax.setText(Integer.toString(cargaMax));
        } else{
            habilitarCamposVip(true);
            txtCargaHorMin.setText("");
            txtCargaHorMax.setText("");
            txtDuracao.setText(duracao.toString());
            txtTotalHoras.setText(String.valueOf(totalHoras));
            int horas, minutos;
            horas = (int)minutosSemanais / 60;
            minutos = minutosSemanais % 60;
            String horasStr = "";

            if(horas < 10)
                horasStr = "0" + String.valueOf(horas);
            else
                horasStr = String.valueOf(horas);

            String minutosStr = "";
            if(minutos < 10)
                minutosStr = "0" + String.valueOf(minutos);
            else
                minutosStr = String.valueOf(minutos);

            txtHorasSemanais.setText(horasStr + minutosStr);
        }
        
        String temp = Double.toString(valorAulas);
        if(valorAulas < 10){
            temp = "000" + temp;
        }
        else if(valorAulas < 100){
            temp = "00" + temp;
        }
        else if(valorAulas < 1000){
            temp = "0" + temp;
        }
        temp = temp.replaceAll("\\.", "");
        StringBuilder builder = new StringBuilder(temp);
        builder.insert(1, '.');
        builder.insert(5, ',');
        temp = builder.toString();
        if(valorAulas.intValue() - valorAulas == 0){
            temp += "0";
        }
        txtValorAulas.setText(temp);

        temp = Double.toString(valorMaterial);
        if(valorMaterial < 10){
            temp = "000" + temp;
        }
        else if(valorMaterial < 100){
            temp = "00" + temp;
        }
        else if(valorMaterial < 1000){
            temp = "0" + temp;
        }
        temp = temp.replaceAll("\\.", "");
        builder = new StringBuilder(temp);
        builder.insert(1, '.');
        builder.insert(5, ',');
        temp = builder.toString();
        if(valorMaterial.intValue() - valorMaterial == 0){
            temp += "0";
        }
        txtValorMaterial.setText(temp);
        jComboIdioma.setSelectedItem(idioma);
        
        txtCodigo.setEditable(true);
        txtNome.setEditable(true);
        txtDescricao.setEditable(true);
        txtValorAulas.setEditable(true);
        txtValorMaterial.setEditable(true);
        txtDuracao.setEditable(true);
        txtTotalHoras.setEditable(true);
        txtHorasSemanais.setEditable(true);
        txtCargaHorMin.setEditable(true);
        txtCargaHorMax.setEditable(true);
        checkBoxVip.setEnabled(true);
        checkBoxGotIt.setEnabled(true);
        jComboIdioma.setEnabled(true);
        
        txtNome.requestFocusInWindow();
        btnExcluir.setEnabled(true);
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
            java.util.logging.Logger.getLogger(CadastroNiveis.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CadastroNiveis.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CadastroNiveis.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CadastroNiveis.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CadastroNiveis().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnPesquisar;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JCheckBox checkBoxGotIt;
    private javax.swing.JCheckBox checkBoxProrrogavel;
    private javax.swing.JCheckBox checkBoxVip;
    private javax.swing.JComboBox<String> jComboIdioma;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTextField txtCargaHorMax;
    private javax.swing.JTextField txtCargaHorMin;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtDescricao;
    private javax.swing.JFormattedTextField txtDuracao;
    private javax.swing.JFormattedTextField txtHorasSemanais;
    private javax.swing.JTextField txtNome;
    private javax.swing.JTextField txtTotalHoras;
    private javax.swing.JFormattedTextField txtValorAulas;
    private javax.swing.JFormattedTextField txtValorMaterial;
    // End of variables declaration//GEN-END:variables
}
