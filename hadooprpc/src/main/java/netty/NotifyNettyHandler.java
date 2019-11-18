package netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * Created by mystoxlol on 2019/1/28, 9:56.
 * company: kongtrolink
 * description:
 * update record:
 */
public class NotifyNettyHandler extends ChannelInboundHandlerAdapter
{
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        RpcNotifyProto.RpcMessage message = (RpcNotifyProto.RpcMessage) msg;
//
//        System.out.println(message.toString());
//        RpcNotifyProto.RpcMessage.Builder rpcMessage = RpcNotifyProto.RpcMessage.newBuilder();
//        rpcMessage.setType(RpcNotifyProto.MessageType.RESPONSE);
//        rpcMessage.setService("server");
//        rpcMessage.setStrResponse("12312313132131232");
//            ctx.writeAndFlush(rpcMessage);

    }
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        // 发生异常，关闭链路
        ctx.close();
    }
}
