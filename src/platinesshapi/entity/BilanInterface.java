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
public class BilanInterface {

    private int total;
    private int eccFilEau;
    private int eccLot;
    private int liaisonHS;
    private int volumeEntrant;
    private int volumeSortant;
    private int dcEntrant;
    private int dcSortant;

    private int nbFichierSortant;
    private int nbMessageSortant;
    private int nbDcDetruit;
    private int nbDCInattendu;

    private int nbDcHorsDelai;

    private int debitGDonneeEntrant;
    private int debitGDonneeSortant;
    private int debitGDCEntrant;
    private int debitGDCSortant;
    
    private int nbLotNonFermeGlobal;
    private int nbLotFermeNonEnvoyeGlobal;

    public BilanInterface() {
    }

    public BilanInterface(int total, int eccFilEau, int eccLot, int liaisonHS, int volumeEntrant, int volumeSortant, int dcEntrant, int dcSortant, int nbFichierSortant, int nbMessageSortant, int nbDcDetruit, int nbDCInattendu, int nbDcHorsDelai, int debitGDonneeEntrant, int debitGDonneeSortant, int debitGDCEntrant, int debitGDCSortant, int nbLotNonFermeGlobal, int nbLotFermeNonEnvoyeGlobal) {
        this.total = total;
        this.eccFilEau = eccFilEau;
        this.eccLot = eccLot;
        this.liaisonHS = liaisonHS;
        this.volumeEntrant = volumeEntrant;
        this.volumeSortant = volumeSortant;
        this.dcEntrant = dcEntrant;
        this.dcSortant = dcSortant;
        this.nbFichierSortant = nbFichierSortant;
        this.nbMessageSortant = nbMessageSortant;
        this.nbDcDetruit = nbDcDetruit;
        this.nbDCInattendu = nbDCInattendu;
        this.nbDcHorsDelai = nbDcHorsDelai;
        this.debitGDonneeEntrant = debitGDonneeEntrant;
        this.debitGDonneeSortant = debitGDonneeSortant;
        this.debitGDCEntrant = debitGDCEntrant;
        this.debitGDCSortant = debitGDCSortant;
        this.nbLotNonFermeGlobal = nbLotNonFermeGlobal;
        this.nbLotFermeNonEnvoyeGlobal = nbLotFermeNonEnvoyeGlobal;
    }

   

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getEccFilEau() {
        return eccFilEau;
    }

    public void setEccFilEau(int eccFilEau) {
        this.eccFilEau = eccFilEau;
    }

    public int getEccLot() {
        return eccLot;
    }

    public void setEccLot(int eccLot) {
        this.eccLot = eccLot;
    }

    public int getLiaisonHS() {
        return liaisonHS;
    }

    public void setLiaisonHS(int liaisonHS) {
        this.liaisonHS = liaisonHS;
    }

    public int getVolumeEntrant() {
        return volumeEntrant;
    }

    public void setVolumeEntrant(int volumeEntrant) {
        this.volumeEntrant = volumeEntrant;
    }

    public int getVolumeSortant() {
        return volumeSortant;
    }

    public void setVolumeSortant(int volumeSortant) {
        this.volumeSortant = volumeSortant;
    }

    public int getDcEntrant() {
        return dcEntrant;
    }

    public void setDcEntrant(int dcEntrant) {
        this.dcEntrant = dcEntrant;
    }

    public int getDcSortant() {
        return dcSortant;
    }

    public void setDcSortant(int dcSortant) {
        this.dcSortant = dcSortant;
    }

    public int getNbFichierSortant() {
        return nbFichierSortant;
    }

    public void setNbFichierSortant(int nbFichierSortant) {
        this.nbFichierSortant = nbFichierSortant;
    }

    public int getNbMessageSortant() {
        return nbMessageSortant;
    }

    public void setNbMessageSortant(int nbMessageSortant) {
        this.nbMessageSortant = nbMessageSortant;
    }

    public int getNbDcDetruit() {
        return nbDcDetruit;
    }

    public void setNbDcDetruit(int nbDcDetruit) {
        this.nbDcDetruit = nbDcDetruit;
    }

    public int getNbDCInattendu() {
        return nbDCInattendu;
    }

    public void setNbDCInattendu(int nbDCInattendu) {
        this.nbDCInattendu = nbDCInattendu;
    }

    public int getNbDcHorsDelai() {
        return nbDcHorsDelai;
    }

    public void setNbDcHorsDelai(int nbDcHorsDelai) {
        this.nbDcHorsDelai = nbDcHorsDelai;
    }

    public int getDebitGDonneeEntrant() {
        return debitGDonneeEntrant;
    }

    public void setDebitGDonneeEntrant(int debitGDonneeEntrant) {
        this.debitGDonneeEntrant = debitGDonneeEntrant;
    }

    public int getDebitGDonneeSortant() {
        return debitGDonneeSortant;
    }

    public void setDebitGDonneeSortant(int debitGDonneeSortant) {
        this.debitGDonneeSortant = debitGDonneeSortant;
    }

    public int getDebitGDCEntrant() {
        return debitGDCEntrant;
    }

    public void setDebitGDCEntrant(int debitGDCEntrant) {
        this.debitGDCEntrant = debitGDCEntrant;
    }

    public int getDebitGDCSortant() {
        return debitGDCSortant;
    }

    public void setDebitGDCSortant(int debitGDCSortant) {
        this.debitGDCSortant = debitGDCSortant;
    }

    public int getNbLotNonFermeGlobal() {
        return nbLotNonFermeGlobal;
    }

    public void setNbLotNonFermeGlobal(int nbLotNonFermeGlobal) {
        this.nbLotNonFermeGlobal = nbLotNonFermeGlobal;
    }

    public int getNbLotFermeNonEnvoyeGlobal() {
        return nbLotFermeNonEnvoyeGlobal;
    }

    public void setNbLotFermeNonEnvoyeGlobal(int nbLotFermeNonEnvoyeGlobal) {
        this.nbLotFermeNonEnvoyeGlobal = nbLotFermeNonEnvoyeGlobal;
    }

    @Override
    public String toString() {
        return "BilanInterface{" + "total=" + total + ", eccFilEau=" + eccFilEau + ", eccLot=" + eccLot + ", liaisonHS=" + liaisonHS + ", volumeEntrant=" + volumeEntrant + ", volumeSortant=" + volumeSortant + ", dcEntrant=" + dcEntrant + ", dcSortant=" + dcSortant + ", nbFichierSortant=" + nbFichierSortant + ", nbMessageSortant=" + nbMessageSortant + ", nbDcDetruit=" + nbDcDetruit + ", nbDCInattendu=" + nbDCInattendu + ", nbDcHorsDelai=" + nbDcHorsDelai + ", debitGDonneeEntrant=" + debitGDonneeEntrant + ", debitGDonneeSortant=" + debitGDonneeSortant + ", debitGDCEntrant=" + debitGDCEntrant + ", debitGDCSortant=" + debitGDCSortant + ", nbLotNonFermeGlobal=" + nbLotNonFermeGlobal + ", nbLotFermeNonEnvoyeGlobal=" + nbLotFermeNonEnvoyeGlobal + '}';
    }

    
}
