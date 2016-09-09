package experiment.designmodle.visitorM;

public abstract class Element {
	public abstract void accept(IVisitor visitor);
	public abstract void dosomgthing();
}
