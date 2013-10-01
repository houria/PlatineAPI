/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package platinesshapi.entity;

/**
 *
 * @author h.el-hayouni
 */
public class BilanCollecte {
    
    private int blocRecus;
    private int blocRejetes;
    private int blocPerdus;
    private int fichierRecus;
    private int fichierRejetes;
    
    private int blocVersTrait;
    private int blocVersArch;
    private int fichierVersTrait;
    private int fichierVersArch;
   
   
    private int totalFichierRecus;
    private int totalBlocRecus;
    private int volumeRecus;
    
    private String nomMachine;
    private String etatmachine;


    public BilanCollecte() {
    }

    public BilanCollecte(int blocRecus, int blocRejetes, int blocPerdus, int fichierRecus, int fichierRejetes, int blocVersTrait, int blocVersArch, int fichierVersTrait, int fichierVersArch,String nomMachine, int totalFichierRecus, int totalBlocRecus, int volumeRecus, String etatmachine) {
        this.blocRecus = blocRecus;
        this.blocRejetes = blocRejetes;
        this.blocPerdus = blocPerdus;
        this.fichierRecus = fichierRecus;
        this.fichierRejetes = fichierRejetes;
        this.blocVersTrait = blocVersTrait;
        this.blocVersArch = blocVersArch;
        this.fichierVersTrait = fichierVersTrait;
        this.fichierVersArch = fichierVersArch;
        
        this.nomMachine = nomMachine;
        this.totalFichierRecus = totalFichierRecus;
        this.totalBlocRecus = totalBlocRecus;
        this.volumeRecus = volumeRecus;
        this.etatmachine = etatmachine;
        
    }

    public int getBlocRecus() {
        return blocRecus;
    }

    public void setBlocRecus(int blocRecus) {
        this.blocRecus = blocRecus;
    }

    public int getBlocRejetes() {
        return blocRejetes;
    }

    public void setBlocRejetes(int blocRejetes) {
        this.blocRejetes = blocRejetes;
    }

    public int getBlocPerdus() {
        return blocPerdus;
    }

    public void setBlocPerdus(int blocPerdus) {
        this.blocPerdus = blocPerdus;
    }

    public int getFichierRecus() {
        return fichierRecus;
    }

    public void setFichierRecus(int fichierRecus) {
        this.fichierRecus = fichierRecus;
    }

    public int getFichierRejetes() {
        return fichierRejetes;
    }

    public void setFichierRejetes(int fichierRejetes) {
        this.fichierRejetes = fichierRejetes;
    }

    public int getBlocVersTrait() {
        return blocVersTrait;
    }

    public void setBlocVersTrait(int blocVersTrait) {
        this.blocVersTrait = blocVersTrait;
    }

    public int getBlocVersArch() {
        return blocVersArch;
    }

    public void setBlocVersArch(int blocVersArch) {
        this.blocVersArch = blocVersArch;
    }

    public int getFichierVersTrait() {
        return fichierVersTrait;
    }

    public void setFichierVersTrait(int fichierVersTrait) {
        this.fichierVersTrait = fichierVersTrait;
    }

    public int getFichierVersArch() {
        return fichierVersArch;
    }

    public void setFichierVersArch(int fichierVersArch) {
        this.fichierVersArch = fichierVersArch;
    }

    public String getNomMachine() {
        return nomMachine;
    }

    public void setNomMachine(String nomMachine) {
        this.nomMachine = nomMachine;
    }



    public int getTotalFichierRecus() {
        return totalFichierRecus;
    }

    public void setTotalFichierRecus(int totalFichierRecus) {
        this.totalFichierRecus = totalFichierRecus;
    }

    public int getTotalBlocRecus() {
        return totalBlocRecus;
    }

    public void setTotalBlocRecus(int totalBlocRecus) {
        this.totalBlocRecus = totalBlocRecus;
    }

    public int getVolumeRecus() {
        return volumeRecus;
    }

    public void setVolumeRecus(int volumeRecus) {
        this.volumeRecus = volumeRecus;
    }

    public String getEtatmachine() {
        return etatmachine;
    }

    public void setEtatmachine(String etatmachine) {
        this.etatmachine = etatmachine;
    }

    @Override
    public String toString() {
        return "BilanCollecte{" + "blocRecus=" + blocRecus + ", blocRejetes=" + blocRejetes + ", blocPerdus=" + blocPerdus + ", fichierRecus=" + fichierRecus + ", fichierRejetes=" + fichierRejetes + ", blocVersTrait=" + blocVersTrait + ", blocVersArch=" + blocVersArch + ", fichierVersTrait=" + fichierVersTrait + ", fichierVersArch=" + fichierVersArch + ", nomMachine=" + nomMachine + ", totalFichierRecus=" + totalFichierRecus + ", totalBlocRecus=" + totalBlocRecus + ", volumeRecus=" + volumeRecus + ", etatmachine=" + etatmachine + '}';
    }

    

    
    
    
    

    

    
}
