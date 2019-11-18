package netty;

import com.cass.rpc.protorpc.RpcNotifyProto;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * Created by mystoxlol on 2019/1/28, 10:27.
 * company: kongtrolink
 * description:
 * update record:
 */
public class NotifyClientNettyHandler extends ChannelInboundHandlerAdapter
{
    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        RpcNotifyProto.RpcMessage.Builder rpcMessage = RpcNotifyProto.RpcMessage.newBuilder();
//        rpcMessage.setType(RpcNotifyProto.MessageType.REQUEST);
//        rpcMessage.setService("server");
//        rpcMessage.setStrResponse("aaaaaaaaaaaaaaa");
            ctx.write(rpcMessage);
        ctx.flush();
    }
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("Service accept server subscribe response : [" + msg + "]");
    }
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
