/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.sql.Connection;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author cadum
 */
public class ConnectionUtilTest {
    
    @Test
    public void getConnectionShouldReturnSuccessfulConnection() {
        Connection connection = ConnectionUtil.getConnection();
        assertNotNull(connection);
        assertTrue(connection.toString().contains("firebirdsql"));
    }
}
