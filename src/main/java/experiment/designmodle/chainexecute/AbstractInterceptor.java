package experiment.designmodle.chainexecute;

public abstract class AbstractInterceptor implements Interceptor {

	public Object intercept(InterptorChain interptorChain) {
		Object result = null;
		before();
		result = interptorChain.doIntercptorChain();
		after();
		return result;
	}
	
	public void before() {
		
	}
	
	public void after() {
		
	}

}
