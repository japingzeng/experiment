package experiment.designmodle.visitorM2;

public class Father {
	public void accept(Execute ex) {
		ex.method(this);
	}
}
