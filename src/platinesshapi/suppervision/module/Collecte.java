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
import platinesshapi.entity.ArboEEC;
import platinesshapi.entity.BilanCollecte;
import platinesshapi.entity.EEC;
import platinesshapi.persistence.AlarmesEECDAO;
import platinesshapi.persistence.EECDAO;
import platinesshapi.util.CurrentDate;
import platinesshapi.util.ReadCSVCollecte;

/**
 *
 * @author h.el-hayouni
 */
public class Collecte {

    public static final String SUPP_BILL_COLL_TAB = ". ./.profile; expl_client bil_coll visu -js @ -tab @";
   // public static final String SUPP_EEC_JS_EECO = ". ./.profile; expl_client bil_coll visu -js @ -eeco @";
    public static final String SUPP_BILL_COLL_TAB_EECO = ". ./.profile; expl_client bil_coll visu -js @ -eeco @ -tab @";
    public static final String LIST_SESS = ". ./.profile; expl_client list_sess";

    public static final String LIST_LIAISON = ". ./.profile; expl_client visu_cpt -nc VetaLiaisColl";
    public static final String ETAT_MACHINE = ". ./.profile; expl_client visu_cpt -nc VetaMachColl";

    public static final String BLOCVERSTRAITEMENT = ". ./.profile; expl_client visu_cpt -nc VfragEmisEec";
    public static final String TAUX_FICHIER_DOUBLONS = ". ./.profile; expl_client visu_cpt -nc VtficDoubColl";
    public static final String TAUX_FICHIER_REJETE = ". ./.profile; expl_client visu_cpt -nc VtFicRejColl";
    public static final String DONNEES_RECUE_DEBIT = ". ./.profile; expl_client visu_cpt -nc VdebEecColl";

    public static final String ARBORESCENCE_EEC = ". ./.profile; expl_client visu_eec | grep fichier | cut -d\\= -f2 | cut -d\\) -f1 | xargs grep  -e ARBO -e IDEN | cut -d\\; -f2";
    //String module = "platine"; 
    
    
    private BufferedReader reader;
    private Session session;

    private static Collecte instance;

    public Collecte() {
    }

    public static Collecte getInstance(Session session) {
        if (instance == null) {
            instance = new Collecte();
        }

        instance.session = session;
        return instance;
    }

    /**
     * Méthode de supervision EEC
     *
     * @param jour String: le jour de supervision ex : "17/06/2013"
     * @return String: fichier csv
     */
//    public BilanCollecte supp_eec(String jour) {
//        String reponse = "";
//        String nomFichier = "";
//        Channel ch = null;
//        nomFichier = getFileName(ch,SUPP_EEC + " " + jour);
//        reponse = Module.getInstance(session).read_csv(nomFichier, 2);
//        //return visu_etat_machine_collecte(ReadCSVCollecte.format_csv_bill_coll(reponse));
//         return ReadCSVCollecte.format_csv_bill_coll(reponse);
//    }

    /**
     * Méthode de supervision EEC
     *
     * @param jour String: le jour de supervision ex : "17/06/2013"
     * @param tab
     * @param nomEEC String: le nom de EEC ex : "IN_CCN"
     * @return String: fichier csv
     */
    public BilanCollecte supp_bill_coll(String jour, String tableau) {
        String reponse = "";
        String nomFichier = "";
        Channel ch = null;
        String cmd = SUPP_BILL_COLL_TAB.replaceFirst("@", jour).replaceFirst("@", tableau);
        nomFichier = getFileName(ch, cmd);
        reponse = Module.getInstance(session).read_csv(nomFichier, 2);
        return visu_etat_machine_collecte(ReadCSVCollecte.format_csv_bill_coll(reponse));
    }

    /**
     * Méthode de supervision EEC
     *
     * @param jour String: le jour de supervision ex : "17/06/2
     * @param tableau"
     * @param nomEEC String: le nom de EEC ex : "IN_CCN"
     * @return String: fichier csv
     */
    public BilanCollecte supp_eec_js_tab_eeco(String jour, String nomEEC, String tableau) {
        String reponse = "";
        String nomFichier = "";
        Channel ch = null;
        String cmd = SUPP_BILL_COLL_TAB_EECO.replaceFirst("@", jour).replaceFirst("@", nomEEC).replaceFirst("@", tableau);
        nomFichier = getFileName(ch, cmd);
        reponse = Module.getInstance(session).read_csv(nomFichier, 3);
        return ReadCSVCollecte.format_csv_bill_coll_eeco(reponse);
    }

