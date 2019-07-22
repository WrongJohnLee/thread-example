package proxy;

import javax.management.MBeanServerInvocationHandler;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @ClassName ProxyMainTest
 * @Description test
 * @Author qzli
 * @Date 2019/7/22 23:17
 * @Version 1.0
 **/
public class ProxyMainTest {
    public static void main(String[] args) {
        ProxyInterface instance = new ProxyInterface() {
            @Override
            public void sing() {
                System.out.println("我是cxk，我会唱！");
            }
    
            @Override
            public void jump() {
                System.out.println("我是cxk，我会跳！");
            }
    
            @Override
            public void rap() {
                System.out.println("我是cxk，我会rap！");
            }
        };
        InvocationHandler handler = new EnpointMethodInvokeHandler(instance);
        ProxyInterface proxyInstance = (ProxyInterface) Proxy.newProxyInstance(ProxyMainTest.class.getClassLoader(), new Class[]{ProxyInterface.class}, handler);
        proxyInstance.sing();
        proxyInstance.jump();
        proxyInstance.rap();
    }
    
    /** 类似生成字节码由类加载器加载成这个样子
     public class $Proxy1 extends Proxy implements ISubject {
     private static Method m1;
     private static Method m2;
     private static Method m3;
     private static Method m0;
 
     public $Proxy1(InvocationHandler var1) throws  {
     super(var1);
     }
 
     public final boolean equals(Object var1) throws  {
     try {
     return ((Boolean)super.h.invoke(this, m1, new Object[]{var1})).booleanValue();
     } catch (RuntimeException | Error var3) {
     throw var3;
     } catch (Throwable var4) {
     throw new UndeclaredThrowableException(var4);
     }
     }
 
     public final String toString() throws  {
     try {
     return (String)super.h.invoke(this, m2, (Object[])null);
     } catch (RuntimeException | Error var2) {
     throw var2;
     } catch (Throwable var3) {
     throw new UndeclaredThrowableException(var3);
     }
     }
 
     public final void execute() throws  {
     try {
     super.h.invoke(this, m3, (Object[])null);
     } catch (RuntimeException | Error var2) {
     throw var2;
     } catch (Throwable var3) {
     throw new UndeclaredThrowableException(var3);
     }
     }
 
     public final int hashCode() throws  {
     try {
     return ((Integer)super.h.invoke(this, m0, (Object[])null)).intValue();
     } catch (RuntimeException | Error var2) {
     throw var2;
     } catch (Throwable var3) {
     throw new UndeclaredThrowableException(var3);
     }
     }
 
     static {
     try {
     m1 = Class.forName("java.lang.Object").getMethod("equals", new Class[]{Class.forName("java.lang.Object")});
     m2 = Class.forName("java.lang.Object").getMethod("toString", new Class[0]);
     m3 = Class.forName("com.crossoverjie.proxy.jdk.ISubject").getMethod("execute", new Class[0]);
     m0 = Class.forName("java.lang.Object").getMethod("hashCode", new Class[0]);
     } catch (NoSuchMethodException var2) {
     throw new NoSuchMethodError(var2.getMessage());
     } catch (ClassNotFoundException var3) {
     throw new NoClassDefFoundError(var3.getMessage());
     }
     }
     }
     */
}
