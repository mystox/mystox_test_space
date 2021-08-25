package tech.mystox.test.swagger.controller;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("测试vo模型")
public class TestVo {
    @ApiModelProperty("说明")
    private String field;
    @ApiModelProperty(name = "name", value = "名称", required = true, example = "mysql")
    private String name;

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TestVo() {
    }

    public TestVo(String field, String name) {
        this.field = field;
        this.name = name;
    }
}
