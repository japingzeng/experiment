package experiment.designmodle.visitorM2;

public class Son1 extends Father {
	public void accept(Execute ex) {
		ex.method(this);
	}
}
