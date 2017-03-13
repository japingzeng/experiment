package uuidGenerate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @Author japing
 * @Date 2017/3/11 10:19
 * @Description:
 */
public class ProsUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProsUtil.class);
    public static Properties loadProps(String fileName) throws Exception{
        Properties props = null;
        InputStream is = null;
        try {
            is = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
            if (null == is) {
                throw new FileNotFoundException(fileName + ": 未找到");
            }
            props = new Properties();
            props.load(is);
        } catch (IOException e) {
            LOGGER.error("***[加载配置文件失败]***", e);
            throw e;
        } finally {
            if (null != is) {
                try {
                    is.close();
                } catch (Exception e) {
                    LOGGER.error("***[close input stream failure]***", e);
                }
            }
        }
        return props;
    }

    public static long getLong(Properties pros, String key, long defaultValue) {
        long value = defaultValue;
        if (pros.containsKey(key)) {
            value = CastUtil.castLong(pros.getProperty(key));
        }
        return value;
    }

    public static long getLong(Properties pros, String key) {
        return getLong(pros, key, 0L);
    }
}
