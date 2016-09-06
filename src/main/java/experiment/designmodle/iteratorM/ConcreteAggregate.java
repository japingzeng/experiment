package experiment.designmodle.iteratorM;

import java.util.ArrayList;
import java.util.List;

public class ConcreteAggregate<T> implements Aggregate<T> {
	private List<T> list = new ArrayList<T>();
	public void add(T t) {
		list.add(t);
	}

	public void remove(T t) {
		list.remove(t);
	}

	public Iterator<T> iterator() {
		return new ConcreteIterator<T>(list);
	}
}
