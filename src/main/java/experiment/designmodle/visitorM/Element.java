package experiment.designmodle.visitorM;

public abstract class Element {
	@SuppressWarnings("rawtypes")
	public abstract void accept(IVisitor visitor);
	public abstract void dosomgthing();
}
