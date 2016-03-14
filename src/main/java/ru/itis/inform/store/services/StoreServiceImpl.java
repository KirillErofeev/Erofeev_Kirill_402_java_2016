package ru.itis.inform.store.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ru.itis.inform.store.dao.ItemsDao;

import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class StoreServiceImpl implements StoreService {

    private static Logger log = Logger.getLogger(StoreServiceImpl.class.getName());

    @Autowired
    @Qualifier("itemsDaoCsvImpl")
    ItemsDao itemsDao;

    public StoreServiceImpl(ItemsDao itemsDao) {
        this.itemsDao = itemsDao;
    }

    // @Bean Configuration
//    @Autowired
//    @Qualifier("itemsDaoCsvImpl")
    public void setItemsDao(ItemsDao itemsDao){ // xml conf
        this.itemsDao = itemsDao;
    }

    public void testShowDao(){
        System.out.println(itemsDao.toString());
    }

    public StoreServiceImpl() {

    }

    public void buy(String itemName) {
        itemsDao.delete(itemName);
        log.log(Level.INFO, "buy " + itemName);
    }

    public boolean isExist(String itemName) {
        return itemsDao.select(itemName) != null;
    }
}

