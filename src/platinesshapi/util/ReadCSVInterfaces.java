/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package platinesshapi.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import platinesshapi.entity.ArboECC;
import platinesshapi.entity.BilanInterface;
import platinesshapi.entity.ECC;

/**
 *
 * @author h.el-hayouni
 */
public class ReadCSVInterfaces {

    public static ArboECC getArborescenceECC(String fileContent, String racine) {

        int index = 0;

        ArboECC arboresence = new ArboECC(racine);

        /**
         * length - 1 pour ignorer la ligne vide a la fin l+2 etre sur la ligne
         * de l'arborescence a chaque fois, il suffira de faire un -1 pour
         * revenir sur la ligne eec cible
         */
        for (int l = 1; l < fileContent.split("\n").length - 1; l = l + 2) {
            /**
             * Tableau qui va contenir la sous arborescence par niveau du parent
             * eec
             */
            List<ArboECC> arboECCTab = null;

            /**
             * Si c'est != 0 => le parent != /
             */
            if (fileContent.split("\n")[l].split("/").length != 0) {

                /**
                 * Réinitialise la tableau pour chaque ligne d'arboresence
                 * parente
                 */
                arboECCTab = new ArrayList<ArboECC>();

                /**
                 * Pour chaque element de cette arborescence
                 */
                for (int i = 1; i < fileContent.split("\n")[l].split("/").length; i++) {

                    /**
                     * Je l'ajoute au tableau
                     */
                    arboECCTab.add(new ArboECC(fileContent.split("\n")[l].split("/")[i]));
                }

                /**
                 * J'ajoute le ECC au dernier element de l'arboresence
                 */
                arboECCTab.get(arboECCTab.size() - 1).getEccs().add(new ECC(fileContent.split("\n")[l - 1]));

                /**
                 * j'inverse l'ordre du tableau de l'arborescence pour commencer
                 * depuis la fin
                 */
                Collections.reverse(arboECCTab);

                /**
                 * J'ajoute chaque fils a son parent
                 */
                for (int i = 0; i < arboECCTab.size(); i++) {
                    if (i + 1 < arboECCTab.size()) {

                        /**
                         * *****************************
                         */
                        arboECCTab.get(i).setId(index);
                        index++;
                        /**
                         * *****************************
                         */

                        arboECCTab.get(i + 1).getFils().add(arboECCTab.get(i));
                    }
                }

                /**
                 * J'ajoute la sous arborescence a la racine
                 *
                 */
                arboresence.addAroberance(arboECCTab.get(arboECCTab.size() - 1));
            } else {
                /**
                 * si le parent du EEC est la racine je l'ajoute a ce dernier
                 */
                arboresence.getEccs().add(new ECC(fileContent.split("\n")[l - 1]));
            }

        }
        return arboresence;
    }

    public static BilanInterface format_csv_bill_interf(String csvContent) {

        BilanInterface bilanInterface = new BilanInterface();
        if (csvContent != null) {
            String[] lines = csvContent.split("\n");
            if (lines.length > 0) {
                try {
                    bilanInterface.setVolumeEntrant(Integer.parseInt(lines[0].split(";")[1]));
                    bilanInterface.setDcEntrant(Integer.parseInt(lines[1].split(";")[1]));
                    bilanInterface.setVolumeSortant(Integer.parseInt(lines[2].split(";")[1]));
                    bilanInterface.setDcSortant(Integer.parseInt(lines[3].split(";")[1]));
                    bilanInterface.setNbFichierSortant(Integer.parseInt(lines[4].split(";")[1]));
                    bilanInterface.setNbDcHorsDelai(Integer.parseInt(lines[5].split(";")[1]));
                    bilanInterface.setNbMessageSortant(Integer.parseInt(lines[6].split(";")[1]));
                    bilanInterface.setNbDcDetruit(Integer.parseInt(lines[7].split(";")[1]));
                    bilanInterface.setNbDCInattendu(Integer.parseInt(lines[8].split(";")[1]));
                } catch (NumberFormatException e) {
                }
            }

        }
        return bilanInterface;
    }


