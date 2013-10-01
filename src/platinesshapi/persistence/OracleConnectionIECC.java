/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package platinesshapi.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author h.el-hayouni
 */
public class OracleConnectionIECC {
    
    private static OracleConnectionIECC instance;
    private Connection conn;
    private static final String HOST="10.0.0.171";
    private static final String USER="db_iecc";
    private static final String PASSWD="plaiecc";
          
    private OracleConnectionIECC()
    {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(OracleConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static OracleConnectionIECC getInstance()
    {
        if(instance == null)
            instance = new OracleConnectionIECC();
        return instance;
    }
    
    public Connection getConnexion()
    {
        this.conn = null;
        try {
            this.conn = DriverManager.getConnection("jdbc:oracle:thin:@"+HOST+":1521:IECC", USER,PASSWD);
        } catch (SQLException ex) {
            Logger.getLogger(OracleConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.conn;
    }
}
