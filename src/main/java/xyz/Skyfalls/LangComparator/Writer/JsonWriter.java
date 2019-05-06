package xyz.Skyfalls.LangComparator.Writer;

import org.json.JSONObject;
import xyz.Skyfalls.LangComparator.Utils;

import java.io.IOException;
import java.util.Map;

public class JsonWriter implements IWriter {
    @Override
    public boolean checkType(String type){
        return type.equalsIgnoreCase("json");
    }

    @Override
    public void write(Map<String, String> map, String filename) throws IOException{
        JSONObject json=new JSONObject(map);
        Utils.writeToFile(json.toString(),filename);
    }
}
