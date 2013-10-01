/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package platinesshapi.entity;

/**
 *
 * @author Houria
 */
public class AlarmesActives {
    private int totalASI, totalAID, totalAII;

    public AlarmesActives() {
    }

    public AlarmesActives(int totalASI, int totalAID, int totalAII) {
        this.totalASI = totalASI;
        this.totalAID = totalAID;
        this.totalAII = totalAII;
    }

    public int getTotalASI() {
        return totalASI;
    }

    public void setTotalASI(int totalASI) {
        this.totalASI = totalASI;
    }

    public int getTotalAID() {
        return totalAID;
    }

    public void setTotalAID(int totalAID) {
        this.totalAID = totalAID;
    }

    public int getTotalAII() {
        return totalAII;
    }

    public void setTotalAII(int totalAII) {
        this.totalAII = totalAII;
    }

    @Override
    public String toString() {
        return "AlarmesActives{" + "totalASI=" + totalASI + ", totalAID=" + totalAID + ", totalAII=" + totalAII + '}';
    }
    
    
}
