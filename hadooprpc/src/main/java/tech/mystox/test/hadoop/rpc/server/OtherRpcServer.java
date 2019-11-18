package tech.mystox.test.hadoop.rpc.server;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.ipc.ProtobufRpcEngine;
import org.apache.hadoop.ipc.RPC;

import java.io.IOException;

/**
 * Created by mystoxlol on 2019/1/22, 16:09.
 * company: kongtrolink
 * description:
 * update record:
 */
public class OtherRpcServer
{
    public static void main(String[] args) throws IOException
    {
        Configuration conf = new Configuration();
        RPC.setProtocolEngine(conf, ClientProtocol.class, ProtobufRpcEngine.class);
        RPC.Builder builder = new RPC.Builder(conf);
        builder.setBindAddress("localhost")
                .setPort(8887)
                .setProtocol(ClientProtocol.class)
                .setInstance(new MyNode())
        ;
        builder.build().start();


    }
}
