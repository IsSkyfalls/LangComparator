package xyz.Skyfalls.LangComparator.Writer;

import java.io.IOException;
import java.util.Map;

public interface IWriter {
    boolean checkType(String type);
    void write(Map<String, String> map,String filename) throws IOException;
}