    /**
     * Méthode lister les sessions
     *
     * @param null
     * @return String: fichier csv
     */
    public List<EEC> list_sess() {
        Channel ch = null;
        String reponse = "";
        String nomFichier = "";
        nomFichier = getFileName(ch, LIST_SESS);
        //nomFichier = "list_sess";
        reponse = Module.getInstance(session).read_csv(nomFichier, 1);
        
        List<EEC> listeEEC = ReadCSVCollecte.format_csv_list_session(reponse);
        
        listeEEC = visu_debit_eec(listeEEC);
        
        listeEEC = visu_blos_emis_vers_trait(listeEEC);
        
        listeEEC = visu_taux_fichier_rejete_eec(listeEEC);
        
        listeEEC = visu_taux_doublons_eec(listeEEC);
        
        /**
         * Pour chaque eec, aller chercher son billan de collect du jour
         */
        for(int i=0;i<listeEEC.size();i++)
        {
            listeEEC.get(i).setBilanColl(supp_eec_js_tab_eeco(CurrentDate.getCurrentDate(), listeEEC.get(i).getEec(), "T3"));
            listeEEC.get(i).setBilanRequete(EECDAO.getInstance().getBilanRequeteCollecte(listeEEC.get(i)));
        }
        
        return list_liais(listeEEC);
        //return listeEEC;
    }
  

    /**
     * Méthode lister les sessions
     *
     * @param eecs
     * @return String: fichier csv
     */
    public List<EEC> list_liais(List<EEC> eecs) {
        Channel ch = null;
        String reponse = "";
        String nomFichier = "";
        nomFichier = getFileName(ch, LIST_LIAISON);
        reponse = Module.getInstance(session).read_csv(nomFichier, 1);
        return ReadCSVCollecte.format_csv_list_liaison(reponse, eecs);
    }

    public BilanCollecte visu_etat_machine_collecte(BilanCollecte bill) {
        String reponse = "";
        String nomFichier = "";
        Channel ch = null;
        nomFichier = getFileName(ch, ETAT_MACHINE);
        reponse = Module.getInstance(session).read_csv(nomFichier, 1);
        
        bill.setNomMachine(ReadCSVCollecte.format_csv_etat_machine(reponse).getNomMachine());
        bill.setEtatmachine(ReadCSVCollecte.format_csv_etat_machine(reponse).getEtatmachine());
        
        return bill;
    }

    public List<EEC> visu_debit_eec(List<EEC> eecs) {
        String reponse = "";
        String nomFichier = "";
        Channel ch = null;
        nomFichier = getFileName(ch, DONNEES_RECUE_DEBIT);
        //nomFichier = "list_debit";
        reponse = Module.getInstance(session).read_csv(nomFichier, 1);
        return ReadCSVCollecte.format_csv_list_eec_debit(reponse, eecs);
    }

    public List<EEC> visu_taux_fichier_rejete_eec(List<EEC> eecs) {
        String reponse = "";
        String nomFichier = "";
        Channel ch = null;
        nomFichier = getFileName(ch, TAUX_FICHIER_REJETE);
        //nomFichier = "list_rejete";
        reponse = Module.getInstance(session).read_csv(nomFichier, 1);
        return ReadCSVCollecte.format_csv_list_eec_fichier_rejeter(reponse, eecs);
    }

    public List<EEC> visu_taux_doublons_eec(List<EEC> eecs) {
        String reponse = "";
        String nomFichier = "";
        Channel ch = null;
        nomFichier = getFileName(ch, TAUX_FICHIER_DOUBLONS);
        //nomFichier = "list_rejete";
        reponse = Module.getInstance(session).read_csv(nomFichier, 1);
        return ReadCSVCollecte.format_csv_list_eec_doublons(reponse, eecs);
    }

    public List<EEC> visu_blos_emis_vers_trait(List<EEC> eecs) {
        String reponse = "";
        String nomFichier = "";
        Channel ch = null;
        nomFichier = getFileName(ch, BLOCVERSTRAITEMENT);
        reponse = Module.getInstance(session).read_csv(nomFichier, 1);
        return ReadCSVCollecte.format_csv_list_bloc_emis_vers_trait(reponse, eecs);
    }

    public ArboEEC getArborescenceEEC(String racine) {
        Channel ch = null;
        String reponse = "";
        try {
            ch = this.session.openChannel("exec");
        } catch (JSchException ex) {
            Logger.getLogger(Supervision.class.getName()).log(Level.SEVERE, null, ex);
        }
        ((ChannelExec) ch).setCommand(ARBORESCENCE_EEC);
        try {
            ch.connect();

            reader = new BufferedReader(new InputStreamReader(ch.getInputStream()));
            String line = null;
            while ((line = reader.readLine()) != null) {

                reponse = reponse + line + "\n";
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
        return ReadCSVCollecte.getArborescenceEEC(reponse,racine);
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
