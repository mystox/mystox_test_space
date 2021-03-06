package tech.mystox.test.aop.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * Created by mystoxlol on 2019/10/28, 8:46.
 * company: kongtrolink
 * description:
 * update record:
 */
@Configuration
public class ExecutorConfig {
    @Value("${executor.threadPool.corePoolSize:10}")
    private int CORE_POOL_SIZE;
    @Value("${executor.threadPool.maxPoolSize:10000}")
    private int MAX_POOL_SIZE;

    @Bean(name = "logExecutor")
    public ThreadPoolTaskExecutor logExecutor()
    {
        return builder( CORE_POOL_SIZE,MAX_POOL_SIZE,200,10000);
    }


    @Bean(name = "testExecutor")
    public ThreadPoolTaskExecutor mqttExecutor()
    {
        return builder( CORE_POOL_SIZE,MAX_POOL_SIZE,200,10000);
    }

    protected ThreadPoolTaskExecutor builder(int corePoolSize,int maxPoolSize,int queueCapacity,int aliveSecondis) {
        ThreadPoolTaskExecutor poolTaskExecutor = new ThreadPoolTaskExecutor();
        //线程池维护线程的最少数量
        poolTaskExecutor.setCorePoolSize(CORE_POOL_SIZE);
        //线程池维护线程的最大数量
        poolTaskExecutor.setMaxPoolSize(MAX_POOL_SIZE);
        //线程池所使用的缓冲队列
        poolTaskExecutor.setQueueCapacity(2000);
        //线程池维护线程所允许的空闲时间
        poolTaskExecutor.setKeepAliveSeconds(10000);
        poolTaskExecutor.setWaitForTasksToCompleteOnShutdown(true);
        return poolTaskExecutor;
    }
}
