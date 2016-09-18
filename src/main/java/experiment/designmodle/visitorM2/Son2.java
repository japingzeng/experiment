package experiment.designmodle.visitorM2;

public class Son2 extends Father {
	public void accept(Execute ex) {
		ex.method(this);
	}
}