    /*
     *********************************************************************************************************
     *********************************************************************************************************
     *********************************************************************************************************
    
     */
    public static List<ECC> format_csv_bill_lotOuvert_iecc(String csvContent, List<ECC> eecs) {

        if (csvContent != null) {
            String[] lines = csvContent.split("\n");

            for (String ligne : lines) {
                String nom = ligne.split(";")[0];

                for (ECC ecc : eecs) {
                    if (ecc.getEcc().equalsIgnoreCase(nom)) {
                        try {
                            ecc.setNbLotNonFerme(Integer.parseInt(lines[0].split(";")[4]));
                        } catch (NumberFormatException e) {
                            ecc.setNbLotNonFerme(0);
                        }
                        break;
                    }
                }
            }
        } else {
            for (ECC ecc : eecs) {
                ecc.setNbLotNonFerme(0);
            }
        }
        return eecs;
    }

    public static List<ECC> format_csv_bill_lotFermeNomEnvoye_iecc(String csvContent, List<ECC> eecs) {
        if (csvContent != null) {
            String[] lines = csvContent.split("\n");

            for (String ligne : lines) {
                String nom = ligne.split(";")[0];

                for (ECC ecc : eecs) {
                    if (ecc.getEcc().equalsIgnoreCase(nom)) {
                        try {

                            ecc.setNbLotFermeNonEnvoye(Integer.parseInt(lines[0].split(";")[4]));

                        } catch (NumberFormatException e) {

                            ecc.setNbLotNonFerme(0);
                        }
                        break;
                    }
                }
            }
        } else {
            for (ECC ecc : eecs) {
                ecc.setNbLotNonFerme(0);
            }
        }
        return eecs;
    }

    public static List<ECC> format_csv_debit_donnees_entree_iecc(String csvContent, List<ECC> eecs) {

        if (csvContent != null) {
            String[] lines = csvContent.split("\n");

            for (String ligne : lines) {
                String nom = ligne.split(";")[0];

                for (ECC ecc : eecs) {
                    if (ecc.getEcc().equalsIgnoreCase(nom)) {

                        try {
                            ecc.setDebitDonneeEntrant(Integer.parseInt(lines[0].split(";")[4]));

                        } catch (NumberFormatException e) {
                            ecc.setDebitDonneeEntrant(0);
                        }
                        break;
                    }
                }
            }
        } else {
            for (ECC ecc : eecs) {
                ecc.setDebitDonneeEntrant(0);
            }
        }
        return eecs;
    }

    public static ECC format_csv_debit_donnees_entree_iecc(String csvContent, ECC ecc) {

        if (csvContent != null) {
            if (csvContent.contains(";")) {
                try {
                    ecc.setDebitDonneeEntrant(Integer.parseInt(csvContent.split(";")[4]));

                } catch (NumberFormatException e) {
                    ecc.setDebitDonneeEntrant(0);
                }
            }
        }
        return ecc;
    }

    public static List<ECC> format_csv_debit_dc_entree_iecc(String csvContent, List<ECC> eecs) {
        if (csvContent != null) {
            String[] lines = csvContent.split("\n");

            for (String ligne : lines) {
                String nom = ligne.split(";")[0];

                for (ECC ecc : eecs) {
                    if (ecc.getEcc().equalsIgnoreCase(nom)) {
                        try {
                            ecc.setDebitDcEntrant(Integer.parseInt(lines[0].split(";")[4]));

                        } catch (NumberFormatException e) {
                            ecc.setDebitDcEntrant(0);
                        }
                        break;
                    }
                }
            }
        } else {
            for (ECC ecc : eecs) {
                ecc.setDebitDcEntrant(0);
            }
        }
        return eecs;

    }

    public static List<ECC> format_csv_debit_donnees_sortie_iecc(String csvContent, List<ECC> eecs) {
        if (csvContent != null) {
            String[] lines = csvContent.split("\n");

            for (String ligne : lines) {
                String nom = ligne.split(";")[0];

                for (ECC ecc : eecs) {
                    if (ecc.getEcc().equalsIgnoreCase(nom)) {
                        try {
                            ecc.setDebitDonneeSortant(Integer.parseInt(lines[0].split(";")[4]));

                        } catch (NumberFormatException e) {
                            ecc.setDebitDonneeSortant(0);
                        }
                        break;
                    }
                }
            }
        } else {
            for (ECC ecc : eecs) {
                ecc.setDebitDonneeSortant(0);
            }
        }
        return eecs;
    }

