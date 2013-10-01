/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package platinesshapi.suppervision.module;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import platinesshapi.Supervision;
import platinesshapi.entity.Alarme;
import platinesshapi.entity.BilanTraitement;
import platinesshapi.entity.CompteurTraitement;
import platinesshapi.entity.EEC;
import platinesshapi.persistence.AlarmesEECDAO;
import platinesshapi.persistence.EECDAO;
import static platinesshapi.suppervision.module.Collecte.LIST_SESS;
import platinesshapi.util.CurrentDate;
import platinesshapi.util.ReadCSVCollecte;
import platinesshapi.util.ReadCSVTraitement;

/**
 *
 * @author h.el-hayouni
 */
public class Traitement {

    public static final String SUPP_BILL_TRAIT_TAB = ". ./.profile; expl_client bil_trait visu -js @ -tab @";
    public static final String SUPP_BILL_TRAIT_EECO_TAB = ". ./.profile; expl_client bil_trait visu -js @ -eeco @ -tab @";
   //public static final String VISU_TYPE_EEC = ". ./.profile; echo 'select type_eec from eec;' | sqlplus db_supe/plasupe ";
    public static final String LIST_SESS = ". ./.profile; expl_client list_sess";
    public static final String DEBIT_DONNEES_IDENTIFIER = ". ./.profile; expl_client visu_cpt -nc VdebEntrTrai ";
    public static final String DEBIT_DC_IDENTIFIER = ". ./.profile; expl_client visu_cpt -nc VdebDcIdenTrai";
    public static final String ARBORESCENCE_EEC = ". ./.profile; expl_client visu_eec | grep fichier | cut -d\\= -f2 | cut -d\\) -f1 | xargs grep  -e ARBO -e IDEN | cut -d\\; -f2";
    private BufferedReader reader;
    private Session session;

    private static Traitement instance;

    public Traitement() {
    }

    public static Traitement getInstance(Session session) {

        if (instance == null) {
            instance = new Traitement();
        }

        instance.session = session;
        return instance;

    }

    /**
     * Méthode de supervision traitement
     *
     * @param jour String: le jour de supervision ex : "17/06/2013"
     * @param tab String: le tableau de supervision à afficher ex : "T1"
     * @return String: fichier csv
     */
    public BilanTraitement supp_bill_trait_tab(String jour, String tab) {
        String reponse = "";
        String nomFichier = "";
        Channel ch = null;
        String cmd = SUPP_BILL_TRAIT_TAB.replaceFirst("@", jour).replaceFirst("@", tab);
        nomFichier = getFileName(ch, cmd);
        reponse = Module.getInstance(session).read_csv(nomFichier, 2);
        
        BilanTraitement bill= ReadCSVTraitement.format_csv_bill_trait_tab(reponse);
        bill=visu_debit_donnes_identifier_trait(bill);
        return visu_debit_dc_identifier_trait(bill);
    }

    /**
     * Méthode de supervision traitement
     *
     * @param jour String: le jour de supervision ex : "17/06/2013"
     * @param nomEEC String: le nom EEC ex : "IN_CCN"
     * @param tab
     * @return String: fichier csv
     */
    public BilanTraitement supp_bill_tait_eeco_tab(String jour, String nomEEC, String tab) {
        String reponse = "";
        String nomFichier = "";
        Channel ch = null;
        String cmd = SUPP_BILL_TRAIT_EECO_TAB.replaceFirst("@", jour).replaceFirst("@", nomEEC).replaceFirst("@", tab);
        nomFichier = getFileName(ch, cmd);
        reponse = Module.getInstance(session).read_csv(nomFichier, 3);
        return ReadCSVTraitement.format_csv_bill_trait_neec(reponse);
    }
        public BilanTraitement visu_debit_donnes_identifier_trait( BilanTraitement bill) {
        String reponse = "";
        String nomFichier = "";
        Channel ch = null;
        nomFichier = getFileName(ch, DEBIT_DONNEES_IDENTIFIER);
        reponse = Module.getInstance(session).read_csv(nomFichier, 1);
        
        bill.setDebitDonneesIdentifier(ReadCSVTraitement.format_csv_debit_donnees_identifier_trait(reponse).getDebitDonneesIdentifier());
        return bill;
        //return ReadCSVTraitement.format_csv_debit_donnees_identifier_trait(reponse);
    }

