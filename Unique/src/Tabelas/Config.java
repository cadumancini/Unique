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
 * @author arlorencini
 */
@Entity
@Table(name="Config")
public class Config implements Serializable{
    @Id
    @GeneratedValue
    private Long ID;
    
    @Column(length=3, nullable=false)
    private Long DescontoMaior;
    
    @Column(length=3, nullable=false)
    private Long DescontoMenor;
    
    @Column(length=2, nullable=false)
    private int DiaLimiteDescontoMaior;
    
    @Column(length=2, nullable=false)
    private int DiaLimiteDescontoMenor;
    
    @Column(nullable=false)
    private boolean DescontoMaiorEnbled;
    
    @Column(nullable=false)
    private boolean DescontoMenorEnbled;

    /**
     * @return the ID
     */
    public Long getID() {
        return ID;
    }

    /**
     * @param ID the ID to set
     */
    public void setID(Long ID) {
        this.ID = ID;
    }

    /**
     * @return the DescontoMaior
     */
    public Long getDescontoMaior() {
        return DescontoMaior;
    }

    /**
     * @param DescontoMaior the DescontoMaior to set
     */
    public void setDescontoMaior(Long DescontoMaior) {
        this.DescontoMaior = DescontoMaior;
    }

    /**
     * @return the DescontoMenor
     */
    public Long getDescontoMenor() {
        return DescontoMenor;
    }

    /**
     * @param DescontoMenor the DescontoMenor to set
     */
    public void setDescontoMenor(Long DescontoMenor) {
        this.DescontoMenor = DescontoMenor;
    }

    /**
     * @return the DiaLimiteDescontoMaior
     */
    public int getDiaLimiteDescontoMaior() {
        return DiaLimiteDescontoMaior;
    }

    /**
     * @param DiaLimiteDescontoMaior the DiaLimiteDescontoMaior to set
     */
    public void setDiaLimiteDescontoMaior(int DiaLimiteDescontoMaior) {
        this.DiaLimiteDescontoMaior = DiaLimiteDescontoMaior;
    }

    /**
     * @return the DiaLimiteDescontoMenor
     */
    public int getDiaLimiteDescontoMenor() {
        return DiaLimiteDescontoMenor;
    }

    /**
     * @param DiaLimiteDescontoMenor the DiaLimiteDescontoMenor to set
     */
    public void setDiaLimiteDescontoMenor(int DiaLimiteDescontoMenor) {
        this.DiaLimiteDescontoMenor = DiaLimiteDescontoMenor;
    }

    /**
     * @return the DescontoMaiorEnbled
     */
    public boolean isDescontoMaiorEnbled() {
        return DescontoMaiorEnbled;
    }

    /**
     * @param DescontoMaiorEnbled the DescontoMaiorEnbled to set
     */
    public void setDescontoMaiorEnbled(boolean DescontoMaiorEnbled) {
        this.DescontoMaiorEnbled = DescontoMaiorEnbled;
    }

    /**
     * @return the DescontoMenorEnbled
     */
    public boolean isDescontoMenorEnbled() {
        return DescontoMenorEnbled;
    }

    /**
     * @param DescontoMenorEnbled the DescontoMenorEnbled to set
     */
    public void setDescontoMenorEnbled(boolean DescontoMenorEnbled) {
        this.DescontoMenorEnbled = DescontoMenorEnbled;
    }
}
