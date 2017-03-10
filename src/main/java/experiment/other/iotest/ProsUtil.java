package experiment.other.iotest;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ProsUtil {

//    private static  final  Logger LOG = LoggerFactory.getLogger(ProsUtil.class);

    public static Properties loadPros(String fileName) {
        Properties pros = null;
        InputStream is = null;
        try {
            is = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
//            is = Properties.class.getResourceAsStream(fileName);
            if ( null == is) {
                throw  new FileNotFoundException("fileName = " + fileName + " is not found!");
            }

            pros = new Properties();
            pros.load(is);
        } catch (IOException e) {
//            LOG.error("***[加载配置文件异常，fileName= {}]***", fileName, e);
        }
        return pros;
    }

}
