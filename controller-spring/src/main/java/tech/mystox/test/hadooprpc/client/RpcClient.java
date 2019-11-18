package tech.mystox.test.hadooprpc.client;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.ipc.RPC;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.InetSocketAddress;

/**
 * Created by mystoxlol on 2019/1/21, 13:10.
 * company: kongtrolink
 * description:
 * update record:
 */
@Component
public class RpcClient
{
    public <T> T getProtocol(Class<T> clazz, long versionID, InetSocketAddress addr) throws IOException
    {

        return RPC.getProxy(clazz,versionID,addr, new Configuration());

    }
}
