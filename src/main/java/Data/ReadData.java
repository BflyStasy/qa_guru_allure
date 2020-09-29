package Data;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

public class ReadData {
    private ReadData() {}


    private static final String path = "src/main/resources/app.properties";
    public static final String LOGIN = "login";
    public static final String PASSWORD = "password";


    public static String loadProperty(String name) {
         Properties prop = new Properties();
        if (tryGetProp(prop, path)) return prop.getProperty(name);
        return null;
    }

    public static boolean tryGetProp(Properties prop, String file) {
        try {
            //обращаемся к файлу и получаем данные
            FileInputStream fileInputStream = new FileInputStream(file);
            InputStreamReader reader = new InputStreamReader(fileInputStream, StandardCharsets.UTF_8);
            prop.load(reader);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
