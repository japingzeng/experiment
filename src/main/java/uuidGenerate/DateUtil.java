package uuidGenerate;

import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	
	public static Date getFormatDate(String curDate, String format) {
		if (StringUtils.isBlank(curDate)) {
			return null;
		}
		
		SimpleDateFormat sdf = null;
		Date date = null;
		try {
			sdf = new SimpleDateFormat(format);
			date = sdf.parse(curDate);
		} catch (Exception e) {
			
		}
		return date;
	}
	/**
	 * 
	 * @return
	 * @Description: TODO(生成当前毫秒时间) 
	 * @author japing 
	 * @date 2016年12月8日 下午4:42:21
	 */
	public static long timeGenerate() {
		return System.currentTimeMillis();
	}
}
