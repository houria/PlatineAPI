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
public class BilanSuppervision {
    
    private int volumeEntrant;
    private int volumeSortant;
    private int articleEntrant;
    private int articleSortant;
    private int tempsMoyenTreversee;
    private int totalArticlesErrone;

    public BilanSuppervision() {
    }

    public BilanSuppervision(int volumeEntrant, int volumeSortant, int articleEntrant, int articleSortant, int tempsMoyenTreversee, int totalArticlesErrone) {
        this.volumeEntrant = volumeEntrant;
        this.volumeSortant = volumeSortant;
        this.articleEntrant = articleEntrant;
        this.articleSortant = articleSortant;
        this.tempsMoyenTreversee = tempsMoyenTreversee;
        this.totalArticlesErrone = totalArticlesErrone;
    }

    public int getVolumeEntrant() {
        return volumeEntrant;
    }

    public void setVolumeEntrant(int volumeEntrant) {
        this.volumeEntrant = volumeEntrant;
    }

    public int getVolumeSortant() {
        return volumeSortant;
    }

    public void setVolumeSortant(int volumeSortant) {
        this.volumeSortant = volumeSortant;
    }

    public int getArticleEntrant() {
        return articleEntrant;
    }

    public void setArticleEntrant(int articleEntrant) {
        this.articleEntrant = articleEntrant;
    }

    public int getArticleSortant() {
        return articleSortant;
    }

    public void setArticleSortant(int articleSortant) {
        this.articleSortant = articleSortant;
    }

    public int getTempsMoyenTreversee() {
        return tempsMoyenTreversee;
    }

    public void setTempsMoyenTreversee(int tempsMoyenTreversee) {
        this.tempsMoyenTreversee = tempsMoyenTreversee;
    }

    public int getTotalArticlesErrone() {
        return totalArticlesErrone;
    }

    public void setTotalArticlesErrone(int totalArticlesErrone) {
        this.totalArticlesErrone = totalArticlesErrone;
    }
    
    

    @Override
    public String toString() {
        return "BilanSuppervision{" + "volumeEntrant=" + volumeEntrant + ", volumeSortant=" + volumeSortant + ", articleEntrant=" + articleEntrant + ", articleSortant=" + articleSortant + ", tempsMoyenTreversee=" + tempsMoyenTreversee + ", totalArticlesErrone=" + totalArticlesErrone + '}';
    }
    
    
    
}
