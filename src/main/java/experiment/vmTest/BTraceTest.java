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
///* BTrace Script Template */
//import com.sun.btrace.annotations.*;
//import static com.sun.btrace.BTraceUtils.*;
//
//@BTrace
//public class TracingScript {
//	/* put your code here */
//    @OnMethod(
//    clazz="experiment.vmTest.BTraceTest",
//    method="add",
//    location=@Location(Kind.RETURN))
//    public static void func(@Self experiment.vmTest.BTraceTest instance,int a,int b, @Return int result){
//        println("调用堆栈");
//        jstack();
//        println(strcat("方法参数A:",str(a)));
//        println(strcat("方法参数B:",str(b)));
//        println(strcat("方法结果:",str(result)));
//    }
//}
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
