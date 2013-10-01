/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package platinesshapi;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import platinesshapi.entity.Alarme;
import platinesshapi.entity.AlarmesActives;
import platinesshapi.entity.ArboECC;
import platinesshapi.entity.ArboEEC;

import platinesshapi.entity.BilanSuppervision;
import platinesshapi.entity.BilanCollecte;
import platinesshapi.entity.BilanDistribution;
import platinesshapi.entity.BilanInterface;
import platinesshapi.entity.BilanTraitement;
import platinesshapi.entity.EEC;
import platinesshapi.entity.ArticleDistribution;
import platinesshapi.entity.BilanRequete;
import platinesshapi.entity.ECC;
import platinesshapi.persistence.AlarmesEECDAO;

import platinesshapi.suppervision.module.Platine;
import platinesshapi.suppervision.module.Collecte;
import platinesshapi.suppervision.module.Traitement;
import platinesshapi.suppervision.module.Recyclage;
import platinesshapi.suppervision.module.Correlation;
import platinesshapi.suppervision.module.Distribution;
import platinesshapi.suppervision.module.Interfaces;


/**
 *
 * @author h.el-hayouni
 */
public class Supervision {

    private Session session;

    public Supervision(Session ch) {
        this.session = ch;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }
    
    

   public AlarmesActives sup_alarme() {
        return Platine.getInstance(session).supp_alarmeActive();
    }
 //Alarme Total
    public List<Alarme> getAllAlarmes(int limit) {
        return AlarmesEECDAO.getInstance().getAllAlarme(limit);
    }
    
    public int getCountAllAlamres()
    {
        return AlarmesEECDAO.getInstance().getCountAllAlarmes();
    }
    public List<Alarme> getAllAlarmesPerPage(int page, int limit){
        return AlarmesEECDAO.getInstance().getAllAlarmesPerPage(page, limit);
    }
    
   //Alarme par module 
    
    public List<Alarme> getAlarmesPerPage(String module, int page, int limit){
        return AlarmesEECDAO.getInstance().getAlarmesPerPage(module, page, limit);
    }
    
    public int getCountAlamresByModule(String module)
    {
        return AlarmesEECDAO.getInstance().getCountAlarmesByModule(module);
    }
    
    
    public List<Alarme> getAlarmesByModule(String module,int limit){
        return AlarmesEECDAO.getInstance().getAlarmesByModule(module, limit);
    }

    /**
     * Méthode de supervision Platine
     *
     * @param jour String: le jour de supervision ex : "17/06/2013"
     * @return String: fichier csv
     */
    public BilanSuppervision sup_platine(String jour) {
        return Platine.getInstance(session).sup_platine(jour);
    }

    /**
     * Méthode de supervision EEC
     *
     * @param jour String: le jour de supervision ex : "17/06/2013"
     * @param tableau
     * @return String: fichier csv
     */
//    public BilanCollecte supp_eec(String jour) {
//        return Collecte.getInstance(session).supp_eec(jour);
//    }
    
     public BilanCollecte supp_bill_coll(String jour, String tableau) {
        return Collecte.getInstance(session).supp_bill_coll(jour, tableau);
    }

    /**
     * Méthode lister les sessions
     *
     * @return List des session eec
     */
    public List<EEC> list_sess_coll() {
        return Collecte.getInstance(session).list_sess();
    }

    /**
     * Méthode lister les eec
     *
     * @return List des eec
     */
    public List<EEC> list_eec_trait() {
        return Traitement.getInstance(session).list_eec();
    }

    /**
     * Méthode lister les eec
     *
     * @return List des eec
     */
    public List<EEC> list_eec_dist() {
        return Distribution.getInstance(session).list_eec();
    }
    
     /**
     * Méthode lister les ecc
     *
     * @return List des eec
     */
    public List<ECC> list_ecc_iecc() {
        return Interfaces.getInstance(session).list_ecc_interface();
    }

    /**
     * Méthode de supervision traitement
     *
     * @param jour String: le jour de supervision ex : "17/06/2013"
     * @param tab String: le tableau de supervision à afficher ex : "T1"
     * @return String: fichier csv
     */
    public BilanTraitement supp_bill_trait(String jour, String tab) {
        return Traitement.getInstance(session).supp_bill_trait_tab(jour, tab);
    }

    /**
     * Méthode de supervision traitement
     *
     * @param jour String: le jour de supervision ex : "17/06/2013"
     * @param nomEEC String: le nom EEC ex : "IN_CCN"
     * @param tab
     * @return String: fichier csv (total de IN_CCN)
     */
    public BilanTraitement supp_bill_tait_eeco(String jour, String nomEEC, String tab) {
        return Traitement.getInstance(session).supp_bill_tait_eeco_tab(jour, nomEEC, tab);
    }

    /**
     * Méthode de supervision Recyclage
     *
     * @param jour String: le jour de supervision ex : "17/06/2013"
     * @param tab String: le tableau de supervision à afficher ex : "T1"
     * @return String: fichier csv
     */
    public String supp_recy(String jour, String tab) {
        return Recyclage.getInstance(session).supp_recy(jour, tab);
    }

    /**
     * Méthode de supervision recyclage
     *
     * @param jour String: le jour de supervision ex : "17/06/2013"
     * @param nomEEC String: le nom EEC ex : "IN_CCN"
     * @return String: fichier csv
     */
    public String supp_recy_eeco(String jour, String nomEEC) {
        return Recyclage.getInstance(session).supp_recy_eeco(jour, nomEEC);
    }

    /**
     * Méthode de supervision Correlation
     *
     * @param jour String: le jour de supervision ex : "17/06/2013"
     * @param tab String: le tableau de supervision à afficher ex : "T1"
     * @return String: fichier csv
     */
    public String supp_corr(String jour, String tab) {
        return Correlation.getInstance(session).supp_corr(jour, tab);
    }

    /**
     * Méthode de supervision distribution
     *
     * @param jour String: le jour de supervision ex : "17/06/2013"
     * @param tab String: le tableau de supervision à afficher ex : "T1"
     * @return String: fichier csv
     */
    public BilanDistribution supp_bill_dist(String jour, String tab) {
        return Distribution.getInstance(session).supp_bill_dist_tab(jour, tab);
    }
    
  /**
     * Méthode de supervision IECC
     *
     * @param jour String: le jour de supervision ex : "17/06/2013"
     * @param tab
     * @return String: fichier csv
     */
    public BilanInterface supp_bill_iecc(String jour,String tab) {
        return Interfaces.getInstance(session).supp_bill_iecc_tab(jour,tab);

    }
   ///////////////// a continuer  pour le reste cpt

    public ArboEEC getArborescenceEEC(String racine) {
        return Collecte.getInstance(session).getArborescenceEEC(racine);
    }

    public ArboECC getArborescenceECC(String racine) {
        return Interfaces.getInstance(session).getArborescenceECC(racine);
    }

    public void disconnect() {
        if (this.session.isConnected()) {
            this.session.disconnect();
        }
    }

}
