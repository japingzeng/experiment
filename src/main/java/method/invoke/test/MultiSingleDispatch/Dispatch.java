package method.invoke.test.MultiSingleDispatch;
/**
 * 
 * @ClassName: Dispatch 
 * @Description: TODO(单分派、多分派演示) 
 * @author japing 
 *
 */
public class Dispatch {
	
	static class Wechat {
		
	}
	
	static class QQ {
		
	}
	
	/**
	 * 
	 * @ClassName: Father 
	 * @Description: TODO(Father 类型) 
	 * @author japing 
	 *
	 */
	public static class Father {
		
		public void hardChoice (QQ qq) {
			System.out.println("Father choose QQ");
		}
		
		public void hardChoice (Wechat wechat) {
			System.out.println("Father choose wechat");
		}
	}
	
	/**
	 * 
	 * @ClassName: Son 
	 * @Description: TODO(Son 类型，继承与Father类型) 
	 * @author japing 
	 *
	 */
	public static class Son extends Father {
		
		public void hardChoice (QQ qq) {
			System.out.println("Son choose QQ");
		}
		
		public void hardChoice (Wechat wechat) {
			System.out.println("Son choose wechat");
		}
	}
	
	public static void main(String[] args) {
		Father father = new Father();
		Father son = new Son();
		
		father.hardChoice(new Wechat());
		son.hardChoice(new QQ());
	}
	
	
}
