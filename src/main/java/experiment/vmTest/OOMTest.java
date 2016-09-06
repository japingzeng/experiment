package experiment.vmTest;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @ClassName: OOMObject 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author japing 
 * @date 2016年9月6日 下午3:43:45 
 * -Xms100m -Xmx100m -XX:+UseSerialGC
 */
public class OOMTest {
	static class OOMObject {
		public Byte[] placeHolder = new Byte[64*2014];
	}
	
	public static void fillHeap(int num) throws InterruptedException {
		List<OOMObject> list = new ArrayList<OOMTest.OOMObject>();
		for (int i = 0; i < num; i++) {
			Thread.sleep(50);
			list.add(new OOMObject());
		}
		
		System.gc();
	}
	
	public static void main(String[] args) throws Exception {
		fillHeap(1000);
	}
}
