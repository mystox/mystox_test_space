package com.kongtrolink.framework.rpc.protobuf;

import com.google.protobuf.RpcController;
import org.apache.hadoop.ipc.ProtocolInfo;

/**
 * Created by mystoxlol on 2019/3/18, 10:35.
 * company: kongtrolink
 * description:
 * update record:
 */
@ProtocolInfo(protocolName = "com.kongtrolink.framework.rpc.com.kongtrolink.framework.rpc.protobuf.PreconditionNotify",
        protocolVersion = ProtocolEnum.VERSION)
public interface PreconditionNotify
{
    PreconditionProto.PreconditionMessage requestMsg(RpcController controller, PreconditionProto.PreconditionMessage message);
}
