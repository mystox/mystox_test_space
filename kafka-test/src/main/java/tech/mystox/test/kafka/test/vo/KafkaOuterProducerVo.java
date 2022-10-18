package tech.mystox.test.kafka.test.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("kafka-outer生产者实体")
public class KafkaOuterProducerVo {

    @ApiModelProperty(value = "kafka地址",example = "192.168.0.234:9093,192.168.0.234:9094,192.168.0.234:9095")
    String kafkaUrl;

    @ApiModelProperty(value = "topic",example = "test.init.sourceA")
    String kafkaTopic;
    @ApiModelProperty(value = "消息实体，例子为写入",example = "test kafka payload")
    String msg;

    Integer count = 1;
    Integer frequency = 1000;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getFrequency() {
        return frequency;
    }

    public void setFrequency(Integer frequency) {
        this.frequency = frequency;
    }

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
