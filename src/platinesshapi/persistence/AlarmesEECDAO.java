/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package platinesshapi.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import oracle.sql.DATE;
import platinesshapi.entity.Alarme;

/**
 *
 * @author h.el-hayouni
 */
public class AlarmesEECDAO {
    private static final String SELECT_ALL_ALARME = "SELECT idAlarme,module,typeEEC,dateDeclench,Urgence,idSrc, Libelle, zoneGeo FROM (SELECT idAlarme,module,typeEEC,dateDeclench,Urgence,idSrc, Libelle, zoneGeo FROM alarmes order by dateDeclench desc)where rownum<=?";
    private static final String SELECT_ALL_ALARMES_PER_PAGE = "select rownum as rn, idAlarme,module,typeEEC,dateDeclench,Urgence,idSrc, Libelle, zoneGeo from "
            + "(select rownum as rn, idAlarme,module,typeEEC,dateDeclench,Urgence,idSrc, Libelle, zoneGeo from "
            + "(select rownum as rn,idAlarme,module,typeEEC,dateDeclench,Urgence,idSrc, Libelle, zoneGeo from alarmes order by dateDeclench desc))"
            + " where rn > ?   and rn <= ? AND rownum<=200";
    private static final String SELECT_ALARMES_BY_MODULE = "SELECT idAlarme,module,typeEEC,dateDeclench,Urgence,idSrc, Libelle, zoneGeo FROM (SELECT idAlarme,module,typeEEC,dateDeclench,Urgence,idSrc, Libelle, zoneGeo FROM alarmes WHERE module=? order by dateDeclench desc)where rownum<=? ";
    private static final String COUNT_ALL_ALARMES = "SELECT count(*) FROM alarmes";
    private static final String COUNT_ALARMES_BY_MODULE = "SELECT count(*) FROM alarmes WHERE module=?";

    private static final String SELECT_ALARMES_BY_PAGE = "select rownum as rn, idAlarme,module,typeEEC,dateDeclench,Urgence,idSrc, Libelle, zoneGeo from "
            + "(select rownum as rn, idAlarme,module,typeEEC,dateDeclench,Urgence,idSrc, Libelle, zoneGeo from "
            + "(select rownum as rn,idAlarme,module,typeEEC,dateDeclench,Urgence,idSrc, Libelle, zoneGeo from alarmes WHERE module=? order by dateDeclench desc))"
            + " where rn > ?   and rn <= ? AND rownum<=200";

    private static AlarmesEECDAO instance;
    private Connection conn;
    private PreparedStatement ps;

    private AlarmesEECDAO() {
        this.conn = OracleConnection.getInstance().getConnexion();
    }

    public static AlarmesEECDAO getInstance() {
        if (instance == null) {
            instance = new AlarmesEECDAO();
        }
        return instance;
    }

