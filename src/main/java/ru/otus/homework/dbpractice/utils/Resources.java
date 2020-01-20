package ru.otus.homework.dbpractice.utils;

import org.apache.commons.io.IOUtils;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

public class Resources {
    private static Map<String, String> map = new HashMap<>();

    public static String resourceAsString(String name) {
        String result = map.get(name);
        if (result != null)
            return result;
        try {
            result = IOUtils.toString(Resources.class.getClassLoader().getResourceAsStream(name), Charset.forName("UTF8"));
            map.put(name, result);
            return result;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
