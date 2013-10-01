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
import platinesshapi.entity.EEC;

/**
 *
 * @author h.el-hayouni
 */
public class EECDAO {
    private static final String SELECT_BILAN_REQUETE_BY_EEC_COLL = "SELECT libelle,type_eec,protocole,couple from eec where libelle=?";
    private static final String SELECT_BILAN_REQUETE_BY_EEC_TRAIT = "SELECT libelle,type_eec,type_trait from eec where libelle=?";

    private static EECDAO instance;
    private Connection conn;
    private PreparedStatement ps;

    private EECDAO() {
        this.conn = OracleConnection.getInstance().getConnexion();
    }

    public static EECDAO getInstance() {
        if (instance == null) {
            instance = new EECDAO();
        }
        return instance;
    }
    
    public BilanRequete getBilanRequeteCollecte(EEC eec)
    {
        BilanRequete bilanRequete = new BilanRequete();
        try {
            this.ps = this.conn.prepareStatement(SELECT_BILAN_REQUETE_BY_EEC_COLL);
            this.ps.setString(1, eec.getEec());
            ResultSet rs = this.ps.executeQuery();
            if(rs.next())
            {
                bilanRequete.setLibelle(rs.getString("libelle"));
                
                bilanRequete.setCouple(rs.getString("couple"));
                bilanRequete.setProtocole(rs.getString("protocole"));
                bilanRequete.setTypeEEC(rs.getString("type_eec"));
 
            }
        } catch (SQLException ex) {
            Logger.getLogger(EECDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return bilanRequete;
    }
    
    public BilanRequete getBilanRequeteTraitement(EEC eec)
    {
        BilanRequete bilanRequete = new BilanRequete();
        try {
            this.ps = this.conn.prepareStatement(SELECT_BILAN_REQUETE_BY_EEC_TRAIT);
            this.ps.setString(1, eec.getEec());
            ResultSet rs = this.ps.executeQuery();
            if(rs.next())
            {
                bilanRequete.setLibelle(rs.getString("libelle"));
                bilanRequete.setTypeEEC(rs.getString("type_eec"));
                bilanRequete.setTypeTraitement(rs.getString("type_trait"));
 
            }
        } catch (SQLException ex) {
            Logger.getLogger(EECDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return bilanRequete;
    }
}
