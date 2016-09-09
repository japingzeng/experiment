package experiment.vmTest.staticinitial;

public class ConstantClass {
	static {
		System.out.println("ConstantClass init");
	}
	public static final String HELLO = "hello";
}
