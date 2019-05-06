package xyz.Skyfalls.LangComparator;

import xyz.Skyfalls.LangComparator.Reader.IReader;
import xyz.Skyfalls.LangComparator.Reader.ReaderManager;
import xyz.Skyfalls.LangComparator.Writer.IWriter;
import xyz.Skyfalls.LangComparator.Writer.WriterManager;

import java.io.IOException;
import java.util.*;
import java.io.File;

public class LangComparator {
    private Map<String, Boolean> flags = new HashMap<String, Boolean>() {
        {
            put("ignore_empty_value", true);
            put("output_results", true);
        }
    };
    private Map<Integer, Map<String, String>> cached = new HashMap<>();
    private static ReaderManager rm = new ReaderManager();
    private static WriterManager wm = new WriterManager();

    public LangComparator(){
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("欢迎使用LangComparator!");
            System.out.println("指令列表:select,compare,merge,flag");
            String command = scanner.nextLine();
            try {
                switch (command) {
                    case "select":
                        selectFiles();
                        break;
                    case "compare":
                        compare();
                        break;
                    case "merge":
                        merge();
                        break;
                    case "flag":
                        setFlag();
                        break;
                    default:
                        System.out.println("无法识别指令!");
                }
            } catch (Exception e) {
                System.out.println("在处理指令的过程中发生了一个异常:");
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args){
        new LangComparator();
    }
    public void setFlag(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入要设置的变量名,当前已定义变量");
        for(String key:flags.keySet()){
            System.out.print(key+" ");
        }
        System.out.println();
        String key=scanner.nextLine();
        System.out.println("请输入变量的值(true,false):");
        boolean value=scanner.nextBoolean();
        flags.put(key,value);
        System.out.println("成功!");
    }
    public void selectFile(int i) throws IOException{
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入文件" + i + "的路径:");
        String path = scanner.nextLine();
        File file = new File(path);
        IReader reader = rm.autoSelect(file);
        while (reader == null) {
            System.out.print("无法识别文件格式，请重试.当前支持");
            System.out.println(rm.getAllSupportedEntries());
            reader = rm.selectByName(scanner.nextLine());
        }
        Map<String, String> cache = reader.read(file, flags.get("ignore_empty_value"));
        System.out.println("成功,共读取到" + cache.size() + "条目!");
        cached.put(i, cache);
    }

    public void selectFiles() throws IOException{
        for (int i = 1; i < 3; i++) {
            selectFile(i);
        }
    }

    public void merge() throws IOException{
        Scanner scanner = new Scanner(System.in);
        if(cached.size() != 2){
            System.out.println("请先使用select选择文件!");
            return;
        }
        IWriter writer = null;
        while (writer == null) {
            System.out.print("请输入文件格式,当前支持");
            System.out.println(wm.getAllSupportedEntries());
            writer = wm.selectByName(scanner.nextLine());
        }
        Map<String, String> m1, m2;
        m1 = cached.get(1);
        m2 = cached.get(2);
        for (String key : m2.keySet()) {
            if(m1.containsKey(key)) continue;
            m1.put(key, m2.get(key));
        }
        System.out.println("请设置文件名:");
        writer.write(m1, scanner.nextLine());
    }

    public void compare() throws IOException{
        Map<String, String> m1, m2;
        m1 = cached.get(1);
        m2 = cached.get(2);
        List<String> d1, d2;
        d1 = compareMaps(m1, m2);
        d2 = compareMaps(m2, m1);
        StringBuilder sb = new StringBuilder();
        sb.append("比对完成,");
        if(d1.size() == 0 && d2.size() == 0){
            sb.append("两文件条目无差别!");
        } else {
            sb.append("结果如下:\n");
            for (String key : d1) {
                sb.append("文件1中的" + key + "在文件2中不存在!(");
                sb.append(key);
                sb.append("=");
                sb.append(m1.get(key));
                sb.append(")\n");
            }
            for (String key : d2) {
                sb.append("文件2中的" + key + "在文件1中不存在!(");
                sb.append(key);
                sb.append("=");
                sb.append(m2.get(key));
                sb.append(")\n");
            }
        }
        System.out.println(sb.toString());
        if(flags.get("output_results")){
            Utils.writeToFile(sb.toString(), "results.txt");
            System.out.println("结果已写入results.txt!");
        }
    }

    private List<String> compareMaps(Map<String, String> m1, Map<String, String> m2){
        List<String> differences = new ArrayList<String>();
        Set<String> keyCache1 = new HashSet<String>(m1.keySet());
        Set<String> keyCache2 = new HashSet<String>(m2.keySet());
        for (String key : keyCache1) {
            if(!keyCache2.contains(key)){
                differences.add(key);
            }
        }
        return differences;
    }
}
