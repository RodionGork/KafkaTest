package kafkafib;

import java.io.IOException;
import java.util.Properties;

public class PropertiesLoader {

    private PropertiesLoader() {
        throw new Error("Oh, die hard, ye who durst to instantiate me!");
    }

    public static Properties propertiesAugmentedWithSystemOnes(String fileName) {
        Properties properties = new Properties();
        try {
            properties.load(PropertiesLoader.class.getResourceAsStream(fileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Properties systemProperties = System.getProperties();
        for (Object key : properties.keySet()) {
            if (systemProperties.containsKey(key)) {
                properties.put(key, systemProperties.get(key));
            }
        }
        System.out.println(properties);
        return properties;
    }



}
