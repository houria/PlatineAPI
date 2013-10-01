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
import platinesshapi.entity.AlarmesActives;
import platinesshapi.entity.BilanSuppervision;
import platinesshapi.persistence.AlarmesEECDAO;
import platinesshapi.util.ReadCSVPlatine;

/**
 *
 * @author h.el-hayouni
 */
public class Platine {

    public static final String SUPP_PLATINE = ". ./.profile; expl_client bil_supe visu -js";
    public static final String VISU_ALARME_ACTIVE = ". ./.profile; echo 'select * from niv_alerte_dom;' | sqlplus db_supe/plasupe ";

    private BufferedReader reader;
    private Session session;

    private static Platine instance;

    private Platine() {
    }

    public static Platine getInstance(Session session) {
        if (instance == null) {
            instance = new Platine();
        }

        instance.session = session;
        return instance;
    }

    /**
     * Méthode de supervision Platine
     *
     * @param jour String: le jour de supervision ex : "17/06/2013"
     * @return String: fichier csv
     */
    public BilanSuppervision sup_platine(String jour) {
        Channel ch = null;
        String reponse = "";
        String nomFichier = "";
        nomFichier=getFileName(ch, SUPP_PLATINE + " " + jour);
        reponse = Module.getInstance(this.session).read_csv(nomFichier, 2);
        return ReadCSVPlatine.format_csv_bill_sup(reponse);
    }
    
    public AlarmesActives supp_alarmeActive() {
        
        //String reponse = "";
        int countLine=0;
        int limitLine = 12;
        Channel ch = null;
        AlarmesActives alarmesActives = new AlarmesActives();
        
        try {
            ch = this.session.openChannel("exec");
        } catch (JSchException ex) {
            Logger.getLogger(Supervision.class.getName()).log(Level.SEVERE, null, ex);
        }
        ((ChannelExec) ch).setCommand(VISU_ALARME_ACTIVE);
        //((ChannelExec) ch).setCommand("cat alarmes");
        try {
            ch.connect();

            reader = new BufferedReader(new InputStreamReader(ch.getInputStream()));
            String line = null;
            while ((line = reader.readLine()) != null) {
                if(countLine>=limitLine)
                {
                    if(!line.contains("MODULE") && !line.contains("-----") && !line.equalsIgnoreCase("") && !line.contains("rows selected")&& !line.contains("Disconnected"))
                    {
                        alarmesActives.setTotalASI(alarmesActives.getTotalASI()+Integer.parseInt(line.split("\\s+")[1]));
                        alarmesActives.setTotalAID(alarmesActives.getTotalAID()+Integer.parseInt(line.split("\\s+")[2]));
                        alarmesActives.setTotalAII(alarmesActives.getTotalAII()+Integer.parseInt(line.split("\\s+")[3]));
                    }
                        
                }
                countLine++;
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

        return alarmesActives;
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
