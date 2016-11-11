package experiment.designmodle.chainexecute;

public class BInterceptor extends AbstractInterceptor {
	@Override
	public void before() {
		System.out.println("B beofre");
	}
	
	@Override
	public void after() {
		System.out.println("B after");
	}
}
