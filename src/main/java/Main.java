import ru.itis.inform.store.dao.ItemsDao;
import ru.itis.inform.store.dao.ItemsDaoFileBasedImpl;

import java.io.*;
import java.util.Scanner;
import java.util.logging.LogManager;

/**
 * Created by love on 10.02.16.
 */
public class Main  {
    public static void main(String args[]){
        try {
            FileInputStream r = new FileInputStream(
                    "/home/love/Projects/Java/Store/src/main/java/ru/itis/inform/store/log.properties");
            LogManager.getLogManager().readConfiguration(r);
        } catch (IOException e) {
            System.err.println("Could not setup logger configuration: " + e.toString());
        }
        }
    }

