package ru.itis.inform.store.services;

/**
 * Created by love on 16.02.16.
 */
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import ru.itis.inform.store.dao.ItemsDao;
import ru.itis.inform.store.dao.models.Item;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class StoreServiceImplTest {

    StoreServiceImpl testedStoreService;
    StoreServiceImpl testedStoreServiceForIsExist;//TODO can i create new object?

    @Mock
    ItemsDao itemsDao;//TODO Mock for the whole class?
    @Mock
    ItemsDao items;

    @Before
    public void setUp() throws Exception {
        // Выброс исключения, если пришла какая-либо строка, которая не Tovar
        doThrow(new IllegalArgumentException()).when(itemsDao).delete(anyString());
        // Делаем stubbing на удаление товара с именем Tovar
        doNothing().when(itemsDao).delete("Tovar");

        testedStoreService = new StoreServiceImpl(itemsDao);
    }

    // Проверяем, корректно ли прошло выполнение метода buy при входном значении Tovar
    @Test
    public void testBuy() throws Exception {//TODO when we write "throws Exceptions"?
        testedStoreService.buy("Tovar");
        // Проверяем, был ли вызван метод delete
        verify(itemsDao).delete("Tovar"); //TODO why we don't use assert?
    }

    // Проверяем, была ли вызвана ошибка при других входных данных
    @Test(expected = IllegalArgumentException.class)
    public void testBuyOnIncorrectData() {//TODO test fo rare situations?
        testedStoreService.buy("Not tovar");
    }


    @Before
    public void setUpForTestIsExist(){
        when(items.select(anyString())).thenThrow(new IllegalArgumentException());
        doReturn(null).when(items).select("Tovar");
        testedStoreServiceForIsExist = new StoreServiceImpl(items);
    }

    @Test
    public void testIsExist(){
        testedStoreServiceForIsExist.isExist("Tovar");
        verify(items).select("Tovar");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIsExistOnIncorrectData(){
        testedStoreServiceForIsExist.isExist("Not tovar");
    }
}