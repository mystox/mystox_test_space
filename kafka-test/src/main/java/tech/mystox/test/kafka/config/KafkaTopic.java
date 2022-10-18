package tech.mystox.test.kafka.config;

import org.apache.kafka.clients.admin.NewTopic;

/**
 * Created by mystox on 2021/7/2, 10:33.
 * company:
 * description:
 * update record:
 */
//@Configuration
public class KafkaTopic {
//        @Bean
        public NewTopic batchTopic() {
            return new NewTopic("testConsumerTopic", 1, (short) 1);
        }
}
