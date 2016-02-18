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

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class StoreServiceImplTest {

    StoreServiceImpl testedStoreService;

    @Mock
    ItemsDao itemsDao;

    @Before
    public void setUp() throws Exception {

        //buy
        {
            // Выброс исключения, если пришла какая-либо строка, которая не Tovar
            doThrow(new IllegalArgumentException()).when(itemsDao).delete(anyString());
            // Делаем stubbing на удаление товара с именем Tovar
            doNothing().when(itemsDao).delete("Tovar");
        }

        //isExist
        {
            when(itemsDao.select(anyString())).thenThrow(new IllegalArgumentException());
            doReturn(new Item("")).when(itemsDao).select("Tovar");
            doReturn(null).when(itemsDao).select("isn't existing");
        }

        testedStoreService = new StoreServiceImpl(itemsDao);
    }

    // Проверяем, корректно ли прошло выполнение метода buy при входном значении Tovar
    @Test
    public void testBuy() throws Exception {
        testedStoreService.buy("Tovar");
        // Проверяем, был ли вызван метод delete
        verify(itemsDao).delete("Tovar");
    }

    // Проверяем, была ли вызвана ошибка при других входных данных
    @Test(expected = IllegalArgumentException.class)
    public void testBuyOnIncorrectData() {
        testedStoreService.buy("Not tovar");
    }

    @Test
    public void testIsExistInvokeSelect(){
        testedStoreService.isExist("Tovar");
        verify(itemsDao).select("Tovar");
    }

    @Test
    public void testIsExistReturnTrue(){
        assertTrue(testedStoreService.isExist("Tovar"));
    }

    @Test
    public void testIsExistReturnFalse(){
        assertFalse(testedStoreService.isExist("isn't existing"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIsExistOnIncorrectData(){
        testedStoreService.isExist("Not tovar");
    }
}
