package tech.mystox.test.hadoop.rpc.server;

/**
 * Created by mystoxlol on 2019/1/22, 13:47.
 * company: kongtrolink
 * description:
 * update record:
 */
public class NameNodePB implements NameNodeProtocolPB
{
    @Override
    public String ping()
    {
        System.out.println("ipipip");
        return "172.16.6.135"+1;
    }
}
