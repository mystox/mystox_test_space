package tech.mystox.test.aop.impl;

import com.alibaba.fastjson.JSONObject;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;
import tech.mystox.test.aop.annotation.ReportOperaCode;

/**
 * Created by mystoxlol on 2019/10/24, 20:19.
 * company: kongtrolink
 * description:
 * update record:
 */
@Aspect
@Component
public class TestAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(TestAspect.class);

    //    @Pointcut("execution(* tech.mystox.test.aop.impl..*.*(..))")
    public void executePackage() {
    }


    /**
     * 1、execution(): 表达式主体。
     * <p>
     *  2、第一个*号：表示返回类型，*号表示所有的类型。
     * <p>
     *  3、包名：表示需要拦截的包名，后面的两个句点表示当前包和当前包的所有子包，com.example.demo.service.impl包、子孙包下所有类的方法。
     * <p>
     *  4、第二个*号：表示类名，*号表示所有的类。
     * <p>
     *  5、*(..):最后这个星号表示方法名，*号表示所有的方法，后面括弧里面表示方法的参数，两个句点表示任何参数。
     *
     * @param joinPoint
     * @return
     * @throws Throwable
     */
//    @Around( value = "execution(* tech.mystox.test.aop.service.*.*(..))" )
    public Object service(ProceedingJoinPoint joinPoint) throws Throwable {
        String method = joinPoint.getTarget().getClass().getSimpleName() + "." + joinPoint.getSignature().getName() + "(..)";
        System.out.println("-----");
        Object[] args = joinPoint.getArgs();
        System.out.println(JSONObject.toJSONString(args));
        joinPoint.proceed();
        return "";
    }

    @Autowired
    ThreadPoolTaskExecutor testExecutor;


    @Around(value = "@annotation(tech.mystox.test.aop.annotation.ReportOperaCode) && @annotation(reportOperaCode)")
    public Object annotationTest(ProceedingJoinPoint joinPoint, ReportOperaCode reportOperaCode) throws Throwable {
        boolean value = reportOperaCode.value();
        System.out.println(value);
        String method = joinPoint.getTarget().getClass().getSimpleName() + "." + joinPoint.getSignature().getName() + "(..)";
        System.out.println("annotationTest");
        Object[] args = joinPoint.getArgs();
        System.out.println(JSONObject.toJSONString(args));
        joinPoint.proceed();
        testExecutor.execute(() -> {
            try {
                joinPoint.proceed();
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        });

        return "";
    }


    /*@Before( value = "@annotation(tech.mystox.test.aop.annotation.ReportOperaCode)" )
    public Object annotationTestBefor(JoinPoint joinPoint ) throws Throwable {
        String method = joinPoint.getTarget().getClass().getSimpleName() + "." + joinPoint.getSignature().getName() + "(..)";
        System.out.println("annotationTest");
        Object[] args = joinPoint.getArgs();
        System.out.println(JSONObject.toJSONString(args));
        return "";
    }*/
}
