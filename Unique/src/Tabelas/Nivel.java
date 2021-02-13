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
@Table(name="Nivel")
public class Nivel implements Serializable{
    @Id
    @GeneratedValue
    private Long ID;
    
    @Column(nullable=false)
    private String Codigo;
    
    @Column(nullable=false)
    private String Nome;
    
    @Column(nullable=true)
    private String Descricao;
    
    @Column(nullable=true)
    private Double ValorAulas;
    
    @Column(nullable=true)
    private Double ValorMaterial;
    
    @Column(nullable=true)
    private Long Duracao;
    
    @Column(nullable=true)
    private int QtdHoras;
    
    @Column(nullable=true)
    private int CargaHorMin;
    
    @Column(nullable=true)
    private int CargaHorMax;
    
    @Column(nullable=false)
    private boolean VIP;
    
    @Column(nullable=false)
    private boolean GotIt;
    
    @Column(nullable=true)
    private int MinutosSemanais;
    
    @Column(nullable=true)
    private boolean Prorrogavel;
    
    @Column(nullable=true)
    private String Idioma;
    
    @Column(nullable=true)
    private boolean Online;

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    /**
     * @return the Codigo
     */
    public String getCodigo() {
        return Codigo;
    }

    /**
     * @param Codigo the Codigo to set
     */
    public void setCodigo(String Codigo) {
        this.Codigo = Codigo;
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
     * @return the ValorAulas
     */
    public Double getValorAulas() {
        return ValorAulas;
    }

    /**
     * @param ValorAulas the Valor to set
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
     * @return the Duracao
     */
    public Long getDuracao() {
        return Duracao;
    }

    /**
     * @param Duracao the Duracao to set
     */
    public void setDuracao(Long Duracao) {
        this.Duracao = Duracao;
    }

    /**
     * @return the QtdHoras
     */
    public int getQtdHoras() {
        return QtdHoras;
    }

    /**
     * @param QtdHoras the QtdHoras to set
     */
    public void setQtdHoras(int QtdHoras) {
        this.QtdHoras = QtdHoras;
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
     * @return the VIP
     */
    public boolean isVIP() {
        return VIP;
    }

    /**
     * @param VIP the VIP to set
     */
    public void setVIP(boolean VIP) {
        this.VIP = VIP;
    }

    /**
     * @return the Prorrogavel
     */
    public boolean isProrrogavel() {
        return Prorrogavel;
    }

    /**
     * @param Prorrogavel the Prorrogavel to set
     */
    public void setProrrogavel(boolean Prorrogavel) {
        this.Prorrogavel = Prorrogavel;
    }

    /**
     * @return the CargaHorMin
     */
    public int getCargaHorMin() {
        return CargaHorMin;
    }

    /**
     * @param CargaHorMin the CargaHorMin to set
     */
    public void setCargaHorMin(int CargaHorMin) {
        this.CargaHorMin = CargaHorMin;
    }

    /**
     * @return the CargaHorMax
     */
    public int getCargaHorMax() {
        return CargaHorMax;
    }

    /**
     * @param CargaHorMax the CargaHorMax to set
     */
    public void setCargaHorMax(int CargaHorMax) {
        this.CargaHorMax = CargaHorMax;
    }

    /**
     * @return the GotIt
     */
    public boolean isGotIt() {
        return GotIt;
    }

    /**
     * @param GotIt the GotIt to set
     */
    public void setGotIt(boolean GotIt) {
        this.GotIt = GotIt;
    }

    /**
     * @return the Idioma
     */
    public String getIdioma() {
        return Idioma;
    }

    /**
     * @param Idioma the Idioma to set
     */
    public void setIdioma(String Idioma) {
        this.Idioma = Idioma;
    }    

    /**
     * @return the Online
     */
    public boolean isOnline() {
        return Online;
    }

    /**
     * @param Online the Online to set
     */
    public void setOnline(boolean Online) {
        this.Online = Online;
    }
}
