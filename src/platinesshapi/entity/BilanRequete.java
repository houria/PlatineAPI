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
public class BilanRequete {
    
    private String nomMachine;
    private String couple;
    private String protocole;
    private String typeEEC;
    private String libelle;
    private String typeTraitement;
    private String etatEchange;
    private String etatObjet;
    private String idECC;
    
    private String nomLot;
    private String etatLot;
    private String indCreation;
    private String dateDebLot;
    private String dateFinLot;
    private String datefermPre;
    private String datefermEff;
    private String dateClassement;
    private String nbreDC;
    private String dateFinEmission;
   
   
    
    
    
    

    public BilanRequete() {
    }

    public BilanRequete(String nomMachine, String couple, String protocole, String typeEEC, String libelle, String typeTraitement, String etatEchange, String etatObjet, String idECC, String nomLot, String etatLot, String indCreation, String dateDebLot, String dateFinLot, String datefermPre, String datefermEff, String dateClassement, String nbreDC, String dateFinEmission) {
        this.nomMachine = nomMachine;
        this.couple = couple;
        this.protocole = protocole;
        this.typeEEC = typeEEC;
        this.libelle = libelle;
        this.typeTraitement = typeTraitement;
        this.etatEchange = etatEchange;
        this.etatObjet = etatObjet;
        this.idECC = idECC;
        this.nomLot = nomLot;
        this.etatLot = etatLot;
        this.indCreation = indCreation;
        this.dateDebLot = dateDebLot;
        this.dateFinLot = dateFinLot;
        this.datefermPre = datefermPre;
        this.datefermEff = datefermEff;
        this.dateClassement = dateClassement;
        this.nbreDC = nbreDC;
        this.dateFinEmission = dateFinEmission;
    }

    
    

    public String getTypeTraitement() {
        return typeTraitement;
    }

    public void setTypeTraitement(String typeTraitement) {
        this.typeTraitement = typeTraitement;
    }

   

    
    
     public String getNomMachine() {
        return nomMachine;
    }

    public void setNomMachine(String nomMachine) {
        this.nomMachine = nomMachine;
    }

    public String getCouple() {
        return couple;
    }

    public void setCouple(String couple) {
        this.couple = couple;
    }

    public String getProtocole() {
        return protocole;
    }

    public void setProtocole(String protocole) {
        this.protocole = protocole;
    }

    public String getTypeEEC() {
        return typeEEC;
    }

    public void setTypeEEC(String typeEEC) {
        this.typeEEC = typeEEC;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getEtatEchange() {
        return etatEchange;
    }

    public void setEtatEchange(String etatEchange) {
        this.etatEchange = etatEchange;
    }

    public String getEtatObjet() {
        return etatObjet;
    }

    public void setEtatObjet(String etatObjet) {
        this.etatObjet = etatObjet;
    }

    public String getIdECC() {
        return idECC;
    }

    public void setIdECC(String idECC) {
        this.idECC = idECC;
    }

    public String getNomLot() {
        return nomLot;
    }

    public void setNomLot(String nomLot) {
        this.nomLot = nomLot;
    }

    public String getEtatLot() {
        return etatLot;
    }

    public void setEtatLot(String etatLot) {
        this.etatLot = etatLot;
    }

    public String getIndCreation() {
        return indCreation;
    }

    public void setIndCreation(String indCreation) {
        this.indCreation = indCreation;
    }

    public String getDateDebLot() {
        return dateDebLot;
    }

    public void setDateDebLot(String dateDebLot) {
        this.dateDebLot = dateDebLot;
    }

    public String getDateFinLot() {
        return dateFinLot;
    }

    public void setDateFinLot(String dateFinLot) {
        this.dateFinLot = dateFinLot;
    }

    public String getDatefermPre() {
        return datefermPre;
    }

    public void setDatefermPre(String datefermPre) {
        this.datefermPre = datefermPre;
    }

    public String getDatefermEff() {
        return datefermEff;
    }

    public void setDatefermEff(String datefermEff) {
        this.datefermEff = datefermEff;
    }

    public String getDateClassement() {
        return dateClassement;
    }

    public void setDateClassement(String dateClassement) {
        this.dateClassement = dateClassement;
    }

    public String getNbreDC() {
        return nbreDC;
    }

    public void setNbreDC(String nbreDC) {
        this.nbreDC = nbreDC;
    }

    public String getDateFinEmission() {
        return dateFinEmission;
    }

    public void setDateFinEmission(String dateFinEmission) {
        this.dateFinEmission = dateFinEmission;
    }

    @Override
    public String toString() {
        return "BilanRequete{" + "nomMachine=" + nomMachine + ", couple=" + couple + ", protocole=" + protocole + ", typeEEC=" + typeEEC + ", libelle=" + libelle + ", typeTraitement=" + typeTraitement + ", etatEchange=" + etatEchange + ", etatObjet=" + etatObjet + ", idECC=" + idECC + ", nomLot=" + nomLot + ", etatLot=" + etatLot + ", indCreation=" + indCreation + ", dateDebLot=" + dateDebLot + ", dateFinLot=" + dateFinLot + ", datefermPre=" + datefermPre + ", datefermEff=" + datefermEff + ", dateClassement=" + dateClassement + ", nbreDC=" + nbreDC + ", dateFinEmission=" + dateFinEmission + '}';
    }
    
    

    
    
    
    

  
    
    
    
    
}
