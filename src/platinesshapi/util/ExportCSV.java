/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package platinesshapi.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import platinesshapi.entity.Alarme;

/**
 *
 * @author ali
 */
public class ExportCSV {
    
    private static ExportCSV instance;
    
    private ExportCSV(){
        
    }
    
    public static ExportCSV getInstance(){
        if(instance == null)
            instance = new ExportCSV();
        return instance;
    }
    
    public void exportAlarmes(String filePath, List<Alarme> alarmes, String separator, OutputStream stream){
        
        if(separator == null || separator == "")
            {
                separator = ";";
            }
        if(filePath !=null)
        {
            
            if(!filePath.contains(".csv"))
            {
                filePath = filePath+".csv";
            }
            
            try {

                OutputStreamWriter writer = new OutputStreamWriter(stream);
                for(Alarme alarme:alarmes)
                {
                    writer.append(alarme.getDate()+separator);
                    writer.append(alarme.getUrgence()+separator);
                    writer.append(alarme.getModule()+separator);
                    writer.append(alarme.getSource()+separator);
                    writer.append(alarme.getType_eec()+separator);
                    writer.append(alarme.getZone()+separator);
                    writer.append(""+alarme.getLibelle());
                    writer.append("\n");
                }
                writer.flush();
                writer.close();
            } catch (IOException ex) {
                Logger.getLogger(ExportCSV.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
