package tech.mystox.socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by mystoxlol on 2019/1/24, 14:29.
 * company: kongtrolink
 * description:
 * update record:
 */
public class HServer implements Runnable
{
    public  int port;

    public HServer(int port)
    {
        this.port = port;
    }

    @Override
    public void run()
    {
        try
        {
            ServerSocket serverSocket = new ServerSocket(port);
            while (true)
            {
                //等待client的请求
                System.out.println("waiting...");
                Socket socket = serverSocket.accept();
                // 接收客户端的数据
                DataInputStream in = new DataInputStream(socket.getInputStream());

                byte[] data = new byte[56000];
                    in.read(data);
                for (int i = 0; i<data.length; i++)
                    System.out.print(data[i]);
//                RpcNotifyProto.RpcMessage message =RpcNotifyProto.RpcMessage.parseFrom(data);
//                System.out.println(message);
//                String string = in.readUTF();
//                System.out.println("client:" + string);
                // 发送给客户端数据
                DataOutputStream out = new DataOutputStream(socket.getOutputStream());
//                out.writeUTF("hi,i am hserver!i say:" + string);
                socket.close();
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        HServer serverApp = new HServer(9998);
        serverApp.run();
    }


}
