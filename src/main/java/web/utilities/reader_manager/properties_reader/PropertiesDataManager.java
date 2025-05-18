package web.utilities.reader_manager.properties_reader;


import web.utilities.exception_handling.ExceptionHandling;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

public class PropertiesDataManager {
    private static final String PROJECT_PATH = System.getProperty("user.dir");
    private static final Properties properties = new Properties();

    private static Properties readProperties(String filePathContentRoot) {
        try {
            InputStream fileInputStream = new FileInputStream((PROJECT_PATH + filePathContentRoot));
            properties.load(fileInputStream);
        } catch (Exception exception) {
            ExceptionHandling.handleException(exception);
        }
        return properties;
    }

    public static String getProperty(String key, String filePathContentRoot) {
        return readProperties(filePathContentRoot).getProperty(key).trim();
    }

    public static void setProperty(String key, String value, String filePathContentRoot) {
        try {
            OutputStream fileOutputStream = new FileOutputStream((PROJECT_PATH + filePathContentRoot));
            properties.put(key, value.trim());
            properties.store(fileOutputStream, null);
        } catch (Exception exception) {
            ExceptionHandling.handleException(exception);
        }
    }


}