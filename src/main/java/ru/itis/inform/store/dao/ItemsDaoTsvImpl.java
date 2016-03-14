package ru.itis.inform.store.dao;

import com.univocity.parsers.tsv.TsvParser;
import com.univocity.parsers.tsv.TsvParserSettings;
import com.univocity.parsers.tsv.TsvWriter;
import com.univocity.parsers.tsv.TsvWriterSettings;
import com.univocity.parsers.tsv.TsvParser;
import com.univocity.parsers.tsv.TsvParserSettings;
import ru.itis.inform.store.Properties;
import ru.itis.inform.store.dao.models.Item;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by love on 03.03.16.
 */
public class ItemsDaoTsvImpl implements ItemsDao {
    FileReader fr;
    TsvParser parser;
    String tsvData;

    private static Logger log = Logger.getLogger(//TODO push log properties?
            ItemsDaoTsvImpl.class.getName());

    public ItemsDaoTsvImpl() throws IOException {
        tsvData = Properties.getProperty("tsvData");
        parser = new TsvParser(new TsvParserSettings());
    }

    public void delete(String itemName) {
        if(select(itemName)==null)//TODO Add
            return;
        try {
            fr = new FileReader(tsvData);
        } catch (IOException e) {
            log.log(Level.SEVERE, "Failed Initialization to reading data", e);
            e.printStackTrace();
        }
        List<String[]> allRows = parser.parseAll(fr);
        try {
            fr.close();
            String[] deleted;
            allRows.size();
            for (int i = 0; i < allRows.size(); i++) {
                if(allRows.get(i)[0].equals(itemName)) {
                    allRows.remove(i);
                    break;
                }
            }
            write(allRows);
        } catch (IOException e) {
            log.log(Level.SEVERE, "Failed Initialization to reading data", e);
            e.printStackTrace();
        }
    }

    public Item select(String itemName) {
        Item out = null;
        try {
            fr = new FileReader(tsvData);
            List<String[]> allRows = parser.parseAll(fr);
            for(String[] string : allRows){
                if(string[0].equals(itemName))
                    out = new Item(itemName);
            }
            fr.close();
        } catch (IOException e) {
            log.log(Level.SEVERE, "Failed Initialization to reading data", e);
            e.printStackTrace();
        }
        return out;
    }

    private void write(List<String[]> rows) throws IOException {
        List<Object[]> toWrite = new ArrayList<>();
        for (int i = 0; i < rows.size(); i++) {
            toWrite.add(rows.get(i));
        }
        FileWriter w = new FileWriter(tsvData);
        TsvWriter writer = new TsvWriter(w, new TsvWriterSettings());
        writer.writeRows(toWrite);
        writer.close();
    }
}
