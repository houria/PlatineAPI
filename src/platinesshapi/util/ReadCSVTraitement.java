/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package platinesshapi.util;

import java.util.ArrayList;
import java.util.List;
import platinesshapi.entity.BilanTraitement;
import platinesshapi.entity.CompteurTraitement;
import platinesshapi.entity.EEC;

/**
 *
 * @author h.el-hayouni
 */
public class ReadCSVTraitement {

    public static BilanTraitement format_csv_bill_trait_tab(String csvContent) {
        System.out.println("csv content " + csvContent);
        BilanTraitement bilanTraitement = new BilanTraitement();
        if (csvContent != null) {
            String[] lines = csvContent.split("\n");
            if (lines.length > 0) {
                try {

                    bilanTraitement.setNbDCIdentifies(Integer.parseInt(lines[0].split(";")[1]));
                    bilanTraitement.setDcSortie(Integer.parseInt(lines[1].split(";")[1]));
                    bilanTraitement.setNbBlocRejetés(Integer.parseInt(lines[2].split(";")[1]));
                    bilanTraitement.setNbOctetsRejetes(Integer.parseInt(lines[3].split(";")[1]));
                    bilanTraitement.setNbBlocIdentifies(Integer.parseInt(lines[4].split(";")[1]));
                    bilanTraitement.setVolumeDonneesIndetifies(Integer.parseInt(lines[5].split(";")[1]));

                    bilanTraitement.setDcVersDistribution(Integer.parseInt(lines[6].split(";")[1]));
                    bilanTraitement.setDcErronnes(Integer.parseInt(lines[7].split(";")[1]));
                    bilanTraitement.setDcElimines(Integer.parseInt(lines[8].split(";")[1]));
                    bilanTraitement.setDcDivers(Integer.parseInt(lines[9].split(";")[1]));
                } catch (NumberFormatException e) {
                    
                }
            }
 
        }

        return bilanTraitement;
    }

    public static BilanTraitement format_csv_bill_trait_neec(String csvContent) {
        BilanTraitement bilanTraitement = new BilanTraitement();

        bilanTraitement.setNbBlocIdentifies(0);
        bilanTraitement.setVolumeDonneesIndetifies(0);
        bilanTraitement.setNbBlocRejetés(0);
        bilanTraitement.setNbOctetsRejetes(0);

        if (csvContent != null) {
            if (csvContent.split("\n").length > 0) {
                String[] lines = csvContent.split("\n");

                try {

                    bilanTraitement.setNbBlocIdentifies(Integer.parseInt(lines[0].split(";")[1].equalsIgnoreCase(" ") || lines[0].split(";")[1].equalsIgnoreCase("") ? "0" : lines[0].split(";")[1]));
                    bilanTraitement.setVolumeDonneesIndetifies(Integer.parseInt(lines[0].split(";")[2].equalsIgnoreCase(" ") || lines[0].split(";")[2].equalsIgnoreCase("") ? "0" : lines[0].split(";")[2]));
                    bilanTraitement.setNbBlocRejetés(Integer.parseInt(lines[0].split(";")[3].equalsIgnoreCase(" ") || lines[0].split(";")[3].equalsIgnoreCase("") ? "0" : lines[0].split(";")[3]));
                    bilanTraitement.setNbOctetsRejetes(Integer.parseInt(lines[0].split(";")[4].equalsIgnoreCase(" ") || lines[0].split(";")[4].equalsIgnoreCase("") ? "0" : lines[0].split(";")[4]));
                } catch (NumberFormatException e) {
                }
            }

        }
        return bilanTraitement;

    }

    public static List<CompteurTraitement> format_csv_list_cas_article(String csvContent, EEC eecs) {

        String[] lines = csvContent.split("\n");

        List<CompteurTraitement> compteurs = new ArrayList<CompteurTraitement>();

        for (String ligne : lines) {

            CompteurTraitement compteur = new CompteurTraitement();
            compteur.setCas(ligne.split(";")[0]);
            compteur.setDc_identifier(ligne.split(";")[1]);
            compteur.setDc_distribution(ligne.split(";")[2]);
            compteur.setDc_errone(ligne.split(";")[3]);
            compteur.setDc_elimine(ligne.split(";")[4]);
            compteur.setDc_divers(ligne.split(";")[5]);

            compteurs.add(compteur);
        }

        return compteurs;
    }

    public static BilanTraitement format_csv_debit_donnees_identifier_trait(String csvContent) {
        BilanTraitement bilanTraitement = new BilanTraitement();
        if (csvContent != null) {
            String[] lines = csvContent.split("\n");
            try {
                bilanTraitement.setDebitDonneesIdentifier(Integer.parseInt(lines[0].split(";")[4]));

            } catch (NumberFormatException e) {
                bilanTraitement.setDebitDonneesIdentifier(0);

            }
        } else {
            bilanTraitement.setDebitDonneesIdentifier(0);
        }

        return bilanTraitement;

    }

    public static BilanTraitement format_csv_debit_dc_identifier_trait(String csvContent) {

        BilanTraitement bilanTraitement = new BilanTraitement();
        if (csvContent != null) {
            String[] lines = csvContent.split("\n");
            try {
                bilanTraitement.setDebitDCIdentifier(Integer.parseInt(lines[0].split(";")[4]));
            } catch (NumberFormatException e) {
                bilanTraitement.setDebitDCIdentifier(0);
            }
        } else {
            bilanTraitement.setDebitDCIdentifier(0);
        }
        return bilanTraitement;
    }
}
