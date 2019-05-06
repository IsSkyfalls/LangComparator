package xyz.Skyfalls.LangComparator.Writer;

import xyz.Skyfalls.LangComparator.Utils;

import java.io.IOException;
import java.util.Map;

public class PropertiesWriter implements IWriter {

    @Override
    public boolean checkType(String type){
        return type.equalsIgnoreCase("properties")||type.equalsIgnoreCase("lang");
    }

    @Override
    public void write(Map<String, String> map,String filename) throws IOException{
        StringBuilder sb=new StringBuilder();
        for(Map.Entry<String,String> e:map.entrySet()){
            sb.append(e.getKey());
            sb.append("=");
            sb.append(e.getValue());
            sb.append("\n");
        }
        Utils.writeToFile(sb.toString(),filename);
    }
}
