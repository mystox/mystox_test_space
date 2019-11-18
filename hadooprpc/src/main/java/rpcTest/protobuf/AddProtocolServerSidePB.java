package rpcTest.protobuf;

import com.google.protobuf.RpcController;

/**
 * Created by mystoxlol on 2019/1/23, 9:32.
 * company: kongtrolink
 * description:
 * update record:
 */
public class AddProtocolServerSidePB implements AddProtocolPB,
        Add.AddService.BlockingInterface
{
    final private AddProtocol server;

    public AddProtocolServerSidePB(AddProtocol server) {
        this.server = server;
    }

    @Override
    public ClientProto.AddResult add(RpcController controller, ClientProto.AddParameters p)
    {
        // TODO Auto-generated method stub
        rpcTest.protobuf.ClientProto.AddResult.Builder builder = ClientProto.AddResult
                .newBuilder();
        int result = server.add(p.getPara1(), p.getPara2());
        builder.setResult(result);
        return builder.build();
    }
}
