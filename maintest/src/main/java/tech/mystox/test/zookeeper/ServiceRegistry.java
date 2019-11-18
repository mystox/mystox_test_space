package tech.mystox.test.zookeeper;

import org.apache.zookeeper.KeeperException;

/**
 * Created by mystoxlol on 2019/1/16, 10:29.
 * company: kongtrolink
 * description:
 * update record:
 */
public interface ServiceRegistry
{

    public void register(String serviceName, String nodeData) throws KeeperException, InterruptedException;
    public void sycData(String serviceName, String nodeData);

    String getData(String path) throws KeeperException, InterruptedException;
    public void close() throws InterruptedException;
}
