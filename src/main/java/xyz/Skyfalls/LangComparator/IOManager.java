package xyz.Skyfalls.LangComparator;

import xyz.Skyfalls.LangComparator.Writer.IWriter;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class IOManager<T> {
    protected Map<T, Map.Entry<String, String>> map = new HashMap<>();
    public T selectByName(String s){
        Set<Map.Entry<T, Map.Entry<String, String>>> set = map.entrySet();
        for (Map.Entry<T, Map.Entry<String, String>> entry : set) {
            if(entry.getValue().getKey().toLowerCase().contains(s.toLowerCase())){
                return entry.getKey();
            }
        }
        return null;
    }
    public String getAllSupportedEntries(){
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> e: map.values()){
            sb.append(e.getKey());
            sb.append(",");
        }
        sb.deleteCharAt(sb.length()-1);
        return sb.toString();
    }
}
