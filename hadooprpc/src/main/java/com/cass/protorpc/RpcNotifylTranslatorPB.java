package com.cass.protorpc;

import com.cass.rpc.protorpc.RpcNotifyProto;

/**
 * Created by mystoxlol on 2019/1/23, 13:50.
 * company: kongtrolink
 * description:
 * update record:
 */
public class RpcNotifylTranslatorPB
{
    final private RpcNotifyPB rpcProxy;

    public  RpcNotifylTranslatorPB(RpcNotifyPB proxy) {
        this.rpcProxy = proxy;

    }

    public RpcNotifyProto.RpcMessage notif(RpcNotifyProto.RpcMessage rpcMessage)
    {
        RpcNotifyProto.RpcMessage req = RpcNotifyProto.RpcMessage.newBuilder().mergeFrom(rpcMessage).build();
        return rpcProxy.notif(null, req);
    }
}
