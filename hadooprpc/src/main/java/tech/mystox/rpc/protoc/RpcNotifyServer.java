package tech.mystox.rpc.protoc;

import com.cass.protorpc.RpcNotify;
import com.cass.rpc.protorpc.RpcNotifyProto;
import com.google.protobuf.BlockingService;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.ipc.ProtobufRpcEngine;
import org.apache.hadoop.ipc.RPC;

import java.io.IOException;

/**
 * Created by mystoxlol on 2019/1/23, 13:07.
 * company: kongtrolink
 * description:
 * update record:
 */
public class RpcNotifyServer
{
    public static void main(String[] args) throws IOException
    {
        Configuration conf = new Configuration();
        String ADDRESS = "172.16.6.39";
        int port = 9998;
        RPC.setProtocolEngine(conf, com.cass.protorpc.RpcNotify.class, ProtobufRpcEngine.class);
        RPC.Server server = new RPC.Builder(conf)
                .setBindAddress(ADDRESS)
                .setPort(port).setNumHandlers(1)
                .setVerbose(true)
                .setProtocol(RpcNotify.class)
                    .setInstance((BlockingService) RpcNotifyProto.RpcNotify.newReflectiveBlockingService(new ChildRpcNotifyImpl()))

                .build();
        server.start();
    }
}
