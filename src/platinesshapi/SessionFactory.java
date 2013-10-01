/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package platinesshapi;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author h.el-hayouni
 */
public class SessionFactory {
    
    // Pattern factory
    
    public static Session getSession(String host, String user, String password, int port)
    {
        JSch jsch = new JSch();
        Session session=null;
        try {
            session = jsch.getSession(user, host, port);
            session.setPassword(password);
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect();
        } catch (JSchException ex) {
            Logger.getLogger(SessionFactory.class.getName()).log(Level.SEVERE, null, ex);
            session=null;
        }
        return session;
    }
    
    
}
