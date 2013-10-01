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
 * @author h.el-hayouni
 */
public class ArboECC {

    private String nomRacine;
    private List<ArboECC> fils;
    private List<ECC> eccs;
    private int id;

    public ArboECC() {
        this.fils = new ArrayList<ArboECC>();
        this.eccs = new ArrayList<ECC>();
    }

    public ArboECC(String nomRacin) {
        this.nomRacine = nomRacin;
        this.fils = new ArrayList<ArboECC>();
        this.eccs = new ArrayList<ECC>();
    }

    public ArboECC(String nomRacine, List<ArboECC> fils, List<ECC> eccs, int id) {
        this.nomRacine = nomRacine;
        this.fils = fils;
        this.eccs = eccs;
        this.id=id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    
    
    /**
     * Cherche et retourne l'arborescene du premier sous niveau
     *
     * @param nomRacine nom de l'arborescence a rechercher
     * @return l'arborescence
     */
    public ArboECC findArboECC(String nomRacine) {
        ArboECC target = null;
        for (ArboECC arboECC : this.fils) {
            if (arboECC.nomRacine.equals(nomRacine)) {
                target = arboECC;
            }
        }
        return target;
    }

    /**
     * Cherche et retourne l'arborescence sur tout les sous niveaux
     *
     * @param nomRacine
     * @return
     */
    public ArboECC findDeepArboECC(String nomRacine) {
        ArboECC target = findArboECC(nomRacine);
        if (target == null) {
            for (ArboECC arbo : this.fils) {
                target = arbo.findDeepArboECC(nomRacine);
                if (target != null) {
                    break;
                }
            }
        }
        return target;
    }

    public void addAroberance(ArboECC arboECC) {
        if (findArboECC(arboECC.getNomRacine()) == null) {
            this.fils.add(arboECC);
        } else {
            if (arboECC.getFils().size() != 0) {
                findArboECC(arboECC.getNomRacine()).addAroberance(arboECC.getFils().get(0));
            }
        }
    }

    public String getNomRacine() {
        return nomRacine;
    }

    public void setNomRacine(String nomRacine) {
        this.nomRacine = nomRacine;
    }

    public List<ArboECC> getFils() {
        return fils;
    }

    public void setFils(List<ArboECC> fils) {
        this.fils = fils;
    }

    public List<ECC> getEccs() {
        return eccs;
    }

    public void setEccs(List<ECC> eccs) {
        this.eccs = eccs;
    }

    /**
     * Retourne tous les ecc de l'arborescence y compris ceux des fils et des
     * petits fils ..Etc
     *
     * @return
     */
    public List<ECC> getAllECC() {
        List<ECC> liste = new ArrayList<ECC>();

        if (this.eccs != null) {
            liste.addAll(this.eccs);
        }
        if (this.fils != null) {
            for (ArboECC arbo : this.fils) {
                liste.addAll(arbo.getAllECC());
            }
        }
        return liste;
    }

    @Override
    public String toString() {
        return "ArboECC{" + "nomRacine=" + nomRacine + ", fils=" + fils + ", eccs=" + eccs + '}';
    }

    public void afficherArbo(int niveau) {
        for (int i = 0; i < niveau; i++) {
            System.out.print("\t");
        }
        System.out.println("-*" + this.nomRacine);
        for (ECC ecc : eccs) {
            for (int i = 0; i < niveau; i++) {
                System.out.print("\t");
            }
            System.out.println("-" + ecc.getEcc());
        }
        for (ArboECC arboECC : fils) {
            arboECC.afficherArbo(niveau + 1);
        }
    }
}
