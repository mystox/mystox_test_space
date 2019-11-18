package tech.mystox.springboot.runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import tech.mystox.springboot.service.ZkTemplate;

/**
 * Created by mystoxlol on 2019/1/17, 15:40.
 * company: kongtrolink
 * description:
 * update record:
 */
//@Component
public class ZkInit implements ApplicationRunner
{


    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception
    {
        testzookeeper();
    }
    @Autowired
    ZkTemplate zkTemplate;
    public void testzookeeper()
    {
        zkTemplate.register();
    }


}
