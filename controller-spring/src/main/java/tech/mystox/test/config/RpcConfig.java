package tech.mystox.test.config;

import org.apache.hadoop.ipc.RPC;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.mystox.test.hadoop.rpc.protocol.ClientWorkProtocol;
import tech.mystox.test.hadooprpc.client.RpcClient;
import tech.mystox.test.servicer.WorkExecuteService;

import java.io.IOException;

/**
 * Created by mystoxlol on 2019/1/19, 10:31.
 * company: kongtrolink
 * description:
 * update record:
 */
@Configuration
public class RpcConfig
{
    @Value("${server.address}")
    private String bindAddress;
    @Value("${server.rpc.port}")
    private Integer rpcPort;

    @Bean
    RPC.Server rpcServer(RPC.Builder builder) throws IOException
    {
        builder.setBindAddress(bindAddress).setPort(rpcPort)
                .setProtocol(ClientWorkProtocol.class)
                .setInstance(new WorkExecuteService());
        return builder.build();

    }

    @Bean
    RPC.Builder builder()
    {
        return new RPC.Builder(new org.apache.hadoop.conf.Configuration());
    }

    @Bean
    RpcClient rpcClient()
    {
        return new RpcClient();
    }

}
