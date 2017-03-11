package uuidGenerate;

/**
 * @Author japing
 * @Date 2017/3/11 10:21
 * @Description:
 */
public class CastUtil {

    public static String castString(Object object) {
        return castString(object, "");
    }

    public static String castString(Object object, String defaultValue) {
        return object != null ? String.valueOf(object) : defaultValue;
    }

    public static long castLong(Object object) {
        return castLong(object, 0);
    }

    public static long castLong(Object object, long defaultValue) {
        long longValue = defaultValue;
        if (null != object) {
            String strValue = castString(object);
            if (StringUtil.isNotEmpty(strValue)) {
                try {
                    longValue = Long.valueOf(strValue);
                } catch (NumberFormatException e) {
                    longValue = defaultValue;
                }
            }
        }
        return longValue;
    }
}
