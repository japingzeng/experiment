package experiment.vmTest.dynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Dynamicproxy implements InvocationHandler {
	private Object originalObj;
	
	Object bind(Object originalObject) {
		this.originalObj = originalObject;
		return Proxy.newProxyInstance(originalObject.getClass().getClassLoader(),
									originalObject.getClass().getInterfaces(), this);
	}
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		System.out.println("welcome");
		return method.invoke(originalObj, args);
	}

}
