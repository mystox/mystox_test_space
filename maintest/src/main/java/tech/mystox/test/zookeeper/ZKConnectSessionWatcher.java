package tech.mystox.test.zookeeper;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

/**
 * Created by mystoxlol on 2019/9/3, 16:40.
 * company: kongtrolink
 * description:
 * update record:
 */
public class ZKConnectSessionWatcher implements Watcher {


    public final static String zkServerPath = "172.16.5.26:2181,172.16.5.26:2182,172.16.5.26:2183";

    public final static int timeout = 5000;

    public static void main(String[] args) throws Exception {
        ZooKeeper zk = new ZooKeeper(zkServerPath, timeout, new ZKConnectSessionWatcher());


        log("客户端开始连接Zookeeper服务器...");
        new Thread().sleep(10000);
        log("连接状态：" + zk.getState());
        long sessionId = zk.getSessionId();
        log(sessionId+"");
        byte[] sessionPassword = zk.getSessionPasswd();
        zk.close();
        new Thread().sleep(10000);
        log("连接状态：" + zk.getState());

        log("开始会话重连。。。");

        ZooKeeper zkSession = new ZooKeeper(zkServerPath, timeout, new ZKConnectSessionWatcher(), sessionId, sessionPassword);
        log("重新连接zkSession：" + zkSession.getState());
        new Thread().sleep(10000);
        log("重新连接zkSession：" + zkSession.getState());
    }

    public void process(WatchedEvent event) {
        System.out.println("接收到watch通知：" + event.toString());
    }

    private static void log(String msg) {
        System.out.println(msg);
    }
}