
import com.univocity.parsers.tsv.TsvWriter;
import com.univocity.parsers.tsv.TsvWriterSettings;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.itis.inform.store.Properties;
import ru.itis.inform.store.dao.ItemsDaoCsvImpl;
import ru.itis.inform.store.dao.ItemsDaoTsvImpl;
import ru.itis.inform.store.dao.configs.ItemsDaoConfig;
import ru.itis.inform.store.services.StoreService;
import ru.itis.inform.store.services.StoreServiceImpl;

import java.io.*;
import java.util.*;
import java.util.logging.LogManager;

/**
 * Created by love on 10.02.16.
 */
public class Main  {
    public static void main(String args[]) throws IOException{
        try {
            FileInputStream r = new FileInputStream(
                    "/home/love/Projects/Java/Store/src/main/java/ru/itis/inform/store/log.properties");
            LogManager.getLogManager().readConfiguration(r);
        } catch (IOException e) {
            System.err.println("Could not setup logger configuration: " + e.toString());
        }

//        ItemsDaoTsvImpl i = new ItemsDaoTsvImpl();
//        i.delete("Returns");

//        Collection<Object[]> rows = new ArrayList<>();
//        rows.add(new String[]{"Alice", "3"});
//        rows.add(new String[]{"Madness", "5"});
//        rows.add(new String[]{"Returns", "7"});
//        FileWriter w = new FileWriter(Properties.getProperty("tsvData"));
//        TsvWriter writer = new TsvWriter(w, new TsvWriterSettings());
//        writer.writeRowsAndClose(rows);

// XML Configuration
//        ApplicationContext context =
//                new ClassPathXmlApplicationContext("app-context.xml");
//
//        StoreService service=
//                context.getBean("service", StoreService.class);
//
//        service.testShowDao();

//        Annotation Configuration
//        properties Annotation
//        StoreService service =
//                ServiceSupportFactory.getInstance().getStoreService();
//        ItemsDao itemsDao =
//                ServiceSupportFactory.getInstance().getItemsDao();
//        service.setItemsDao(itemsDao);
//        service.testShowDao();


//@Bean Configuration
//        ApplicationContext ctx =
//                new AnnotationConfigApplicationContext(ItemsDaoConfig.class);
//
//        StoreService storeService= ctx.getBean(StoreServiceImpl.class);
//        storeService.testShowDao();


//Auto Configuration
        ApplicationContext context =
                new ClassPathXmlApplicationContext(new String[] {"AutoConfiguration.xml"});

        StoreService storeService = (StoreService) context.getBean("storeServiceImpl");//Why first letter lower Case?
        storeService.testShowDao();
        }
    }

