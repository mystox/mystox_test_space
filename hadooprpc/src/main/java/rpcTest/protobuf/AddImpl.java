package rpcTest.protobuf;

/**
 * Created by mystoxlol on 2019/1/23, 9:09.
 * company: kongtrolink
 * description:
 * update record:
 */
public class AddImpl implements AddProtocol
{

    @Override
    public int add(int para1, int para2)
    {
        return para1 + para2;
    }
}
