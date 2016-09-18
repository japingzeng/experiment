package experiment.vmTest.dynamicProxy;

import java.lang.reflect.Method;


public class Client {
	public static void main(String[] args) throws NoSuchMethodException, SecurityException, ClassNotFoundException {
	//	IHello hello = (IHello) new Dynamicproxy().bind(new Hello());
//		hello.sayHello();
	//	System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
	//	Method m4 = Class.forName("experiment.vmTest.dynamicProxy.IHello").getMethod("printUserInfo", new Class[] { Class.forName("experiment.vmTest.dynamicProxy.User") });
		Class<?> [] interfaces = {IHello.class};
		IHello hello = new Hello();
		String path = hello.getClass().getResource(".").getPath();
		ProxyUtils.generateClassFile(path, interfaces, "helloProxy");
	}
}
