package tech.mystox.test.hadoop.rpc.server;

import org.apache.hadoop.ipc.ProtocolInfo;

/**
 * Created by mystoxlol on 2019/1/22, 16:26.
 * company: kongtrolink
 * description:
 * update record:
 */
@ProtocolInfo(protocolName = "tech.mystox.test.hadoop.rpc.server.ClientProtocol", protocolVersion = 11L)
public interface ClientProtocol
{
    public String getMetaData(String path);
}
