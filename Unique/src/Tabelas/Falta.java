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
@Table(name="Falta")
public class Falta implements Serializable{
    @Id
    @GeneratedValue
    private Long ID;

    @ManyToOne
    private Aluno Aluno;
    
    @ManyToOne
    private Turma Turma;
    
    @Column(nullable=false)
    private Long AulasPerdidas;
    
    @Column(length=10, nullable=false)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date Data;
    
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
     * @return the Turma
     */
    public Turma getTurma() {
        return Turma;
    }

    /**
     * @param Turma the Turma to set
     */
    public void setTurma(Turma Turma) {
        this.Turma = Turma;
    }

    /**
     * @return the AulasPerdidas
     */
    public Long getAulasPerdidas() {
        return AulasPerdidas;
    }

    /**
     * @param AulasPerdidas the AulasPerdidas to set
     */
    public void setAulasPerdidas(Long AulasPerdidas) {
        this.AulasPerdidas = AulasPerdidas;
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
    
}
