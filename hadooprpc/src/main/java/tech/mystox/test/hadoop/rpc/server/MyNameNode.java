package tech.mystox.test.hadoop.rpc.server;

import tech.mystox.test.hadoop.rpc.entity.ParamEntityTest;

/**
 * Created by mystoxlol on 2019/1/18, 13:07.
 * company: kongtrolink
 * description:
 * update record:
 */
public class MyNameNode implements ClientNamenodeProtocol
{
    public String getMetaData(String path, int integer)
    {
        System.out.println("getMetaData" + path);
        return path+": 3 - {BLK_1,BLK_2} ....";
    }

    public String getParamData(ParamEntityTest entity)
    {
        System.out.println("getMetaData" + entity.toString());
        return entity.toString()+": 3 - {BLK_1,BLK_2} ....";
    }
}
