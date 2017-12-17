/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tabelas;

import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author CarlosEduardo
 */

@Entity
@Table(name="Responsavel")
public class Responsavel implements Serializable{
    @Id
    @GeneratedValue
    private Long ID;
    
    @Column(length=14, nullable=false)
    private String CPF;
    
    @Column(nullable=false)
    private String RG;
    
    @ManyToOne
    private Aluno Aluno;
    
    @Column(nullable=false)
    private String Nome;
    
    @Column(nullable=false)
    private String Telefone;
    
    @Column(nullable=false)
    private String Celular;
    
    @Column(nullable=false)
    private String GrauParentesco;

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    /**
     * @return the CPF
     */
    public String getCPF() {
        return CPF;
    }

    /**
     * @param CPF the CPF to set
     */
    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    /**
     * @return the RG
     */
    public String getRG() {
        return RG;
    }

    /**
     * @param RG the RG to set
     */
    public void setRG(String RG) {
        this.RG = RG;
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
     * @return the Nome
     */
    public String getNome() {
        return Nome;
    }

    /**
     * @param Nome the Nome to set
     */
    public void setNome(String Nome) {
        this.Nome = Nome;
    }

    /**
     * @return the Telefone
     */
    public String getTelefone() {
        return Telefone;
    }

    /**
     * @param Telefone the Telefone to set
     */
    public void setTelefone(String Telefone) {
        this.Telefone = Telefone;
    }

    /**
     * @return the Celular
     */
    public String getCelular() {
        return Celular;
    }

    /**
     * @param Celular the Celular to set
     */
    public void setCelular(String Celular) {
        this.Celular = Celular;
    }

    /**
     * @return the GrauParentesco
     */
    public String getGrauParentesco() {
        return GrauParentesco;
    }

    /**
     * @param GrauParentesco the GrauParentesco to set
     */
    public void setGrauParentesco(String GrauParentesco) {
        this.GrauParentesco = GrauParentesco;
    }
}
