package tech.mystox.springboot.config;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * Created by mystoxlol on 2019/1/17, 14:48.
 * company: kongtrolink
 * description:
 * update record:
 */
//@Configuration
public class ZookeeperConfig implements Watcher
{
    @Value("${zookeeper.url}")
    private String zookeeperUrl;
    @Value("${zookeeper.sessionTimeout}")
    private int sessionTimeout;
    private CountDownLatch latch = new CountDownLatch(1);

    @Bean
    public ZooKeeper zooKeeper() throws IOException
    {
        ZooKeeper zooKeeper = new ZooKeeper(zookeeperUrl, sessionTimeout, this);
        return zooKeeper;
    }


    @Override
    public void process(WatchedEvent watchedEvent)
    {

        if (watchedEvent.getState() == Event.KeeperState.SyncConnected)
        {
            latch.countDown();
        }
        System.out.println("已经触发了" + watchedEvent.getType() + "事件！" + watchedEvent.toString());
    }

}
