
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tabelas;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author CarlosEduardo
 */

@Entity
@Table(name="Aluno")
public class Aluno implements Serializable{
    @Id
    @GeneratedValue
    private Long ID;
    
    @Column(length=14, nullable=true)
    private String CPF;
    
    @Column(nullable=true)
    private String RG;
    
    @Column(nullable=false)
    private String Nome;
    
    @Column(length=10, nullable=true)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date Nascimento;
    
    @Column(nullable=true)
    private String Telefone;
    
    @Column(nullable=true)
    private String Celular;
    
    @Column(nullable=false)
    private String Endereco;
    
    @Column(nullable=false)
    private Long Numero;
    
    @Column(nullable=true)
    private String Complemento;
    
    @Column(nullable=false)
    private String Bairro;
    
    @Column(length=9, nullable=false)
    private String CEP;
    
    @Column(nullable=false)
    private String Cidade;
    
    @Column(length=2, nullable=false)
    private String Estado;
    
    @Column(nullable=true)
    private String Email;
    
    @Column(length=3, nullable=false)
    private Long Desconto;
    
    @Column(nullable=false)
    private boolean Vip;
    
    @Column(nullable=false)
    private boolean Ativo;
    
    @ManyToOne
    private Nivel NivelAtual;
    
    @ManyToMany(mappedBy = "Alunos")
    private List<Turma> Turmas;
    
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
     * @return the Nascimento
     */
    public Date getNascimento() {
        return Nascimento;
    }

    /**
     * @param Nascimento the Nascimento to set
     */
    public void setNascimento(Date Nascimento) {
        this.Nascimento = Nascimento;
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
     * @return the Endereco
     */
    public String getEndereco() {
        return Endereco;
    }

    /**
     * @param Endereco the Endereco to set
     */
    public void setEndereco(String Endereco) {
        this.Endereco = Endereco;
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
     * @return the Complemento
     */
    public String getComplemento() {
        return Complemento;
    }

    /**
     * @param Complemento the Complemento to set
     */
    public void setComplemento(String Complemento) {
        this.Complemento = Complemento;
    }

    /**
     * @return the Bairro
     */
    public String getBairro() {
        return Bairro;
    }

    /**
     * @param Bairro the Bairro to set
     */
    public void setBairro(String Bairro) {
        this.Bairro = Bairro;
    }

    /**
     * @return the CEP
     */
    public String getCEP() {
        return CEP;
    }

    /**
     * @param CEP the CEP to set
     */
    public void setCEP(String CEP) {
        this.CEP = CEP;
    }

    /**
     * @return the Cidade
     */
    public String getCidade() {
        return Cidade;
    }

    /**
     * @param Cidade the Cidade to set
     */
    public void setCidade(String Cidade) {
        this.Cidade = Cidade;
    }

    /**
     * @return the Estado
     */
    public String getEstado() {
        return Estado;
    }

    /**
     * @param Estado the Estado to set
     */
    public void setEstado(String Estado) {
        this.Estado = Estado;
    }

    /**
     * @return the Email
     */
    public String getEmail() {
        return Email;
    }

    /**
     * @param Email the Email to set
     */
    public void setEmail(String Email) {
        this.Email = Email;
    }

    /**
     * @return the Desconto
     */
    public Long getDesconto() {
        return Desconto;
    }

    /**
     * @param Desconto the Desconto to set
     */
    public void setDesconto(Long Desconto) {
        this.Desconto = Desconto;
    }

    /**
     * @return the NivelAtual
     */
    public Nivel getNivelAtual() {
        return NivelAtual;
    }

    /**
     * @param NivelAtual the NivelAtual to set
     */
    public void setNivelAtual(Nivel NivelAtual) {
        this.NivelAtual = NivelAtual;
    }

    /**
     * @return the Ativo
     */
    public boolean isAtivo() {
        return Ativo;
    }

    /**
     * @param Ativo the Ativo to set
     */
    public void setAtivo(boolean Ativo) {
        this.Ativo = Ativo;
    }

    /**
     * @return the Turmas
     */
    public List<Turma> getTurmas() {
        return Turmas;
    }

    /**
     * @param Turmas the Turmas to set
     */
    public void setTurmas(List<Turma> Turmas) {
        this.Turmas = Turmas;
    }

    /**
     * @return the Vip
     */
    public boolean isVip() {
        return Vip;
    }

    /**
     * @param Vip the Vip to set
     */
    public void setVip(boolean Vip) {
        this.Vip = Vip;
    }
}
