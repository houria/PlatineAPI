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
public class ArticleDistribution {
    
    private String casArticleDist;
    private int interfaceECC;
    private int recyclage;
    private int correlation;
    private int redistribution;
    private int errones;
    
    private int elimines;
    private int divers;

    public ArticleDistribution() {
    }

    public ArticleDistribution(String casArticleDist, int interfaceECC, int recyclage, int correlation, int redistribution, int errones, int elimines, int divers) {
        this.casArticleDist = casArticleDist;
        this.interfaceECC = interfaceECC;
        this.recyclage = recyclage;
        this.correlation = correlation;
        this.redistribution = redistribution;
        this.errones = errones;
        this.elimines = elimines;
        this.divers = divers;
    }

    public String getCasArticleDist() {
        return casArticleDist;
    }

    public void setCasArticleDist(String casArticleDist) {
        this.casArticleDist = casArticleDist;
    }

    public int getInterfaceECC() {
        return interfaceECC;
    }

    public void setInterfaceECC(int interfaceECC) {
        this.interfaceECC = interfaceECC;
    }

    public int getRecyclage() {
        return recyclage;
    }

    public void setRecyclage(int recyclage) {
        this.recyclage = recyclage;
    }

    public int getCorrelation() {
        return correlation;
    }

    public void setCorrelation(int correlation) {
        this.correlation = correlation;
    }

    public int getRedistribution() {
        return redistribution;
    }

    public void setRedistribution(int redistribution) {
        this.redistribution = redistribution;
    }

    public int getErrones() {
        return errones;
    }

    public void setErrones(int errones) {
        this.errones = errones;
    }

    public int getElimines() {
        return elimines;
    }

    public void setElimines(int elimines) {
        this.elimines = elimines;
    }

    public int getDivers() {
        return divers;
    }

    public void setDivers(int divers) {
        this.divers = divers;
    }

    @Override
    public String toString() {
        return "ArticleDistribution{" + "casArticleDist=" + casArticleDist + ", interfaceECC=" + interfaceECC + ", recyclage=" + recyclage + ", correlation=" + correlation + ", redistribution=" + redistribution + ", errones=" + errones + ", elimines=" + elimines + ", divers=" + divers + '}';
    }

    
    
}
