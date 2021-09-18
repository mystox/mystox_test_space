package tech.mystox.test.kafka.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;

/**
 * Created by mystox on 2021/7/2, 10:33.
 * company:
 * description:
 * update record:
 */
//@Configuration
public class KafkaTopic {
        @Bean
        public NewTopic batchTopic() {
            return new NewTopic("testConsumerTopic", 12, (short) 1);
        }
}
