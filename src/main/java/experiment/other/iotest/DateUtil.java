package experiment.other.iotest;

import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author japing
 * @Date 2017/3/7 10:53
 * @Description:
 */
public class DateUtil {

    public static String dateToString(Date date, String patern) {
        SimpleDateFormat sdf = null;
        String timeStr = "";
        if (null != date && StringUtils.isNotBlank(patern)) {
            sdf = new SimpleDateFormat(patern);
            timeStr = sdf.format(date);
        }
        return timeStr;
    }
}
