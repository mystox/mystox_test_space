package tech.mystox.test.zookeeper;

import org.apache.zookeeper.KeeperException;

import java.io.IOException;

/**
 * Created by mystoxlol on 2019/1/15, 15:31.
 * company: kongtrolink
 * description:
 * update record:
 */
public class ZookTest
{
    public static void main(String[] args) throws IOException, KeeperException, InterruptedException
    {
        for (int i= 0; i< 1; i++)
        {
//            ServiceRegistry serviceRegistry = new ServiceRegistryImpl("172.16.5.26:2181,172.16.5.26:2182,172.16.5.26:2183");
            ServiceRegistry serviceRegistry = new ServiceRegistryImpl("192.168.0.201:2181");
            serviceRegistry.register(null, null);
            serviceRegistry.getData("/test01");
            serviceRegistry.close();
            System.out.println("----------------"+i+"------------------");
        }

    }
}
