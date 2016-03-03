package ru.itis.inform.store.services;

/**
 * Created by love on 03.03.16.
 */

import ru.itis.inform.store.dao.ItemsDao;

import java.io.FileInputStream;
import java.util.Properties;

public class ServiceSupportFactory {
    private static ServiceSupportFactory instance;

    private Properties properties;

    private ItemsDao items;
    private StoreService service;

    private ServiceSupportFactory() {
        properties = new Properties();

        try {
            properties.load(
                    new FileInputStream("/home/love/Projects/Java/Store/src/main/resources/services.properties"));

            String serviceClass = properties.getProperty("service.class");
            String itemsClass = properties.getProperty("items.class");

            this.service  = (StoreService) Class.forName(serviceClass).newInstance();
            this.items = (ItemsDao) Class.forName(itemsClass).newInstance();
        } catch (Exception ex) {
            throw new IllegalArgumentException(ex);
        }
    }

    static {
        instance = new ServiceSupportFactory();
    }

    public static ServiceSupportFactory getInstance() {
        return instance;
    }

    public ItemsDao getItemsDao() {
        return items;
    }

    public StoreService getStoreService() {
        return service;
    }
}
