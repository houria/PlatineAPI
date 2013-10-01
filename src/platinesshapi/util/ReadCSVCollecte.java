/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package platinesshapi.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import platinesshapi.entity.ArboEEC;
import platinesshapi.entity.BilanCollecte;
import platinesshapi.entity.EEC;

/**
 *
 * @author h.el-hayouni
 */
public class ReadCSVCollecte {

    public static BilanCollecte format_csv_bill_coll(String csvContent) {

        BilanCollecte bilanCollecte = new BilanCollecte();

        if (csvContent != null) {
            String[] lines = csvContent.split("\n");
            if (lines.length > 0) {
                try {

                    bilanCollecte.setBlocVersArch(Integer.parseInt(lines[0].split(";")[1]));
                    bilanCollecte.setFichierVersArch(Integer.parseInt(lines[1].split(";")[1]));
                    bilanCollecte.setBlocVersTrait(Integer.parseInt(lines[2].split(";")[1]));
                    bilanCollecte.setFichierVersTrait(Integer.parseInt(lines[3].split(";")[1]));
                    bilanCollecte.setFichierRecus(Integer.parseInt(lines[4].split(";")[1]));
                    bilanCollecte.setBlocRecus(Integer.parseInt(lines[5].split(";")[1]));
                    bilanCollecte.setFichierRejetes(Integer.parseInt(lines[6].split(";")[1]));
                    bilanCollecte.setBlocRejetes(Integer.parseInt(lines[7].split(";")[1]));
                    bilanCollecte.setBlocPerdus(Integer.parseInt(lines[9].split(";")[1]));
                    bilanCollecte.setTotalBlocRecus(Integer.parseInt(lines[18].split(";")[1].equalsIgnoreCase(" ") || lines[18].split(";")[1].equalsIgnoreCase("") ? "0" : lines[18].split(";")[1]));
                    bilanCollecte.setTotalFichierRecus(Integer.parseInt(lines[18].split(";")[2].equalsIgnoreCase(" ") || lines[18].split(";")[2].equalsIgnoreCase("") ? "0" : lines[18].split(";")[2]));
                } catch (NumberFormatException e) {

                }
            }

        }
        return bilanCollecte;
    }

    public static BilanCollecte format_csv_bill_coll_eeco(String csvContent) {

        BilanCollecte bilanCollecte = new BilanCollecte();
        if (csvContent != null) {
            String[] lines = csvContent.split("\n");
            try {
                bilanCollecte.setFichierRecus(Integer.parseInt(lines[0].split(";")[2].equalsIgnoreCase(" ") || lines[0].split(";")[2].equalsIgnoreCase("") ? "0" : lines[0].split(";")[2]));
                bilanCollecte.setFichierRejetes(Integer.parseInt(lines[0].split(";")[4].equalsIgnoreCase(" ") || lines[0].split(";")[4].equalsIgnoreCase("") ? "0" : lines[0].split(";")[4]));
                bilanCollecte.setVolumeRecus(Integer.parseInt(lines[0].split(";")[7].equalsIgnoreCase(" ") || lines[0].split(";")[7].equalsIgnoreCase("") ? "0" : lines[0].split(";")[7]));

            } catch (NumberFormatException e) {

                bilanCollecte.setFichierRecus(0);
                bilanCollecte.setFichierRejetes(0);
                bilanCollecte.setVolumeRecus(0);
            } catch (ArrayIndexOutOfBoundsException e) {
                bilanCollecte.setFichierRecus(0);
                bilanCollecte.setFichierRejetes(0);
                bilanCollecte.setVolumeRecus(0);
            }
        } else {
            bilanCollecte.setFichierRecus(0);
            bilanCollecte.setFichierRejetes(0);
            bilanCollecte.setVolumeRecus(0);
        }
        return bilanCollecte;
    }

    public static BilanCollecte format_csv_etat_machine(String csvContent) {

        String[] lines = csvContent.split("\n");

        BilanCollecte machineColl = new BilanCollecte();
        machineColl.setNomMachine(lines[0].split(";")[0].equalsIgnoreCase(" ") || lines[0].split(";")[0].equalsIgnoreCase("") ? "0" : lines[0].split(";")[0]);
        machineColl.setEtatmachine(lines[0].split(";")[4].equalsIgnoreCase(" ") || lines[0].split(";")[4].equalsIgnoreCase("") ? "0" : lines[0].split(";")[4]);

        return machineColl;
    }

    public static List<EEC> format_csv_list_session(String csvContent) {

        List<EEC> list = new ArrayList<EEC>();

        String[] lines = csvContent.split("\n");

        for (String ligne : lines) {
            EEC eecSession = new EEC();

            eecSession.setProtocole(ligne.split(";")[0]);
            eecSession.setEec(ligne.split(";")[1]);
            eecSession.setSession(ligne.split(";")[2]);
            eecSession.setEtatSession(ligne.split(";")[3].trim());

            list.add(eecSession);
        }

        return list;
    }

