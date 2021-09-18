package tech.mystox.test.kafka.test.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("kafka生产者实体")
public class KafkaProducerVo {

    @ApiModelProperty(value = "kafka地址",example = "192.168.0.234:9093,192.168.0.234:9094,192.168.0.234:9095")
    String kafkaUrl;

    @ApiModelProperty(value = "topic",example = "test.init.demoA")
    String kafkaTopic;
    @ApiModelProperty(value = "消息实体，例子为写入",example = "{\"neureTopic\":\"test.init.demoA\",\"time\":1626924083258,\"value\":\"Cpu total percent is [98%]\",\"subjectId\":\"192.168.2.178\"}")
    String msg;


    public String getKafkaUrl() {
        return kafkaUrl;
    }

    public void setKafkaUrl(String kafkaUrl) {
        this.kafkaUrl = kafkaUrl;
    }

    public String getKafkaTopic() {
        return kafkaTopic;
    }

    public void setKafkaTopic(String kafkaTopic) {
        this.kafkaTopic = kafkaTopic;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
