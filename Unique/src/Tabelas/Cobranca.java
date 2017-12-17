/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tabelas;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

/**
 *
 * @author CarlosEduardo
 */

@Entity
@Table(name="Cobranca")
public class Cobranca implements Serializable{
    @Id
    @GeneratedValue
    private Long ID;

    @ManyToOne
    private Mensalidade Mensalidade;
    
    @Column(nullable=false)
    private Long Numero;
    
    @Column(nullable=false)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date Data;
    
    @Column(nullable=false)
    private boolean Gerada;
    
    @Column
    private String ValorExtenso;
    
    @Column
    private String NumeroParcela;
    
    @Column(nullable=true)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date DataComparecimento;
    
    @Column(nullable=true)
    private String LocalCartorio;

    @Column(nullable=true)
    private boolean MaiorDeIdade;
    
    /**
     * @return the ID
     */
    public Long getID() {
        return ID;
    }

    /**
     * @param ID the ID to set
     */
    public void setID(Long ID) {
        this.ID = ID;
    }

    /**
     * @return the Mensalidade
     */
    public Mensalidade getMensalidade() {
        return Mensalidade;
    }

    /**
     * @param Mensalidade the Mensalidade to set
     */
    public void setMensalidade(Mensalidade Mensalidade) {
        this.Mensalidade = Mensalidade;
    }

    /**
     * @return the Numero
     */
    public Long getNumero() {
        return Numero;
    }

    /**
     * @param Numero the Numero to set
     */
    public void setNumero(Long Numero) {
        this.Numero = Numero;
    }

    /**
     * @return the Data
     */
    public Date getData() {
        return Data;
    }

    /**
     * @param Data the Data to set
     */
    public void setData(Date Data) {
        this.Data = Data;
    }

    /**
     * @return the Gerada
     */
    public boolean isGerada() {
        return Gerada;
    }

    /**
     * @param Gerada the Gerada to set
     */
    public void setGerada(boolean Gerada) {
        this.Gerada = Gerada;
    }

    /**
     * @return the valorExtenso
     */
    public String getValorExtenso() {
        return ValorExtenso;
    }

    /**
     * @param valorExtenso the valorExtenso to set
     */
    public void setValorExtenso(String valorExtenso) {
        this.ValorExtenso = valorExtenso;
    }

    /**
     * @return the numeroParcela
     */
    public String getNumeroParcela() {
        return NumeroParcela;
    }

    /**
     * @param numeroParcela the numeroParcela to set
     */
    public void setNumeroParcela(String numeroParcela) {
        this.NumeroParcela = numeroParcela;
    }

    /**
     * @return the DataComparecimento
     */
    public Date getDataComparecimento() {
        return DataComparecimento;
    }

    /**
     * @param DataComparecimento the DataComparecimento to set
     */
    public void setDataComparecimento(Date DataComparecimento) {
        this.DataComparecimento = DataComparecimento;
    }

    /**
     * @return the LocalCartorio
     */
    public String getLocalCartorio() {
        return LocalCartorio;
    }

    /**
     * @param LocalCartorio the LocalCartorio to set
     */
    public void setLocalCartorio(String LocalCartorio) {
        this.LocalCartorio = LocalCartorio;
    }

    /**
     * @return the MaiorDeIdade
     */
    public boolean isMaiorDeIdade() {
        return MaiorDeIdade;
    }

    /**
     * @param MaiorDeIdade the MaiorDeIdade to set
     */
    public void setMaiorDeIdade(boolean MaiorDeIdade) {
        this.MaiorDeIdade = MaiorDeIdade;
    }
    
    
}
