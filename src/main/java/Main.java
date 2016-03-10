import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.itis.inform.store.dao.ItemsDao;
import ru.itis.inform.store.dao.config.ItemsDaoConfig;
import ru.itis.inform.store.dao.ItemsDaoCsvImpl;
import ru.itis.inform.store.dao.models.Item;
import ru.itis.inform.store.services.StoreService;
import ru.itis.inform.store.services.StoreServiceImpl;

import java.io.*;
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

// XML Annotation
//        ApplicationContext context =
//                new ClassPathXmlApplicationContext("app-context.xml");
//
//        StoreService service=
//                context.getBean("service", StoreService.class);
//
//        service.testShowDao();

//        properties Annotation
//        StoreService service =
//                ServiceSupportFactory.getInstance().getStoreService();
//        ItemsDao itemsDao =
//                ServiceSupportFactory.getInstance().getItemsDao();
//        service.setItemsDao(itemsDao);
//        service.testShowDao();

        ApplicationContext ctx =
                new AnnotationConfigApplicationContext(ItemsDaoConfig.class);

        StoreService storeService= ctx.getBean(StoreServiceImpl.class);
        ItemsDao itemsDao = ctx.getBean(ItemsDaoCsvImpl.class);

        storeService.setItemsDao(itemsDao);
        storeService.testShowDao();

        }
    }

