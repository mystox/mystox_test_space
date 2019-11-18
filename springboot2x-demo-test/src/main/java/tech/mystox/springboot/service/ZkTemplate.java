package tech.mystox.springboot.service;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by mystoxlol on 2019/1/17, 15:23.
 * company: kongtrolink
 * description:
 * update record:
 */
//@Service
public class ZkTemplate
{
    @Autowired
    ZooKeeper zooKeeper;

    public void register(){
        // 创建一个目录节点 ==>已经触发了 None 事件！
        try
        {

            if (zooKeeper.exists("/testRootPath", true) == null)
                zooKeeper.create("/testRootPath", "testRootData".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            // 创建一个子目录节点
            zooKeeper.create("/testRootPath/testChildPathOne", "testChildDataOne".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,
                    CreateMode.EPHEMERAL);

            // testRootData 此处false 不触发事件

            System.out.println(new String(zooKeeper.getData("/testRootPath", false, null)));

            // 取出子目录节点列表 ==>[testChildPathOne] 在节点/testRootPath的getChildren上设置观察
            System.out.println(zooKeeper.getChildren("/testRootPath", true));

            // 修改子目录节点数据 由于上面的修改数据不触发观察 这边不执行事件
            zooKeeper.setData("/testRootPath/testChildPathOne", "modifyChildDataOne".getBytes(), -1);

            // 目录节点状态：[5,5,1281804532336,1281804532336,0,1,0,0,12,1,6]
            System.out.println("目录节点状态：[" + zooKeeper.exists("/testRootPath", true) + "]");

            // 创建另外一个子目录节点 ==>已经触发了 NodeChildrenChanged 事件！
            zooKeeper.create("/testRootPath/testChildPathTwo", "testChildDataTwo".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,
                    CreateMode.EPHEMERAL);

            // testChildDataTwo
            System.out.println(new String(zooKeeper.getData("/testRootPath/testChildPathTwo", true, null)));
        } catch (KeeperException e)
        {
            e.printStackTrace();
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}
