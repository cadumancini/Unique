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
@Table(name="Historico")
public class Historico implements Serializable {
    @Id
    @GeneratedValue
    private Long ID;
    
    @ManyToOne
    private Aluno Aluno;
    
    @ManyToOne
    private Turma Turma;
    
    @Column(nullable=true)
    private Long Faltas;
    
    @Column(nullable=true)
    private Double Nota1;
    
    @Column(nullable=true)
    private Double Nota2;
    
    @Column(nullable=true)
    private Double Nota3;
    
    @Column(nullable=true)
    private Double Nota4;
    
    @Column(nullable=true)
    private Double Nota5;
    
    @Column(nullable=true)
    private Double Media;

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
     * @return the Faltas
     */
    public Long getFaltas() {
        return Faltas;
    }

    /**
     * @param Faltas the Faltas to set
     */
    public void setFaltas(Long Faltas) {
        this.Faltas = Faltas;
    }

    /**
     * @return the Nota1
     */
    public Double getNota1() {
        return Nota1;
    }

    /**
     * @param Nota1 the Nota1 to set
     */
    public void setNota1(Double Nota1) {
        this.Nota1 = Nota1;
    }

    /**
     * @return the Nota2
     */
    public Double getNota2() {
        return Nota2;
    }

    /**
     * @param Nota2 the Nota2 to set
     */
    public void setNota2(Double Nota2) {
        this.Nota2 = Nota2;
    }

    /**
     * @return the Media
     */
    public Double getMedia() {
        return Media;
    }

    /**
     * @param Media the Media to set
     */
    public void setMedia(Double Media) {
        this.Media = Media;
    }

    /**
     * @return the Nota3
     */
    public Double getNota3() {
        return Nota3;
    }

    /**
     * @param Nota3 the Nota3 to set
     */
    public void setNota3(Double Nota3) {
        this.Nota3 = Nota3;
    }

    /**
     * @return the Nota4
     */
    public Double getNota4() {
        return Nota4;
    }

    /**
     * @param Nota4 the Nota4 to set
     */
    public void setNota4(Double Nota4) {
        this.Nota4 = Nota4;
    }

    /**
     * @return the Nota5
     */
    public Double getNota5() {
        return Nota5;
    }

    /**
     * @param Nota5 the Nota5 to set
     */
    public void setNota5(Double Nota5) {
        this.Nota5 = Nota5;
    }
    
}
