/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package platinesshapi.entity;

import java.util.List;

/**
 *
 * @author h.el-hayouni
 */
public class ECC {

    private int id;
    private String ecc;
    private int debitDonneeEntrant;
    private int debitDonneeSortant;
    private int debitDcEntrant;
    private int debitDcSortant;

    private int nbLotNonFerme;
    private int nbLotFermeNonEnvoye;
    private BilanInterface bilanIECC;
    private BilanRequete bilanRequete;
   
    

    public ECC() {
    }

    public ECC(String ecc) {
        this.ecc = ecc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    

    public ECC(String ecc, int debitDonneeEntrant, int debitDonneeSortant, int debitDcEntrant, int debitDcSortant, int nbLotNonFerme, int nbLotFermeNonEnvoye,BilanInterface bilanIECC,BilanRequete bilanRequete) {
        this.ecc = ecc;
        this.debitDonneeEntrant = debitDonneeEntrant;
        this.debitDonneeSortant = debitDonneeSortant;
        this.debitDcEntrant = debitDcEntrant;
        this.debitDcSortant = debitDcSortant;
        this.nbLotNonFerme = nbLotNonFerme;
        this.nbLotFermeNonEnvoye = nbLotFermeNonEnvoye;
        this.bilanIECC=bilanIECC;
        this.bilanRequete=bilanRequete;
        
    }

    public String getEcc() {
        return ecc;
    }

    public void setEcc(String ecc) {
        this.ecc = ecc;
    }
    
    public BilanInterface getBilanIECC() {
        return bilanIECC;
    }

    public void setBilanIECC(BilanInterface bilanIECC) {
        this.bilanIECC = bilanIECC;
    }
    
    public int getDebitDonneeEntrant() {
        return debitDonneeEntrant;
    }

    public void setDebitDonneeEntrant(int debitDonneeEntrant) {
        this.debitDonneeEntrant = debitDonneeEntrant;
    }

    public int getDebitDonneeSortant() {
        return debitDonneeSortant;
    }

    public void setDebitDonneeSortant(int debitDonneeSortant) {
        this.debitDonneeSortant = debitDonneeSortant;
    }

    public int getDebitDcEntrant() {
        return debitDcEntrant;
    }

    public void setDebitDcEntrant(int debitDcEntrant) {
        this.debitDcEntrant = debitDcEntrant;
    }

    public int getDebitDcSortant() {
        return debitDcSortant;
    }

    public void setDebitDcSortant(int debitDcSortant) {
        this.debitDcSortant = debitDcSortant;
    }

    public int getNbLotNonFerme() {
        return nbLotNonFerme;
    }

    public void setNbLotNonFerme(int nbLotNonFerme) {
        this.nbLotNonFerme = nbLotNonFerme;
    }

    public int getNbLotFermeNonEnvoye() {
        return nbLotFermeNonEnvoye;
    }

    public void setNbLotFermeNonEnvoye(int nbLotFermeNonEnvoye) {
        this.nbLotFermeNonEnvoye = nbLotFermeNonEnvoye;
    }

    public BilanRequete getBilanRequete() {
        return bilanRequete;
    }

    public void setBilanRequete(BilanRequete bilanRequete) {
        this.bilanRequete = bilanRequete;
    }



    @Override
    public String toString() {
        return "ECC{" + "ecc=" + ecc + ", debitDonneeEntrant=" + debitDonneeEntrant + ", debitDonneeSortant=" + debitDonneeSortant + ", debitDcEntrant=" + debitDcEntrant + ", debitDcSortant=" + debitDcSortant + ", nbLotNonFerme=" + nbLotNonFerme + ", nbLotFermeNonEnvoye=" + nbLotFermeNonEnvoye + ", bilanIECC=" + bilanIECC + ", bilanRequete=" + bilanRequete +'}';
    }
    
    

   
    

    

   
}
