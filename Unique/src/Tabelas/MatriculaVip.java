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
}
