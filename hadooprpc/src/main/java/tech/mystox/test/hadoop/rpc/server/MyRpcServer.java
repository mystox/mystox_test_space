package tech.mystox.test.hadoop.rpc.server;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.ipc.RPC;

import java.io.IOException;

/**
 * Created by mystoxlol on 2019/1/18, 11:30.
 * company: kongtrolink
 * description:
 * update record:
 */
public class MyRpcServer
{
    public static void main(String[] args) throws IOException
    {
        Configuration conf = new Configuration();
//        System.setProperty("hadoop.home.dir", "/");
//        RPC.setProtocolEngine(conf,NameNodeProtocolPB.class, ProtobufRpcEngine.class);
        RPC.Builder builder = new RPC.Builder(conf);
        builder.setBindAddress("localhost")
                .setPort(8888)
                .setProtocol(ClientNamenodeProtocol.class)
                .setInstance(new MyNameNode())
                .setProtocol(NameNodeProtocolPB.class).setInstance(new NameNodePB())
                ;
        RPC.Server server = builder.build();
//        server.addProtocol(RPC.RpcKind.RPC_PROTOCOL_BUFFER, NameNodeProtocolPB.class, new NameNodePB());
//        server.registerProtocolEngine(RPC.RpcKind.RPC_PROTOCOL_BUFFER,NameNodeProtocolPB.class,new NameNodePB());
        server.start();
        System.out.println("start...end");
    }
}
