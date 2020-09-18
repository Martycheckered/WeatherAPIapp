package com.myprojects.Services;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyGetter {

    private static PropertyGetter instance;

    public static  PropertyGetter getInstance() {
        if (instance == null) {
            instance = new PropertyGetter();
        }
        return instance;
    }

     public String getValueFromPropertiesFile(String name) {
        String result ="";

        try {
            FileInputStream fis;
            Properties prop = new Properties();
            fis = new FileInputStream("src/main/resources/Application.properties");
            prop.load(fis);
            result = prop.getProperty(name);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
