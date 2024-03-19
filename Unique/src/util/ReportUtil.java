/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;

/**
 *
 * @author cadum
 */
public class ReportUtil {
    public static JasperReport getReport(String report) {
        try {
            PropertiesReader propsReader = new PropertiesReader();
            System.out.println("\\\\" + propsReader.getProperty("db.ipAddress") + 
                    propsReader.getProperty("reports.path") + report + propsReader.getProperty("reports.format"));
            return JasperCompileManager.compileReport("\\\\" + propsReader.getProperty("db.ipAddress") + 
                    propsReader.getProperty("reports.path") + report + propsReader.getProperty("reports.format"));
        } catch (JRException | IOException ex) {
            Logger.getLogger(ReportUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
