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
@Table(name="MatriculaVip")
public class MatriculaVip implements Serializable{
    @Id
    @GeneratedValue
    private Long ID;
    
    @ManyToOne
    private Aluno Aluno;
    
    @ManyToOne
    private Nivel Nivel;
    
    @Column(nullable=false)
    private Double ValorMensal;
    
    @Column(nullable=false)
    private Double ValorMaterial;
    
    @Column(nullable=false)
    private Double ValorAulaExcedente;
    
    @Column(nullable=false)
    private boolean MaterialEscola;
    
    @Column(nullable=false)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date DataMatricula;
    
    @Column(nullable=false)
    private Long AulasSemanais;
    
    @Column(nullable=false)
    private int MinutosSemanais;
    
    @Column(nullable=true)
    private int MesIni;
    
    @Column(nullable=true)
    private int AnoIni;
    
    @Column(nullable=true)
    private int MesFim;
    
    @Column(nullable=true)
    private int AnoFim;
    
    @Column(nullable=true)
    private Long QtdeParcelasAulas;

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
     * @return the MaterialEscola
     */
    public boolean isMaterialEscola() {
        return MaterialEscola;
    }

    /**
     * @param MaterialEscola the MaterialEscola to set
     */
    public void setMaterialEscola(boolean MaterialEscola) {
        this.MaterialEscola = MaterialEscola;
    }

    /**
     * @return the DataMatricula
     */
    public Date getDataMatricula() {
        return DataMatricula;
    }

    /**
     * @param DataMatricula the DataMatricula to set
     */
    public void setDataMatricula(Date DataMatricula) {
        this.DataMatricula = DataMatricula;
    }

    /**
     * @return the ValorMensal
     */
    public Double getValorMensal() {
        return ValorMensal;
    }

    /**
     * @param ValorMensal the ValorMensal to set
     */
    public void setValorMensal(Double ValorMensal) {
        this.ValorMensal = ValorMensal;
    }

    /**
     * @return the ValorAulaExcedente
     */
    public Double getValorAulaExcedente() {
        return ValorAulaExcedente;
    }

    /**
     * @param ValorAulaExcedente the ValorAulaExcedente to set
     */
    public void setValorAulaExcedente(Double ValorAulaExcedente) {
        this.ValorAulaExcedente = ValorAulaExcedente;
    }

    /**
     * @return the AulasSemanais
     */
    public Long getAulasSemanais() {
        return AulasSemanais;
    }

    /**
     * @param AulasSemanais the AulasSemanais to set
     */
    public void setAulasSemanais(Long AulasSemanais) {
        this.AulasSemanais = AulasSemanais;
    }

    /**
     * @return the MinutosSemanais
     */
    public int getMinutosSemanais() {
        return MinutosSemanais;
    }

    /**
     * @param MinutosSemanais the MinutosSemanais to set
     */
    public void setMinutosSemanais(int MinutosSemanais) {
        this.MinutosSemanais = MinutosSemanais;
    }

    /**
     * @return the MesIni
     */
    public int getMesIni() {
        return MesIni;
    }

    /**
     * @param MesIni the MesIni to set
     */
    public void setMesIni(int MesIni) {
        this.MesIni = MesIni;
    }

    /**
     * @return the AnoIni
     */
    public int getAnoIni() {
        return AnoIni;
    }

    /**
     * @param AnoIni the AnoIni to set
     */
    public void setAnoIni(int AnoIni) {
        this.AnoIni = AnoIni;
    }

    /**
     * @return the MesFim
     */
    public int getMesFim() {
        return MesFim;
    }

    /**
     * @param MesFim the MesFim to set
     */
    public void setMesFim(int MesFim) {
        this.MesFim = MesFim;
    }

    /**
     * @return the AnoFim
     */
    public int getAnoFim() {
        return AnoFim;
    }

    /**
     * @param AnoFim the AnoFim to set
     */
    public void setAnoFim(int AnoFim) {
        this.AnoFim = AnoFim;
    }

    /**
     * @return the QtdeParcelasAulas
     */
    public Long getQtdeParcelasAulas() {
        return QtdeParcelasAulas;
    }

    /**
     * @param QtdeParcelasAulas the QtdeParcelasAulas to set
     */
    public void setQtdeParcelasAulas(Long QtdeParcelasAulas) {
        this.QtdeParcelasAulas = QtdeParcelasAulas;
    }
}
