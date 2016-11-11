package experiment.designmodle.chainexecute;

import java.util.ArrayList;
import java.util.List;


public class InterptorChain {
	
	private List<Interceptor> chainList = new ArrayList<Interceptor>();
	int indexInterceptor = 0;
	public InterptorChain(List<Interceptor> chainList) {
		this.chainList = chainList;
	}
	
	public Object doIntercptorChain() {
		Object result = null;
		try {
			if (indexInterceptor < chainList.size()) {
				chainList.get(indexInterceptor++).intercept(this);
			} else {
				result = execute();
			}
		} catch (Exception e) {
			
		}
		
		return result;
	}
	
	private Object execute() {
		System.out.println("---------- 执行主要方法 -----------");
		return new String("执行主要方法");
	}
}
