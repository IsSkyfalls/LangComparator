package xyz.Skyfalls.LangComparator.Reader;

import xyz.Skyfalls.LangComparator.Utils;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class PropertiesReader implements IReader {
    public boolean checkFileType(String filename){
        String extension=Utils.getExtension(filename);
        return extension!=null&&(extension.equals("properties")||extension.equals("lang"));
    }

    public Map<String, String> read(File f, boolean flagIgnoreEmptyEntry) throws IOException{
        FileInputStream fis = new FileInputStream(f);
        BufferedReader br = new BufferedReader(new InputStreamReader(fis));

        Map<String, String> map = new HashMap<>();
        String line;
        while ((line = br.readLine()) != null) {
            if(line.equals("")) continue;
            String parts[] = line.split("=");
            if(parts.length == 2){
                map.put(parts[0], parts[1]);
            } else {
                if(flagIgnoreEmptyEntry)continue;
                map.put(parts[0], "");
            }
        }
        return map;
    }
}