    public BilanTraitement visu_debit_dc_identifier_trait(BilanTraitement bill) {
        String reponse = "";
        String nomFichier = "";
        Channel ch = null;
        nomFichier = getFileName(ch, DEBIT_DC_IDENTIFIER);
        reponse = Module.getInstance(session).read_csv(nomFichier, 1);
        
        bill.setDebitDCIdentifier(ReadCSVTraitement.format_csv_debit_dc_identifier_trait(reponse).getDebitDCIdentifier());
return bill;
// return ReadCSVTraitement.format_csv_debit_dc_identifier_trait(reponse);
    }

    
       /**
     * Méthode lister les sessions
     *
     * @param null
     * @return String: fichier csv
     */
    public List<EEC> list_eec() {
        Channel ch = null;
        String reponse = "";
        String nomFichier = "";
        nomFichier = getFileName(ch, LIST_SESS);
        reponse = Module.getInstance(session).read_csv(nomFichier, 1);
        
        List<EEC> listeEEC = ReadCSVCollecte.format_csv_list_session(reponse);
        //List<EEC> listeEEC =new ArrayList<EEC>();
  
        /**
         * Pour chaque eec, aller chercher son billan de traitement du jour
         */
        for(int i=0;i<listeEEC.size();i++)
        {
            listeEEC.get(i).setBilanTrait(supp_bill_tait_eeco_tab(CurrentDate.getCurrentDate(),listeEEC.get(i).getEec(), "T3"));
            
        }    

        for (EEC eec : listeEEC) {
            eec.setCompteurs(supp_tait_list_cas_article(CurrentDate.getCurrentDate(), eec, "T4"));
            eec.setBilanRequete(EECDAO.getInstance().getBilanRequeteTraitement(eec));
        }
        
        return listeEEC;
    }
    
     /**
     * Méthode de supervision traitement
     *
     * @param jour String: le jour de supervision ex : "17/06/2013"
     * @param eecs
     * @param tab
     * @return Une liste des cas d'articles traitement
     */
    
    public List<CompteurTraitement> supp_tait_list_cas_article(String jour, EEC eecs, String tab) {
        
        String reponse = "";
        String nomFichier = "";
        Channel ch = null;

        String cmd = SUPP_BILL_TRAIT_EECO_TAB.replaceFirst("@", jour).replaceFirst("@", eecs.getEec()).replaceFirst("@", tab);
        nomFichier = getFileName(ch, cmd);
        reponse = Module.getInstance(session).read_csv(nomFichier, 3);
        return ReadCSVTraitement.format_csv_list_cas_article(reponse, eecs);
    }
    
    private String getFileName(Channel ch, String commande) {
        String nomFichier = null;
        try {
            ch = this.session.openChannel("exec");
        } catch (JSchException ex) {
            Logger.getLogger(Supervision.class.getName()).log(Level.SEVERE, null, ex);
        }
        ((ChannelExec) ch).setCommand(commande);
        try {
            ch.connect();

            reader = new BufferedReader(new InputStreamReader(ch.getInputStream()));
            String line = null;
            while ((line = reader.readLine()) != null) {

                if (line.contains(".csv")) {
                    nomFichier = line.substring(line.indexOf("=") + 1, line.indexOf(")"));
                }
            }
        } catch (JSchException ex) {
            Logger.getLogger(Supervision.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Supervision.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            reader.close();
        } catch (IOException ex) {
            Logger.getLogger(Supervision.class.getName()).log(Level.SEVERE, null, ex);
        }
        ch.disconnect();
        return nomFichier;
    }

}
