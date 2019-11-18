package com.cass.protorpc;

/**
 * Created by mystoxlol on 2019/2/25, 20:22.
 * company: kongtrolink
 * description:
 * update record:
 */
public class ChildRpcNotifyImpl extends RpcNotifyImpl
{
    @Override
    String execute(String context){
        return "i'm child....";
    }
}
