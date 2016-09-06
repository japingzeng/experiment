package experiment.designmodle.oom;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
/**
 * 
 * @ClassName: OOMPermTest 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author japing 
 * @date 2016年9月5日 上午10:59:09 
 * 测试参数 -verbose:gc -Xmn5M -Xms10M -Xmx10M -XX:MaxPermSize=1M -XX:+PrintGC
 */
public class OOMPermTest {
	public static void main(String[] args) {
		List<String> list = new ArrayList<String>();
		while (true) {
			list.add(UUID.randomUUID().toString().intern());
		}
	}
}
