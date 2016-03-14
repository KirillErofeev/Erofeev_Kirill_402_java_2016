package ru.itis.inform.store;

import java.io.*;

/**
 * Created by love on 14.03.16.
 */
public class Properties {
   synchronized public static String getProperty(String property) throws IOException {
        FileReader fr = new FileReader(
                "/home/love/Projects/Java/Store/src/main/java/ru/itis/inform/store/dao/configs/paths.properties");
        BufferedReader br = new BufferedReader(fr);
        String red;
        while(!(red = br.readLine()).startsWith(property));
        br.close();
        fr.close();
        return red.split("=")[1];
    }
}
