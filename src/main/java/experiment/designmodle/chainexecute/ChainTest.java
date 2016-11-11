package experiment.designmodle.chainexecute;

import java.util.ArrayList;
import java.util.List;

public class ChainTest {
	public static void main(String[] args) {
		List<Interceptor> list = new ArrayList<Interceptor>();
		list.add(new Aintercepto());
		list.add(new BInterceptor());
		InterptorChain cInterptorChain = new InterptorChain(list);
		cInterptorChain.doIntercptorChain();
	}
}
