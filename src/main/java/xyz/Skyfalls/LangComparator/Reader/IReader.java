package xyz.Skyfalls.LangComparator.Reader;

import java.io.IOException;
import java.util.Map;
import java.io.File;

public interface IReader {
    boolean checkFileType(String filename);
    Map<String,String> read(File f,boolean flagIgnoreEmptyEntry) throws IOException;
}
