package rpcTest.protobuf;

/**
 * Created by mystoxlol on 2019/1/23, 9:31.
 * company: kongtrolink
 * description:
 * update record:
 */
public class AddProtocolTranslatorPB implements AddProtocol
{

    final private AddProtocolPB rpcProxy;

    public  AddProtocolTranslatorPB(AddProtocolPB proxy) {
        this.rpcProxy = proxy;

    }

    @Override
    public int add(int para1, int para2)
    {
        // TODO Auto-generated method stub
        ClientProto.AddParameters req = ClientProto.AddParameters.newBuilder()
                .setPara1(para1).setPara2(para2).build();
        return rpcProxy.add(null, req).getResult();

    }
}
