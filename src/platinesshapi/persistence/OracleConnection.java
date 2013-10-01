/*
 * To change this template, choose Tools | Templates
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
 * @author houria
 */
public class OracleConnection {
    
    private static OracleConnection instance;
    private Connection conn, connIECC;
    private static final String HOST="10.0.0.171";
    private static final String USER="db_supe";
    private static final String PASSWD="plasupe";
    
    private static final String USER_IECC="db_iecc";
    private static final String PASSWD_IECC="plaiecc";
          
    private OracleConnection()
    {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(OracleConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static OracleConnection getInstance()
    {
        if(instance == null)
            instance = new OracleConnection();
        return instance;
    }
    
    public Connection getConnexion()
    {
        this.conn = null;
        try {
            this.conn = DriverManager.getConnection("jdbc:oracle:thin:@"+HOST+":1521:SUPE", USER,PASSWD);
        } catch (SQLException ex) {
            Logger.getLogger(OracleConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.conn;
    }
    public Connection getConnexionIECC()
    {
        this.connIECC = null;
        try {
            this.connIECC = DriverManager.getConnection("jdbc:oracle:thin:@"+HOST+":1521:IECC", USER_IECC,PASSWD_IECC);
        } catch (SQLException ex) {
            Logger.getLogger(OracleConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.connIECC;
    }
}
