package rpcTest.protobuf;

import com.google.protobuf.BlockingService;
import org.apache.hadoop.HadoopIllegalArgumentException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.ipc.ProtobufRpcEngine;
import org.apache.hadoop.ipc.RPC;

import java.io.IOException;

/**
 * Created by mystoxlol on 2019/1/23, 9:38.
 * company: kongtrolink
 * description:
 * update record:
 */
public class ProtoRpcServer
{
    public static void main(String[] args) throws HadoopIllegalArgumentException, IOException
    {
        Configuration conf = new Configuration();
        String ADDRESS = "localhost";
        int port = 9997;
        RPC.setProtocolEngine(conf, AddProtocolPB.class, ProtobufRpcEngine.class);

        RPC.Server  server = new RPC.Builder(conf)
                .setProtocol(
                        AddProtocolPB.class)
                .setInstance((BlockingService)Add.AddService.newReflectiveBlockingService(new AddProtocolServerSidePB(new AddImpl())))
                .setBindAddress(ADDRESS)
                .setPort(port).setNumHandlers(1)
                .setVerbose(true)
                .build();
        server.start();
    }

}
