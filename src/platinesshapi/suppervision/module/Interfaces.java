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
import platinesshapi.entity.ArboECC;
import platinesshapi.entity.ArboEEC;
import platinesshapi.entity.BilanInterface;
import platinesshapi.entity.BilanTraitement;
import platinesshapi.entity.ECC;
import platinesshapi.entity.EEC;
import platinesshapi.persistence.AlarmesEECDAO;
import platinesshapi.persistence.ECCDAO;
import platinesshapi.persistence.EECDAO;
import static platinesshapi.suppervision.module.Collecte.ARBORESCENCE_EEC;
import static platinesshapi.suppervision.module.Collecte.LIST_SESS;
import platinesshapi.util.CurrentDate;
import platinesshapi.util.ReadCSVCollecte;
import platinesshapi.util.ReadCSVInterfaces;

/**
 *
 * @author h.el-hayouni
 */
public class Interfaces {

    public static final String SUPP_BILL_IECC_TAB = ". ./.profile; expl_client bil_iecc visu -js @ -tab @";

    // donnees reletives a chaque ecc
    public static final String DEBIT_DONNEES_ENTREE_IECC = ". ./.profile; expl_client visu_cpt -nc VdebEntrIecc";
    public static final String DEBIT_DC_ENTREE_IECC = ". ./.profile; expl_client visu_cpt -nc VdebDcEntrIecc";
    public static final String SUPP_LOT_IECC_NON_FERME = ". ./.profile; expl_client visu_cpt -nc VlotOuvIecc";
    public static final String SUPP_LOT_FERME_NON_ENVOYER_IECC = ". ./.profile; expl_client visu_cpt -nc VlotFermIecc";

    // donnees relatives a l 'interface iecc
    //Not okii
    public static final String DEBIT_GLOBAL_DONNEES_ENTREE = ". ./.profile; expl_client visu_cpt -nc VgdebEntrIecc"; // recuperer 1 ere lignhe 4eme colonne
    public static final String DEBIT_GLOBAL_DC_ENTREE = ". ./.profile; expl_client visu_cpt -nc VgdebDcEntrIecc";
    public static final String DEBIT_GLOBAL_DONNEES_SORTIE = ". ./.profile; expl_client visu_cpt -nc VgdebSortIecc";
    public static final String DEBIT_GLOBAL_DC_SORTIE = ". ./.profile; expl_client visu_cpt -nc VgdebDcSortIecc";

    //okkkk
    public static final String SUPP_LOT_GLOBAL_IECC_NON_FERME = ". ./.profile; expl_client visu_cpt -nc VGlotOuvIecc";
    public static final String SUPP_LOT_GLOBAL_FERME_NON_ENVOYER_IECC = ". ./.profile; expl_client visu_cpt -nc VGlotFermIecc";

    public static final String ARBORESCENCE_ECC = ". ./.profile; expl_client visu_ecc | grep fichier | cut -d\\= -f2 | cut -d\\) -f1 | xargs grep  -i -e ARBO -e libelle | grep -v libelle_instance | cut -d\\; -f2";
    // expl_client visu_ecc | grep fichier | cut -d\= -f2 | cut -d\) -f1 | xargs grep  -i -e ARBO -e libelle -e mode_interface | cut -d\; -f2
    private BufferedReader reader;
    private Session session;

    private static Interfaces instance;
    private ArboECC arborescence;

    public Interfaces() {
    }

    public static Interfaces getInstance(Session session) {
        if (instance == null) {
            instance = new Interfaces();
        }

        instance.session = session;
        return instance;
    }

    /**
     * Méthode de supervision IECC
     *
     * @param jour String: le jour de supervision ex : "17/06/2013"
     * @return String: fichier csv
     */
    public BilanInterface supp_bill_iecc_tab(String jour, String tab) {
        String reponse = "";
        String nomFichier = "";
        Channel ch = null;
        String cmd = SUPP_BILL_IECC_TAB.replaceFirst("@", jour).replaceFirst("@", tab);
        nomFichier = getFileName(ch, cmd);
        reponse = Module.getInstance(session).read_csv(nomFichier, 2);
        BilanInterface bill = ReadCSVInterfaces.format_csv_bill_interf(reponse);
        bill = supp_debit_global_donnees_entree_iecc(bill);
        bill = supp_debit_global_dc_sortie_iecc(bill);
        bill = supp_debit_global_dc_entree_iecc(bill);
        bill = supp_debit_global_dc_sortie_iecc(bill);
        bill = supp_lot_global_non_ferme(bill);
        return supp_lot_global_ferme_Nom_Envoye(bill);
    }

