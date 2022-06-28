package com.atguigu.jdk;

import org.junit.Test;

/**
 * @author yjq
 * @create 2022-05-21-13:23
 */
public class JdkTest {
    @Test
    public void test01(){
        Calculate proxyCalculator = ProxyCalculator.getProxyCalculator(new Calculattor());
        System.out.println(proxyCalculator.add(12, 123));
    }
}
