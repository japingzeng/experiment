package experiment.vmTest.invokeddynamicTest;
import static java.lang.invoke.MethodHandles.lookup;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodType;

/**
 * 
 * @ClassName: Test 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author japing 
 * @date 2016年9月15日 下午4:18:53 
 *
 */
public class Test {
	class GrandFather {
		public void thinking() {
			System.out.println("i am grandfather");
		}
	}
	
	class Father extends GrandFather {
		@Override
		public void thinking() {
			// TODO Auto-generated method stub
			System.out.println("i am father");
		}
	}
	
	class Son extends Father {
		@Override
		public void thinking() {
			try {
				MethodType mt = MethodType.methodType(void.class);
				MethodHandle mh = lookup().findSpecial(GrandFather.class,
						"thinking", mt, getClass());
				mh.invoke(this);
			} catch (Throwable e) {
				
			}
		}
	}
	
	public static void main(String[] args) {
		Son son = new Test().new Son();
		son.thinking();
	}
}
