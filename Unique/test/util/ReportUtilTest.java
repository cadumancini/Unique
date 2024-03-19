/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import net.sf.jasperreports.engine.JasperReport;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;

/**
 *
 * @author cadum
 */
public class ReportUtilTest {
    @Test
    public void getReportShouldReturnExistingReport() {
        JasperReport report = ReportUtil.getReport("Contrato");
        String toString = report.toString();
        assertNotNull(report);
    }
}
