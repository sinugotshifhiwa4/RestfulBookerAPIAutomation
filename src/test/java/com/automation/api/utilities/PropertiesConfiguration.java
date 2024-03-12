package com.automation.api.utilities;

import org.apache.commons.lang3.StringUtils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertiesConfiguration {

    private static final Properties properties = new Properties();
    private static final String propertiesFilePath = System.getProperty("user.dir") + "/src/main/resources/config.properties";

    public static String getPropertyValueByKey(String key){

        try{

            FileInputStream inputStream = new FileInputStream(propertiesFilePath);
            properties.load(inputStream);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String value = properties.getProperty(key);

        try {
            if (StringUtils.isEmpty(value)) {
                throw new IOException("Invalid key specified " + key + " on properties file");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return value;
    }
}
