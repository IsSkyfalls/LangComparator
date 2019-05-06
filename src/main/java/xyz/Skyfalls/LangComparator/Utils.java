package xyz.Skyfalls.LangComparator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class Utils {
    public static String getExtension(String fileName){
        if(!fileName.contains("."))return null;
        String extension = fileName.substring(fileName.lastIndexOf(".") + 1);
        return extension;
    }
    public static void writeToFile(String s,String fileName) throws IOException{
        File file = new File(fileName);
        Writer out = new FileWriter(file);
        // 声明一个String类型对象
        out.write(s);
        out.write("\n");
        out.flush();
        out.close();
    }
}
