/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unique;

import unique.Consultas.AlunosCadastrados;
import Tabelas.Aluno;
import Tabelas.Config;
import Tabelas.Mensalidade;
import java.awt.Cursor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import util.HibernateUtil;

/**
 *
 * @author arlorencini
 */
public class PagamentoParcela extends javax.swing.JFrame {
    
    Session conexao = null;    
    Config configuracoes = null;
    private static PagamentoParcela instance = null;
    
    /**
     * Creates new form PagamentoParcela
     */
    private PagamentoParcela() {
        initComponents();
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("Logo.png")));
        
        this.setLocationRelativeTo(null);   
        comboBoxMensalidades.removeAllItems();
        lblInfoNome.setVisible(false);
        
        comboBoxMensalidades.addActionListener((ActionEvent e) -> {
            MudouItemComboParcelas(e); 
        });
        
        comboPagtoCheque.addActionListener((ActionEvent e) -> {
            MudouItemComboCheque(e); 
        });
        
        lblVlrDesconto.setText("R$ 00,00");
        idAlunoSelecionado = 0;
        
        try {
            //Buscando as configurações atuais:
            conexao = HibernateUtil.openSession();
            Criteria select = conexao.createCriteria(Config.class);
            select.add(Restrictions.eq("ID", 1L));

            if(select.list().size() > 0)
                configuracoes = (Config)select.list().get(0);

            conexao.close();
        } catch(Exception e){
            JOptionPane.showMessageDialog(this, "Operação mal sucedida. Motivo: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public static PagamentoParcela getInstance() {
        if (instance == null)
            instance = new PagamentoParcela();
        return instance;
    }
    
    private void MudouItemComboCheque(ActionEvent e) {
        if(comboPagtoCheque.getSelectedIndex() > 0) {
            txtNumeroCheque.setEnabled(true);
            txtDataCompensacao.setEnabled(true);
        } else{
            txtNumeroCheque.setText("");
            txtNumeroCheque.setEnabled(false);
            txtDataCompensacao.setText("");
            txtDataCompensacao.setEnabled(false);
        }
    }
    
    private void MudouItemComboParcelas(ActionEvent e) {
        if(comboBoxMensalidades.getItemCount() > 0 && !"NENHUMA PARCELA PENDENTE".equals(comboBoxMensalidades.getSelectedItem().toString())) {
            txtNumeroCheque.setEnabled(false);
            txtDataCompensacao.setEnabled(false);
            comboPagtoCheque.setSelectedIndex(0);
            String textMes = comboBoxMensalidades.getSelectedItem().toString().substring(0, 2);
            String textAno = comboBoxMensalidades.getSelectedItem().toString().substring(3, 7);
            System.out.println("Mes/Ano: " + textMes + "/" + textAno);

            //Buscando o valor:
            for(Mensalidade m : mensalidades) {
                if(m.getMesVencto() == Integer.parseInt(textMes) && m.getAnoVencto() == Integer.parseInt(textAno)){
                    //Total a Pagar:
                    totalPagar = 0;
                    valorMulta = 0;

                    //Calculando os dias de atraso:
                    Date dataAtual = Calendar.getInstance().getTime();
                    Date dataVencimento = null, dataParaDesconto = null;
                    try {
                        int mesCorreto = Integer.parseInt(textMes) + 1;
                        dataVencimento = DateFormat.getDateInstance().parse("01/" + mesCorreto + "/" + textAno);
                        dataParaDesconto = DateFormat.getDateInstance().parse("01/" + textMes + "/" + textAno);
                    } catch (ParseException ex) {
                        Logger.getLogger(PagamentoParcela.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    long diferencaTempo = dataAtual.getTime() - dataVencimento.getTime();
                    TimeUnit timeUnit = TimeUnit.DAYS;
                    long diferencaDias = timeUnit.convert(diferencaTempo, TimeUnit.MILLISECONDS);
                    long diferencaMes = (Calendar.getInstance().get(Calendar.MONTH) + 1) - Integer.parseInt(textMes);
                    if(Calendar.getInstance().get(Calendar.YEAR) < Integer.parseInt(textAno))
                        diferencaMes = (Calendar.getInstance().get(Calendar.MONTH) + 1) - (Integer.parseInt(textMes) + 12);

                    //Setando os valores:
                    lblVlrPar.setText("R$ " + String.format("%.2f", m.getValorAulas()).replace(',', '.'));
                    totalPagar += m.getValorAulas() + m.getValorMaterial();

                    if(diferencaDias < 0)
                        lblDiasAtraso.setText("0");
                    else
                        lblDiasAtraso.setText(String.valueOf(diferencaDias));

                    if((diferencaMes <= 0) /*&& (!m.getNivel().isVIP())*/){
                        int diaAtual = Calendar.getInstance().get(Calendar.DATE);

                        //Estamos pagando no memo mês de vencimento OU adiantados?
                        lblVlrDesconto.setText("R$ 00,00");
                        
                        if((Calendar.getInstance().get(Calendar.MONTH) + 1 <= Integer.parseInt(textMes) &&
                            Calendar.getInstance().get(Calendar.YEAR) <= Integer.parseInt(textAno)) ||
                            Integer.parseInt(textAno) > Calendar.getInstance().get(Calendar.YEAR)){
                            Date diaDescMaior = retornaDiaUtilApos(configuracoes.getDiaLimiteDescontoMaior(), dataParaDesconto);
                            Date diaDescMenor = retornaDiaUtilApos(configuracoes.getDiaLimiteDescontoMenor(), dataParaDesconto);
                            
                            //Desconto maior se aplica?
                            
                            if((configuracoes.isDescontoMaiorEnbled()) && ((diaAtual <= configuracoes.getDiaLimiteDescontoMaior()) ||
                               (Calendar.getInstance().get(Calendar.MONTH) + 1 < Integer.parseInt(textMes)) ||
                                    (Calendar.getInstance().get(Calendar.YEAR) < Integer.parseInt(textAno)))){ 
                            //if(configuracoes.isDescontoMaiorEnbled() && dataAtual.getTime() <= diaDescMaior.getTime()){
                                //Aplicando o desconto maior:
                                lblVlrDesconto.setText("R$ " + configuracoes.getDescontoMaior());
                                totalPagar -= configuracoes.getDescontoMaior();
                            } else if((configuracoes.isDescontoMenorEnbled()) && ((diaAtual <= configuracoes.getDiaLimiteDescontoMenor()) ||
                               (Calendar.getInstance().get(Calendar.MONTH) + 1 < Integer.parseInt(textMes)))){ 
                            //} else if(configuracoes.isDescontoMenorEnbled() && dataAtual.getTime() <= diaDescMenor.getTime()){
                                //Aplicando o desconto menor:
                                lblVlrDesconto.setText("R$ " + configuracoes.getDescontoMenor());
                                totalPagar -= configuracoes.getDescontoMenor();
                            }
                        }
                    }

                    if(diferencaMes < 0 && diferencaDias > 0)
                        diferencaMes = 12 - (diferencaMes * (-1));
                    
                    valorMulta += CalculaJurosComposto(totalPagar, 0.02, (int)diferencaMes);       //Mes
                    valorMulta += CalculaJurosComposto(totalPagar, 0.00033, (int)diferencaDias);   //Dias
                    
                    if(valorMulta < 0.5d)
                        valorMulta = 0d;
                    
                    valorMulta = Double.parseDouble(String.format("%.2f", valorMulta).replace(',', '.'));
                    totalPagar += valorMulta;
                    lblVlrMulta.setText("R$ " + String.format("%.2f", valorMulta).replace(',', '.'));
                    lblTotalPagar.setText("R$ " + String.format("%.2f", totalPagar).replace(',', '.'));
                }
            }
        }
    }
    
    private Date retornaDiaUtilApos(int qtdDias, Date dataInicial){
        int diasUteis = 0;
        Date dataUtilRetorno = dataInicial;
        GregorianCalendar gc = new GregorianCalendar();
        Calendar cal = Calendar.getInstance();
        while (diasUteis < qtdDias){
            gc.setTime(dataUtilRetorno);
            if(gc.get(GregorianCalendar.DAY_OF_WEEK) != 1 && gc.get(GregorianCalendar.DAY_OF_WEEK) != 7) // 1 = domingo; 7 = sábado
                diasUteis++;
            
            if(diasUteis < qtdDias){
                // Adicionando um dia:
                cal.setTime(dataUtilRetorno);
                cal.add(Calendar.DATE, 1);
                dataUtilRetorno = cal.getTime();
            }
        }
        return dataUtilRetorno;
    }
    
    private double CalculaJurosComposto(double valor, double porcentagemJuros, int qtdAplicacoes){
        double valorMulta = 0.0;
        if(qtdAplicacoes > 0){
            valorMulta = valor * porcentagemJuros;
            valorMulta *= qtdAplicacoes;
            String str = String.format("%.2f", valorMulta);
            str = str.replace(',', '.');
            valorMulta = Double.parseDouble(str);
        }
        return valorMulta;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel12 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel4 = new javax.swing.JLabel();
        txtBoxNome = new javax.swing.JTextField();
        lblInfoNome = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        comboBoxMensalidades = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        lblTotalPagar = new javax.swing.JLabel();
        lblVlrPar = new javax.swing.JLabel();
        lblDiasAtraso = new javax.swing.JLabel();
        lblVlrMulta = new javax.swing.JLabel();
        lblVlrDesconto = new javax.swing.JLabel();
        btnEfetuaPagamento = new javax.swing.JToggleButton();
        btnCancelar = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        comboPagtoCheque = new javax.swing.JComboBox();
        txtNumeroCheque = new javax.swing.JTextField();
        txtDataCompensacao = new javax.swing.JFormattedTextField();

        jLabel12.setText("R$ 000,000,00");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Pagamento de Parcelas");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI Light", 0, 36)); // NOI18N
        jLabel1.setText("Pagamentos de Parcelas");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("Nome do Aluno:");

        txtBoxNome.setColumns(50);
        txtBoxNome.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtBoxNome.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBoxNomeKeyReleased(evt);
            }
        });

        lblInfoNome.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        lblInfoNome.setIcon(new javax.swing.ImageIcon(getClass().getResource("/unique/Imagens/package-installed-outdated.png"))); // NOI18N
        lblInfoNome.setText("huehue");
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

        comboBoxMensalidades.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        comboBoxMensalidades.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comboBoxMensalidades.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                comboBoxMensalidadesPropertyChange(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("Mensalidade a ser paga:");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Valor da Parcela das Aulas:");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Valor da Multa:");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setText("Total a Pagar:");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setText("Desconto de Pagamento Antecipado:");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setText("Quantidade de Dias de Atraso:");

        lblTotalPagar.setText("R$ 000,000,00");
        lblTotalPagar.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        lblVlrPar.setText("R$ 000,000,00");

        lblDiasAtraso.setText("0");
        lblDiasAtraso.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        lblVlrMulta.setText("R$ 000,000,00");

        lblVlrDesconto.setText("R$ 000,000,00");

        btnEfetuaPagamento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/unique/Imagens/dialog-ok.png"))); // NOI18N
        btnEfetuaPagamento.setText("Efetuar Pagamento");
        btnEfetuaPagamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEfetuaPagamentoActionPerformed(evt);
            }
        });

        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/unique/Imagens/list-remove.png"))); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel10.setText("Pagamento em cheque?");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel11.setText("Número do cheque:");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel13.setText("Data de Compensação:");

        comboPagtoCheque.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Não", "Sim" }));
        comboPagtoCheque.setEnabled(false);

        txtNumeroCheque.setColumns(50);
        txtNumeroCheque.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtNumeroCheque.setEnabled(false);
        txtNumeroCheque.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNumeroChequeKeyReleased(evt);
            }
        });

        txtDataCompensacao.setColumns(10);
        try {
            txtDataCompensacao.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtDataCompensacao.setEnabled(false);
        txtDataCompensacao.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator1)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel4))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtBoxNome, javax.swing.GroupLayout.DEFAULT_SIZE, 412, Short.MAX_VALUE)
                                    .addComponent(comboBoxMensalidades, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addComponent(lblInfoNome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jSeparator2))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnCancelar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEfetuaPagamento))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(67, 67, 67)
                                .addComponent(jLabel2)
                                .addGap(275, 275, 275)
                                .addComponent(lblVlrPar, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addGap(88, 88, 88)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel6)
                                        .addComponent(jLabel10)
                                        .addComponent(jLabel11)
                                        .addComponent(jLabel13))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(comboPagtoCheque, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtNumeroCheque, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtDataCompensacao, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createSequentialGroup()
                                            .addGap(271, 271, 271)
                                            .addComponent(lblTotalPagar, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addGap(45, 45, 45)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel3)
                                        .addComponent(jLabel8))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                            .addGap(275, 275, 275)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(lblDiasAtraso, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(lblVlrDesconto)
                                                .addComponent(lblVlrMulta))
                                            .addGap(0, 0, Short.MAX_VALUE))))
                                .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING)))
                        .addGap(607, 827, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtBoxNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblInfoNome, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(comboBoxMensalidades, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(lblVlrPar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(lblDiasAtraso))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(lblVlrMulta))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(lblVlrDesconto))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(lblTotalPagar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(comboPagtoCheque, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtNumeroCheque, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txtDataCompensacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEfetuaPagamento)
                    .addComponent(btnCancelar))
                .addGap(31, 31, 31))
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
                    lblInfoNome.setText("Foram encontrados " + select.list().size() + " resultados semelhantes cadastrados. Clique para visualizar.");
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
            comboBoxMensalidades.setEnabled(false);
        }
    }//GEN-LAST:event_txtBoxNomeKeyReleased

    private void lblInfoNomeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblInfoNomeMouseClicked
        AlunosCadastrados consultaAlunos;
        consultaAlunos = AlunosCadastrados.getInstance(txtBoxNome.getText(), AlunosCadastrados.CALLER.CALLER_PAGAMENTO);
        consultaAlunos.SetJanelaPai(this);
        consultaAlunos.setVisible(true);
    }//GEN-LAST:event_lblInfoNomeMouseClicked

    private void lblInfoNomeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblInfoNomeMouseEntered
        lblInfoNome.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_lblInfoNomeMouseEntered

    private void lblInfoNomeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblInfoNomeMouseExited
        lblInfoNome.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_lblInfoNomeMouseExited

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        comboBoxMensalidades.setEnabled(false);
    }//GEN-LAST:event_formWindowOpened

    private void comboBoxMensalidadesPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_comboBoxMensalidadesPropertyChange
        
    }//GEN-LAST:event_comboBoxMensalidadesPropertyChange

    private void btnEfetuaPagamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEfetuaPagamentoActionPerformed
        //Obtendo confirmação:
        String textMes = comboBoxMensalidades.getSelectedItem().toString().substring(0, 2);
        String textAno = comboBoxMensalidades.getSelectedItem().toString().substring(3, 7);
        
        if(JOptionPane.showConfirmDialog(this, "Confirmar pagamento da mensalidade "
                + "referente ao mês " + textMes + "/" + textAno + ", pelo aluno "
                + txtBoxNome.getText() + "?", "Aviso", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION){
            
            //Efetuando o pagamento:
            conexao = HibernateUtil.openSession();
            Transaction tx = conexao.beginTransaction();
            boolean removeu = false;
            
            for(Mensalidade m : mensalidades) {
                if(!removeu && m.getMesVencto() == Integer.parseInt(textMes) && m.getAnoVencto() == Integer.parseInt(textAno)){
                    m.setDataPagto(Calendar.getInstance().getTime());
                    m.setPaga(true);
                    m.setValorPago(totalPagar);
                    
                    if(comboPagtoCheque.getSelectedIndex() > 0){
                        m.setNumeroCheque(txtNumeroCheque.getText());
                        //Obtendo a data:
                        try {
                            Date dataTemporaria = DateFormat.getDateInstance().parse(txtDataCompensacao.getText());
                            m.setDataCompensacao(dataTemporaria);
                        } catch (ParseException ex) {
                            Logger.getLogger(PagamentoParcela.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    try{
                    conexao.saveOrUpdate(m);
                    //Removendo essa mensalidade da lista:
                    mensalidades.remove(m);
                    
                    AtualizaDadosDasMensalidades();
                    } catch(Exception e){
                        JOptionPane.showMessageDialog(this, "Operação mal sucedida. Motivo: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                    break;
                }
            }
            try{
                tx.commit();
            } catch(Exception e){
                JOptionPane.showMessageDialog(this, "Operação mal sucedida. Motivo: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }

            conexao.close();
            JOptionPane.showMessageDialog(this, "Pagamento registrado com sucesso.", "Aviso", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnEfetuaPagamentoActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        //Fechando a janela:
        this.dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void txtNumeroChequeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNumeroChequeKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNumeroChequeKeyReleased

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
        instance = null;
    }//GEN-LAST:event_formWindowClosed
    
    public void LoadInfoForStudent(Long id){
        System.out.println("ID RECEBIDO: " + id.toString());
        idAlunoSelecionado = 0;
        Aluno aluno = null;

        //Verificando se existe o nome cadastrado no banco:
        conexao = HibernateUtil.openSession();
        Criteria select = conexao.createCriteria(Aluno.class);
        select.add(Restrictions.eq("ID", id));
        
        if(select.list().size() > 0)
            aluno = (Aluno)select.list().get(0);
        
        //Inicializando o Hibernate:
        conexao.close();
        
        //Encontramos um aluno?
        if(aluno != null){
            //Buscando as parcelas:
            conexao = HibernateUtil.openSession();
            select = conexao.createCriteria(Mensalidade.class);
            select.add(Restrictions.eq("Aluno", aluno));
            select.add(Restrictions.eq("Paga", false));
            select.addOrder(Order.asc("AnoVencto"));
            select.addOrder(Order.asc("MesVencto"));
            
            if(select.list().size() > 0){
                //Adicionando os valores na tabela:
                mensalidades = select.list();
                AtualizaDadosDasMensalidades();
                btnEfetuaPagamento.setEnabled(true);
                comboBoxMensalidades.setEnabled(true);
                comboPagtoCheque.setEnabled(true);
            } else {
                comboBoxMensalidades.addItem("NENHUMA PARCELA PENDENTE");
                btnEfetuaPagamento.setEnabled(false);
                comboBoxMensalidades.setEnabled(false);
                comboPagtoCheque.setEnabled(false);
                txtBoxNome.requestFocusInWindow();
            }
            txtBoxNome.setText(aluno.getNome());
            comboBoxMensalidades.setEnabled(true);
            idAlunoSelecionado = id;
        }
    }
    
    private void AtualizaDadosDasMensalidades(){
        //Limpando:
        comboBoxMensalidades.removeAllItems();
        
        //Temos quantos itens na lista?
        if(mensalidades.size() > 0) {
            //Processando a lista:
            mensalidades.stream().forEach((m) -> {
                String mes;
                if(m.getMesVencto() < 10)
                    mes = "0" + m.getMesVencto();
                else
                    mes = String.valueOf(m.getMesVencto());

                comboBoxMensalidades.addItem(mes + "/" + m.getAnoVencto());
            });
        }else {
            comboBoxMensalidades.addItem("NENHUMA PARCELA PENDENTE");
            btnEfetuaPagamento.setEnabled(false);
            comboBoxMensalidades.setEnabled(false);
            txtBoxNome.requestFocusInWindow();
        }
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
            java.util.logging.Logger.getLogger(PagamentoParcela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PagamentoParcela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PagamentoParcela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PagamentoParcela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new PagamentoParcela().setVisible(true);
        });
    }

    List<Mensalidade> mensalidades;
    long idAlunoSelecionado;
    double totalPagar = 0, valorMulta = 0;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JToggleButton btnEfetuaPagamento;
    private javax.swing.JComboBox comboBoxMensalidades;
    private javax.swing.JComboBox comboPagtoCheque;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JLabel lblDiasAtraso;
    private javax.swing.JLabel lblInfoNome;
    private javax.swing.JLabel lblTotalPagar;
    private javax.swing.JLabel lblVlrDesconto;
    private javax.swing.JLabel lblVlrMulta;
    private javax.swing.JLabel lblVlrPar;
    private javax.swing.JTextField txtBoxNome;
    private javax.swing.JFormattedTextField txtDataCompensacao;
    private javax.swing.JTextField txtNumeroCheque;
    // End of variables declaration//GEN-END:variables
}
