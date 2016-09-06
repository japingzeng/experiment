package experiment.designmodle.oom;

import java.util.ArrayList;
import java.util.List;
/**
 * 
 * @ClassName: OOMTest 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author japing 
 * @date 2016年9月5日 上午10:46:18 
 * 测试VM 参数  -verbose:gc -Xmn10M -Xms20M -Xmx20M -XX:+PrintGC
 */
public class OOMHeapTest {
	public static void main(String[] args) {
		List<Byte[]> buffer = new ArrayList<Byte[]>();
		buffer.add(new Byte[10*1024*1024]);
	}
}
