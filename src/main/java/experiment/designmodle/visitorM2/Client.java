package experiment.designmodle.visitorM2;
/**
 * 
 * @ClassName: Client 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author japing 
 * @date 2016年9月12日 下午7:23:21 
 *
 */
public class Client {
	public static void main(String[] args) {
		Father father = new Father();
		Son1 son1 = new Son1();
		Son2 son2 = new Son2();
		
		Execute ex = new Execute();
		
		father.accept(ex);
		son1.accept(ex);
		son2.accept(ex);
	}
	
}
