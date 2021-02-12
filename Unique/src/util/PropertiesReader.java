/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 *
 * @author cadum
 */
public class PropertiesReader {
    private Properties properties = new Properties();
    
    public PropertiesReader() throws IOException {
        try(InputStream inputStream = getClass().getClassLoader().getResourceAsStream("application.properties")) {
            properties.load(inputStream);
        } catch (NullPointerException | IOException e) {
            throw new IOException(e);
        }
    }
    
    public String getProperty(String key) {
        return properties.getProperty(key);
    }
}
