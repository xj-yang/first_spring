package com.atguigu.aspectj;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * 切面类：包含的是切入点和通知的信息
 * 1.使用的基本格式：
 *      try{
 *          @Before
 *          method.invoke(obj,args);目标方法执行
 *          @AfterReturning
 *      }catch(){
 *          @AfterThrowing
 *      }finally{
 *          @After
 *      }
 *
 * 2.切入点表达式：
 *   在通知方法上的注解上书写：@Before("execution(public int com.atguigu.aspectj.Calculator.add(int,int))")
 *   定义在切入方法上：
 *           @Pointcut("execution(public int com.atguigu.aspectj.Calculator.add(int,int))")
 *           public void myCut(){}
 *   通配符*号的意思：
 *           1.匹配一个或多个字符：execution(public int com.atguigu.aspectj.Calculator.*add(int,int))
 *                  指的就是匹配该路径下的Calculator类中的所有以add结尾的方法
 *           2.匹配任意一个参数：execution(public int com.atguigu.aspectj.Calculator.*add(int,*))
 *                  指的是第二个参数可以是任意类型的参数
 *           3.匹配一层路径：execution(public int com.atguigu.aspectj.*.Calculator.add(..))
 *   通配符..号的意思：
 *           1.匹配任意多个参数：execution(public int com.atguigu.aspectj.Calculator.add(..))
 *           2.匹配多层路径：execution(public int com.atguigu.aspectj..Calculator.add(..))
 *                  指的是匹配aspectj包下的以及他的子包下的Calculator.add(..)方法。
 *
 *
 * 3.通知方法的执行顺序：
 *           方法正常执行的时候： @Before------>@After----->@AfterReturning
 *           方法异常执行的时候： @Before------>@After----->@AfterThrowing
 *
 *
 * 4.在通知方法执行的时候获取到目标方法的详细信息：
 *           JoinPoint joinPoint:指的是连接点信息，可以获取到连接掉点的信息。
 *           joinPoint.getArgs()：获取目标方法运行时使用的参数。
 *           joinPoint.getSignature()：获取到目标方法的方法签名。
 *           joinPoint.getSignature().getName():获取目标方的方的名。
 *
 * 5.当目标方法有返回值我们获取这个返回值：
 *           在通知方法上定义一个形参Object result,并且在返回通知的注解上指定哪个参数就是返回的结果。
 *           @AfterReturning(value = "myCut()",returning = "result")
 *           public void returnBy(JoinPoint joinPoint,Object result){}
 *
 * 6.当目标方法有异常时我们获取这个异常信息：
 *           在通知方法上定义一个形参Exception exception,并且在异常通知的注解上指定哪个参数就是异常的信息。
 *           @AfterThrowing(value = "myCut()",throwing ="exception" )
 *           public void exceptionBy(JoinPoint joinPoint,Exception exception ){}
 *
 *
 * 7.抽取可重入的切入点表达式：
 *           1.随便定义一个方法（又叫切入方法）
 *           2.在方法上添加一个@Pointcut注解
 *                  @Pointcut("execution(public int com.atguigu.aspectj.Calculator.add(int,int))")
 *                  public void myCut(){}
 *           使用：在通知方法的通知注解上添加切入方法
 *                  @AfterReturning(value = "myCut()",returning = "result")
 *                  public void returnBy(JoinPoint joinPoint,Object result){}
 *
 *
 * 8.环绕通知（可以替代其他四个通知）：重点
 *      强大之处在与它有一个参数：ProceedingJoinPoint pjp
 *
 *      环绕通知是优先于普通通知执行的。
 *      理论顺序：
 *          普通前置
 *          方法执行（
 *              try{
 *                  环绕前置
 *  *              方法执行
 *  *              环绕返回
 *              }catch(){
 *                  环绕异常
 *              }finally{
 *                  环绕后置
 *              }
 *          ）
 *          普通后置
 *          普通方法返回/方法异常
 *
 *       真实顺序：    环绕前置->普通前置->方法执行->环绕返回/环绕异常->环绕后置->普通返回/普通异常->普通后置
 *
 *    注意：
 *          1.因为spring在实现aop编程时，是通过反射去调用目标方法的 method.invoke(obj,args);所以在通知方法的
 *          参数我们是不能乱填的，必须是spring aop知道的参数才行，或者我们进行绑定（如返回结果的参数）。
 *          2. 接收异常的形参 异常的范围要尽量写大；
 *
 *          3.对于环绕通知和普通通知的选择？
 *              如果是需要操作目标方法本身的我们选择添加环绕通知；
 *              如果只是对方法进行记录的我们使用普通通知即可。
 *
 *          4.当多个切面操作同一个方法时执行的顺序问题？
 *              其实就是先入先出的问题 ，切面1->切面2->目标方法->切面2->切面1
 *
 *
 * @author yjq
 * @create 2022-05-21-13:32
 */
