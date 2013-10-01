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
public class Recyclage {

    public static final String SUPP_RECY = ". ./.profile; expl_client bil_recy visu -js @ -tab @";
    public static final String SUPP_RECY_EECO = ". ./.profile; expl_client bil_recy visu -js @ -eeco @";

    private BufferedReader reader;
    private Session session;

    private static Recyclage instance;

    public Recyclage() {
    }

    public static Recyclage getInstance(Session session) {
        if (instance == null) {
            instance = new Recyclage();
        }

        instance.session = session;
        return instance;
    }

    /**
     * Méthode de supervision Recyclage
     *
     * @param jour String: le jour de supervision ex : "17/06/2013"
     * @param tab String: le tableau de supervision à afficher ex : "T1"
     * @return String: fichier csv
     */
    public String supp_recy(String jour, String tab) {
        String reponse = "";
        String nomFichier = "";
        Channel ch = null;
        String cmd = SUPP_RECY.replaceFirst("@", jour).replaceFirst("@", tab);
        nomFichier = getFileName(ch, cmd);
        reponse = Module.getInstance(session).read_csv(nomFichier, 2);
        return reponse;
    }

    /**
     * Méthode de supervision recyclage
     *
     * @param jour String: le jour de supervision ex : "17/06/2013"
     * @param nomEEC String: le nom EEC ex : "IN_CCN"
     * @return String: fichier csv
     */
    public String supp_recy_eeco(String jour, String nomEEC) {
        String reponse = "";
        String nomFichier = "";
        Channel ch = null;
        String cmd = SUPP_RECY_EECO.replaceFirst("@", jour).replaceFirst("@", nomEEC);
        nomFichier = getFileName(ch, cmd);
        reponse = Module.getInstance(session).read_csv(nomFichier, 2);
        return reponse;
    }
    
     public List<Alarme> getAlarmes() {
        return AlarmesEECDAO.getInstance().getAlarmesByModule("recyclage",20);
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
