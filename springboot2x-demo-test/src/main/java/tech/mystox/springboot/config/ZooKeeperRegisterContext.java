package tech.mystox.springboot.config;

import org.apache.zookeeper.ZooKeeper;

/**
 * Created by mystoxlol on 2019/1/17, 16:52.
 * company: kongtrolink
 * description:
 * update record:
 */
public class ZooKeeperRegisterContext
{
    private String path;
    private Object object;


    public ZooKeeperRegisterContext(String path, ZooKeeper zooKeeper, Object object)
    {
        this.path = path;
        this.object = object;
    }

    public String getPath()
    {
        return path;
    }

    public void setPath(String path)
    {
        this.path = path;
    }


    public Object getObject()
    {
        return object;
    }

    public void setObject(Object object)
    {
        this.object = object;
    }
}
