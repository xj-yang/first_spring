package com.atguigu.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author yjq
 * @create 2022-05-21-13:14
 */
public class ProxyCalculator {

    public static Calculate getProxyCalculator(final Calculattor calculattor){
        Object proxy = Proxy.newProxyInstance(calculattor.getClass().getClassLoader(),
                calculattor.getClass().getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                        before();

                        Object invoke = method.invoke(calculattor, args);
                        after();
                        return invoke;
                    }
                });

        return (Calculate) proxy;
    }

    public static void before(){
        System.out.println("之前调用。。。。");
    }
    public static void after(){
        System.out.println("之后调用。。。。");
    }

}
