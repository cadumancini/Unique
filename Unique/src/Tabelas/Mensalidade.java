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
@Table(name="Mensalidade")
public class Mensalidade implements Serializable{
    @Id
    @GeneratedValue
    private Long ID;
    
    @ManyToOne
    private Aluno Aluno;
    
    @Column(nullable=false)
    private Double ValorAulas;
    
    @Column(nullable=false)
    private Double ValorMaterial;
    
    @Column(nullable=true)
    private Double ValorPago;
    
    @Column(nullable=false)
    private int MesVencto;
    
    @Column(nullable=false)
    private int AnoVencto;
    
    @Column(nullable=true)
    private Double QtdAulasExtras;
    
    @Column(length=10)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date DataPagto;
    
    @Column(nullable=false)
    private boolean Paga;
    
    @Column(nullable=true)
    private String NumeroCheque;
    
    @Column(nullable=true)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date DataCompensacao;
    
    @Column(nullable=true)
    private String Observacao;
    
    @ManyToOne
    private Nivel Nivel;

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
     * @return the Paga
     */
    public boolean isPaga() {
        return Paga;
    }

    /**
     * @param Paga the Paga to set
     */
    public void setPaga(boolean Paga) {
        this.Paga = Paga;
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

    /**
     * @return the MesVencto
     */
    public int getMesVencto() {
        return MesVencto;
    }

    /**
     * @param MesVencto the MesVencto to set
     */
    public void setMesVencto(int MesVencto) {
        this.MesVencto = MesVencto;
    }

    /**
     * @return the AnoVencto
     */
    public int getAnoVencto() {
        return AnoVencto;
    }

    /**
     * @param AnoVencto the AnoVencto to set
     */
    public void setAnoVencto(int AnoVencto) {
        this.AnoVencto = AnoVencto;
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
     * @return the ValorAulas
     */
    public Double getValorAulas() {
        return ValorAulas;
    }

    /**
     * @param ValorAulas the ValorAulas to set
     */
    public void setValorAulas(Double ValorAulas) {
        this.ValorAulas = ValorAulas;
    }

    /**
     * @return the ValorMaterial
     */
    public Double getValorMaterial() {
        return ValorMaterial;
    }

    /**
     * @param ValorMaterial the ValorMaterial to set
     */
    public void setValorMaterial(Double ValorMaterial) {
        this.ValorMaterial = ValorMaterial;
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
     * @return the Observacao
     */
    public String getObservacao() {
        return Observacao;
    }

    /**
     * @param Observacao the Observacao to set
     */
    public void setObservacao(String Observacao) {
        this.Observacao = Observacao;
    }

    /**
     * @return the QtdAulasExtras
     */
    public Double getQtdAulasExtras() {
        return QtdAulasExtras;
    }

    /**
     * @param QtdAulasExtras the QtdAulasExtras to set
     */
    public void setQtdAulasExtras(Double QtdAulasExtras) {
        this.QtdAulasExtras = QtdAulasExtras;
    }
}
