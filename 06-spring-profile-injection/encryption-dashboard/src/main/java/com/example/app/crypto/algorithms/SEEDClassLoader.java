package com.example.app.crypto.algorithms;
import java.io.*;
import java.net.*;

public class SEEDClassLoader {
    void loadSEEDJar() {
        File file = new File("./external/jar/kisa-seed.jar");
        try {
            URL url = file.toURL();
            URL[] urls = new URL[]{ url };

            try {
                ClassLoader cl = new URLClassLoader(urls);
                Class  cls = cl.loadClass("KISA_SEED_CBC");
                // wtf? 3시간 안에 어떻게 해?
                /*
                Method encryptMethod = seedClass.getDeclaredMethos
                */

            } catch(Exception e) {
                System.out.println("Classloader failed with error: " + e);
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}
