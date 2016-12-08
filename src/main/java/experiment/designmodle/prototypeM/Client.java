package experiment.designmodle.prototypeM;

public class Client {
	public static void main(String[] args) {
		ConcretePrototype cp = new ConcretePrototype();
		for (int i = 0; i < 10; i++) {
			ConcretePrototype clonecp = (ConcretePrototype) cp.clone();
			clonecp.show();
		}
		
//		while (true) {
//			System.out.println("test jps jstat");
//		}
	}
}
