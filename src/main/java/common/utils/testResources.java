package common.utils;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.util.Enumeration;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by Administrator on 2017/6/18.
 */
public class testResources {

    public static void main(String[] args) throws Exception{
        Enumeration<URL> urls = Thread.currentThread().getContextClassLoader().getResources("config");
        while (urls.hasMoreElements()) {
            URL url = urls.nextElement();
            if (url == null) {
                System.out.println("不存在");
            } else {
                File file = new File(url.toURI());

                File[] files = file.listFiles(new FileFilter() {
                    @Override
                    public boolean accept(File file1) {
                        return file1.isDirectory() || file1.isFile();
                    }
                });
                for (File item : files) {
                    System.out.println(item.getName());
                }

            }
        }

        System.out.print("dddd");

        URL url = testResources.class.getClassLoader().getResource("config");

        if (url == null) {
            System.out.println("不存在");
        } else {
            File file = new File(url.toURI());

            File[] files = file.listFiles(new FileFilter() {
                @Override
                public boolean accept(File file1) {
                    return file1.isDirectory() || file1.isFile();
                }
            });

            for (File item : files) {
                System.out.println(item.getName());
            }

        }

        System.out.print("dddd");
    }
}
