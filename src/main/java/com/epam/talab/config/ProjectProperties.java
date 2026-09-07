package com.epam.talab.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ProjectProperties {

    private static Properties properties;

    static {
        try (InputStream is = ProjectProperties.class.getClassLoader().getResourceAsStream("project.properties")) {
            properties = new Properties();
            properties.load(is);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getValue(String key) {
        return System.getProperty(key, properties.getProperty(key));
    }

    public static int getIntValue(String key) {
        return Integer.parseInt(getValue(key));
    }
}
