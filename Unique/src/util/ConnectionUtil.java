/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author cadum
 */
public class ConnectionUtil {
    
    public static Connection getConnection() {
        try {
            PropertiesReader propsReader = new PropertiesReader();
            
            Properties props = new Properties();
            props.put("user", propsReader.getProperty("db.user"));
            props.put("password", propsReader.getProperty("db.pass"));
            props.put("charset", propsReader.getProperty("db.charset"));
            props.put("lc_ctype", propsReader.getProperty("db.lc_ctype"));        		   
            
            String dataBasePath = "jdbc:firebirdsql:" + propsReader.getProperty("db.ipAddress") + ":" + propsReader.getProperty("db.path");
           
	   return DriverManager.getConnection(dataBasePath, props);
//           return DriverManager.getConnection("jdbc:firebirdsql:" + propsReader.getProperty("db.ipAddress") + ":" + propsReader.getProperty("db.path")
//                    + "?" + propsReader.getProperty("db.parameters"),
//                    propsReader.getProperty("db.user"),
//                    propsReader.getProperty("db.pass"));
        } catch (SQLException | IOException ex) {
            Logger.getLogger(ConnectionUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