    public int getCountAllAlarmes() {
        int count = 0;
        try {
            this.ps = this.conn.prepareStatement(COUNT_ALL_ALARMES);
            ResultSet rs = this.ps.executeQuery();       
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AlarmesEECDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (count > 200) {
            count = 200;
        }
        return count;
    }

    public int getCountAlarmesByModule(String module) {
        int count = 0;
        try {
            this.ps = this.conn.prepareStatement(COUNT_ALARMES_BY_MODULE);
            this.ps.setString(1, module);
            ResultSet rs = this.ps.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AlarmesEECDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (count > 200) {
            count = 200;
        }
        return count;
    }
    public List<Alarme> getAllAlarmesPerPage(int page, int limit) {
        List<Alarme> liste = new ArrayList<Alarme>();

        try {
            this.ps = this.conn.prepareStatement(SELECT_ALL_ALARMES_PER_PAGE); 
            this.ps.setInt(1, (page - 1) * limit);
            this.ps.setInt(2, ((page - 1) * limit) + limit);
            ResultSet rs = this.ps.executeQuery();
            while (rs.next()) {

                Alarme alarmeEEC = new Alarme();

                alarmeEEC.setId_alarme(rs.getInt("idAlarme"));
                alarmeEEC.setModule(rs.getString("module"));
                alarmeEEC.setType_eec(rs.getString("typeEEC"));
                alarmeEEC.setDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(rs.getInt("dateDeclench") * 1000L)));

                if (rs.getInt("Urgence") == 1) {
                    alarmeEEC.setUrgence("ASI");
                }
                if (rs.getInt("Urgence") == 2) {
                    alarmeEEC.setUrgence("AID");
                }
                if (rs.getInt("Urgence") == 3) {
                    alarmeEEC.setUrgence("AII");
                }

                alarmeEEC.setSource(rs.getString("idSrc"));
                alarmeEEC.setLibelle(rs.getString("Libelle"));
                alarmeEEC.setZone(rs.getString("zoneGeo"));

                liste.add(alarmeEEC);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AlarmesEECDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return liste;
    }
    public List<Alarme> getAlarmesPerPage(String module, int page, int limit) {
        List<Alarme> liste = new ArrayList<Alarme>();

        try {
            this.ps = this.conn.prepareStatement(SELECT_ALARMES_BY_PAGE);
            this.ps.setString(1, module);
            this.ps.setInt(2, (page - 1) * limit);
            this.ps.setInt(3, ((page - 1) * limit) + limit);
            ResultSet rs = this.ps.executeQuery();
            while (rs.next()) {

                Alarme alarmeEEC = new Alarme();

                alarmeEEC.setId_alarme(rs.getInt("idAlarme"));
                alarmeEEC.setModule(rs.getString("module"));
                alarmeEEC.setType_eec(rs.getString("typeEEC"));
                alarmeEEC.setDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(rs.getInt("dateDeclench") * 1000L)));

                if (rs.getInt("Urgence") == 1) {
                    alarmeEEC.setUrgence("ASI");
                }
                if (rs.getInt("Urgence") == 2) {
                    alarmeEEC.setUrgence("AID");
                }
                if (rs.getInt("Urgence") == 3) {
                    alarmeEEC.setUrgence("AII");
                }

                alarmeEEC.setSource(rs.getString("idSrc"));
                alarmeEEC.setLibelle(rs.getString("Libelle"));
                alarmeEEC.setZone(rs.getString("zoneGeo"));

                liste.add(alarmeEEC);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AlarmesEECDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return liste;
    }

    public List<Alarme> getAlarmesByModule(String module, int limit) {
        List<Alarme> liste = new ArrayList<Alarme>();

        try {
            this.ps = this.conn.prepareStatement(SELECT_ALARMES_BY_MODULE);
            this.ps.setString(1, module);
            this.ps.setInt(2, limit);
            ResultSet rs = this.ps.executeQuery();
            while (rs.next()) {

                Alarme alarmeEEC = new Alarme();

                alarmeEEC.setId_alarme(rs.getInt("idAlarme"));
                alarmeEEC.setModule(rs.getString("module"));
                alarmeEEC.setType_eec(rs.getString("typeEEC"));
                alarmeEEC.setDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(rs.getInt("dateDeclench") * 1000L)));

                if (rs.getInt("Urgence") == 1) {
                       alarmeEEC.setUrgence("ASI");
                }
                if (rs.getInt("Urgence") == 2) {
                    alarmeEEC.setUrgence("AID");
                }
                if (rs.getInt("Urgence") == 3) {
                    alarmeEEC.setUrgence("AII");
                }

                alarmeEEC.setSource(rs.getString("idSrc"));
                alarmeEEC.setLibelle(rs.getString("Libelle"));
                alarmeEEC.setZone(rs.getString("zoneGeo"));

                liste.add(alarmeEEC);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AlarmesEECDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return liste;
    }

    public List<Alarme> getAllAlarme(int limit) {

        List<Alarme> liste = new ArrayList<Alarme>();

        try {
            this.ps = this.conn.prepareStatement(SELECT_ALL_ALARME);
            this.ps.setInt(1, limit);
            ResultSet rs = this.ps.executeQuery();
            while (rs.next()) {

                Alarme alarmeEEC = new Alarme();

                alarmeEEC.setId_alarme(rs.getInt("idAlarme"));
                alarmeEEC.setModule(rs.getString("module"));
                alarmeEEC.setType_eec(rs.getString("typeEEC"));
                alarmeEEC.setDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(rs.getInt("dateDeclench") * 1000L)));

                if (rs.getInt("Urgence") == 1) {
                    alarmeEEC.setUrgence("ASI");
                }
                if (rs.getInt("Urgence") == 2) {
                    alarmeEEC.setUrgence("AID");
                }
                if (rs.getInt("Urgence") == 3) {
                    alarmeEEC.setUrgence("AII");
                }

                alarmeEEC.setSource(rs.getString("idSrc"));
                alarmeEEC.setZone(rs.getString("zoneGeo"));
                alarmeEEC.setLibelle(rs.getString("Libelle"));

                liste.add(alarmeEEC);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AlarmesEECDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return liste;
    }
}
