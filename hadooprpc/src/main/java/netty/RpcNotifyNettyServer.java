package netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * Created by mystoxlol on 2019/1/25, 17:08.
 * company: kongtrolink
 * description:
 * update record:
 */
public class RpcNotifyNettyServer
{
    public void bind(int port) throws Exception
    {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workGroup = new NioEventLoopGroup();

        ServerBootstrap b = new ServerBootstrap();
        b.group(workGroup, bossGroup).channel(NioServerSocketChannel.class)
        .option(ChannelOption.SO_BACKLOG,100)
                .handler(new LoggingHandler(LogLevel.DEBUG))
                .childHandler(new ChannelInitializer<SocketChannel>()
                {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception
                    {
                        // 添加ProtobufVarint32FrameDecoder，主要用于Protobuf的半包处理
                        socketChannel.pipeline().addLast(
                                new ProtobufVarint32FrameDecoder()
                        );
//                        socketChannel.pipeline().addLast(
//                                new ProtobufDecoder(
//                                        RpcNotifyProto.RpcMessage.getDefaultInstance()
//                                )
//                        );
                        socketChannel.pipeline().addLast(new ProtobufVarint32LengthFieldPrepender());
                        socketChannel.pipeline().addLast(new ProtobufEncoder());
                        socketChannel.pipeline().addLast(new NotifyNettyHandler());
                    }
                });

        // 发起异步连接操作
        ChannelFuture f = b.bind("172.16.6.107",port).sync();
        f.channel().close().sync();


    }


    public static void main(String[] args) throws Exception
    {

        int port = 9998;
        new RpcNotifyNettyServer().bind(9998);

    }

}
