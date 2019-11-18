package tech.mystox.test.zookeeper;

import org.apache.zookeeper.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * Created by mystoxlol on 2019/1/16, 10:35.
 * company: kongtrolink
 * description:
 * update record:
 */
public class ServiceRegistryImpl implements ServiceRegistry,Watcher
{
    Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());
    private CountDownLatch latch = new CountDownLatch(1);
    private static final int SESSION_TIMEOUT = 150;
    private ZooKeeper zk;
    public ServiceRegistryImpl() {
    }
    public ServiceRegistryImpl(String zkServers) {
        try {
            if (zk== null)
            zk = new ZooKeeper(zkServers, SESSION_TIMEOUT, this);
            latch.await();
            logger.info("connected to zookeeper");

        } catch (Exception e) {
            logger.error("create zookeeper client failuer", e);
        }
    }
    @Override
    public void register(String serviceName, String nodeData)
    {
        // 创建一个目录节点 ==>已经触发了 None 事件！
        try
        {
//            Thread.sleep(10000);
            if (zk.exists("/testRootPath", true) == null)
                zk.create("/testRootPath", "testRootData".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            // 创建一个子目录节点
            zk.create("/testRootPath/testChildPathOne", "testChildDataOne".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,
                    CreateMode.EPHEMERAL);

            // testRootData 此处false 不触发事件

//            Thread.sleep(10000);
            System.out.println(new String(zk.getData("/testRootPath", false, null)));

            // 取出子目录节点列表 ==>[testChildPathOne] 在节点/testRootPath的getChildren上设置观察
            System.out.println(zk.getChildren("/testRootPath", true));

            // 修改子目录节点数据 由于上面的修改数据不触发观察 这边不执行事件
            zk.setData("/testRootPath/testChildPathOne", "modifyChildDataOne".getBytes(), -1);
            Thread.sleep(100);
            // 目录节点状态：[5,5,1281804532336,1281804532336,0,1,0,0,12,1,6]
            System.out.println("目录节点状态：[" + zk.exists("/testRootPath", true) + "]");
//            Thread.sleep(10000);
            // 创建另外一个子目录节点 ==>已经触发了 NodeChildrenChanged 事件！
            zk.create("/testRootPath/testChildPathTwo", "testChildDataTwo".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,
                    CreateMode.EPHEMERAL);

            // testChildDataTwo
            System.out.println(new String(zk.getData("/testRootPath/testChildPathTwo", true, null)));
        } catch (KeeperException e)
        {
            e.printStackTrace();
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }

    public String getData(String path) throws KeeperException, InterruptedException
    {
        List<String> paths = zk.getChildren("/testRootPath", false);
        System.out.println("---------get data--------"+paths);

        return "";
    }
    @Override
    public void sycData(String serviceName, String nodeData)
    {

    }

    @Override
    public void close() throws InterruptedException
    {
        if (zk!=null)
            zk.close();
    }


    @Override
    public void process(WatchedEvent watchedEvent)
    {

        System.out.println("watched-------------------------"+watchedEvent.getState());
        if (watchedEvent.getState() == Event.KeeperState.SyncConnected && latch.getCount() !=0) {
            System.out.println("执行一次减速-------------------------");
            latch.countDown();
        }
        System.out.println("已经触发了" + watchedEvent.getType() + "事件！"  + watchedEvent.toString());
    }

}
