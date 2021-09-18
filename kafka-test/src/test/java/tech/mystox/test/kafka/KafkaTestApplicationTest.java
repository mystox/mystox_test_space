package tech.mystox.test.kafka;

import com.alibaba.fastjson.JSONObject;
import org.apache.kafka.clients.KafkaClient;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.DeleteTopicsResult;
import org.apache.kafka.clients.admin.ListTopicsResult;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import tech.mystox.test.kafka.mq.KafkaListenerService;

import java.util.Collections;
import java.util.Properties;

/**
 * Created by mystox on 2021/7/2, 10:14.
 * company:
 * description:
 * update record:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@MockBean(KafkaListenerService.class)
public class KafkaTestApplicationTest {


    @Autowired
    KafkaTemplate kafkaTemplate;

    @Autowired
    ConsumerFactory consumerFactory;
    @Test
    public void testKafka() {

        for (int i = 0; i <20; i++) {
        kafkaTemplate.send("test_driver_topic", "dddddd");
        }
    }

    KafkaClient kafkaClient;

    @Autowired
    KafkaAdmin kafkaAdmin;
    @Test
    public void testFactory() {
        AdminClient adminClient = AdminClient.create(kafkaAdmin.getConfig());
//        NewTopic neure_deafult_topic = new NewTopic("neure_default_topic", 7, (short) 1);
//        CreateTopicsResult topics = adminClient.createTopics(Collections.singletonList(neure_deafult_topic));
//        System.out.println(JSONObject.toJSONString(topics));
        ListTopicsResult listTopicsResult = adminClient.listTopics();
        System.out.println(JSONObject.toJSONString(listTopicsResult));
        DeleteTopicsResult neure_default_topic = adminClient.deleteTopics(Collections.singletonList("neure_default_topic"));
        System.out.println(neure_default_topic);
    }


    @Test
    public void testProducer() {
        Properties kafkaProperties = new Properties();
        kafkaProperties.put("bootstrap.servers", "192.168.0.234:9093,192.168.0.234:9094,192.168.0.234:9095");
        kafkaProperties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        kafkaProperties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        KafkaProducer producer = new KafkaProducer(kafkaProperties);
        String s = "{\"ip\":\"192.168.2.178\",\"content\":\"Mem total percent is [98%]\"}";
        String topic = "test.init.sourceB";
        ProducerRecord record = new ProducerRecord(topic, s);
        System.out.println("kafka msg send [{}] msg [{}]"+topic+s);//(, msg);
        producer.send(record);
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
