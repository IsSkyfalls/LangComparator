package xyz.Skyfalls.LangComparator.Writer;

import xyz.Skyfalls.LangComparator.IOManager;

import java.util.AbstractMap;

public class WriterManager extends IOManager<IWriter> {

    public WriterManager(){
        map.put(new PropertiesWriter(), new AbstractMap.SimpleEntry<>("properties/lang", "key-value"));
        map.put(new JsonWriter(), new AbstractMap.SimpleEntry<>("json", "\"key\":\"value\""));
    }

}
