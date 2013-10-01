/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package platinesshapi.util;

import platinesshapi.entity.BilanSuppervision;

/**
 *
 * @author h.el-hayouni
 */
public class ReadCSVPlatine {

    public static BilanSuppervision format_csv_bill_sup(String csvContent) {
        BilanSuppervision bilanSuppervision = new BilanSuppervision();

        if (csvContent != null) {
            String[] lines = csvContent.split("\n");
            if (lines.length > 0) {
                try {

                    bilanSuppervision.setVolumeEntrant(Integer.parseInt(lines[0].split(";")[1]));
                    bilanSuppervision.setVolumeSortant(Integer.parseInt(lines[1].split(";")[1]));
                    bilanSuppervision.setArticleEntrant(Integer.parseInt(lines[2].split(";")[1]));
                    bilanSuppervision.setArticleSortant(Integer.parseInt(lines[3].split(";")[1]));
                    bilanSuppervision.setTempsMoyenTreversee(Integer.parseInt(lines[4].split(";")[1]));
                    bilanSuppervision.setTotalArticlesErrone(Integer.parseInt(lines[5].split(";")[1]));

                } catch (NumberFormatException e) {

                }
            }

        }
        return bilanSuppervision;
    }

}
