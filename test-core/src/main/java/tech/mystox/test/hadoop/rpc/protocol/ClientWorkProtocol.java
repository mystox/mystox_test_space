package tech.mystox.test.hadoop.rpc.protocol;

/**
 * Created by mystoxlol on 2019/1/19, 11:16.
 * company: kongtrolink
 * description:
 * update record:
 */
public interface ClientWorkProtocol
{
    static final long versionID = 11L;

    String messageExecute(String message);
    String jsonMessageExecute(String jsonMessage);
}
