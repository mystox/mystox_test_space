package tech.mystox.test.redis.listen;

import org.springframework.data.redis.listener.KeyExpirationEventMessageListener;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;

/**
 * Created by mystox on 2021/6/15, 15:10.
 * company:
 * description:
 * update record:
 */
//@Component
public class RedisListener extends KeyExpirationEventMessageListener {
    public RedisListener(RedisMessageListenerContainer listenerContainer) {
        super(listenerContainer);
    }
}
