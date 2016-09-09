package experiment.vmTest.staticinitial;

public class SubClass extends SuperClass {
	static {
		System.out.println("SubClass init");
	}
}
