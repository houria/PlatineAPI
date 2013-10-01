/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package platinesshapi.entity;

/**
 *
 * @author h.el-hayouni
 */
public class CompteurDistribution {
    private String cas;
    private String dc_iecc;
    private String dc_recyclage;
    private String dc_correlation;
    private String dc_redistribution;
    private String dc_errone;
    private String dc_elimine;
    private String dc_divers;

    public CompteurDistribution() {
    }

    public CompteurDistribution(String cas, String dc_iecc, String dc_recyclage, String dc_correlation, String dc_redistribution, String dc_errone, String dc_elimine, String dc_divers) {
        this.cas = cas;
        this.dc_iecc = dc_iecc;
        this.dc_recyclage = dc_recyclage;
        this.dc_correlation = dc_correlation;
        this.dc_redistribution = dc_redistribution;
        this.dc_errone = dc_errone;
        this.dc_elimine = dc_elimine;
        this.dc_divers = dc_divers;
    }

    public String getCas() {
        return cas;
    }

    public void setCas(String cas) {
        this.cas = cas;
    }

    public String getDc_iecc() {
        return dc_iecc;
    }

    public void setDc_iecc(String dc_iecc) {
        this.dc_iecc = dc_iecc;
    }

    public String getDc_recyclage() {
        return dc_recyclage;
    }

    public void setDc_recyclage(String dc_recyclage) {
        this.dc_recyclage = dc_recyclage;
    }

    public String getDc_correlation() {
        return dc_correlation;
    }

    public void setDc_correlation(String dc_correlation) {
        this.dc_correlation = dc_correlation;
    }

    public String getDc_redistribution() {
        return dc_redistribution;
    }

    public void setDc_redistribution(String dc_redistribution) {
        this.dc_redistribution = dc_redistribution;
    }

    public String getDc_errone() {
        return dc_errone;
    }

    public void setDc_errone(String dc_errone) {
        this.dc_errone = dc_errone;
    }

    public String getDc_elimine() {
        return dc_elimine;
    }

    public void setDc_elimine(String dc_elimine) {
        this.dc_elimine = dc_elimine;
    }

    public String getDc_divers() {
        return dc_divers;
    }

    public void setDc_divers(String dc_divers) {
        this.dc_divers = dc_divers;
    }

    @Override
    public String toString() {
        return "CompteurDistribution{" + "cas=" + cas + ", dc_iecc=" + dc_iecc + ", dc_recyclage=" + dc_recyclage + ", dc_correlation=" + dc_correlation + ", dc_redistribution=" + dc_redistribution + ", dc_errone=" + dc_errone + ", dc_elimine=" + dc_elimine + ", dc_divers=" + dc_divers + '}';
    }
    
    
    
}
