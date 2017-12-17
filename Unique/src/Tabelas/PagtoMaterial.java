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
 * @author cemancini
 */

@Entity
@Table(name="PagtoMaterial")
public class PagtoMaterial implements Serializable{
    @Id
    @GeneratedValue
    private Long ID;
    
    @ManyToOne
    private Aluno Aluno;
    
    @ManyToOne
    private Nivel Nivel;
    
    @Column(nullable=false)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date DataPagto;
    
    @Column(nullable=false)
    private Double ValorPago;
    
    @Column(nullable=true)
    private String NumeroCheque;
    
    @Column(nullable=true)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date DataCompensacao;

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    /**
     * @return the Aluno
     */
    public Aluno getAluno() {
        return Aluno;
    }

    /**
     * @param Aluno the Aluno to set
     */
    public void setAluno(Aluno Aluno) {
        this.Aluno = Aluno;
    }

    /**
     * @return the DataPagto
     */
    public Date getDataPagto() {
        return DataPagto;
    }

    /**
     * @param DataPagto the DataPagto to set
     */
    public void setDataPagto(Date DataPagto) {
        this.DataPagto = DataPagto;
    }

    /**
     * @return the ValorPago
     */
    public Double getValorPago() {
        return ValorPago;
    }

    /**
     * @param ValorPago the ValorPago to set
     */
    public void setValorPago(Double ValorPago) {
        this.ValorPago = ValorPago;
    }

    /**
     * @return the NumeroCheque
     */
    public String getNumeroCheque() {
        return NumeroCheque;
    }

    /**
     * @param NumeroCheque the NumeroCheque to set
     */
    public void setNumeroCheque(String NumeroCheque) {
        this.NumeroCheque = NumeroCheque;
    }

    /**
     * @return the DataCompensacao
     */
    public Date getDataCompensacao() {
        return DataCompensacao;
    }

    /**
     * @param DataCompensacao the DataCompensacao to set
     */
    public void setDataCompensacao(Date DataCompensacao) {
        this.DataCompensacao = DataCompensacao;
    }

    /**
     * @return the Nivel
     */
    public Nivel getNivel() {
        return Nivel;
    }

    /**
     * @param Nivel the Nivel to set
     */
    public void setNivel(Nivel Nivel) {
        this.Nivel = Nivel;
    }
    
}
