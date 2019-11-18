package com.cass.protorpc;

import com.cass.rpc.protorpc.RpcNotifyProto;
import com.google.protobuf.RpcController;

/**
 * Created by mystoxlol on 2019/1/23, 13:44.
 * company: kongtrolink
 * description:
 * update record:
 */
public class RpcNotifyServerSidePB implements RpcNotifyPB, RpcNotifyProto.RpcNotify.BlockingInterface
{

final private RpcNotify server;

    public RpcNotifyServerSidePB(RpcNotify server)
    {
        this.server = server;
    }

    @Override
    public RpcNotifyProto.RpcMessage notif(RpcController controller, RpcNotifyProto.RpcMessage rpcMessage)
    {
        return null;
    }

//    @Override
//    public RpcNotifyProto.RpcMessage notify(RpcController controller, RpcNotifyProto.RpcMessage rpcMessage)
//    {
////        return server.notify(rpcMessage);
//        return null;
//    }

}
