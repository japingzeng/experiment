package experiment.designmodle.iteratorM;
/**
 * 
 * @ClassName: Client 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author japing 
 * @date 2016年9月6日 下午8:27:48 
 *
 */
public class Client {
	public static void main(String[] args) {
		Aggregate<String> aggregate = new ConcreteAggregate<String>();
		aggregate.add("xiao s");
		aggregate.add("xiao m");
		aggregate.add("xiao z");
		Iterator< String> iterator = aggregate.iterator();
		while (iterator.hasNext()) {
			String str = iterator.next();
			System.out.println(str);
		}
	}
}
