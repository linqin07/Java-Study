package 动态Proxy.Proxy特性;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ObjectProxy implements InvocationHandler {
    private static final String METHOD_PREFIX = "test";
    private Object target;

    public ObjectProxy(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable {
        // 我们使用这个标志来识别是否使用代理还是使用方法本体
        if (method.getName().startsWith(METHOD_PREFIX)) {
            System.out.println("========分隔符========");
        }
        return method.invoke(target, args);
    }

    public Object getProxy() {
        return Proxy.newProxyInstance(Thread.currentThread()
                                            .getContextClassLoader(), target.getClass().getInterfaces(),
                this);
    }
}
