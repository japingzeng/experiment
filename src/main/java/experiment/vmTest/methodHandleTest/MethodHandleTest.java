package experiment.vmTest.methodHandleTest;

import java.lang.invoke.MethodHandle;
import static java.lang.invoke.MethodHandles.lookup;
import java.lang.invoke.MethodType;

/**
 * 
 * @ClassName: MethodHandleTest 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author japing 
 * @date 2016年9月13日 下午4:13:28 
 *
 */
public class MethodHandleTest {
	static class ClassA {
		public void println(String s) {
			System.out.println(s);
		}
	}
	
	public static void main(String[] args) throws Throwable {
		Object obj = System.currentTimeMillis() % 2 == 0 ? System.out : new ClassA();
		//无论obj最终是哪个实现类，下面这句都能正确调用到println方法
		getPrintlnMH(obj).invokeExact("methodHandleTest");
	}
	
	private static MethodHandle getPrintlnMH(Object reciever) throws Throwable {
		/**
		 * MethodType: 代表“方法类型”，包含了方法的返回值（methodType()的第一个参数）
		 * 和具体参数（methodType（）第二个及以后的参数）
		 */
		MethodType mt = MethodType.methodType(void.class, String.class);
		
		/**
		 * Lookup（）方法来自于MethodHandles.lookup,这句的作用是在指定类中查找符合给定的方法名称、方法类型，
		 * 并且符合调用权限的方法句柄
		 */
		
		/**
		 * 因为这里调用的是一个虚方法，按照java语言的规则，方法第一个参数是隐式的，代表方法的接收者，也即是this指向的对象
		 * 这个参数以前是否昂在参数列表中进行传递的，而现在提供了bindTo()方法来完成这件事
		 */
		
		return lookup().findVirtual(reciever.getClass(), "println", mt).bindTo(reciever);
	}
}