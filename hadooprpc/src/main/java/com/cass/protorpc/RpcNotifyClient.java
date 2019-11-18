package com.cass.protorpc;

import com.cass.rpc.protorpc.RpcNotifyProto;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.ipc.ProtobufRpcEngine;
import org.apache.hadoop.ipc.RPC;

import java.io.IOException;
import java.net.InetSocketAddress;

/**
 * Created by mystoxlol on 2019/1/23, 13:08.
 * company: kongtrolink
 * description:
 * update record:
 */
public class RpcNotifyClient
{
    final static long VERSION = 2;
    public static void main(String[] args) throws IOException
    {
        Configuration conf = new Configuration();
        String ADDRESS = "172.16.6.142";
        int port = 9981;

        ADDRESS = "172.16.6.39";
        port = 9998;
        port = 18888;
        RpcNotifyProto.RpcMessage message = RpcNotifyProto.RpcMessage.newBuilder().setContent("hello i'm clinet111").build();
        byte[] bytes = message.toByteArray();
        System.out.println(bytes.length);
        RPC.setProtocolEngine(conf, RpcNotify.class, ProtobufRpcEngine.class);
        RpcNotify proxy = RPC.getProxy(RpcNotify.class, VERSION,  new InetSocketAddress(ADDRESS,port),
                conf);
//        CASSRpcProto.
//        RpcNotify notify = new RpcNotifylTranslatorPB(proxy);
        RpcNotifyProto.RpcMessage  result = proxy.notif(null,message);
//        for (int i=0;i<10; i++)
//            result = proxy.Notify(null,message);
        System.out.println("client result:" + result);
    }
}
