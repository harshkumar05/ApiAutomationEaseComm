package org.ease.utils;

import java.io.InputStream;
import java.util.Map;
import org.yaml.snakeyaml.Yaml;
import java.io.InputStream;
public class YamlUtils {
    public static String fetchStringFromYaml(String key) {
        String filePath="environment/"+System.getProperty("env")+".yaml";
        Yaml yaml = new Yaml();
        try (InputStream inputStream = YamlUtils.class.getClassLoader().getResourceAsStream(filePath)) {
            if (inputStream == null) {
                throw new IllegalArgumentException("File not found: " + filePath);
            }
            Map<String, Object> data = yaml.load(inputStream);
            String info=getNestedValue(data, key);
            return info;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    private static String getNestedValue(Map<String, Object> map, String key) {
        String[] keys = key.split("\\.");
        Object value = map;
        for (String k : keys) {
            if (value instanceof Map) {
                value = ((Map<String, Object>) value).get(k);
            } else {
                return null;
            }
        }
        return value != null ? value.toString() : null;
    }
}