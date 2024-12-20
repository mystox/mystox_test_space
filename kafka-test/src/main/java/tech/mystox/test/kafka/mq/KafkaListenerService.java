package tech.mystox.test.kafka.mq;

import com.alibaba.fastjson.JSONObject;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by mystox on 2021/7/2, 10:05.
 * company:
 * description:
 * update record:
 */
@Service
public class KafkaListenerService {


    @Value("${kafka.consumer.time:5000}")
    private Long time;
//    @KafkaListener(topics = {"test.init.demoA","12313123"},groupId = "A")
    public void receiver(ConsumerRecord<String, String> record) {
        System.out.println("AAAAAAAAA"+record);
    }

    private AtomicInteger atomicInteger = new AtomicInteger();
    @KafkaListener(topics = "filebeat.docker.logs",groupId = "aaaabcDD")
//    @KafkaListener(topics = "5553544152.49545F4D4F4E49544F525F4D414E414745525F53595354454D.E58E8BE6B58BE794A8E6A8A1E68B9FE4BA8BE4BBB6E5A484E7908631",groupId = "aaaa")
    public void receiver2(ConsumerRecord<String, String> record) {
        JSONObject jsonObject = JSONObject.parseObject(record.value());
        System.out.println("["+jsonObject.getJSONObject("container").getString("name")+"]           "+jsonObject.getString("message"));
        int andIncrement = atomicInteger.getAndIncrement();
        long offset = record.offset();
//        System.out.println(System.currentTimeMillis()+"-"+andIncrement+"-"+offset+"-"+record.partition());
//        try {
//            Thread.sleep(1);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        System.out.println("111111111111111111"+record);
    }
}
