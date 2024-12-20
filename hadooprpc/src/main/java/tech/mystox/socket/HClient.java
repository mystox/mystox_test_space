package tech.mystox.socket;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.nio.charset.Charset;

/**
 * Created by mystoxlol on 2019/1/24, 14:34.
 * company: kongtrolink
 * description:
 * update record:
 */
public class HClient
{
    public static void main(String[] args) throws IOException, InterruptedException {
        Socket socket = new Socket("172.21.77.23", 9998);
        System.out.println("please input...");
//        RpcNotifyProto.RpcMessage message = RpcNotifyProto.RpcMessage.newBuilder()
//                .setId(1)
//                .setType(RpcNotifyProto.MessageType.REQUEST)
//                .setService("service")
//                .setStrRequest("sdfadasdfsafsafsafsfsfsafsafsafsaf").build();

        // 发送给服务器的数据
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
        String message = "sadfasfsASDFSAFSAFAFAFDSFSAFSAFa111111111111111111111111111f";
        byte[] bytes = message.getBytes(Charset.defaultCharset());
//        System.out.println(bytes.length);
//        for (int i = 0; i<bytes.length; i++)
//        System.out.print(bytes[i]);
//        System.out.println(bytes.toString());
        out.write(bytes);
        Thread.sleep(1000);
    }
}
