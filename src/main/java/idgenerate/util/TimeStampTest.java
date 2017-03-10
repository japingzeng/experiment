package idgenerate.util;

import java.text.SimpleDateFormat;

/**
 * Created by Administrator on 2017/3/10.
 */
public class TimeStampTest {
    private static final String dateStartStr = "2017-03-15 00:00:00";
    private static final String dateEndStr = "2036-06-6 06:06:06";
    private static final String format = "yyyy-MM-dd HH:mm:ss";
    public static void main(String[] args) throws Exception {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long dateStart = sdf.parse(dateStartStr).getTime();
        long dateEnd = sdf.parse(dateEndStr).getTime();
        System.out.println(dateStart);
        System.out.println(dateEnd);
        long xx = dateEnd  - dateStart;
        System.out.println(xx);
    }
}
