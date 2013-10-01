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
import java.util.logging.Level;
import java.util.logging.Logger;
import platinesshapi.Supervision;

/**
 *
 * @author h.el-hayouni
 */
public class Module {

    private Session session;
    private BufferedReader reader;

    private static Module instance;
    public static final String READER_CSV = "cat";
    public static final String READER_CSV_SEARCH = "cat @ | grep @";

    private Module() {
    }

    public static Module getInstance(Session session) {
        if (instance == null) {
            instance = new Module();
        }

        instance.session = session;
        return instance;
    }

    /**
     * Méthode reader de fichier CSV
     *
     * @param filename String: le nom de fichier à lire ex :
     * "/data/REFA/exp/data/resultats/753189/info.csv"
     * @param nbrLigneCommentaire int: le nombre de ligne à sauter pour lire le
     * contenue voulu du fichier CSV ex : "2"
     * @return String: fichier csv
     */
    public String read_csv(String filename, int nbrLigneCommentaire) {
       String reponse = null;
        if (filename != null) {
            Channel ch = null;
            reponse="";
            try {
                ch = this.session.openChannel("exec");
            } catch (JSchException ex) {
                Logger.getLogger(Supervision.class.getName()).log(Level.SEVERE, null, ex);
            }
            ((ChannelExec) ch).setCommand(READER_CSV + " " + filename);
            try {
                ch.connect();
                reader = new BufferedReader(new InputStreamReader(ch.getInputStream()));

                String line = null;
                int count = 0;
                while ((line = reader.readLine()) != null) {
                    if (count >= nbrLigneCommentaire) {
                        reponse = reponse + line + "\n";
                    }

                    count++;
                }
            } catch (JSchException ex) {
                Logger.getLogger(Supervision.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Supervision.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return reponse;
    }
    public String read_csv_search(String filename, String ecc) {
       String reponse = null;
        if (filename != null) {
            Channel ch = null;
            reponse="";
            try {
                ch = this.session.openChannel("exec");
            } catch (JSchException ex) {
                Logger.getLogger(Supervision.class.getName()).log(Level.SEVERE, null, ex);
            }
            ((ChannelExec) ch).setCommand(READER_CSV.replaceFirst("@", filename).replaceFirst("@", ecc));
            try {
                ch.connect();
                reader = new BufferedReader(new InputStreamReader(ch.getInputStream()));

                String line = null;
                int count = 0;
                while ((line = reader.readLine()) != null) {
                        reponse = reponse + line + "\n";
                }
            } catch (JSchException ex) {
                Logger.getLogger(Supervision.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Supervision.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return reponse;
    }
//    public String read_csv(String filename, int nbrLigneCommentaire) {
//        String reponse = "";
//        Channel ch = null;
//        try {
//            ch = this.session.openChannel("exec");
//        } catch (JSchException ex) {
//            Logger.getLogger(Supervision.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        ((ChannelExec) ch).setCommand(READER_CSV + " " + filename);
//        try {
//            ch.connect();
//            reader = new BufferedReader(new InputStreamReader(ch.getInputStream()));
//
//            String line = null;
//            int count = 0;
//            while ((line = reader.readLine()) != null) {
//                if (count >= nbrLigneCommentaire) {
//                    reponse = reponse + line + "\n";
//                }
//
//                count++;
//            }
//        } catch (JSchException ex) {
//            Logger.getLogger(Supervision.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (IOException ex) {
//            Logger.getLogger(Supervision.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//        return reponse;
//    }
}