    public static List<ECC> format_csv_debit_dc_sortie_iecc(String csvContent, List<ECC> eecs) {
        if (csvContent != null) {
            String[] lines = csvContent.split("\n");

            for (String ligne : lines) {
                String nom = ligne.split(";")[0];

                for (ECC ecc : eecs) {
                    if (ecc.getEcc().equalsIgnoreCase(nom)) {
                        try {
                            ecc.setDebitDcSortant(Integer.parseInt(lines[0].split(";")[4]));
                        } catch (NumberFormatException e) {

                            ecc.setDebitDcSortant(0);
                        }

                        break;
                    }
                }
            }
        } else {
            for (ECC ecc : eecs) {
                ecc.setDebitDcSortant(0);
            }
        }

        return eecs;
    }
    /*
     *********************************************************************************************************
     *********************************************************************************************************
     *********************************************************************************************************
    
     */

    public static BilanInterface format_csv_bill_lot_Ouvert_global(String csvContent) {
        BilanInterface lotIecc = new BilanInterface();
        if (csvContent != null) {
            String[] lines = csvContent.split("\n");
            try {
                lotIecc.setNbLotNonFermeGlobal(Integer.parseInt(lines[0].split(";")[4]));

            } catch (NumberFormatException e) {
                lotIecc.setNbLotNonFermeGlobal(0);
            }
        } else {
            lotIecc.setNbLotNonFermeGlobal(0);

        }

        return lotIecc;
    }

    public static BilanInterface format_csv_bill_lotFermeNomEnvoye_global(String csvContent) {
        BilanInterface lotIecc = new BilanInterface();
        if (csvContent != null) {
            String[] lines = csvContent.split("\n");

            try {
                lotIecc.setNbLotFermeNonEnvoyeGlobal(Integer.parseInt(lines[0].split(";")[4]));

            } catch (NumberFormatException e) {

                lotIecc.setNbLotFermeNonEnvoyeGlobal(0);
            }
        } else {
            lotIecc.setNbLotFermeNonEnvoyeGlobal(0);

        }

        return lotIecc;
    }

    public static BilanInterface format_csv_debit_global_donnees_entree_iecc(String csvContent) {
        BilanInterface lotIecc = new BilanInterface();
        if (csvContent != null) {
            String[] lines = csvContent.split("\n");

            try {
                lotIecc.setDebitGDonneeEntrant(Integer.parseInt(lines[0].split(";")[4]));

            } catch (NumberFormatException e) {
                lotIecc.setDebitGDonneeEntrant(0);

            }
        } else {
            lotIecc.setDebitGDonneeEntrant(0);

        }

        return lotIecc;
    }

    public static BilanInterface format_csv_debit_global_donnees_sortie_iecc(String csvContent) {
        BilanInterface lotIecc = new BilanInterface();
        if (csvContent != null) {
            String[] lines = csvContent.split("\n");

            try {
                lotIecc.setDebitGDonneeSortant(Integer.parseInt(lines[0].split(";")[4]));

            } catch (NumberFormatException e) {
                lotIecc.setDebitGDonneeSortant(0);
            }
        } else {
            lotIecc.setDebitGDonneeSortant(0);

        }

        return lotIecc;
    }

    public static BilanInterface format_csv_debit_global_dc_entree_iecc(String csvContent) {
        BilanInterface lotIecc = new BilanInterface();
        if (csvContent != null) {
            String[] lines = csvContent.split("\n");

            try {
                lotIecc.setDebitGDCEntrant(Integer.parseInt(lines[0].split(";")[4]));

            } catch (NumberFormatException e) {
                lotIecc.setDebitGDCEntrant(0);

            }
        } else {
            lotIecc.setDebitGDCEntrant(0);

        }

        return lotIecc;
    }

    public static BilanInterface format_csv_debit_global_dc_sortie_iecc(String csvContent) {
        BilanInterface lotIecc = new BilanInterface();
        if (csvContent != null) {
            String[] lines = csvContent.split("\n");

            try {
                lotIecc.setDebitGDCSortant(Integer.parseInt(lines[0].split(";")[4]));

            } catch (NumberFormatException e) {
                lotIecc.setDebitGDCSortant(0);

            }
        } else {
            lotIecc.setDebitGDCSortant(0);

        }

        return lotIecc;
    }

}
