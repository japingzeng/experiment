package experiment.vmTest.dynamicProxy.cglib;


import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CglibProxy implements IProxy, MethodInterceptor{

    private static CglibProxy instance = new CglibProxy();

    public static CglibProxy getInstance() {
        return instance;
    }


    public <T> T getProxy(Class<T> cls) {
        return (T) Enhancer.create(cls, this);
    }

    @Override
    public void before() {
        System.out.println("before do somgthing");
    }

    @Override
    public void after() {
        System.out.println("after do something");

    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        before();
        Object result = methodProxy.invokeSuper(o, objects);
        after();
        return null;
    }

    public static void main(String[] args) {
        Programer programer = CglibProxy.getInstance().getProxy(Programer.class);

        programer.code();
    }
}
