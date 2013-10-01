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
public class EEC {

    private String protocole, eec, session, etatLiaisonNormal, etatLisaisonSecours, etatSession;
    private int debit, tauxFichRejete, blocEmisTrait, doublons;

    private List<CompteurTraitement> compteurs;
    private List<CompteurDistribution> compteursDist;
    
    

    private int sortieDistCasArt;

    private BilanCollecte bilanColl;
    private BilanTraitement bilanTrait;
    private BilanDistribution bilanDist;
    private BilanRequete bilanRequete;

    public EEC(String protocole, String eec, String session, String etatLiaisonNormal, String etatLisaisonSecours, String etatSession, int debit, int tauxFichRejete, int blocEmisTrait, int doublons, List<CompteurTraitement> compteurs,List<CompteurDistribution> compteursDist, String CasArticleDist, int interfaceECCCasArt, int recyclageCasArt, int correlationCasArt, int redistributionCasArt, int erronesCasArt, int eliminesCasArt, int diversCasArt, int sortieDistCasArt, BilanCollecte bilanColl, BilanTraitement bilanTrait, BilanDistribution bilanDist,BilanRequete bilanRequete) {
        this.protocole = protocole;
        this.eec = eec;
        this.session = session;
        this.etatLiaisonNormal = etatLiaisonNormal;
        this.etatLisaisonSecours = etatLisaisonSecours;
        this.etatSession = etatSession;
        this.debit = debit;
        this.tauxFichRejete = tauxFichRejete;
        this.blocEmisTrait = blocEmisTrait;
        this.doublons = doublons;
        this.compteurs = compteurs;
        this.compteursDist=compteursDist;
        this.sortieDistCasArt = sortieDistCasArt;
        this.bilanColl = bilanColl;
        this.bilanTrait = bilanTrait;
        this.bilanDist = bilanDist;
        this.bilanRequete=bilanRequete;
    }

    public EEC() {
    }

    public EEC(String eec) {
        this.eec = eec;
    }

    public String getProtocole() {
        return protocole;
    }

    public void setProtocole(String protocole) {
        this.protocole = protocole;
    }

    public String getEec() {
        return eec;
    }

    public void setEec(String eec) {
        this.eec = eec;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public String getEtatLiaisonNormal() {
        return etatLiaisonNormal;
    }

    public void setEtatLiaisonNormal(String etatLiaisonNormal) {
        this.etatLiaisonNormal = etatLiaisonNormal;
    }

    public String getEtatLisaisonSecours() {
        return etatLisaisonSecours;
    }

    public void setEtatLisaisonSecours(String etatLisaisonSecours) {
        this.etatLisaisonSecours = etatLisaisonSecours;
    }

    public String getEtatSession() {
        return etatSession;
    }

    public void setEtatSession(String etatSession) {
        this.etatSession = etatSession;
    }

    public int getDebit() {
        return debit;
    }

    public void setDebit(int debit) {
        this.debit = debit;
    }

    public int getTauxFichRejete() {
        return tauxFichRejete;
    }

    public void setTauxFichRejete(int tauxFichRejete) {
        this.tauxFichRejete = tauxFichRejete;
    }

    public int getBlocEmisTrait() {
        return blocEmisTrait;
    }

    public void setBlocEmisTrait(int blocEmisTrait) {
        this.blocEmisTrait = blocEmisTrait;
    }

    public int getDoublons() {
        return doublons;
    }

    public void setDoublons(int doublons) {
        this.doublons = doublons;
    }

    public List<CompteurTraitement> getCompteurs() {
        return compteurs;
    }

    public void setCompteurs(List<CompteurTraitement> compteurs) {
        this.compteurs = compteurs;
    }

    public List<CompteurDistribution> getCompteursDist() {
        return compteursDist;
    }

    public void setCompteursDist(List<CompteurDistribution> compteursDist) {
        this.compteursDist = compteursDist;
    }
    
    

    public int getSortieDistCasArt() {
        return sortieDistCasArt;
    }

    public void setSortieDistCasArt(int sortieDistCasArt) {
        this.sortieDistCasArt = sortieDistCasArt;
    }

    public BilanCollecte getBilanColl() {
        return bilanColl;
    }

    public void setBilanColl(BilanCollecte bilanColl) {
        this.bilanColl = bilanColl;
    }

  

    public BilanTraitement getBilanTrait() {
        return bilanTrait;
    }

    public void setBilanTrait(BilanTraitement bilanTrait) {
        this.bilanTrait = bilanTrait;
    }

    public BilanDistribution getBilanDist() {
        return bilanDist;
    }

    public void setBilanDist(BilanDistribution bilanDist) {
        this.bilanDist = bilanDist;
    }

    public BilanRequete getBilanRequete() {
        return bilanRequete;
    }

    public void setBilanRequete(BilanRequete bilanRequete) {
        this.bilanRequete = bilanRequete;
    }

    @Override
    public String toString() {
        return "EEC{" + "protocole=" + protocole + ", eec=" + eec + ", session=" + session + ", etatLiaisonNormal=" + etatLiaisonNormal + ", etatLisaisonSecours=" + etatLisaisonSecours + ", etatSession=" + etatSession + ", debit=" + debit + ", tauxFichRejete=" + tauxFichRejete + ", blocEmisTrait=" + blocEmisTrait + ", doublons=" + doublons + ", compteurs=" + compteurs + ", compteursDist=" + compteursDist + ", sortieDistCasArt=" + sortieDistCasArt + ", bilanColl=" + bilanColl + ", bilanTrait=" + bilanTrait + ", bilanDist=" + bilanDist + ", bilanRequete=" + bilanRequete + '}';
    }

  

  
    
    
    

   
   

}
