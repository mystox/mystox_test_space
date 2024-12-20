package tech.mystox.test.zookeeper;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;

/**
 * Created by mystoxlol on 2019/1/15, 16:00.
 * company: kongtrolink
 * description:
 * update record:
 */
public class ZookeeperConstructorSimple
{
    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
        for (int i=0; i< 1; i++)
        {
            System.out.println("程序开始执行。。。初始化zookeeper");
            // 创建一个与服务器的连接
            //        ZooKeeper zk = new ZooKeeper("172.16.5.26:2181", 30000, new Watcher() {
// 监控所有被触发的事件
            ZooKeeper zk = new ZooKeeper("192.168.50.101:2181", 30000,
                    event -> System.out.println("已经触发了" + event.getType() + "事件！" + event.getPath() + "|" + event.toString()));
//            System.out.println(zk.getState());
            // 创建一个目录节点 ==>已经触发了 None 事件！
            System.out.println("创建目录节点");
            if (zk.exists("/testRootPath", true) == null)
                zk.create("/testRootPath", "testRootData".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            // 创建一个子目录节点
            zk.create("/testRootPath/testChildPathOne", "testChildDataOne".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,
                    CreateMode.EPHEMERAL);

            System.out.println(zk.getChildren("/testRootPath", new Watcher()
            {
                @Override
                public void process(WatchedEvent watchedEvent)
                {
                    System.out.println("--------------------------"+watchedEvent.toString());
                }
            }));
            // testRootData 此处false 不触发事件
            System.out.println("++++++++++++++++++++++++++"+new String(zk.getData("/testRootPath", false, null)));

            // 取出子目录节点列表 ==>[testChildPathOne] 在节点/testRootPath的getChildren上设置观察
            System.out.println(zk.getChildren("/testRootPath", true));

            // 修改子目录节点数据 由于上面的修改数据不触发观察 这边不执行事件
            zk.setData("/testRootPath/testChildPathOne", "modifyChildDataOne".getBytes(), -1);

            // 目录节点状态：[5,5,1281804532336,1281804532336,0,1,0,0,12,1,6]
            System.out.println("目录节点状态：[" + zk.exists("/testRootPath", true) + "]");

            // 创建另外一个子目录节点 ==>已经触发了 NodeChildrenChanged 事件！
            zk.create("/testRootPath/testChildPathTwo", "testChildDataTwo".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,
                    CreateMode.EPHEMERAL);
            zk.create("/testRootPath/testChildPathTwo1", "testChildDataTwo".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,
                    CreateMode.EPHEMERAL);

            zk.create("/testRootPath/testChildPathTwo1", "testChildDataTwo".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,
                    CreateMode.EPHEMERAL_SEQUENTIAL);
            zk.create("/testRootPath/testChildPathTwo1", "testChildDataTwo".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,
                    CreateMode.EPHEMERAL_SEQUENTIAL);

            // testChildDataTwo
            System.out.println(new String(zk.getData("/testRootPath/testChildPathTwo", true, null)));


            Thread.sleep(10000l);
            // 删除子目录节点 已经触发了 NodeDeleted 事件！

            // 删除父目录节点 已经触发了 NodeDeleted 事件！
//        zk.delete("/testRootPath", -1);
            // 关闭连接
            Stat testChildDataTwo = zk.exists("/testRootPath/testChildPathTwo2", true);
            System.out.println(testChildDataTwo.getVersion());
            zk.close();
        }
    }
}
