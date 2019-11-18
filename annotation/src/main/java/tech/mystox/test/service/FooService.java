package tech.mystox.test.service;

import tech.mystox.test.stereotype.OperaCode;
import tech.mystox.test.stereotype.Register;

/**
 * Created by mystoxlol on 2019/8/23, 13:41.
 * company: kongtrolink
 * description:
 * update record:
 */
@Register
public interface FooService {
    @OperaCode(code = "fooHi")
    void foo();
    @OperaCode
    void bar();
}
