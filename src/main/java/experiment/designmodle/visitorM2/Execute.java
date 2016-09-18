package experiment.designmodle.visitorM2;

public class Execute {
	public void method(Father father) {
		System.out.println("father's method execute");
	}
	
	public void method(Son1 son1) {
		System.out.println("Son1's mtthod execute");
	}
	
	public void method(Son2 son2) {
		System.out.println("Son2's method execute");
	}
}
