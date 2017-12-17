/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unique;

import Tabelas.Cobranca;
import Tabelas.Matricula;
import Tabelas.Mensalidade;
import java.awt.Container;
import static java.awt.Frame.MAXIMIZED_BOTH;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
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
public class Cobrancas extends javax.swing.JFrame {

    /**
     * Creates new form Cobrancas
     */
    
    Mensalidade m;
    Session conexao;
    Transaction tx;
    
    public Cobrancas() {
        initComponents();
        
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("Logo.png")));
        setLocationRelativeTo(null);
    }
    
    public void SetMensalidade(Mensalidade mens) throws ParseException{
        m = mens;
        
        lblAluno.setText(m.getAluno().getNome());
        lblNivel.setText(m.getNivel().getCodigo());
        
        int mes = m.getMesVencto();
        String mesStr;
        if(mes < 10)
            mesStr = "0" + String.valueOf(mes);
        else
            mesStr = String.valueOf(mes);
        
        lblMesAno.setText(mesStr + "/" + String.valueOf(m.getAnoVencto()));
        lblValor.setText(String.valueOf(m.getValorAulas()));
        
        if(m.isPaga())
            lblStatus.setText("Paga");
        else
            lblStatus.setText("Pendente");
        
        conexao = HibernateUtil.openSession();
        //Procurando por Avisos e Cobrancas gerados:
        Criteria crit = conexao.createCriteria(Cobranca.class);
        crit.add(Restrictions.eq("Mensalidade", m));
        crit.addOrder(Order.asc("Numero"));
        
        List<Cobranca> cobrancas = crit.list();
        if(cobrancas.size() <= 0){ //Criar registros
            for(int i = 0; i <= 4; i++){
                Cobranca cobranca = new Cobranca();
                cobranca.setMensalidade(m);
                cobranca.setGerada(false);
                cobranca.setNumero(Long.parseLong(String.valueOf(i)));
                
                String data;
                int mesCob = mes + 1;
                int anoCob = m.getAnoVencto();
                
                if(mesCob == 13){
                    mesCob = 1;
                    anoCob++;
                }
                
                if(mesCob < 10){
                    mesStr = "0" + String.valueOf(mesCob);
                } else{
                    mesStr = String.valueOf(mesCob);
                }
                //Montando data da cobranca:
                switch(i){
                    case 0:
                        data = "01/" + mesStr + "/" + String.valueOf(anoCob);
                        lblComunicado.setText("Data: " + data + " - Gerada: Não.");
                        break;
                    case 1:
                        data = "06/" + mesStr + "/" + String.valueOf(anoCob);
                        lblCobranca1.setText("Data: " + data + " - Gerada: Não.");
                        break;
                    case 2:
                        data = "11/" + mesStr + "/" + String.valueOf(anoCob);
                        lblCobranca2.setText("Data: " + data + " - Gerada: Não.");
                        break;
                    case 3:
                        data = "15/" + mesStr + "/" + String.valueOf(anoCob);
                        lblCobranca3.setText("Data: " + data + " - Gerada: Não.");
                        break;
                    default:
                        data = "21/" + mesStr + "/" + String.valueOf(anoCob);
                        lblCobranca4.setText("Data: " + data + " - Gerada: Não.");
                        break;
                }
                Date dataTemporaria = DateFormat.getDateInstance().parse(data);
                cobranca.setData(dataTemporaria);
                
                
                if(!m.getAluno().isVip()){
                    //Buscando numero da parcela:
                    Criteria critMatr = conexao.createCriteria(Matricula.class);
                    critMatr.add(Restrictions.eq("Aluno", m.getAluno()));
                    critMatr.add(Restrictions.eq("Nivel", m.getNivel()));

                    Matricula mat = (Matricula) critMatr.list().get(0);
                    Long nroParcelas = mat.getQtdeParcelasAulas();

                    Criteria critMens = conexao.createCriteria(Mensalidade.class);
                    critMens.add(Restrictions.eq("Aluno", m.getAluno()));
                    critMens.add(Restrictions.eq("Nivel", m.getNivel()));
                    critMens.addOrder(Order.asc("AnoVencto"));
                    critMens.addOrder(Order.asc("MesVencto"));

                    int numero = 0;
                    List<Mensalidade> mensalidades = critMens.list();
                    for(Mensalidade men : mensalidades){
                        numero++;
                        if(men.getMesVencto() == m.getMesVencto() && men.getAnoVencto() == m.getAnoVencto()){
                            cobranca.setNumeroParcela(String.valueOf(numero) + "/" + nroParcelas.toString());
                            break;
                        }
                    }
                } else{
                    cobranca.setNumeroParcela(null);
                }
                
                Calendar hoje = Calendar.getInstance();
                Calendar nascimento = Calendar.getInstance();
                nascimento.setTime(m.getAluno().getNascimento());
                
                int anos = 0;
                while(hoje.after(nascimento)){
                    nascimento.add(Calendar.YEAR, 1);
                    anos++;
                }
                
                if(anos >= 18){
                    cobranca.setMaiorDeIdade(true);
                } else{
                    cobranca.setMaiorDeIdade(false);
                }
                
                cobranca.setValorExtenso(valorPorExtenso(m.getValorAulas().doubleValue()));
                conexao.saveOrUpdate(cobranca);
            }
            
            try{
                tx = conexao.beginTransaction();
                tx.commit();
            } catch(Exception e){
                JOptionPane.showMessageDialog(this, "Operação mal sucedida. Movivo: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
            
            conexao.close();
        } else{ //Ja existe registros, somente listar.
            for(Cobranca cob : cobrancas){
                String data;
                String gerada = "Não.";
                Date dataCob = cob.getData();
                DateFormat df = new SimpleDateFormat("dd/MM/yyyy");     

                data = df.format(dataCob);
                
                if(cob.isGerada())
                    gerada = "Sim.";
                
                switch(cob.getNumero().intValue()){
                    case 0:
                        lblComunicado.setText("Data: " + data + " - Gerada: " + gerada);
                        break;
                    case 1:
                        lblCobranca1.setText("Data: " + data + " - Gerada: " + gerada);
                        break;
                    case 2:
                        lblCobranca2.setText("Data: " + data + " - Gerada: " + gerada);
                        break;
                    case 3:
                        lblCobranca3.setText("Data: " + data + " - Gerada: " + gerada);
                        break;
                    default:
                        lblCobranca4.setText("Data: " + data + " - Gerada: " + gerada);
                        break;
                }
            }
        }
    }
   
    public static String valorPorExtenso(double vlr) {
        if (vlr == 0) {
            return ("zero");
        }
        long inteiro = (long) Math.abs(vlr); // parte inteira do valor 
        double resto = vlr - inteiro; // parte fracionária do valor 
        
        String vlrS = String.valueOf(inteiro); 
        if (vlrS.length() > 15) 
            return("Erro: valor superior a 999 trilhões."); 
        
        String s = "", saux, vlrP; 
        String centavos = String.valueOf((int)Math.round(resto * 100)); 
        String[] unidade = {"", "Um", "Dois", "Três", "Quatro", "Cinco", 
            "Seis", "Sete", "Oito", "Nove", "Dez", "Onze", "Doze", "Treze", 
            "Quatorze", "Quinze", "Dezesseis", "Dezessete", "Dezoito", "Dezenove"}; 
    
        String[] centena = {"", "Cento", "Duzentos", "Trezentos", "Quatrocentos", 
            "Quinhentos", "Seiscentos", "Setecentos", "Oitocentos", "Novecentos"}; 
        
        String[] dezena = {"", "", "Vinte", "Trinta", "Quarenta", "Cinquenta", 
            "Sessenta", "Setenta", "Oitenta", "Noventa"}; 
        
        String[] qualificaS = {"", "Mil", "Milhão", "Bilhão", "Trilhão"}; 
        
        String[] qualificaP = {"", "Mil", "Milhões", "Bilhões", "Trilhões"};
        
        // definindo o extenso da parte inteira do valor 
        int n, unid, dez, cent, tam, i = 0; 
        boolean umReal = false, tem = false; 
        while (!vlrS.equals("0")) { tam = vlrS.length(); 

        // retira do valor a 1a. parte, 2a. parte, por exemplo, para 123456789: 
        // 1a. parte = 789 (centena) 
        // 2a. parte = 456 (mil) 
        // 3a. parte = 123 (milhões) 
        
        if (tam > 3) { 
            vlrP = vlrS.substring(tam-3, tam);
            vlrS = vlrS.substring(0, tam-3); 
        } else { // última parte do valor 
            vlrP = vlrS; vlrS = "0"; 
        } 
        
        if (!vlrP.equals("000")) { 
            saux = "";
            if (vlrP.equals("100")) 
                saux = "Cem"; 
            else { 
                n = Integer.parseInt(vlrP, 10); // para n = 371, tem-se: 
                cent = n / 100; // cent = 3 (centena trezentos) 
                dez = (n % 100) / 10; // dez = 7 (dezena setenta) 
                unid = (n % 100) % 10; // unid = 1 (unidade um) 
                if (cent != 0) 
                    saux = centena[cent]; 
                if ((n % 100) <= 19) { 
                    if (saux.length() != 0) 
                        saux = saux + " e " + unidade[n % 100];
                    else saux = unidade[n % 100]; 
                } else { 
                    if (saux.length() != 0) 
                        saux = saux + " e " + dezena[dez];
                    else 
                        saux = dezena[dez]; 
                    
                    if (unid != 0) { 
                        if (saux.length() != 0) 
                            saux = saux + " e " + unidade[unid];
                        else saux = unidade[unid];
                    } 
                } 
            } 
            if (vlrP.equals("1") || vlrP.equals("001")) { 
                if (i == 0) // 1a. parte do valor (um real) 
                    umReal = true; 
                else 
                    saux = saux + " " + qualificaS[i]; 
            } else if (i != 0) 
                saux = saux + " " + qualificaP[i]; 
            if (s.length() != 0) 
                s = saux + ", " + s; 
            else s = saux; 
        } 
        
        if (((i == 0) || (i == 1)) && s.length() != 0) 
            tem = true; // tem centena ou mil no valor 
        i = i + 1; // próximo qualificador: 1- mil, 2- milhão, 3- bilhão, ... 
        } 
        
        if (s.length() != 0) { 
            if (umReal) 
                s = s + " Real";
            else if (tem) 
                s = s + " Reais"; 
            else s = s + " de Reais"; 
        } 

        // definindo o extenso dos centavos do valor 
        if (!centavos.equals("0")) { // valor com centavos 
            if (s.length() != 0) // se não é valor somente com centavos 
                s = s + " e "; 
            if (centavos.equals("1")) 
                s = s + "Um Centavo"; 
            else { 
                n = Integer.parseInt(centavos, 10);
                if (n <= 19) 
                    s = s + unidade[n];
                else { // para n = 37, tem-se: 
                    unid = n % 10; // unid = 37 % 10 = 7 (unidade sete) 
                    dez = n / 10; // dez = 37 / 10 = 3 (dezena trinta) 
                    s = s + dezena[dez]; 
                    if (unid != 0) 
                        s = s + " e " + unidade[unid]; 
                } s = s + " Centavos"; 
            } 
        } 
        return(s); 
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnCancelar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lblAluno = new javax.swing.JLabel();
        lblNivel = new javax.swing.JLabel();
        lblMesAno = new javax.swing.JLabel();
        lblValor = new javax.swing.JLabel();
        lblStatus = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel7 = new javax.swing.JLabel();
        lblComunicado = new javax.swing.JLabel();
        lblCobranca1 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        lblCobranca2 = new javax.swing.JLabel();
        lblCobranca3 = new javax.swing.JLabel();
        lblCobranca4 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        btnImprimirCom = new javax.swing.JButton();
        btnImprimirCob1 = new javax.swing.JButton();
        btnImprimirCob2 = new javax.swing.JButton();
        btnImprimirCob3 = new javax.swing.JButton();
        btnImprimirCob4 = new javax.swing.JButton();
        btnSair = new javax.swing.JButton();

        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/unique/Imagens/list-remove.png"))); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Avisos e Cobranças");

        jLabel1.setFont(new java.awt.Font("Segoe UI Light", 0, 36)); // NOI18N
        jLabel1.setText("Avisos e Cobranças");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Aluno(a):");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Nível:");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("Mês/Ano:");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("Valor(R$):");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setText("Status:");

        lblAluno.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblAluno.setText(" ");

        lblNivel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblNivel.setText(" ");

        lblMesAno.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblMesAno.setText(" ");

        lblValor.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblValor.setText(" ");

        lblStatus.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblStatus.setText(" ");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setText("Comunicado:");

        lblComunicado.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblComunicado.setText(" ");

        lblCobranca1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblCobranca1.setText(" ");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setText("01ª Cobrança:");

        lblCobranca2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblCobranca2.setText(" ");

        lblCobranca3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblCobranca3.setText(" ");

        lblCobranca4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblCobranca4.setText(" ");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setText("02ª Cobrança:");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel10.setText("03ª Cobrança:");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel11.setText("04ª Cobrança:");

        btnImprimirCom.setIcon(new javax.swing.ImageIcon(getClass().getResource("/unique/Imagens/package-installed-outdated.png"))); // NOI18N
        btnImprimirCom.setText("Imprimir");
        btnImprimirCom.setMaximumSize(new java.awt.Dimension(75, 23));
        btnImprimirCom.setMinimumSize(new java.awt.Dimension(75, 23));
        btnImprimirCom.setPreferredSize(new java.awt.Dimension(75, 23));
        btnImprimirCom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimirComActionPerformed(evt);
            }
        });

        btnImprimirCob1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/unique/Imagens/package-installed-outdated.png"))); // NOI18N
        btnImprimirCob1.setText("Imprimir");
        btnImprimirCob1.setMaximumSize(new java.awt.Dimension(75, 23));
        btnImprimirCob1.setMinimumSize(new java.awt.Dimension(75, 23));
        btnImprimirCob1.setPreferredSize(new java.awt.Dimension(75, 23));
        btnImprimirCob1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimirCob1ActionPerformed(evt);
            }
        });

        btnImprimirCob2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/unique/Imagens/package-installed-outdated.png"))); // NOI18N
        btnImprimirCob2.setText("Imprimir");
        btnImprimirCob2.setMaximumSize(new java.awt.Dimension(75, 23));
        btnImprimirCob2.setMinimumSize(new java.awt.Dimension(75, 23));
        btnImprimirCob2.setPreferredSize(new java.awt.Dimension(75, 23));
        btnImprimirCob2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimirCob2ActionPerformed(evt);
            }
        });

        btnImprimirCob3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/unique/Imagens/package-installed-outdated.png"))); // NOI18N
        btnImprimirCob3.setText("Imprimir");
        btnImprimirCob3.setMaximumSize(new java.awt.Dimension(75, 23));
        btnImprimirCob3.setMinimumSize(new java.awt.Dimension(75, 23));
        btnImprimirCob3.setPreferredSize(new java.awt.Dimension(75, 23));
        btnImprimirCob3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimirCob3ActionPerformed(evt);
            }
        });

        btnImprimirCob4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/unique/Imagens/package-installed-outdated.png"))); // NOI18N
        btnImprimirCob4.setText("Imprimir");
        btnImprimirCob4.setMaximumSize(new java.awt.Dimension(75, 23));
        btnImprimirCob4.setMinimumSize(new java.awt.Dimension(75, 23));
        btnImprimirCob4.setPreferredSize(new java.awt.Dimension(75, 23));
        btnImprimirCob4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimirCob4ActionPerformed(evt);
            }
        });

        btnSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/unique/Imagens/list-remove.png"))); // NOI18N
        btnSair.setText("Sair");
        btnSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSairActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator2)
                            .addComponent(jSeparator1)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel5))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblAluno, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblNivel, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblMesAno, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblValor, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel10)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lblCobranca3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel11)
                                            .addComponent(jLabel9))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblCobranca2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(lblCobranca4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel8)
                                            .addComponent(jLabel7))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblCobranca1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                .addComponent(lblComunicado, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                .addGap(28, 28, 28)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnImprimirCom, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnImprimirCob1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnImprimirCob2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnImprimirCob3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnImprimirCob4, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(13, 13, 13)))
                        .addContainerGap())))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnSair)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(lblAluno))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(lblNivel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(lblMesAno))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(lblValor))
                        .addGap(5, 5, 5)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(lblStatus))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(9, 9, 9)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(lblComunicado))
                        .addGap(9, 9, 9)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(lblCobranca1))
                        .addGap(9, 9, 9)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblCobranca2)
                            .addComponent(jLabel9))
                        .addGap(9, 9, 9)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblCobranca3)
                            .addComponent(jLabel10))
                        .addGap(9, 9, 9)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(lblCobranca4)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnImprimirCom, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnImprimirCob1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnImprimirCob2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnImprimirCob3, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnImprimirCob4, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                .addComponent(btnSair, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnImprimirComActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirComActionPerformed
        if(JOptionPane.showConfirmDialog(this, "Deseja realmente gerar o aviso de atraso?", "Confirmação", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
            conexao = HibernateUtil.openSession();
            tx = conexao.beginTransaction();
            //Procurando por Avisos e Cobrancas gerados:
            Criteria crit = conexao.createCriteria(Cobranca.class);
            crit.add(Restrictions.eq("Mensalidade", m));
            crit.add(Restrictions.eq("Numero", 0L));
            
            Cobranca cobranca = (Cobranca)crit.list().get(0);
            cobranca.setGerada(true);
            
            try{
                conexao.saveOrUpdate(cobranca);
                tx.commit();
            } catch(Exception e){
                tx.rollback();
                JOptionPane.showMessageDialog(this, "Falha ao gravar aviso de atraso. Motivo: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
            
            //Gerando relatorio:
            HashMap map = new HashMap();
            JasperPrint jasperPrint = null;
            Connection connection = null;
            try {
                connection = DriverManager.getConnection("jdbc:firebirdsql:localhost:C:\\Banco\\UNIQUE.FDB","sysdba","1123581321");
            } catch (SQLException ex) {
                Logger.getLogger(GerarMensalidades.class.getName()).log(Level.SEVERE, null, ex);
            }

            map.put("mensalidadeID", m.getID());
            try {
                JasperReport compiled;
                if(!m.getAluno().isVip())
                    compiled = JasperCompileManager.compileReport("C:\\Banco\\Relatorios\\AvisoMensalidade.jrxml");
                else
                    compiled = JasperCompileManager.compileReport("C:\\Banco\\Relatorios\\AvisoMensalidadeVip.jrxml");
                jasperPrint = JasperFillManager.fillReport(compiled, map, connection);
                JRViewer viewer = new JRViewer(jasperPrint);
                JFrame report = new JFrame();
                report.setExtendedState(MAXIMIZED_BOTH);
                report.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                report.setTitle("Aviso de Atraso");
                Container c = report.getContentPane();
                c.add(viewer);
                report.setVisible(true);
                
                this.dispose();

            } catch (JRException ex) {
                JOptionPane.showMessageDialog(this, "Não foi possível imprimir o relatório. Motivo: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
            
        } else{
            JOptionPane.showMessageDialog(this, "Operação cancelada!", "Informação", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnImprimirComActionPerformed

    private void btnImprimirCob1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirCob1ActionPerformed
        if(JOptionPane.showConfirmDialog(this, "Deseja realmente gerar a primeira cobrança?", "Confirmação", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
            conexao = HibernateUtil.openSession();
            tx = conexao.beginTransaction();
            //Procurando por Avisos e Cobrancas gerados:
            Criteria crit = conexao.createCriteria(Cobranca.class);
            crit.add(Restrictions.eq("Mensalidade", m));
            crit.add(Restrictions.eq("Numero", 1L));
            
            Cobranca cobranca = (Cobranca)crit.list().get(0);
            cobranca.setGerada(true);
            
            try{
                conexao.saveOrUpdate(cobranca);
                tx.commit();
            } catch(Exception e){
                tx.rollback();
                JOptionPane.showMessageDialog(this, "Falha ao gravar a primeira cobrança. Motivo: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
            
            //Gerando relatorio:
            HashMap map = new HashMap();
            JasperPrint jasperPrint = null;
            Connection connection = null;
            try {
                connection = DriverManager.getConnection("jdbc:firebirdsql:localhost:C:\\Banco\\UNIQUE.FDB","sysdba","1123581321");
            } catch (SQLException ex) {
                Logger.getLogger(GerarMensalidades.class.getName()).log(Level.SEVERE, null, ex);
            }

            map.put("mensalidadeID", m.getID());
            try {
                JasperReport compiled;
                if(!m.getAluno().isVip())
                    compiled = JasperCompileManager.compileReport("C:\\Banco\\Relatorios\\Cobranca1.jrxml");
                else
                    compiled = JasperCompileManager.compileReport("C:\\Banco\\Relatorios\\Cobranca1Vip.jrxml");    
                jasperPrint = JasperFillManager.fillReport(compiled, map, connection);
                JRViewer viewer = new JRViewer(jasperPrint);
                JFrame report = new JFrame();
                report.setExtendedState(MAXIMIZED_BOTH);
                report.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                report.setTitle("Primeira Cobrança");
                Container c = report.getContentPane();
                c.add(viewer);
                report.setVisible(true);
                
                this.dispose();

            } catch (JRException ex) {
                JOptionPane.showMessageDialog(this, "Não foi possível imprimir o relatório. Motivo: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
            
        } else{
            JOptionPane.showMessageDialog(this, "Operação cancelada!", "Informação", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnImprimirCob1ActionPerformed

    private void btnImprimirCob2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirCob2ActionPerformed
        if(JOptionPane.showConfirmDialog(this, "Deseja realmente gerar a segunda cobrança?", "Confirmação", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
            conexao = HibernateUtil.openSession();
            tx = conexao.beginTransaction();
            //Procurando por Avisos e Cobrancas gerados:
            Criteria crit = conexao.createCriteria(Cobranca.class);
            crit.add(Restrictions.eq("Mensalidade", m));
            crit.add(Restrictions.eq("Numero", 2L));
            
            Cobranca cobranca = (Cobranca)crit.list().get(0);
            cobranca.setGerada(true);
            
            try{
                conexao.saveOrUpdate(cobranca);
                tx.commit();
            } catch(Exception e){
                tx.rollback();
                JOptionPane.showMessageDialog(this, "Falha ao gravar a segunda cobrança. Motivo: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
            
            //Gerando relatorio:
            HashMap map = new HashMap();
            JasperPrint jasperPrint = null;
            Connection connection = null;
            try {
                connection = DriverManager.getConnection("jdbc:firebirdsql:localhost:C:\\Banco\\UNIQUE.FDB","sysdba","1123581321");
            } catch (SQLException ex) {
                Logger.getLogger(GerarMensalidades.class.getName()).log(Level.SEVERE, null, ex);
            }

            map.put("mensalidadeID", m.getID());
            try {
                JasperReport compiled;
                if(!m.getAluno().isVip())
                    compiled = JasperCompileManager.compileReport("C:\\Banco\\Relatorios\\Cobranca2.jrxml");
                else
                    compiled = JasperCompileManager.compileReport("C:\\Banco\\Relatorios\\Cobranca2Vip.jrxml");
                jasperPrint = JasperFillManager.fillReport(compiled, map, connection);
                JRViewer viewer = new JRViewer(jasperPrint);
                JFrame report = new JFrame();
                report.setExtendedState(MAXIMIZED_BOTH);
                report.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                report.setTitle("Segunda Cobrança");
                Container c = report.getContentPane();
                c.add(viewer);
                report.setVisible(true);
                
                this.dispose();

            } catch (JRException ex) {
                JOptionPane.showMessageDialog(this, "Não foi possível imprimir o relatório. Motivo: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
            
        } else{
            JOptionPane.showMessageDialog(this, "Operação cancelada!", "Informação", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnImprimirCob2ActionPerformed

    private void btnImprimirCob3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirCob3ActionPerformed
        if(JOptionPane.showConfirmDialog(this, "Deseja realmente gerar a terceira cobrança?", "Confirmação", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
            conexao = HibernateUtil.openSession();
            tx = conexao.beginTransaction();
            //Procurando por Avisos e Cobrancas gerados:
            Criteria crit = conexao.createCriteria(Cobranca.class);
            crit.add(Restrictions.eq("Mensalidade", m));
            crit.add(Restrictions.eq("Numero", 3L));
            
            Cobranca cobranca = (Cobranca)crit.list().get(0);
            cobranca.setGerada(true);
            
            String dataLimite = JOptionPane.showInputDialog(this, "Informe a data limite para o comparecimento à escola (dd/mm/aaaa):", "Informação", JOptionPane.INFORMATION_MESSAGE);
            try {
                Date dataTemporaria = DateFormat.getDateInstance().parse(dataLimite);
                cobranca.setDataComparecimento(dataTemporaria);
            } catch (ParseException ex) {
                Logger.getLogger(Cobrancas.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(this, "Falha ao gravar a data do comparecimento. Motivo: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
            
            crit = conexao.createCriteria(Cobranca.class);
            crit.add(Restrictions.eq("Mensalidade", m));
            crit.add(Restrictions.eq("Numero", 4L));
            
            Cobranca cobranca2 = (Cobranca)crit.list().get(0);
            Calendar cal = Calendar.getInstance();
            cal.setTime(cobranca.getDataComparecimento());
            cal.add(Calendar.DATE, 1);
            cobranca2.setData(cal.getTime());
            
            try{
                conexao.saveOrUpdate(cobranca);
                conexao.saveOrUpdate(cobranca2);
                tx.commit();
            } catch(Exception e){
                tx.rollback();
                JOptionPane.showMessageDialog(this, "Falha ao gravar a terceira cobrança. Motivo: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
            
            //Gerando relatorio:
            HashMap map = new HashMap();
            JasperPrint jasperPrint = null;
            Connection connection = null;
            try {
                connection = DriverManager.getConnection("jdbc:firebirdsql:localhost:C:\\Banco\\UNIQUE.FDB","sysdba","1123581321");
            } catch (SQLException ex) {
                Logger.getLogger(GerarMensalidades.class.getName()).log(Level.SEVERE, null, ex);
            }

            map.put("mensalidadeID", m.getID());
            try {
                JasperReport compiled;
                if(!m.getAluno().isVip())
                    compiled = JasperCompileManager.compileReport("C:\\Banco\\Relatorios\\Cobranca3.jrxml");
                else
                    compiled = JasperCompileManager.compileReport("C:\\Banco\\Relatorios\\Cobranca3Vip.jrxml");
                jasperPrint = JasperFillManager.fillReport(compiled, map, connection);
                JRViewer viewer = new JRViewer(jasperPrint);
                JFrame report = new JFrame();
                report.setExtendedState(MAXIMIZED_BOTH);
                report.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                report.setTitle("Terceira Cobrança");
                Container c = report.getContentPane();
                c.add(viewer);
                report.setVisible(true);
                
                this.dispose();

            } catch (JRException ex) {
                JOptionPane.showMessageDialog(this, "Não foi possível imprimir o relatório. Motivo: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
            
        } else{
            JOptionPane.showMessageDialog(this, "Operação cancelada!", "Informação", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnImprimirCob3ActionPerformed

    private void btnImprimirCob4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirCob4ActionPerformed
        if(JOptionPane.showConfirmDialog(this, "Deseja realmente gerar a quarta cobrança?", "Confirmação", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
            conexao = HibernateUtil.openSession();
            tx = conexao.beginTransaction();
            //Procurando por Avisos e Cobrancas gerados:
            Criteria crit = conexao.createCriteria(Cobranca.class);
            crit.add(Restrictions.eq("Mensalidade", m));
            crit.add(Restrictions.eq("Numero", 4L));
            
            Cobranca cobranca = (Cobranca)crit.list().get(0);
            cobranca.setGerada(true);
            
            String localCartorio = JOptionPane.showInputDialog(this, "Informe o local onde o devedor deverá comparecer:", "Informação", JOptionPane.INFORMATION_MESSAGE);
            cobranca.setLocalCartorio(localCartorio);
            
            try{
                conexao.saveOrUpdate(cobranca);
                tx.commit();
            } catch(Exception e){
                tx.rollback();
                JOptionPane.showMessageDialog(this, "Falha ao gravar a segunda cobrança. Motivo: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
            
            //Gerando relatorio:
            HashMap map = new HashMap();
            JasperPrint jasperPrint = null;
            Connection connection = null;
            try {
                connection = DriverManager.getConnection("jdbc:firebirdsql:localhost:C:\\Banco\\UNIQUE.FDB","sysdba","1123581321");
            } catch (SQLException ex) {
                Logger.getLogger(GerarMensalidades.class.getName()).log(Level.SEVERE, null, ex);
            }

            map.put("mensalidadeID", m.getID());
            try {
                JasperReport compiled;
                if(!m.getAluno().isVip())
                    compiled = JasperCompileManager.compileReport("C:\\Banco\\Relatorios\\Cobranca4.jrxml");
                else
                    compiled = JasperCompileManager.compileReport("C:\\Banco\\Relatorios\\Cobranca4Vip.jrxml");
                jasperPrint = JasperFillManager.fillReport(compiled, map, connection);
                JRViewer viewer = new JRViewer(jasperPrint);
                JFrame report = new JFrame();
                report.setExtendedState(MAXIMIZED_BOTH);
                report.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                report.setTitle("Quarta Cobrança");
                Container c = report.getContentPane();
                c.add(viewer);
                report.setVisible(true);
                
                this.dispose();

            } catch (JRException ex) {
                JOptionPane.showMessageDialog(this, "Não foi possível imprimir o relatório. Motivo: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
            
        } else{
            JOptionPane.showMessageDialog(this, "Operação cancelada!", "Informação", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnImprimirCob4ActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSairActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnSairActionPerformed

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
            java.util.logging.Logger.getLogger(Cobrancas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Cobrancas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Cobrancas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Cobrancas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Cobrancas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnImprimirCob1;
    private javax.swing.JButton btnImprimirCob2;
    private javax.swing.JButton btnImprimirCob3;
    private javax.swing.JButton btnImprimirCob4;
    private javax.swing.JButton btnImprimirCom;
    private javax.swing.JButton btnSair;
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
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel lblAluno;
    private javax.swing.JLabel lblCobranca1;
    private javax.swing.JLabel lblCobranca2;
    private javax.swing.JLabel lblCobranca3;
    private javax.swing.JLabel lblCobranca4;
    private javax.swing.JLabel lblComunicado;
    private javax.swing.JLabel lblMesAno;
    private javax.swing.JLabel lblNivel;
    private javax.swing.JLabel lblStatus;
    private javax.swing.JLabel lblValor;
    // End of variables declaration//GEN-END:variables
}
