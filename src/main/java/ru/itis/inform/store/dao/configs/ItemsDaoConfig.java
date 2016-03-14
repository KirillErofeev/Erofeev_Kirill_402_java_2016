package ru.itis.inform.store.dao.configs;
import org.springframework.context.annotation.*;
import ru.itis.inform.store.dao.ItemsDao;
import ru.itis.inform.store.dao.ItemsDaoCsvImpl;
import ru.itis.inform.store.dao.ItemsDaoTsvImpl;
import ru.itis.inform.store.services.StoreService;
import ru.itis.inform.store.services.StoreServiceImpl;

import java.io.IOException;

/**
 * Created by love on 10.03.16.
 */
@Configuration
public class ItemsDaoConfig {

    @Bean
    public ItemsDao itemsDaoCsvImpl() throws IOException{
        return new ItemsDaoCsvImpl();
    }

    @Bean
    public ItemsDao itemsDaoTsvImpl() throws IOException{
        return new ItemsDaoTsvImpl();
    }

    @Bean
    public StoreService storeService(){
        return new StoreServiceImpl();
    }

}
