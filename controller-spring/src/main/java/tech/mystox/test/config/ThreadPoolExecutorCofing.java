package tech.mystox.test.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * Created by mystoxlol on 2019/1/21, 14:47.
 * company: kongtrolink
 * description:
 * update record:
 */
@Configuration
public class ThreadPoolExecutorCofing
{

    @Bean
    public ThreadPoolTaskExecutor defaultThreadPool() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        return executor;
    }
}
