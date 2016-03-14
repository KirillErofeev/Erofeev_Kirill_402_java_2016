package ru.itis.inform.store.dao;

import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import com.univocity.parsers.csv.CsvWriter;
import com.univocity.parsers.csv.CsvWriterSettings;
import ru.itis.inform.store.Properties;
import ru.itis.inform.store.dao.models.Item;

import java.io.BufferedReader;
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


public class ItemsDaoCsvImpl implements ItemsDao {
    FileReader fr;
    CsvParser parser;
    String csvData;

    public ItemsDaoCsvImpl() throws IOException{
        csvData = Properties.getProperty("csvData");
        parser = new CsvParser(new CsvParserSettings());
    }

    private static Logger log = Logger.getLogger(//TODO push log properties?
            ItemsDaoCsvImpl.class.getName());

    public void delete(String itemName){
        if(select(itemName)==null) //TODO Add
            return;
        try {
            fr = new FileReader(csvData);
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

    public Item select(String itemName){ //TODO What i must do with the exception?
        Item out = null;
        try {
            fr = new FileReader(csvData);
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
        FileWriter w = new FileWriter(csvData);
        CsvWriter writer = new CsvWriter(w, new CsvWriterSettings());
        writer.writeRows(toWrite);
        writer.close();
    }
}
