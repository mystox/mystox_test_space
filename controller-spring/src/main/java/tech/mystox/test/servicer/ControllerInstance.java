package tech.mystox.test.servicer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by mystoxlol on 2019/1/21, 14:04.
 * company: kongtrolink
 * description:
 * update record:
 */
@Component
public class ControllerInstance
{
    @Value("${server.address}")
    private String host;
    @Value("${server.rpc.port}")
    private Integer port;





}
