package common.utils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * @Author japing
 * @Date 2017/3/3 18:01
 * @Description: class 工具类
 */
public class ClassUtil {

    public static Logger LOG = LoggerFactory.getLogger(ClassUtil.class);
    public static Set<Class<?>> classSet = new HashSet<>();

    public static ClassLoader getClassLoader() {
        return Thread.currentThread().getContextClassLoader();
    }


    public static Set<Class<?>> getClass(String packageName){
        if (null == packageName) {
            LOG.error("***[packageName= {}]***", packageName);
            return null;
        }
       try {
           Enumeration<URL> urls = getClassLoader().getResources(packageName.replace(".", "/"));
           while (urls.hasMoreElements()) {
               URL url = urls.nextElement();
               if (null != url) {
                   String protocol = url.getProtocol();
                   if ("file".equals(protocol)) {
                       String packagePath = packageName.replace("%20", " ");
                       addClass(classSet, packagePath, packageName);
                   } else if ("jar".equals(protocol)) {
                       JarURLConnection jarURLConnection = (JarURLConnection) url.openConnection();
                       JarFile jarFile = jarURLConnection.getJarFile();
                       if (null != jarFile) {
                           Enumeration<JarEntry> entries = jarFile.entries();
                           while (entries.hasMoreElements()) {
                               JarEntry jarEntry = entries.nextElement();
                               String jarEntryname = jarEntry.getName();
                               if (jarEntryname.startsWith(".class")) {
                                   String pathName = jarEntryname.substring(0, jarEntryname.lastIndexOf(".")).replace("/", ".");
                                   doAddClass(pathName, classSet);
                               }
                           }
                       }
                   }
               }
           }

       } catch (Exception e) {

       }
        return null;
    }

    private static void addClass(Set<Class<?>> set, String packagePath, String packageName) {

        File[] files = new File(packagePath).listFiles(new FileFilter() {
            @Override
            public boolean accept(File file) {
                return (file.isFile() && file.getName().startsWith(".class") || file.isDirectory());
            }
        });
        for (File file : files) {
            String fileName = file.getName();
            if (file.isFile()) {
                String className = fileName.substring(0, fileName.lastIndexOf("."));
                if (StringUtils.isNotEmpty(className)) {
                    packageName = packageName + "." + className;
                    doAddClass(packageName, classSet);
                }
            } else {
                String subPackagePath = fileName;
                if (StringUtils.isNotEmpty(packagePath)) {
                    subPackagePath = packagePath + "/" + subPackagePath;
                }
                String subPackageName =fileName;
                if (StringUtils.isNotEmpty(packageName)) {
                    subPackageName = packageName + "." + subPackageName;
                }
                addClass(classSet, subPackagePath, subPackageName);
            }
        }
    }

    public static void doAddClass(String className, Set<Class<?>> set){
        Class<?> cls = loadClass(className, false);
        set.add(cls);
    }

    public static Class<?> loadClass(String className, boolean intialnized){
        Class<?> cls = null;
        try {
            if (null != className) {
                cls = Class.forName(className, intialnized, getClassLoader());
            }
        } catch (ClassNotFoundException e) {

        }
        return cls;
    }
}