    public static List<EEC> format_csv_list_eec_debit(String csvContent, List<EEC> eecs) {

        if (csvContent != null) {
            String[] lines = csvContent.split("\n");

            for (String ligne : lines) {
                String nom = ligne.split(";")[0];

                for (EEC eec : eecs) {
                    if (eec.getEec().equalsIgnoreCase(nom)) {
                        try {
                            eec.setDebit(Integer.parseInt(ligne.split(";")[4]));
                        } catch (NumberFormatException e) {
                            eec.setDebit(0);
                        }
                        break;
                    }

                }
            }
        } else {
            for (EEC eec : eecs) {
                eec.setDebit(0);
            }

        }

        return eecs;

//        String[] lines = csvContent.split("\n");
//
//        for (String ligne : lines) {
//            String nom = ligne.split(";")[0];
//
//            for (EEC eec : eecs) {
//                if (eec.getEec().equalsIgnoreCase(nom)) {
//                    eec.setDebit(Integer.parseInt(ligne.split(";")[4]));
//                    break;
//                }
//
//            }
//        }
//
//        return eecs;
    }

    public static List<EEC> format_csv_list_eec_fichier_rejeter(String csvContent, List<EEC> eecs) {

        if (csvContent != null) {
            String[] lines = csvContent.split("\n");

            for (String ligne : lines) {
                String nom = ligne.split(";")[0];

                for (EEC eec : eecs) {
                    if (eec.getEec().equalsIgnoreCase(nom)) {
                        try {
                            eec.setTauxFichRejete(Integer.parseInt(ligne.split(";")[4]));
                        } catch (NumberFormatException e) {
                            eec.setDebit(0);
                        }
                        break;
                    }

                }
            }
        } else {
            for (EEC eec : eecs) {
                eec.setTauxFichRejete(0);
            }

        }

        return eecs;
    }

    public static List<EEC> format_csv_list_eec_doublons(String csvContent, List<EEC> eecs) {

        if (csvContent != null) {
            String[] lines = csvContent.split("\n");

            for (String ligne : lines) {
                String nom = ligne.split(";")[0];

                for (EEC eec : eecs) {
                    if (eec.getEec().equalsIgnoreCase(nom)) {
                        try {
                            eec.setDoublons(Integer.parseInt(ligne.split(";")[4]));
                        } catch (NumberFormatException e) {
                            eec.setDebit(0);
                        }
                        break;
                    }

                }
            }
        } else {
            for (EEC eec : eecs) {
                eec.setDoublons(0);
            }

        }

        return eecs;
    }

    public static List<EEC> format_csv_list_bloc_emis_vers_trait(String csvContent, List<EEC> eecs) {

        if (csvContent != null) {
            String[] lines = csvContent.split("\n");

            for (String ligne : lines) {
                String nom = ligne.split(";")[0];

                for (EEC eec : eecs) {
                    if (eec.getEec().equalsIgnoreCase(nom)) {
                        try {
                            eec.setBlocEmisTrait(Integer.parseInt(ligne.split(";")[4]));
                        } catch (NumberFormatException e) {
                            eec.setDebit(0);
                        }
                        break;
                    }

                }
            }
        } else {
            for (EEC eec : eecs) {
                eec.setBlocEmisTrait(0);
            }

        }

        return eecs;
    }

    public static List<EEC> format_csv_list_liaison(String csvContent, List<EEC> eecs) {

        String[] lines = csvContent.split("\n");

        for (String ligne : lines) {
            for (EEC eec : eecs) {
                if (eec.getEec().equalsIgnoreCase(ligne.split(";")[0])) {
                    if (ligne.split(";")[1].equalsIgnoreCase("normale")) {
                        eec.setEtatLiaisonNormal(ligne.split(";")[4].trim());
                    } else {
                        eec.setEtatLisaisonSecours(ligne.split(";")[4].trim());
                    }

                    break;
                }
            }

        }

        return eecs;
    }

    public static ArboEEC getArborescenceEEC(String fileContent, String racine) {
        ArboEEC arboresence = new ArboEEC(racine);

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
            List<ArboEEC> arboEECTab = null;

            /**
             * Si c'est != 0 => le parent != /
             */
            if (fileContent.split("\n")[l].split("/").length != 0) {

                /**
                 * Réinitialise la tableau pour chaque ligne d'arboresence
                 * parente
                 */
                arboEECTab = new ArrayList<ArboEEC>();

                /**
                 * Pour chaque element de cette arborescence
                 */
                for (int i = 1; i < fileContent.split("\n")[l].split("/").length; i++) {

                    /**
                     * Je l'ajoute au tableau
                     */
                    arboEECTab.add(new ArboEEC(fileContent.split("\n")[l].split("/")[i]));
                }

                /**
                 * J'ajoute le EEC au dernier element de l'arboresence
                 */
                arboEECTab.get(arboEECTab.size() - 1).getEccs().add(new EEC(fileContent.split("\n")[l - 1]));

                /**
                 * j'inverse l'ordre du tableau de l'arborescence pour commencer
                 * depuis la fin
                 */
                Collections.reverse(arboEECTab);

                /**
                 * J'ajoute chaque fils a son parent
                 */
                for (int i = 0; i < arboEECTab.size(); i++) {
                    if (i + 1 < arboEECTab.size()) {
                        arboEECTab.get(i + 1).getFils().add(arboEECTab.get(i));
                    }
                }

                /**
                 * J'ajoute la sous arborescence a la racine
                 *
                 */
                arboresence.addAroberance(arboEECTab.get(arboEECTab.size() - 1));
            } else {
                /**
                 * si le parent du EEC est la racine je l'ajoute a ce dernier
                 */
                arboresence.getEccs().add(new EEC(fileContent.split("\n")[l - 1]));
            }

        }
        return arboresence;
    }
}
