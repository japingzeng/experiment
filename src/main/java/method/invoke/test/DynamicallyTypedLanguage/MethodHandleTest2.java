package method.invoke.test.DynamicallyTypedLanguage;
import static java.lang.invoke.MethodHandles.lookup;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodType;

/**
 * 
 * @ClassName: Test 
 * @Description: TODO(子类调用祖父类的方法演示) 
 * @author japing 
 *
 */
public class MethodHandleTest2 {
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
		Son son = new MethodHandleTest2().new Son();
		son.thinking();
	}
}
