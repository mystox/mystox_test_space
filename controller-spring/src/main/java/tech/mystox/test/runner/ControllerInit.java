package tech.mystox.test.runner;

import org.apache.hadoop.ipc.RPC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import tech.mystox.test.servicer.ControllerInstance;

/**
 * Created by mystoxlol on 2019/1/19, 12:08.
 * company: kongtrolink
 * description:
 * update record:
 */
@Component
public class ControllerInit implements ApplicationRunner
{
    @Autowired
    ControllerInstance springInstance;
    @Autowired
    RPC.Server rpc;
    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception
    {
        System.out.println(springInstance);
//        rpc.start();
    }
}
