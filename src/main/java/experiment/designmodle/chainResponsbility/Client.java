package experiment.designmodle.chainResponsbility;

public class Client {
	public static void main(String[] args) {
		AbstractHandler handler1 = new Handler1(new Level(1));
		AbstractHandler handler2 = new Handler2(new Level(2));
		AbstractHandler handler3 = new Handler3(new Level(3));
		
		handler1.setNextHandler(handler2);
		handler2.setNextHandler(handler3);
		
		Request request = new Request(new Level(4));
		
		handler1.handleRequest(request);
	}
}
