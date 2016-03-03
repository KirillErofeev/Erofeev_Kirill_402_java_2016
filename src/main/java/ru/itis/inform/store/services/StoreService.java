package ru.itis.inform.store.services;


import ru.itis.inform.store.dao.ItemsDao;

public interface StoreService {
    /**
     * Покупка товара по имени
     * @param itemName имя товара
     */
    void buy(String itemName);

    /**
     * Проверяет, есть ли товар на складе
     * @param itemName название товара
     * @return true - если товар найден,
     * false - в противном случае
     */
    boolean isExist(String itemName);

    public void testShowDao();

    public void setItemsDao(ItemsDao itemsDao);
}
