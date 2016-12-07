package method.invoke.test.staticDispatch;

public class StaticDispatch {
	
	static abstract class Human {
		
	}
	
	/**
	 * 
	 * @ClassName: Man 
	 * @Description: TODO(类型1) 
	 */
	static class Man extends Human {
		
	}
	
	/**
	 * 
	 * @ClassName: Woman 
	 * @Description: TODO(类型2) 
	 */
	static class Woman extends Human {
		
	}
	
	/**
	 * 
	 * @param human
	 * @Description: TODO()
	 */
	public void syaHello(Human human) {
		System.out.println("hello, human");
	}
	
	/**
	 * 
	 * @param man
	 * @Description: TODO(重载方法1)
	 */
	public void sayHello(Man man) {
		System.out.println("hello, man");
	}
	
	/**
	 * 
	 * @param woman
	 * @Description: TODO(重载方法2)
	 */
	public void sayHello(Woman woman) {
		System.out.println("hello, woman");
	}
	
	public static void main(String[] args) {
		Human man = new Man();
		
		Human woman = new Woman();
		
		StaticDispatch sd = new StaticDispatch();
		sd.syaHello(man);
		sd.syaHello(woman);
		
	}
}
