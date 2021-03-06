package experiment.other.iotest;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

/**
 * @Author japing
 * @Date 2017/3/6 10:53
 * @Description:
 */
public class TestJavaIO {
    private static final String dateFomat = "yyyyMMdd";
    public static void main(String[] args) {
        try {
            if (null != args) {
                printFile(args[0]);
            }
            System.out.println("======================================================");
            if (null != args) {
                printFile2(args[0]);
            }
            System.out.println("======================================================");
            readDataFromLocal("E:\\test\\Test.txt");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static  void printFile(String args) throws Exception{
        File file = new File(args);
        File[] files = file.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {

                return true;
            }
        });

        for (File item : files) {
            if (item.isFile()) {
                System.out.println(item.getAbsolutePath());
                System.out.println(item.getCanonicalPath());
                System.out.println(item.getPath());
                System.out.println(item.getName());
            } else {
                System.out.println(item.getAbsolutePath());
                System.out.println(item.getCanonicalPath());
                System.out.println(item.getPath());
                System.out.println("====================================");
                System.out.println(item.getName());
                String subpath = args + "\\" + item.getName();

                printFile(subpath);

                System.out.println(DateUtil.dateToString(new Date(), dateFomat));
                String rowKey = "-" + DateUtil.dateToString(new Date(), dateFomat) + "-" + String.valueOf(System.currentTimeMillis());
                System.out.println(rowKey);
            }
        }
    }

    public static void printFile2(String path) {
       try {
           String pathStr = "experiment.other";
           String pathStr2 = pathStr.replace(".", "/");
           Enumeration<URL> enumerations = getClassLoader().getResources(pathStr2);
           if (null != enumerations) {
               while (enumerations.hasMoreElements()) {
                   URL url = enumerations.nextElement();
                   String protocol = url.getProtocol();
                   if ("file".equals(protocol)) {
                       System.out.println(url.getPath());
                       System.out.println(url.getFile());
                       printFile(url.getPath());
                   }
               }
           }
       } catch (Exception e) {
           System.out.println(e.getMessage());
       }
    }
    public static ClassLoader getClassLoader() {
        return Thread.currentThread().getContextClassLoader();
    }

    public static void readDataFromLocal(String path) {
        BufferedInputStream bufferedInputStream = null;
        BufferedReader bufferedReader = null;

        FileInputStream fis = null;
        BufferedReader br = null;
        List<String> lines = new ArrayList<>();
        BufferedReader br1= null;
        try{
            fis = new FileInputStream(path);
            byte[] encode = new byte[3];
            String charSet = "gb2312";
            if (null != encode) {
                if (-1 != fis.read(encode)) {
                    if(encode[0]==-17 && encode[1]==-69 && encode[2] ==-65) {
                        charSet = "UTF-8";
                    } else if (encode[0] == -1 && encode[1] == -2 ) {
                        charSet = "UTF-16";
                    } else if (encode[0] == -2 && encode[1] == -1 ) {
                        charSet = "Unicode";
                    }
                }
            }
            System.out.println(charSet);
            FileReader fileReader = new FileReader(path);
            br = new BufferedReader(new InputStreamReader(fis, "UTF-8"));
            String str = "";
            while ((str = br.readLine()) != null) {
                lines.add(str);
            }
            for (String item : lines) {
                System.out.println(item);
            }
        } catch (IOException e) {

       } finally {
           try {
               br.close();
           } catch (Exception e) {

           }
       }

    }

}
