package tech.mystox.test.aop.annotation;

import java.lang.annotation.*;

/**
 * Created by mystoxlol on 2019/8/27, 14:57.
 * company: kongtrolink
 * description:
 * update record:
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ReportOperaCode {

    boolean value() default false;
}
