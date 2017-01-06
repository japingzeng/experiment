package experiment.other;
/**
 * 
 * @ClassName: InctanceOfTest 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author japing 
 * @date 2016年12月22日 下午4:18:56 
 *
 */
public class InctanceOfTest {
	
	public static void main(String[] args) {
		Heloo obj = null;
		try {
			if (obj instanceof Heloo) {
				System.out.println("yes");
			} else {
				System.out.println("no");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static class Heloo {
		private int i;
		private String name;
	}
}
