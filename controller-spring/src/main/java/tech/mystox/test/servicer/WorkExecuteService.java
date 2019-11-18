package tech.mystox.test.servicer;

import org.springframework.stereotype.Service;
import tech.mystox.test.hadoop.rpc.protocol.ClientWorkProtocol;

/**
 * Created by mystoxlol on 2019/1/19, 11:29.
 * company: kongtrolink
 * description:
 * update record:
 */
@Service
public class WorkExecuteService implements ClientWorkProtocol
{
    @Override
    public String messageExecute(String message)
    {
        String execute = "";
        System.out.println(execute);
        return "hello";
    }

    @Override
    public String jsonMessageExecute(String jsonMessage)
    {
        return null;
    }

}
