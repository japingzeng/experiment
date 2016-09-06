package experiment.designmodle.iteratorM;

public interface Aggregate<T> {
	public void add(T t);
	public void remove(T t);
	public Iterator<T> iterator();
}
