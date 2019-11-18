package tech.mystox.test.maintest;

import java.io.IOException;

/**
 * Created by mystoxlol on 2019/1/19, 11:57.
 * company: kongtrolink
 * description:
 * update record:
 */
public class HadoopClientTest
{
    public static void main(String[] args) throws IOException
    {
//        ClientWorkProtocol namenode = RPC.getProxy(ClientWorkProtocol.class, 1L,
//                new InetSocketAddress("localhost", 8201), new Configuration());
//        String rp = namenode.messageExecute("");
//
//        System.out.println(rp);
//
//        long start = System.currentTimeMillis();
//        ClientNamenodeProtocol namenode = RPC.getProxy(ClientNamenodeProtocol.class, 1L,
//                new InetSocketAddress("localhost", 8888), new Configuration());
//        System.out.println("-----------"+(System.currentTimeMillis() - start));
//        String metaData = namenode.getMetaData("/angela.mygirl",1);
//
//        System.out.println(metaData);
//        ParamEntityTest entity = new ParamEntityTest();
//        entity.setName("_---------------------------");
//        System.out.println(System.currentTimeMillis() - start);
//        namenode.getParamData(entity);
        System.out.println(System.currentTimeMillis() + 100000);
    }
}
