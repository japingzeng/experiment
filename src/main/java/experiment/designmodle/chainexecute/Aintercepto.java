package experiment.designmodle.chainexecute;

public class Aintercepto extends AbstractInterceptor {
	@Override
	public void before() {
		System.out.println("A before");
	}
	
	@Override
	public void after() {
		System.out.println("A after");
	}
}
