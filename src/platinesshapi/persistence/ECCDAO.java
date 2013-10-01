/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package platinesshapi.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import platinesshapi.entity.BilanRequete;
import platinesshapi.entity.ECC;

/**
 *
 * @author h.el-hayouni
 */
public class ECCDAO {
    private static final String SELECT_BILAN_REQUETE_BY_ECC_INTERFACE = "SELECT id_ecc,etat_echange,etat_objet from ctx_ecc where id_ecc=?";
    private static final String GET_ID_ECC = "SELECT IDECC from ecc where LIBELLE=?";
    private static final String SELECT_BILAN_LOT_BY_ECC_INTERFACE = "SELECT nom_lot,ind_creation,etat_lot,date_debut_lot,date_fin_lot,date_ferm_pre,date_ferm_eff,date_classement,nb_dc,date_fin_1ere_emission from ctx_lot";
    private static ECCDAO instance;
    private Connection conn, connIECC;
    private PreparedStatement ps;

    private ECCDAO() {
        this.conn = OracleConnection.getInstance().getConnexion();
        this.connIECC = OracleConnection.getInstance().getConnexionIECC();
    }

    public static ECCDAO getInstance() {
        if (instance == null) {
            instance = new ECCDAO();
        }
        return instance;
    }
    public BilanRequete getBilanRequeteInterface(ECC ecc)
    {
        BilanRequete bilanRequete = new BilanRequete();
        try {
            this.ps = this.connIECC.prepareStatement(SELECT_BILAN_REQUETE_BY_ECC_INTERFACE);
            this.ps.setInt(1, ecc.getId());
            ResultSet rs = this.ps.executeQuery();
            if(rs.next())
            {
                
                bilanRequete.setIdECC(rs.getString("id_ecc"));
                bilanRequete.setEtatEchange(rs.getString("etat_echange"));
                bilanRequete.setEtatObjet(rs.getString("etat_objet"));
                //bilanRequete.setTypeEEC(rs.getString("type_eec"));
 
            }
        } catch (SQLException ex) {
            Logger.getLogger(ECCDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return bilanRequete;
    }
    
    public ECC getIdECC(ECC ecc)
    {
        try {
            this.ps = this.conn.prepareStatement(GET_ID_ECC);
            this.ps.setString(1, ecc.getEcc());
            ResultSet rs = this.ps.executeQuery();
            if(rs.next())
            {
                ecc.setId(rs.getInt("IDECC"));
 
            }
        } catch (SQLException ex) {
            Logger.getLogger(ECCDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return ecc;
    }
    
    
    public BilanRequete getBilanLotIECC(ECC ecc)
    {
        BilanRequete bilanRequete = new BilanRequete();
        try {
            this.ps = this.connIECC.prepareStatement(SELECT_BILAN_LOT_BY_ECC_INTERFACE);
            this.ps.setString(1, ecc.getEcc());
            ResultSet rs = this.ps.executeQuery();
            if(rs.next())
            {            
                bilanRequete.setNomLot(rs.getString("nom_lot"));
                bilanRequete.setIndCreation(rs.getString("ind_creation"));
                bilanRequete.setEtatLot(rs.getString("etat_lot"));
                bilanRequete.setDateDebLot(rs.getString("date_debut_lot"));
                bilanRequete.setDateFinLot(rs.getString("date_fin_lot"));
                bilanRequete.setDatefermPre(rs.getString("date_ferm_pre"));
                bilanRequete.setDatefermEff(rs.getString("date_ferm_eff  "));
                bilanRequete.setDateClassement(rs.getString("date_classement"));
                bilanRequete.setNbreDC(rs.getString("nb_dc"));
                bilanRequete.setDateFinEmission(rs.getString("date_fin_1ere_emission"));
 
            }
        } catch (SQLException ex) {
            Logger.getLogger(ECCDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return bilanRequete;
    }
    

    
}
