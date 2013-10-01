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
import platinesshapi.entity.BilanDistribution;
import platinesshapi.entity.ArticleDistribution;
import platinesshapi.entity.CompteurDistribution;
import platinesshapi.entity.EEC;
import platinesshapi.persistence.AlarmesEECDAO;
import platinesshapi.persistence.EECDAO;
import static platinesshapi.suppervision.module.Collecte.ARBORESCENCE_EEC;
import static platinesshapi.suppervision.module.Collecte.LIST_SESS;
import platinesshapi.util.CurrentDate;
import platinesshapi.util.ReadCSVCollecte;
import platinesshapi.util.ReadCSVDistribution;

/**
 *
 * @author h.el-hayouni
 */
public class Distribution {

    public static final String SUPP_BILL_DIST_TAB = ". ./.profile; expl_client bil_dist visu -js @ -tab @";
    public static final String SUPP_BILL_DIST_EECO_TAB = ". ./.profile; expl_client bil_dist visu -js @ -eeco @ -tab @";
    public static final String TAUX_MULT_DIST = ". ./.profile; expl_client visu_cpt -nc VtauxMultDist";
    public static final String LIST_SESS = ". ./.profile; expl_client list_sess";
    public static final String ARBORESCENCE_EEC = ". ./.profile; expl_client visu_eec | grep fichier | cut -d\\= -f2 | cut -d\\) -f1 | xargs grep  -e ARBO -e IDEN | cut -d\\; -f2";

    private BufferedReader reader;
    private Session session;

    private static Distribution instance;

    public Distribution() {
    }

    public static Distribution getInstance(Session session) {
        if (instance == null) {
            instance = new Distribution();
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
    public BilanDistribution supp_bill_dist_tab(String jour, String tab) {
        String reponse = "";
        String nomFichier = "";
        Channel ch = null;
        String cmd = SUPP_BILL_DIST_TAB.replaceFirst("@", jour).replaceFirst("@", tab);
        nomFichier = getFileName(ch, cmd);
        reponse = Module.getInstance(session).read_csv(nomFichier, 2);
        return supp_taux_mult_dist(ReadCSVDistribution.format_csv_bill_dist(reponse));
    }

    /**
     * Méthode de supervision distribution
     *
     * @param jour String: le jour de supervision ex : "17/06/2013"
     * @param eecs
     * @param tab
     * @return Une liste des cas d'articles distribution
     */
    public List<CompteurDistribution> supp_bill_dist_eeco_tab(String jour, String tab, EEC eecs) {

        String reponse = "";
        String nomFichier = "";
        Channel ch = null;
        String cmd = SUPP_BILL_DIST_EECO_TAB.replaceFirst("@", jour).replaceFirst("@", eecs.getEec()).replaceFirst("@", tab);
        nomFichier = getFileName(ch, cmd);
        reponse = Module.getInstance(session).read_csv(nomFichier, 3);
        return ReadCSVDistribution.format_csv_list_cas_article_dist(reponse, eecs);
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

        /**
         * Pour chaque eec, aller chercher son cas d'article du jour
         */
        
        for (EEC eec : listeEEC) {
            eec.setCompteursDist(supp_bill_dist_eeco_tab(CurrentDate.getCurrentDate(), "T4", eec));
            
        }

        return listeEEC;
    }

    public BilanDistribution supp_taux_mult_dist(BilanDistribution bill) {

        String reponse = "";
        String nomFichier = "";
        Channel ch = null;
        nomFichier = getFileName(ch, TAUX_MULT_DIST);
        reponse = Module.getInstance(session).read_csv(nomFichier, 1);
        
         bill.setTauxMultipDC(ReadCSVDistribution.format_csv_taux_mult_dist(reponse).getTauxMultipDC());
        return bill;
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
