package ru.itis.inform.store.dao.models;

import java.io.Serializable;

public class Item implements Serializable{
    String name;//TODO implement ORM

    public Item(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
