package tech.mystox.test.hadoop.rpc.server;

/**
 * Created by mystoxlol on 2019/1/22, 16:27.
 * company: kongtrolink
 * description:
 * update record:
 */
public class MyNode implements ClientProtocol
{
    @Override
    public String getMetaData(String path)
    {
        System.out.println(path);
        return "SDFjkweiflsdfjljslkfjewifjldkfj";
    }
}