    /**
     * Méthode lister les sessions ecc
     *
     * @param null
     * @return String: fichier csv
     */
    public List<ECC> list_ecc_interface() {

        List<ECC> listeECC = new ArrayList<ECC>();

        if (this.arborescence != null) {
            listeECC = this.arborescence.getAllECC();
        } else {
            listeECC = getArborescenceECC("Interfaces").getAllECC();
        }

        listeECC = supp_debit_donnees_entree_iecc(listeECC);
        listeECC = supp_debit_dc_entree_iecc(listeECC);
        listeECC = supp_debit_donnees_sortie_iecc(listeECC);
        listeECC = supp_debit_dc_sortie_iecc(listeECC);
        listeECC = supp_lot_iecc_non_ferme(listeECC);
        listeECC = supp_lot_iecc_ferme_Nom_Envoye(listeECC);

        for (int i = 0; i < listeECC.size(); i++) {
            listeECC.get(i).setBilanIECC(supp_bill_iecc_tab(CurrentDate.getCurrentDate(), "T2")); //à restifier this.ecc.get(i)
            listeECC.get(i).setBilanRequete(ECCDAO.getInstance().getBilanRequeteInterface(listeECC.get(i)));
           // listeECC.get(i).setBilanRequete(ECCDAO.getInstance().getBilanLotIECC(listeECC.get(i)));

        }

        return listeECC;
    }

    /*
     *******************************************************************************************************
     */
    //Compteurs relative à un ecc
    public List<ECC> supp_lot_iecc_non_ferme(List<ECC> eecs) {
        String reponse = "";
        String nomFichier = "";
        Channel ch = null;
        nomFichier = getFileName(ch, SUPP_LOT_IECC_NON_FERME);
        reponse = Module.getInstance(session).read_csv(nomFichier, 1);
        return ReadCSVInterfaces.format_csv_bill_lotOuvert_iecc(reponse, eecs);

    }

    public List<ECC> supp_lot_iecc_ferme_Nom_Envoye(List<ECC> eecs) {
        String reponse = "";
        String nomFichier = "";
        Channel ch = null;
        nomFichier = getFileName(ch, SUPP_LOT_FERME_NON_ENVOYER_IECC);
        reponse = Module.getInstance(session).read_csv(nomFichier, 1);
        return ReadCSVInterfaces.format_csv_bill_lotFermeNomEnvoye_iecc(reponse, eecs);

    }

    public List<ECC> supp_debit_donnees_entree_iecc(List<ECC> eecs) {
        String reponse = "";
        String nomFichier = "";
        Channel ch = null;
        nomFichier = getFileName(ch, DEBIT_DONNEES_ENTREE_IECC);
        reponse = Module.getInstance(session).read_csv(nomFichier, 1);
        return ReadCSVInterfaces.format_csv_debit_donnees_entree_iecc(reponse, eecs);
    }
    
    public ECC supp_debit_donnees_entree_iecc(ECC ecc) {
        String reponse = "";
        String nomFichier = "";
        Channel ch = null;
        nomFichier = getFileName(ch, DEBIT_DONNEES_ENTREE_IECC);
        reponse = Module.getInstance(session).read_csv_search(nomFichier, ecc.getEcc());
        return ReadCSVInterfaces.format_csv_debit_donnees_entree_iecc(reponse, ecc);
    }

    public List<ECC> supp_debit_dc_entree_iecc(List<ECC> eecs) {
        String reponse = "";
        String nomFichier = "";
        Channel ch = null;
        nomFichier = getFileName(ch, DEBIT_DC_ENTREE_IECC);
        reponse = Module.getInstance(session).read_csv(nomFichier, 1);
        return ReadCSVInterfaces.format_csv_debit_dc_entree_iecc(reponse, eecs);
    }

    public List<ECC> supp_debit_donnees_sortie_iecc(List<ECC> eecs) {
        String reponse = "";
        String nomFichier = "";
        Channel ch = null;
        nomFichier = getFileName(ch, DEBIT_DONNEES_ENTREE_IECC);
        reponse = Module.getInstance(session).read_csv(nomFichier, 1);
        return ReadCSVInterfaces.format_csv_debit_donnees_sortie_iecc(reponse, eecs);
    }

