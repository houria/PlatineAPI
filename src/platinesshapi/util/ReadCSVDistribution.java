/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package platinesshapi.util;

import java.util.ArrayList;
import java.util.List;
import platinesshapi.entity.BilanDistribution;
import platinesshapi.entity.CompteurDistribution;
import platinesshapi.entity.EEC;

/**
 *
 * @author h.el-hayouni
 */
public class ReadCSVDistribution {

    public static BilanDistribution format_csv_bill_dist(String csvContent) {

        BilanDistribution bilanDistribution = new BilanDistribution();
        if (csvContent != null) {
            String[] lines = csvContent.split("\n");
            if (lines.length > 0) {
                try {
                    bilanDistribution.setSortieDist(Integer.parseInt(lines[0].split(";")[1]));
                    bilanDistribution.setInterfaceECC(Integer.parseInt(lines[1].split(";")[1]));
                    bilanDistribution.setRecyclage(Integer.parseInt(lines[2].split(";")[1]));
                    bilanDistribution.setCorrelation(Integer.parseInt(lines[3].split(";")[1]));
                    bilanDistribution.setRedistribution(Integer.parseInt(lines[4].split(";")[1]));
                    bilanDistribution.setErrones(Integer.parseInt(lines[5].split(";")[1]));
                    bilanDistribution.setElimines(Integer.parseInt(lines[6].split(";")[1]));
                    bilanDistribution.setDivers(Integer.parseInt(lines[7].split(";")[1]));
                } catch (NumberFormatException e) {
                }
            }
        }

        return bilanDistribution;
    }

    public static BilanDistribution format_csv_taux_mult_dist(String csvContent) {

        BilanDistribution bilanDistribution = new BilanDistribution();
        if (csvContent != null) {
            String[] lines = csvContent.split("\n");
            try {

                bilanDistribution.setTauxMultipDC((float) Integer.parseInt(lines[0].split(";")[4]) / 100);
            } catch (NumberFormatException e) {
                bilanDistribution.setTauxMultipDC(0);
            }
        } else {
            bilanDistribution.setTauxMultipDC(0);
        }

        return bilanDistribution;
    }

    public static List<CompteurDistribution> format_csv_list_cas_article_dist(String csvContent, EEC eecs) {

        List<CompteurDistribution> compteurs = new ArrayList<CompteurDistribution>();
        String[] lines = csvContent.split("\n");

        for (String ligne : lines) {

            CompteurDistribution compteur = new CompteurDistribution();
            compteur.setCas(ligne.split(";")[0]);
            compteur.setDc_iecc(ligne.split(";")[1].equalsIgnoreCase(" ") || ligne.split(";")[1].equalsIgnoreCase("") ? "0" : ligne.split(";")[1]);
            compteur.setDc_recyclage(ligne.split(";")[2].equalsIgnoreCase(" ") || ligne.split(";")[2].equalsIgnoreCase(" ") ? "0" : ligne.split(";")[2]);
            compteur.setDc_correlation(ligne.split(";")[3].equalsIgnoreCase(" ") || ligne.split(";")[3].equalsIgnoreCase("") ? "0" : ligne.split(";")[3]);
            compteur.setDc_redistribution(ligne.split(";")[4].equalsIgnoreCase(" ") || ligne.split(";")[4].equalsIgnoreCase("") ? "0" : ligne.split(";")[4]);
            compteur.setDc_errone(ligne.split(";")[5].equalsIgnoreCase(" ") || ligne.split(";")[5].equalsIgnoreCase("") ? "0" : ligne.split(";")[5]);
            compteur.setDc_elimine(ligne.split(";")[6].equalsIgnoreCase(" ") || ligne.split(";")[6].equalsIgnoreCase("") ? "0" : ligne.split(";")[6]);
            compteur.setDc_divers(ligne.split(";")[7].equalsIgnoreCase(" ") || ligne.split(";")[7].equalsIgnoreCase("") ? "0" : ligne.split(";")[7]);

            compteurs.add(compteur);
        }

        return compteurs;
    }

}
