/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tabelas;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author CarlosEduardo
 */

@Entity
@Table(name="Turma")
public class Turma implements Serializable{
    @Id
    @GeneratedValue
    private Long ID;
    
    @ManyToMany
    @JoinTable(name="TURMA_ALUNO", joinColumns=
      {@JoinColumn(name="TURMA_ID")}, inverseJoinColumns=
        {@JoinColumn(name="ALUNO_ID")})
    private List<Aluno> Alunos;
    
    @ManyToOne
    private Funcionario Professor;
    
    @Column(nullable=false)
    private String Descricao;
    
    @Column(length=5, nullable=false)
    private Time Horario;
    
    @Column(length=30, nullable=false)
    private String DiasSemana;
    
    @ManyToOne
    private Nivel Nivel;
    
    @Column(nullable=true)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date DataInicio;
    
    @Column(nullable=false)
    private boolean Ativa;

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    /**
     * @return the Alunos
     */
    public List<Aluno> getAlunos() {
        return Alunos;
    }

    /**
     * @param Alunos the Alunos to set
     */
    public void setAlunos(List<Aluno> Alunos) {
        this.Alunos = Alunos;
    }

    /**
     * @return the Professor
     */
    public Funcionario getProfessor() {
        return Professor;
    }

    /**
     * @param Professor the Professor to set
     */
    public void setProfessor(Funcionario Professor) {
        this.Professor = Professor;
    }

    /**
     * @return the DiasSemana
     */
    public String getDiasSemana() {
        return DiasSemana;
    }

    /**
     * @param DiasSemana the DiasSemana to set
     */
    public void setDiasSemana(String DiasSemana) {
        this.DiasSemana = DiasSemana;
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
     * @return the Descricao
     */
    public String getDescricao() {
        return Descricao;
    }

    /**
     * @param Descricao the Descricao to set
     */
    public void setDescricao(String Descricao) {
        this.Descricao = Descricao;
    }

    /**
     * @return the DataInicio
     */
    public Date getDataInicio() {
        return DataInicio;
    }

    /**
     * @param DataInicio the DataInicio to set
     */
    public void setDataInicio(Date DataInicio) {
        this.DataInicio = DataInicio;
    }

    /**
     * @return the Ativa
     */
    public boolean isAtiva() {
        return Ativa;
    }

    /**
     * @param Ativa the Ativa to set
     */
    public void setAtiva(boolean Ativa) {
        this.Ativa = Ativa;
    }

    /**
     * @return the Horario
     */
    public Time getHorario() {
        return Horario;
    }

    /**
     * @param Horario the Horario to set
     */
    public void setHorario(Time Horario) {
        this.Horario = Horario;
    }
}
