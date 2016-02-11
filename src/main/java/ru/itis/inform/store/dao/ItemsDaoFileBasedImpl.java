package ru.itis.inform.store.dao;

import ru.itis.inform.store.dao.models.Item;

import java.io.*;
import java.util.ArrayList;

public class ItemsDaoFileBasedImpl implements ItemsDao {
    // TODO: реализовать класс, возможно с использованием
    // сериализации ArrayList, либо просто считывать данные из
    // файла в ArrayList
    // проект с гитхаба сфоркать, и доделать в своем репозитории
    // как у Абрамского

    private static  ArrayList<Item> items;

    public void add(String itemName){//debug
        init();
        items.add(new Item(itemName));
        save();
    }

    public void printItems(){//debug
        init();
        for(Item item:items){
            System.out.println(item.getName());
        }
    }

    public void delete(String itemName) {
        init();

        for (int i = 0; i < items.size(); i++) {
            Item item = items.get(i);
            if(item.getName().equals(itemName)) {
                items.remove(item);
                // TODO Do i have to used removing by index for more performance?
            }
        }
//        for (Item item: items) {
//            if(item.getName().equals(itemName)) {
//                items.remove(item);
//                // TODO Do i have to used removing by index for more performance?
//            }
//        }
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
                File e = new File(
                        "/home/love/Projects/Java/Store/src/main/java/ru/itis/inform/store/dao/data/items.ser");


                if(e.exists()) {

                        FileInputStream fis = new FileInputStream(
                                "/home/love/Projects/Java/Store/src/main/java/ru/itis/inform/store/dao/data/items.ser"
                        );
                        ObjectInputStream in = new ObjectInputStream(fis);

                        items = (ArrayList) in.readObject();

                        in.close();
                        fis.close();

                } else {
                    items = new ArrayList();
                    //System.out.println("New storage is created");
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException cln) {
                cln.printStackTrace();
            }
        }

    }

    private static void save() {

        try {
            FileOutputStream fos = new FileOutputStream(
                    "/home/love/Projects/Java/Store/src/main/java/ru/itis/inform/store/dao/data/items.ser");
            ObjectOutputStream e = new ObjectOutputStream(fos);
            e.writeObject(items);
            e.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
