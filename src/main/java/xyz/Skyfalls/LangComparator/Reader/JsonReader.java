package xyz.Skyfalls.LangComparator.Reader;

import org.json.JSONObject;
import xyz.Skyfalls.LangComparator.Utils;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class JsonReader implements IReader {
    public boolean checkFileType(String filename){
        String extension=Utils.getExtension(filename);
        return extension!=null&&extension.equals("json");
    }

    public Map<String, String> read(File f, boolean flagIgnoreEmptyEntry){
        JSONObject json=new JSONObject(f);
        Map<String,String> mapCache= new HashMap<>();
        Map<String, Object> map=json.toMap();
        for(String key:map.keySet()){
            String value=map.get(key).toString();
            if(flagIgnoreEmptyEntry&&value.equals(""))continue;
            mapCache.put(key,value);
        }
        return mapCache;
    }
}
