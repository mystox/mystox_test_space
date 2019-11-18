package tech.mystox.test.hadoop.rpc.client;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.ipc.RPC;
import tech.mystox.test.hadoop.rpc.server.NameNodeProtocolPB;

import java.io.IOException;
import java.net.InetSocketAddress;

/**
 * Created by mystoxlol on 2019/1/18, 11:29.
 * company: kongtrolink
 * description:
 * update record:
 */
public class MyRpcClient
{
    public static void main(String[] args) throws IOException
    {

/*//WritableRpcEngine 引擎模式
        long start = System.currentTimeMillis();
        ClientNamenodeProtocol namenode = RPC.getProxy(ClientNamenodeProtocol.class, 1L,
                new InetSocketAddress("localhost", 8888), new Configuration());
        System.out.println("-----------"+(System.currentTimeMillis() - start));
        String metaData = namenode.getMetaData("/angela.mygirl",1);

        System.out.println(metaData);
        ParamEntityTest entity = new ParamEntityTest();
        entity.setName("_---------------------------");
        System.out.println(System.currentTimeMillis() - start);
        namenode.getParamData(entity);*/


        //protocolpb引擎
        Configuration conf = new Configuration();
//        RPC.setProtocolEngine(conf, NameNodeProtocolPB.class, ProtobufRpcEngine.class);
//        NameNodeProtocolPB proxy =
//                RPC.getProtocolProxy(NameNodeProtocolPB.class,RPC.getProtocolVersion(NameNodeProtocolPB.class),
//                        new InetSocketAddress("localhost", 8888),
//                conf, NetUtils.getDefaultSocketFactory(conf)).getProxy();
        long versionId = RPC.getProtocolVersion(NameNodeProtocolPB.class);
        System.out.println(versionId);
        NameNodeProtocolPB proxy = RPC.getProxy(NameNodeProtocolPB.class, versionId,
                new InetSocketAddress("localhost", 8888), conf);
        proxy.ping();
    }
}
