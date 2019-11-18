package rpcTest.protobuf;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.ipc.ProtobufRpcEngine;
import org.apache.hadoop.ipc.RPC;

import java.io.IOException;
import java.net.InetSocketAddress;

/**
 * Created by mystoxlol on 2019/1/23, 9:35.
 * company: kongtrolink
 * description:
 * update record:
 */
public class ProtoRpcClient
{
    final static long VERSION = 1;
    public static void main(String[] args) throws IOException
    {
        Configuration conf = new Configuration();
        String ADDRESS = "localhost";
        int port = 9997;
        RPC.setProtocolEngine(conf, AddProtocolPB.class, ProtobufRpcEngine.class);
        AddProtocolPB proxy = RPC.getProxy(AddProtocolPB.class, VERSION,  new InetSocketAddress(ADDRESS,port),
                conf);
//        CASSRpcProto.
        AddProtocol add = new AddProtocolTranslatorPB(proxy);
        int result = add.add(100, 200);
        System.out.println("client result:" + result);
        // int result = proxy.add(5, 6);
        // System.out.println(result);
    }

}
