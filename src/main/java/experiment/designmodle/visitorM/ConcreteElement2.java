package experiment.designmodle.visitorM;

public class ConcreteElement2 extends Element {

	@Override
	public void accept(IVisitor visitor) {
		visitor.visit(this);
	}

	@Override
	public void dosomgthing() {
		System.out.println("这是元素2");
	}

}
