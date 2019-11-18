package rpcTest.protobuf;

import com.google.protobuf.RpcController;
import org.apache.hadoop.ipc.ProtocolInfo;

/**
 * Created by mystoxlol on 2019/1/23, 9:11.
 * company: kongtrolink
 * description:
 * update record:
 */
@ProtocolInfo(protocolName = "rpcTest.com.kongtrolink.framework.rpc.protobuf.AddProtocolPB",
        protocolVersion = 1)
public interface AddProtocolPB
{
    public ClientProto.AddResult add(RpcController controller, ClientProto.AddParameters p) ;
}
