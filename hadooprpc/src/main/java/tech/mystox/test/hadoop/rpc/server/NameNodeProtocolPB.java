package tech.mystox.test.hadoop.rpc.server;

/**
 * Created by mystoxlol on 2019/1/22, 13:40.
 * company: kongtrolink
 * description:
 * update record:
 */
//@ProtocolInfo(protocolName = , protocolVersion = 11l)
public interface NameNodeProtocolPB
{
    public static final long versionID=11L;
    public String ping();
}
