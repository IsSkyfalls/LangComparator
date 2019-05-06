package xyz.Skyfalls.LangComparator.Reader;

import xyz.Skyfalls.LangComparator.IOManager;

import java.util.*;
import java.io.File;

public class ReaderManager extends IOManager<IReader> {

    public ReaderManager(){
        map.put(new PropertiesReader(), new AbstractMap.SimpleEntry<>("properties/lang", "key=value"));
        map.put(new JsonReader(), new AbstractMap.SimpleEntry<>("json", "\"key\":\"value\""));
    }

    public IReader autoSelect(File f){
        for (IReader reader : map.keySet()) {
            if(reader.checkFileType(f.getName())) return reader;
        }
        return null;
    }

}
