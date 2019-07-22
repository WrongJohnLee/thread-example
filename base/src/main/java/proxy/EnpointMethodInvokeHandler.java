package proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @ClassName EnpointMethodInvokeHandler
 * @Description 生成的代理类以这个为真正代理类
 * @Author qzli
 * @Date 2019/7/22 22:58
 * @Version 1.0
 **/
public class EnpointMethodInvokeHandler implements InvocationHandler {
    
    /**
     * 一般来说，真正实现类，也可以自己灵活运用
     */
    private Object target;
    
    public EnpointMethodInvokeHandler(Object target) {
        this.target = target;
    }
    
    /**
     * 所有接口的都会调这个方法
     * @param proxy 没啥用的，就是这个类的实例
     * @param method 真正调用的方法，调用的话需要真正的接口实例调用
     * @param args 真正调用方法的入参
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("wa！坤坤等场");
        method.invoke(target, args);
        System.out.println("wa！坤坤离场");
        return null;
    }
}
