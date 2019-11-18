package tech.mystox.rpc.protoc;

import com.cass.protorpc.RpcNotify;
import com.cass.rpc.protorpc.RpcNotifyProto;
import com.google.protobuf.RpcController;

/**
 * Created by mystoxlol on 2019/1/23, 13:07.
 * company: kongtrolink
 * description:
 * update record:
 */
public abstract class RpcNotifyImpl implements RpcNotify, RpcNotifyProto.RpcNotify.BlockingInterface
{

    @Override
    public RpcNotifyProto.RpcMessage notif(RpcController controller, RpcNotifyProto.RpcMessage rpcMessage)
    {
        System.out.println(rpcMessage.toString());
        try
        {
            System.out.println("接收数据....................");
            Thread.sleep(10000l);
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        /*return RpcNotifyProto.RpcMessage.newBuilder().setStrResponse("abc")
                .setService("abc")
                .setId(11)
                .setType(RpcNotifyProto.MessageType.RESPONSE)
                .build();*/
        return RpcNotifyProto.RpcMessage.newBuilder().setContent(execute(rpcMessage.getContent()))
                .build();
    }


    abstract String execute(String context);
}
