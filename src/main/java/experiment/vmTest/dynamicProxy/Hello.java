package experiment.vmTest.dynamicProxy;
/**
 * 
 * @ClassName: Hello 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author japing 
 * @date 2016年9月18日 下午3:33:16 
 *
 */
public class Hello implements IHello {

	@Override
	public void sayHello() {
		System.out.println("hello world");
	}

	@Override
	public void printUserInfo(User user, User user2) {
		
	}

}
