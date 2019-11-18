package tech.mystox.test.hadoop.rpc.server;

import tech.mystox.test.hadoop.rpc.entity.ParamEntityTest;

/**
 * Created by mystoxlol on 2019/1/18, 13:05.
 * company: kongtrolink
 * description:
 * update record:
 */
public interface ClientNamenodeProtocol {
    public static final long versionID=11L;
    public String getMetaData(String path,int integer);
    public String getParamData(ParamEntityTest entity);
}
