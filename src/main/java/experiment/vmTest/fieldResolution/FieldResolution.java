package experiment.vmTest.fieldResolution;

/** 
 * @ClassName: FieldResolution 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author japing 
 * @date 2016年9月9日 下午5:54:41 
 *  
 */  
public class FieldResolution {
	interface Interface0 {
		int A = 0;
	}
	
	interface Interface1 extends Interface0 {
		int A = 1;
	}
	
	interface interface2 {
		int A = 2;
	}
	
	static class Parent implements Interface1 {
		public static int A = 3;
	}
	
	static class Sub extends Parent implements interface2 {
		public static int A = 4;
	}
	
	public static void main(String[] args) {
		System.out.println(Sub.A);
	}
}
