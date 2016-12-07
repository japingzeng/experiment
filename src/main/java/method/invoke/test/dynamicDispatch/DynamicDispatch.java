package method.invoke.test.dynamicDispatch;

public class DynamicDispatch {
	
	static abstract class Human {
		
		protected abstract void sayHello();
	}
	
	/**
	 * 
	 * @ClassName: Man 
	 * @Description: TODO(类型1) 
	 * @author japing 
	 */
	static class Man extends Human {

		@Override
		protected void sayHello() {
			System.out.println("hello, man");
		}
	}
	
	static class Woman extends Human {

		@Override
		protected void sayHello() {
			System.out.println("hello, woman");
		}	
	}
	
	public static void main(String[] args) {
		Human man = new Man();
		Human woman = new Woman();
		
		man.sayHello();
		woman.sayHello();
		man = new Woman();
		man.sayHello();
	}
	
	
	
	
}
