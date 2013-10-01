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
public class BilanTraitement {

    
    private int nbBlocIdentifies;
    private int nbBlocRejetés;
    private int nbOctetsRejetes;
    
    private int dcVersDistribution;
    private int dcErronnes;
    private int dcElimines;
    private int dcSortie;
    private int dcDivers;
    
    private int volumeDonneesIndetifies;
    private int nbDCIdentifies;
    
    private int debitDonneesIdentifier;
    private int debitDCIdentifier;
    
    
   
 
   

    public BilanTraitement() {
    }

    public BilanTraitement(int nbBlocIdentifies, int nbBlocRejetés, int nbOctetsRejetes, int dcVersDistribution, int dcErronnes, int dcElimines, int dcSortie, int dcDivers, int volumeDonneesIndetifies, int nbDCIdentifies, int debitDonneesIdentifier, int debitDCIdentifier) {
        this.nbBlocIdentifies = nbBlocIdentifies;
        this.nbBlocRejetés = nbBlocRejetés;
        this.nbOctetsRejetes = nbOctetsRejetes;
        this.dcVersDistribution = dcVersDistribution;
        this.dcErronnes = dcErronnes;
        this.dcElimines = dcElimines;
        this.dcSortie = dcSortie;
        this.dcDivers = dcDivers;
        this.volumeDonneesIndetifies = volumeDonneesIndetifies;
        this.nbDCIdentifies = nbDCIdentifies;
        this.debitDonneesIdentifier = debitDonneesIdentifier;
        this.debitDCIdentifier = debitDCIdentifier;
    }



    

  
   


  

    public int getNbBlocIdentifies() {
        return nbBlocIdentifies;
    }

    public void setNbBlocIdentifies(int nbBlocIdentifies) {
        this.nbBlocIdentifies = nbBlocIdentifies;
    }

    public int getNbBlocRejetés() {
        return nbBlocRejetés;
    }

    public void setNbBlocRejetés(int nbBlocRejetés) {
        this.nbBlocRejetés = nbBlocRejetés;
    }

    public int getNbOctetsRejetes() {
        return nbOctetsRejetes;
    }

    public void setNbOctetsRejetes(int nbOctetsRejetes) {
        this.nbOctetsRejetes = nbOctetsRejetes;
    }

    public int getDcVersDistribution() {
        return dcVersDistribution;
    }

    public void setDcVersDistribution(int dcVersDistribution) {
        this.dcVersDistribution = dcVersDistribution;
    }

    public int getDcErronnes() {
        return dcErronnes;
    }

    public void setDcErronnes(int dcErronnes) {
        this.dcErronnes = dcErronnes;
    }

    public int getDcElimines() {
        return dcElimines;
    }

    public void setDcElimines(int dcElimines) {
        this.dcElimines = dcElimines;
    }

    public int getDcSortie() {
        return dcSortie;
    }

    public void setDcSortie(int dcSortie) {
        this.dcSortie = dcSortie;
    }

    

    public int getDcDivers() {
        return dcDivers;
    }

    public void setDcDivers(int dcDivers) {
        this.dcDivers = dcDivers;
    }



    public int getVolumeDonneesIndetifies() {
        return volumeDonneesIndetifies;
    }

    public void setVolumeDonneesIndetifies(int volumeDonneesIndetifies) {
        this.volumeDonneesIndetifies = volumeDonneesIndetifies;
    }

  

    public int getNbDCIdentifies() {
        return nbDCIdentifies;
    }

    public void setNbDCIdentifies(int nbDCIdentifies) {
        this.nbDCIdentifies = nbDCIdentifies;
    }

    public void setDebitDonneesIdentifier(int debitDonneesIdentifier) {
        this.debitDonneesIdentifier = debitDonneesIdentifier;
    }

    public void setDebitDCIdentifier(int debitDCIdentifier) {
        this.debitDCIdentifier = debitDCIdentifier;
    }

    public int getDebitDonneesIdentifier() {
        return debitDonneesIdentifier;
    }

    public int getDebitDCIdentifier() {
        return debitDCIdentifier;
    }

    @Override
    public String toString() {
        return "BilanTraitement{" + "nbBlocIdentifies=" + nbBlocIdentifies + ", nbBlocRejet\u00e9s=" + nbBlocRejetés + ", nbOctetsRejetes=" + nbOctetsRejetes + ", dcVersDistribution=" + dcVersDistribution + ", dcErronnes=" + dcErronnes + ", dcElimines=" + dcElimines + ", dcSortie=" + dcSortie + ", dcDivers=" + dcDivers + ", volumeDonneesIndetifies=" + volumeDonneesIndetifies + ", nbDCIdentifies=" + nbDCIdentifies + ", debitDonneesIdentifier=" + debitDonneesIdentifier + ", debitDCIdentifier=" + debitDCIdentifier + '}';
    }

    

    






    
  
 



   
    
    
 
  
   
  


    
    

  

    
    
    
}
