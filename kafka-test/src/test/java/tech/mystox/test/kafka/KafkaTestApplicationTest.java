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
       kafkaProperties.put("bootstrap.servers", "172.21.150.185:19093");
       kafkaProperties.put("bootstrap.servers", "192.168.50.101:9093");
//        kafkaProperties.put("bootstrap.servers", "121.43.98.132:9093,121.43.98.132:9094,121.43.98.132:9095");
//        kafkaProperties.put("bootstrap.servers", "192.168.0.235:9092");
        kafkaProperties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        kafkaProperties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        KafkaProducer producer = new KafkaProducer(kafkaProperties);
        String s = "{\n" +
                "    \"target_topic\": \"init.demo.batch.13\",\n" +
                "    \"TYPE_NAME\": \"阈值告警##Threshold-Crossing Alarm\",\n" +
                "    \"receiveTime\": 1657605298995,\n" +
                "    \"assetLocation\": \"888-omczyglpt-BM-1650283986818\",\n" +
                "    \"enterpriseCode\": \"888\",\n" +
                "    \"subjectId\": \"888-omczyglpt-BM-1650283986818\",\n" +
                "    \"value\": \"99\",\n" +
                "    \"occurTime\": 1657274640000,\n" +
                "    \"tags\": [\n" +
                "      \"gateway\"\n" +
                "    ],\n" +
                "    \"alarmLevel\": \"一级\",\n" +
                "    \"neureTopic\": \"init.demo.batch.13\",\n" +
                "    \"shieldTag\": null,\n" +
                "    \"cleanTime\": 1657605299000,\n" +
                "    \"ciId\": \"888-omczyglpt-BM-1650283986818\",\n" +
                "    \"objectName\": \"综合监控\",\n" +
                "    \"networkType\": \"51\",\n" +
                "    \"alarmText\": \"AOTEST|51|阈值告警##Threshold-Crossing Alarm|AMS|阈值规则状态从“数据不足”变为“超限阈值”。状态变化详细信息：指标名称为“instanceAvailableRate”，最新指标数据取值为“0,0,0”，符合超限阈值条件“<=0”，上报告警。##Threshold rule status changed from \\\"insufficientdata\\\" to \\\"alarm\\\".Details: The latest value of metric \\\"instanceAvailableRate\\\" is \\\"0,0,0\\\" and has met threshold rule \\\"<=0\\\",report alarm.|NA|一级|2022-07-08T18:04:00:165,带告警level业务性能数据\",\n" +
                "    \"serverCode\": \"omczyglpt\",\n" +
                "    \"alarmName\": \"AMS\",\n" +
                "    \"INDEX_UNIT\": \"NA\",\n" +
                "    \"@timestamp\": \"2022-07-12T13:54:57.627Z\",\n" +
                "    \"message\": \"{\\n    \\\"BUSINESS_NAME\\\": \\\"AOTEST\\\",\\n    \\\"MODULE_NAME\\\": \\\"综合监控\\\",\\n    \\\"INDEX_TYPE\\\": \\\"51\\\",\\n    \\\"TYPE_NAME\\\": \\\"阈值告警##Threshold-Crossing Alarm\\\",\\n    \\\"INDEX_NAME\\\": \\\"AMS\\\",\\n    \\\"INDEX_VALUE\\\": \\\"阈值规则状态从“数据不足”变为“超限阈值”。状态变化详细信息：指标名称为“instanceAvailableRate”，最新指标数据取值为“0,0,0”，符合超限阈值条件“<=0”，上报告警。##Threshold rule status changed from \\\\\\\"insufficientdata\\\\\\\" to \\\\\\\"alarm\\\\\\\".Details: The latest value of metric \\\\\\\"instanceAvailableRate\\\\\\\" is \\\\\\\"0,0,0\\\\\\\" and has met threshold rule \\\\\\\"<=0\\\\\\\",report alarm.\\\",\\n    \\\"INDEX_UNIT\\\": \\\"NA\\\",\\n    \\\"COLLECT_TIME\\\": \\\"2022-07-08T18:04:00:165\\\",\\n    \\\"ALARM_LEVEL\\\": \\\"一级\\\"\\n}\",\n" +
                "    \"alarmType\": \"AOTEST.综合监控.AMS.一级\",\n" +
                "    \"time\": \"1657274640000\",\n" +
                "    \"@version\": \"1\",\n" +
                "    \"networkName\": \"AOTEST\",\n" +
                "    \"indexValue\": \"阈值规则状态从“数据不足”变为“超限阈值”。状态变化详细信息：指标名称为“instanceAvailableRate”，最新指标数据取值为“0,0,0”，符合超限阈值条件“<=0”，上报告警。##Threshold rule status changed from \\\"insufficientdata\\\" to \\\"alarm\\\".Details: The latest value of metric \\\"instanceAvailableRate\\\" is \\\"0,0,0\\\" and has met threshold rule \\\"<=0\\\",report alarm.\"\n" +
                "  }";
        String topic = "testKafka";
        ProducerRecord record = new ProducerRecord(topic, s);
        System.out.println("kafka msg send [{}] msg [{}]"+topic+s);//(, msg);
        producer.send(record);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
