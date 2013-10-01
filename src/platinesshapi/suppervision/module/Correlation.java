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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import platinesshapi.Supervision;
import platinesshapi.entity.Alarme;
import platinesshapi.persistence.AlarmesEECDAO;

/**
 *
 * @author h.el-hayouni
 */
public class Correlation {

    public static final String SUPP_CORR = ". ./.profile; expl_client bil_corr visu -js @ -tab @";

    private BufferedReader reader;
    private Session session;

    private static Correlation instance;

    public Correlation() {
    }

    public static Correlation getInstance(Session session) {
        if (instance == null) {
            instance = new Correlation();
        }

        instance.session = session;
        return instance;
    }

    /**
     * Méthode de supervision Correlation
     *
     * @param jour String: le jour de supervision ex : "17/06/2013"
     * @param tab String: le tableau de supervision à afficher ex : "T1"
     * @return String: fichier csv
     */
    public String supp_corr(String jour, String tab) {
        String reponse = "";
        String nomFichier = "";
        Channel ch = null;
        String cmd = SUPP_CORR.replaceFirst("@", jour).replaceFirst("@", tab);
        nomFichier = getFileName(ch, cmd);
        reponse = Module.getInstance(session).read_csv(nomFichier, 2);
        return reponse;
    }
     public List<Alarme> getAlarmes() {
        return AlarmesEECDAO.getInstance().getAlarmesByModule("correlation",20);
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
