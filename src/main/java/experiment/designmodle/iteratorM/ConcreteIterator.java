package experiment.designmodle.iteratorM;

import java.util.ArrayList;
import java.util.List;

public class ConcreteIterator<T> implements Iterator<T> {
	private List<T> list = new ArrayList<T>();
	private int cursor = 0;
	
	public ConcreteIterator(List<T> list) {
		this.list = list;
	}
	public T next() {
		T t = null;
		if (this.hasNext()) {
			t = this.list.get(cursor++);
		}
		return t;
	}

	public boolean hasNext() {
		if (cursor == list.size()) {
			return false;
		}
		return true;
	}

}
