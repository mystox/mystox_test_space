package netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;

/**
 * Created by mystoxlol on 2019/1/28, 10:21.
 * company: kongtrolink
 * description:
 * update record:
 */
public class RpcNotifyNettyClient
{
    public void connect(int port, String host) throws InterruptedException
    {
        EventLoopGroup group = new NioEventLoopGroup();


        Bootstrap b = new Bootstrap();
        b.group(group).channel(NioSocketChannel.class).option(ChannelOption.TCP_NODELAY,true)
        .handler(new ChannelInitializer<SocketChannel>()
        {
            @Override
            protected void initChannel(SocketChannel socketChannel) throws Exception
            {
                // 添加ProtobufVarint32FrameDecoder，主要用于Protobuf的半包处理
                socketChannel.pipeline().addLast(
                        new ProtobufVarint32FrameDecoder()
                );
//                socketChannel.pipeline().addLast(
//                        new ProtobufDecoder(
//                                RpcNotifyProto.RpcMessage.getDefaultInstance()
//                        )
//                );

                socketChannel.pipeline().addLast(new ProtobufVarint32LengthFieldPrepender());
                socketChannel.pipeline().addLast(new ProtobufEncoder());
                socketChannel.pipeline().addLast(new NotifyClientNettyHandler());
            }
        });

        // 发起异步连接操作
        ChannelFuture f = b.connect(host, port).sync();
        // 等待客户端链路关闭
        f.channel().closeFuture().sync();
    }
    public static void main(String[] args) throws InterruptedException
    {
        new RpcNotifyNettyClient().connect(9998,"172.16.6.107");
    }
}
