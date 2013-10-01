/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package platinesshapi.entity;

/**
 *
 * @author h.el-hayouni
 */
public class CompteurTraitement {
    private String cas;
    private String dc_identifier;
    private String dc_distribution;
    private String dc_errone;
    private String dc_elimine;
    private String dc_divers;

    public CompteurTraitement(String cas, String dc_identifier, String dc_distribution, String dc_errone, String dc_elimine, String dc_divers) {
        this.cas = cas;
        this.dc_identifier = dc_identifier;
        this.dc_distribution = dc_distribution;
        this.dc_errone = dc_errone;
        this.dc_elimine = dc_elimine;
        this.dc_divers = dc_divers;
    }

    public CompteurTraitement() {
    }

    
    public String getCas() {
        return cas;
    }

    public void setCas(String cas) {
        this.cas = cas;
    }

    public String getDc_identifier() {
        return dc_identifier;
    }

    public void setDc_identifier(String dc_identifier) {
        this.dc_identifier = dc_identifier;
    }

    public String getDc_distribution() {
        return dc_distribution;
    }

    public void setDc_distribution(String dc_distribution) {
        this.dc_distribution = dc_distribution;
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
        return "CompteurTraitement{" + "cas=" + cas + ", dc_identifier=" + dc_identifier + ", dc_distribution=" + dc_distribution + ", dc_errone=" + dc_errone + ", dc_elimine=" + dc_elimine + ", dc_divers=" + dc_divers + '}';
    }
    
    
}
