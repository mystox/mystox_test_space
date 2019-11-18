package tech.mystox.socket;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Created by mystoxlol on 2019/1/24, 14:34.
 * company: kongtrolink
 * description:
 * update record:
 */
public class HClient
{
    public static void main(String[] args) throws IOException
    {
        Socket socket = new Socket("localhost", 9998);
        System.out.println("please input...");
//        RpcNotifyProto.RpcMessage message = RpcNotifyProto.RpcMessage.newBuilder()
//                .setId(1)
//                .setType(RpcNotifyProto.MessageType.REQUEST)
//                .setService("service")
//                .setStrRequest("sdfadasdfsafsafsafsfsfsafsafsafsaf").build();

        // 发送给服务器的数据
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
//        byte[] bytes = message.toByteArray();
//        System.out.println(bytes.length);
//        for (int i = 0; i<bytes.length; i++)
//        System.out.print(bytes[i]);
//        System.out.println(bytes.toString());
//        out.write(bytes);
    }
}
