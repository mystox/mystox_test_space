package com.cass.protorpc;

import com.cass.rpc.protorpc.RpcNotifyProto;
import com.google.protobuf.RpcController;
import org.apache.hadoop.ipc.ProtocolInfo;

/**
 * Created by mystoxlol on 2019/1/23, 13:07.
 * company: kongtrolink
 * description:
 * update record:
 */
@ProtocolInfo(protocolName = "com.cass.protorpc.RpcNotifyPB",
        protocolVersion = 1)
public interface RpcNotifyPB
{
    public RpcNotifyProto.RpcMessage notif(RpcController controller, RpcNotifyProto.RpcMessage rpcMessage);


}
