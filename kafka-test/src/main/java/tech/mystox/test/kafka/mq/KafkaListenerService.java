package tech.mystox.test.kafka.mq;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

/**
 * Created by mystox on 2021/7/2, 10:05.
 * company:
 * description:
 * update record:
 */
@Service
public class KafkaListenerService {


    @KafkaListener(topics = "test.init.demoA",groupId = "")
    public void receiver(ConsumerRecord<String, String> record) {
        System.out.println(record);
    }


//    @KafkaListener(topics = "neure.test.init.demoA",groupId = "")
    public void receiver2(ConsumerRecord<String, String> record) {
        System.out.println(record);
    }
}
