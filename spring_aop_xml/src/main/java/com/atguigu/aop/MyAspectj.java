package com.atguigu.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;


@Component
@Aspect
public class MyAspectj {

    public void myCut(){}



    public void before(JoinPoint joinPoint){
        Signature signature = joinPoint.getSignature();
        System.out.println("【前置通知】目标方法的方法名是："+signature.getName());
        System.out.println("目标方法的参数是："+Arrays.asList(joinPoint.getArgs()));
    }

    public void after(JoinPoint joinPoint){
        Signature signature = joinPoint.getSignature();
        System.out.println("【后置通知】目标方法的方法名是："+signature.getName());
        System.out.println("目标方法的参数是："+Arrays.asList(joinPoint.getArgs()));
    }





    public void returnBy(JoinPoint joinPoint,Object result){
        Signature signature = joinPoint.getSignature();
        System.out.println("【返回通知】目标方法的方法名是："+signature.getName()+"返回的结果是："+result);
        System.out.println("目标方法的参数是："+Arrays.asList(joinPoint.getArgs()));
    }


    public void exceptionBy(JoinPoint joinPoint,Exception exception ){
        Signature signature = joinPoint.getSignature();
        System.out.println("【异常通知】目标方法的方法名是："+signature.getName()+"抛的异常为："+exception);
        System.out.println("目标方法的参数是："+Arrays.asList(joinPoint.getArgs()));
    }


    public Object myAround(ProceedingJoinPoint pjp){

        Object[] methodArgs = pjp.getArgs();//获取参数
        String methodName = pjp.getSignature().getName();//获取方法名
        Object result = null;
        try {
            System.out.println("【环绕通知】这是目标方法:"+methodName+"的前置通知");
            result = pjp.proceed(methodArgs);
            System.out.println("【环绕通知】这是目标方法:"+methodName+"的返回通知。返回结果为："+result);
        }catch (Throwable throwable) {
            Exception exception= (Exception) throwable;
            System.out.println("【环绕通知】这是目标方法:"+methodName+"的异常通知,异常信息为："+exception.getMessage());
        } finally {
            System.out.println("【环绕通知】这是目标方法:"+methodName+"的后置通知");
        }

        return result;
    }
}
