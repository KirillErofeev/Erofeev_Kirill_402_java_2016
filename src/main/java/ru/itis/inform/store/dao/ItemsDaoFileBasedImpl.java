package ru.itis.inform.store.dao;

import ru.itis.inform.store.Properties;
import ru.itis.inform.store.dao.models.Item;

import java.io.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ItemsDaoFileBasedImpl implements ItemsDao {
    private static Logger log = Logger.getLogger(//TODO push log properties?
                    ItemsDaoFileBasedImpl.class.getName());

    private static  ArrayList<Item> items;

    public void delete(String itemName) {
        init();
        for (int i = 0; i < items.size(); i++) {
            Item item = items.get(i);
            if(item.getName().equals(itemName)) {
                items.remove(item);

            }
        }
        save();
        return;
    }

    public Item select(String itemName) {
        init();
        Item found = null;
        for (Item item: items) {
            if(item.getName().equals(itemName))
                found = item;
        }
        save();
        return found;
    }

    private void init() {
        if(items == null) {
            try {
                File e = new File(Properties.getProperty("fileData"));
                if(e.exists()) {
                    FileInputStream fis = new FileInputStream(Properties.getProperty("fileData"));
                    ObjectInputStream in = new ObjectInputStream(fis);
                    Object objList = in.readObject();
                    if(objList instanceof ArrayList){
                        items = (ArrayList<Item>) in.readObject();//TODO improve
                    }else{
                        createNewItemslist();
                        log.log(Level.SEVERE, "Unreadable data");
                    }
                        in.close();
                        fis.close();
                } else {
                    createNewItemslist();
                }
            } catch (IOException e) {
                log.log(Level.SEVERE, "Failed Initialization to reading data", e);
            } catch (ClassNotFoundException cln) {
                createNewItemslist();
                log.log(Level.SEVERE, "Failed Initialization to reading data", cln);
            }
        }

    }

    private static void createNewItemslist(){
        items = new ArrayList<Item>();
    }

    private static void save() {
        try {
            FileOutputStream fos = new FileOutputStream(Properties.getProperty("fileData"));
            ObjectOutputStream e = new ObjectOutputStream(fos);
            e.writeObject(items);
            e.close();
            fos.close();
        } catch (IOException e) {
            log.log(Level.SEVERE, "Failed Initialization to reading data", e);
        }
    }
}
