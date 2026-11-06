package utils;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigReader {
    public static Properties getProperties() {
        Properties prop = new Properties();
        try {
            FileInputStream fis = new FileInputStream("src/test/java/config.properties");
            prop.load(fis);
        } catch (Exception e) {
            throw new RuntimeException("Unable to fetch the config file", e);
        }
        return prop;
    }
}