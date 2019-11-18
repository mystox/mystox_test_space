package tech.mystox.test.stereotype;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by mystoxlol on 2019/8/22, 17:08.
 * company: kongtrolink
 * description:
 * update record:
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Register {
}
