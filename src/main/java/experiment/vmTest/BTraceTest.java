package experiment.vmTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 
 * @ClassName: BTraceTest 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author japing 
 * @date 2016年9月6日 下午5:19:03 
 *
 */
public class BTraceTest {
	public int add(int a, int b) {
		return a + b;
	}
	
	public static void main(String[] args) throws IOException {
		BTraceTest test = new BTraceTest();
		
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		for (int i = 0; i < 10; i++) {
			bf.readLine();
			
			int a = (int) Math.round(Math.random()*1000);
			int b = (int) Math.round(Math.random()*1000);
			
			System.out.println(test.add(a, b));
		}
	}
}
