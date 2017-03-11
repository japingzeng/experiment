package uuidGenerate;

import org.apache.commons.lang3.StringUtils;

/**
 * @Author japing
 * @Date 2017/3/11 10:31
 * @Description:
 */
public class StringUtil {

    public static boolean isEmpty(String str) {
        if (str != null) {
            str = str.trim();
        }
        return StringUtils.isEmpty(str);
    }

    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }
}