    public List<ECC> supp_debit_dc_sortie_iecc(List<ECC> eecs) {
        String reponse = "";
        String nomFichier = "";
        Channel ch = null;
        nomFichier = getFileName(ch, DEBIT_DC_ENTREE_IECC);
        reponse = Module.getInstance(session).read_csv(nomFichier, 1);
        return ReadCSVInterfaces.format_csv_debit_dc_sortie_iecc(reponse, eecs);
    }
    /*
     *******************************************************************************************************
     */
    //Compteurs relative à  l'interface

    public BilanInterface supp_lot_global_non_ferme(BilanInterface bill) {
        String reponse = "";
        String nomFichier = "";
        Channel ch = null;
        nomFichier = getFileName(ch, SUPP_LOT_GLOBAL_IECC_NON_FERME);
        reponse = Module.getInstance(session).read_csv(nomFichier, 1);
        bill.setNbLotNonFermeGlobal(ReadCSVInterfaces.format_csv_bill_lot_Ouvert_global(reponse).getNbLotNonFermeGlobal());
        return bill;
    }

    public BilanInterface supp_lot_global_ferme_Nom_Envoye(BilanInterface bill) {
        String reponse = "";
        String nomFichier = "";
        Channel ch = null;
        nomFichier = getFileName(ch, SUPP_LOT_GLOBAL_FERME_NON_ENVOYER_IECC);
        reponse = Module.getInstance(session).read_csv(nomFichier, 1);
        bill.setNbLotFermeNonEnvoyeGlobal(ReadCSVInterfaces.format_csv_bill_lotFermeNomEnvoye_global(reponse).getNbLotFermeNonEnvoyeGlobal());
        return bill;
    }

    public BilanInterface supp_debit_global_donnees_entree_iecc(BilanInterface bill) {
        String reponse = "";
        String nomFichier = "";
        Channel ch = null;
        nomFichier = getFileName(ch, DEBIT_GLOBAL_DONNEES_ENTREE);
        reponse = Module.getInstance(session).read_csv(nomFichier, 1);
        bill.setDebitGDonneeEntrant(ReadCSVInterfaces.format_csv_debit_global_donnees_entree_iecc(reponse).getDebitGDonneeEntrant());
        return bill;
    }

    public BilanInterface supp_debit_global_donnees_sortie_iecc(BilanInterface bill) {
        String reponse = "";
        String nomFichier = "";
        Channel ch = null;
        nomFichier = getFileName(ch, DEBIT_GLOBAL_DONNEES_SORTIE);
        reponse = Module.getInstance(session).read_csv(nomFichier, 1);
        bill.setDebitGDonneeSortant(ReadCSVInterfaces.format_csv_debit_global_donnees_sortie_iecc(reponse).getDebitGDonneeSortant());
        return bill;
    }

    public BilanInterface supp_debit_global_dc_entree_iecc(BilanInterface bill) {
        String reponse = "";
        String nomFichier = "";
        Channel ch = null;
        nomFichier = getFileName(ch, DEBIT_GLOBAL_DC_ENTREE);
        reponse = Module.getInstance(session).read_csv(nomFichier, 1);
        bill.setDebitGDCEntrant(ReadCSVInterfaces.format_csv_debit_global_dc_entree_iecc(reponse).getDebitGDCEntrant());
        return bill;

    }

    public BilanInterface supp_debit_global_dc_sortie_iecc(BilanInterface bill) {
        String reponse = "";
        String nomFichier = "";
        Channel ch = null;
        nomFichier = getFileName(ch, DEBIT_GLOBAL_DC_SORTIE);
        reponse = Module.getInstance(session).read_csv(nomFichier, 1);
        bill.setDebitGDCSortant(ReadCSVInterfaces.format_csv_debit_global_dc_sortie_iecc(reponse).getDebitGDCSortant());
        return bill;

    }

    /*
     *******************************************************************************************************
     */
    public ArboECC getArborescenceECC(String racine) {
        Channel ch = null;
        String reponse = "";
        try {
            ch = this.session.openChannel("exec");
        } catch (JSchException ex) {
            Logger.getLogger(Supervision.class.getName()).log(Level.SEVERE, null, ex);
        }
        ((ChannelExec) ch).setCommand(ARBORESCENCE_ECC);
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
        return ReadCSVInterfaces.getArborescenceECC(reponse, racine);
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
