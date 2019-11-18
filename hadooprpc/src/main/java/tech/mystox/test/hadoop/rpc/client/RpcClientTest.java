package tech.mystox.test.hadoop.rpc.client;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.ipc.ProtobufRpcEngine;
import org.apache.hadoop.ipc.RPC;
import tech.mystox.test.hadoop.rpc.server.ClientProtocol;

import java.io.IOException;
import java.net.InetSocketAddress;

/**
 * Created by mystoxlol on 2019/1/22, 16:31.
 * company: kongtrolink
 * description:
 * update record:
 */
public class RpcClientTest
{
    public static void main(String[] args) throws IOException
    {
        Configuration conf = new Configuration();
                RPC.setProtocolEngine(conf,ClientProtocol.class, ProtobufRpcEngine.class);
        ClientProtocol proxy = RPC.getProxy(ClientProtocol.class,11L, new InetSocketAddress("localhost", 8887), conf);

        String result = proxy.getMetaData("123123");
        System.out.println(result);
    }
}
