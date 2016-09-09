package experiment.designmodle.visitorM;

import java.util.Iterator;
import java.util.List;

public class Client {
	public static void main(String[] args) {
		List<Element> list = ObjectStructure.getList();
		
		for (Iterator<Element> iterator = list.iterator(); iterator.hasNext();) {
			Element element = (Element) iterator.next();
			element.accept(new Visitor());
		}
	}
}
