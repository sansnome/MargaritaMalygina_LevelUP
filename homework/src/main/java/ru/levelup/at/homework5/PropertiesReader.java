package ru.levelup.at.homework5;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertiesReader {

    Properties userInfo = new Properties();

    public String getUserInfoByKey(String key) {
        FileInputStream path;
        try {
            path = new FileInputStream("src/test/resources/UsersData.properties");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        try {
            userInfo.load(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return userInfo.getProperty(key);
    }
}
