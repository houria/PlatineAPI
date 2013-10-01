/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package platinesshapi.entity;

import java.util.Date;

/**
 *
 * @author houria
 */
public class Alarme {

    private String date;
    private String urgence;
    private int id_alarme;
    private String module;
    private String source;
    private String type_eec;
    private String zone;
    private String libelle;

    public Alarme() {
    }

    public Alarme(String date, String urgence, int id_alarme, String module, String source, String type_eec, String zone, String libelle) {
        this.date = date;
        this.urgence = urgence;
        this.id_alarme = id_alarme;
        this.module = module;
        this.source = source;
        this.type_eec = type_eec;
        this.zone = zone;
        this.libelle = libelle;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUrgence() {
        return urgence;
    }

    public void setUrgence(String urgence) {
        this.urgence = urgence;
    }

    public int getId_alarme() {
        return id_alarme;
    }

    public void setId_alarme(int id_alarme) {
        this.id_alarme = id_alarme;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getType_eec() {
        return type_eec;
    }

    public void setType_eec(String type_eec) {
        this.type_eec = type_eec;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    @Override
    public String toString() {
        return "AlarmeEEC{" + "date=" + date + ", urgence=" + urgence + ", id_alarme=" + id_alarme + ", module=" + module + ", source=" + source + ", type_eec=" + type_eec + ", zone=" + zone + ", libelle=" + libelle + '}';
    }
    
    

}
