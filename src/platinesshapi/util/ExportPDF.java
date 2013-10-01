/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package platinesshapi.util;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import platinesshapi.entity.Alarme;

/**
 *
 * @author ali
 */
public class ExportPDF {

    private static ExportPDF instance;
    private static Font H1SOFRECOM = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD, BaseColor.ORANGE);
    private static Font H1 = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
    private static Font H3 = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.BOLD);
    private static Font FONTCELL = new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.NORMAL);

    private ExportPDF() {
    }

    public static ExportPDF getInstance() {

        if (instance == null) {
            instance = new ExportPDF();
        }

        return instance;
    }

    public void exportAlarmes(String filename, List<Alarme> alarmes, OutputStream stream) {
        if (filename == null) {
            filename = "alarmes.pdf";
        }
        if (!filename.contains(".pdf")) {
            filename = filename + ".pdf";
        }
        Document document = new Document();

        document.setPageSize(PageSize.A4.rotate());
        document.setMargins(10, 10, 10, 10);
        document.setMarginMirroring(true);
        try {
            PdfWriter.getInstance(document, stream);

            document.open();

            /**
             * Ajout des metadata
             */
            Date date = new Date();
            document.addTitle("Alarmes exportés");
            document.addSubject("PlatineIHM: Export des alarmes du " + date);

            Paragraph paragraph = new Paragraph();

            /**
             * Ajout du header de la page
             */
            Image image = Image.getInstance(new URL("http://sofrecom-staging.s3.amazonaws.com/assets/logo-5dec1ed2c218da94f4cb0f0d9c86e451.jpg"));
            paragraph.add(image);
            
            paragraph.add(new Paragraph("Alarmes exportés", H1));
            addEmptyLine(paragraph, 1);
            paragraph.add(new Paragraph("Export des alarmes du " + date, H3));

            /**
             * Ajout du tableau d'alarmes
             */
            PdfPTable table = new PdfPTable(8);
            table.setWidthPercentage(100);
            
            addTableHeader(table,new String[]{"DATE","URGENCE","MODULE","SOURCE","TYPE_EEC","ZONE","LIBELLE"});
            
            for(Alarme alarme:alarmes)
            {
                
                table.addCell(new Phrase(""+alarme.getDate(),FONTCELL));
                table.addCell(new Phrase(""+alarme.getUrgence(),FONTCELL));
                table.addCell(new Phrase(""+alarme.getModule(),FONTCELL));
                table.addCell(new Phrase(""+alarme.getSource(),FONTCELL));
                table.addCell(new Phrase(""+alarme.getType_eec(),FONTCELL));
                table.addCell(new Phrase(""+alarme.getZone(),FONTCELL));
                table.addCell(new Phrase(""+alarme.getLibelle(),FONTCELL));
            }

            /**
             * Definir la largeur des colones
             */
            float[] columnWidths = new float[] {40f, 20f, 40f,40f,40f,40f,170f};
            table.setWidths(columnWidths);
            
            addEmptyLine(paragraph, 2);
            paragraph.add(table);
            
            document.add(paragraph);
            document.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ExportPDF.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(ExportPDF.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(ExportPDF.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ExportPDF.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Ajouter des sauts ligne
     * @param paragraph
     * @param number 
     */
    private void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }

    /**
     * Ajouter le header a un tableau
     * @param table
     * @param headers 
     */
    private void addTableHeader(PdfPTable table, String[] headers) {

        for (String head : headers) {
            PdfPCell c1 = new PdfPCell(new Phrase(head, FONTCELL));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(c1);
        }
        
        table.setHeaderRows(1);

    }
}
