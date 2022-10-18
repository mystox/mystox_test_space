package tech.mystox.test.kafka.test;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.mystox.test.kafka.test.vo.JsonResult;
import tech.mystox.test.kafka.test.vo.KafkaOuterProducerVo;

import java.util.Properties;

/**
 * Created by mystox on 2021/7/20, 13:45.
 * company:
 * description:
 * update record:
 */
@Api(tags = "kafka测试调试")
@RestController
@RequestMapping("/kafkaTest")
public class NeureTestApi {
    Logger logger = LoggerFactory.getLogger(NeureTestApi.class);
    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;


    @ApiOperation(value = "模拟接入报文信息,服务启动时配置url")
    @PostMapping("/sendKafkaBatch")
    public JsonResult sendKafkaBatch(KafkaOuterProducerVo kafkaProducerVo) {
                kafkaTemplate.send( kafkaProducerVo.getKafkaTopic(),kafkaProducerVo.getMsg());
        return new JsonResult();
    }

    @ApiOperation(value = "模拟接入报文信息,单个发送，需要传入url")
    @PostMapping("/sendOuterKafka")
    public JsonResult sendOuterKafka(KafkaOuterProducerVo kafkaProducerVo) {
        Properties kafkaProperties = new Properties();
        kafkaProperties.put("bootstrap.servers", kafkaProducerVo.getKafkaUrl());
        kafkaProperties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        kafkaProperties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        KafkaProducer producer = new KafkaProducer(kafkaProperties);
        String kafkaTopic = kafkaProducerVo.getKafkaTopic();
        String s = kafkaProducerVo.getMsg();
        JSONObject.toJSON(s);
        ProducerRecord record = new ProducerRecord(kafkaTopic, s);
        logger.info("kafka msg send [{}] msg [{}]",kafkaTopic,s);//(, msg);
        producer.send(record);
        return new JsonResult();
    }


    @ApiOperation(value = "按一定发送频率模拟接入报文信息,服务启动时配置url")
    @PostMapping("/sendKafkaBatchFrequency")
    public JsonResult sendKafkaBatchFrequency(KafkaOuterProducerVo kafkaProducerVo) throws InterruptedException {
        logger.info(JSONObject.toJSONString(kafkaProducerVo));
        for (int i = 0; i< kafkaProducerVo.getCount(); i++) {
            kafkaTemplate.send(kafkaProducerVo.getKafkaTopic(), kafkaProducerVo.getMsg());
//            Thread.sleep(kafkaProducerVo.getFrequency());
        }
        return new JsonResult();
    }

}
