package tech.mystox.test.aop.impl;

import org.springframework.stereotype.Service;
import tech.mystox.test.aop.annotation.ReportOperaCode;
import tech.mystox.test.aop.service.TestAnnotionService;

import java.util.Date;

/**
 * Created by mystoxlol on 2019/10/24, 20:18.
 * company: kongtrolink
 * description:
 * update record:
 */
@Service
public class TestAspectServiceImpl implements TestAnnotionService {

    @ReportOperaCode(true)
    public String testAspect(String str)
    {
        System.out.println(str+"========" + new Date().getTime() + "===========");
        return str + "yes";
    }
}
