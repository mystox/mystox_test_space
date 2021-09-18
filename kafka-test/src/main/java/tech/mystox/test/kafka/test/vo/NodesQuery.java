package tech.mystox.test.kafka.test.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by mystox on 2021/7/20, 13:48.
 * company:
 * description:
 * update record:
 */
@ApiModel("获取内存对象筛选条件")
public class NodesQuery {
    @ApiModelProperty("实体对象id")
    private String subjectId;
    @ApiModelProperty("神经元topic")
    private String neureTopic;

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public String getNeureTopic() {
        return neureTopic;
    }

    public void setNeureTopic(String neureTopic) {
        this.neureTopic = neureTopic;
    }
}
