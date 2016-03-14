package ru.itis.inform.store.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import ru.itis.inform.store.dao.ItemsDao;

import java.util.logging.Level;
import java.util.logging.Logger;

public class StoreServiceImpl implements StoreService {

    private static Logger log = Logger.getLogger(StoreServiceImpl.class.getName());

    ItemsDao itemsDao;

    public StoreServiceImpl(ItemsDao itemsDao) {
        this.itemsDao = itemsDao;
    }

    @Autowired
    @Qualifier("itemsDaoCsvImpl")
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

