/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package platinesshapi.entity;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Houria
 */
public class ArboEEC {

    private String nomRacine;
    private List<ArboEEC> fils;
    private List<EEC> eccs;

    public ArboEEC() {
        this.fils = new ArrayList<ArboEEC>();
        this.eccs = new ArrayList<EEC>();
    }
    public ArboEEC(String nomRacin) {
        this.nomRacine = nomRacin;
        this.fils = new ArrayList<ArboEEC>();
        this.eccs = new ArrayList<EEC>();
    }

    public ArboEEC(String nomRacine, List<ArboEEC> fils, List<EEC> eccs) {
        this.nomRacine = nomRacine;
        this.fils = fils;
        this.eccs = eccs;
    }

    /**
     * Cherche et retourne l'arborescene du premier sous niveau
     * @param nomRacine nom de l'arborescence a rechercher
     * @return l'arborescence
     */
    public ArboEEC findArboEEC(String nomRacine) {
        ArboEEC target = null;
        for (ArboEEC arboEEC : this.fils) {
            if (arboEEC.nomRacine.equals(nomRacine)) {
                target = arboEEC;
            }
        }
        return target;
    }
    
    /**
     * Cherche et retourne l'arborescence sur tout les sous niveaux
     * @param nomRacine
     * @return 
     */
    public ArboEEC findDeepArboEEC(String nomRacine)
    {
        ArboEEC target = findArboEEC(nomRacine);
        if(target == null)
        {
            for(ArboEEC arbo:this.fils)
            {
                target = arbo.findDeepArboEEC(nomRacine);
                if(target !=null)
                    break;
            }
        }
        return target;
    }
    
    public void addAroberance(ArboEEC arboEEC)
    {
        if(findArboEEC(arboEEC.getNomRacine())==null)
        {
            this.fils.add(arboEEC);
        }
        else
        {
            findArboEEC(arboEEC.getNomRacine()).addAroberance(arboEEC.getFils().get(0));
        }
    }

    public String getNomRacine() {
        return nomRacine;
    }

    public void setNomRacine(String nomRacine) {
        this.nomRacine = nomRacine;
    }

    public List<ArboEEC> getFils() {
        return fils;
    }

    public void setFils(List<ArboEEC> fils) {
        this.fils = fils;
    }

    public List<EEC> getEccs() {
        return eccs;
    }

    public void setEccs(List<EEC> eccs) {
        this.eccs = eccs;
    }
    
    /**
     * Retourne tous les eec de l'arborescence y compris ceux des fils et des petits fils ..Etc
     * @return 
     */
    public List<EEC> getAllEEC()
    {
        List<EEC> liste = new ArrayList<EEC>();
        
        if(this.eccs!=null)
        {
            liste.addAll(this.eccs);
        }
        if(this.fils !=null)
        {
            for(ArboEEC arbo:this.fils)
            {
                liste.addAll(arbo.getAllEEC());
            }
        }
        return liste;
    }

    @Override
    public String toString() {
        return "ArboEEC{" + "nomRacine=" + nomRacine + ", fils=" + fils + ", eccs=" + eccs + '}';
    }

    public void afficherArbo(int niveau)
    {
        for(int i=0;i<niveau;i++)
        {
            System.out.print("\t");
        }
        System.out.println("-*"+this.nomRacine);
        for(EEC eec:eccs)
        {
            for(int i=0;i<niveau;i++)
            {
                System.out.print("\t");
            }
            System.out.println("-"+eec.getEec());
        }
        for(ArboEEC arboEEC:fils)
        {
            arboEEC.afficherArbo(niveau+1);
        }
    }
    
}
