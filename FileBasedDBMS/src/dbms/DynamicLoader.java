package dbms;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;

public class DynamicLoader {

    public static Object createInstance(String className) throws Exception {

        File file = new File("generated/");
        URL[] urls = new URL[]{file.toURI().toURL()};

        URLClassLoader loader = new URLClassLoader(urls);
        Class<?> clazz = Class.forName(className, true, loader);

        return clazz.getDeclaredConstructor().newInstance();
    }
}