@Component
@Aspect
public class MyAspectj {
    @Pointcut("execution(public int com.atguigu.aspectj.Calculator.add(int,int))")
    public void myCut(){}


    //@Before("execution(* com.atguigu.aspectj..Calculator.*(..))")
    public void before(JoinPoint joinPoint){
        System.out.println("目标方法的参数是："+Arrays.asList(joinPoint.getArgs()));
        Signature signature = joinPoint.getSignature();
        System.out.println("目标方法的方法名是："+signature.getName());
        System.out.println("before ......");
    }


    //@After("myCut()")
    public void after(){
        System.out.println("after ......");
    }
    //@AfterReturning(value = "myCut()",returning = "result")
    public void returnBy(JoinPoint joinPoint,Object result){
        System.out.println("目标方法的参数是："+Arrays.asList(joinPoint.getArgs()));
        Signature signature = joinPoint.getSignature();
        System.out.println("目标方法的方法名是："+signature.getName()+"返回的结果是："+result);
    }

    //@AfterThrowing(value = "myCut()",throwing ="exception" )
    public void exceptionBy(JoinPoint joinPoint,Exception exception ){
        System.out.println("目标方法的参数是："+Arrays.asList(joinPoint.getArgs()));
        Signature signature = joinPoint.getSignature();
        System.out.println("目标方法的方法名是："+signature.getName()+"抛的异常为："+exception);
    }

    /**
     * 环绕通知
     * @param pjp ：包含目标方法信息的参数
     *
     *  解析：
     *  pjp.getArgs()：获取目标方法的参数
     *  pjp.proceed(args)：相当与反射中的method.invoke(obj,args)方法。只有调用了它目标方法才会执行
     *
     *  原由：之所以我们把环绕通知称为四合一通知就是因为它有一个调用目标方的方法，可以控制目标方法的执行。
     *  操作：
     *      1.我们可以进入环绕方法后对方法的信息进行判断然后可以根据信息决定是否执行这个目标方法。
     *      2.我们可以对pjp.proceed(args);进行try catch处理，然后添加上其它通知要进行操作的内容。
     */
    @Around("myCut()")
    public Object myAround(ProceedingJoinPoint pjp){

        Object[] methodArgs = pjp.getArgs();//获取参数
        String methodName = pjp.getSignature().getName();//获取方法名
        Object result = null;
        try {
            System.out.println("这是目标方法:"+methodName+"的前置通知");
            result = pjp.proceed(methodArgs);
            System.out.println("这是目标方法:"+methodName+"的返回通知。返回结果为："+result);
        }catch (Throwable throwable) {
            Exception exception= (Exception) throwable;
            System.out.println("这是目标方法:"+methodName+"的异常通知,异常信息为："+exception.getMessage());
        } finally {
            System.out.println("这是目标方法:"+methodName+"的后置通知");
        }

        return result;
    }
}
