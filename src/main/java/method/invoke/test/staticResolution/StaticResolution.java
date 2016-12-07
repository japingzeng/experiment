package method.invoke.test.staticResolution;

/**
 * 
 * @ClassName: StaticResolution 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author japing 
 * 使用javap -verbose StaticResolution > StaticResolution.txt 
 */
public class StaticResolution {
	
	public static void sayHello() {
		System.out.println("hello world");
	}
	
	public static void main(String[] args) {
		StaticResolution.sayHello();
	}
	
}
