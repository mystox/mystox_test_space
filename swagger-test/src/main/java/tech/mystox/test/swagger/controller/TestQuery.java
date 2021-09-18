package tech.mystox.test.swagger.controller;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by mystox on 2021/8/25, 14:09.
 * company:
 * description:
 * update record:
 */
@ApiModel("查询实体")
public class TestQuery {
    @ApiModelProperty("参数实体")
    TestVo vo;
    @ApiModelProperty(value = "json字符", example = "{123123123123123}")
    private String jsonStr;

    public String getJsonStr() {
        return jsonStr;
    }

    public void setJsonStr(String jsonStr) {
        this.jsonStr = jsonStr;
    }

    public TestVo getVo() {
        return vo;
    }

    public void setVo(TestVo vo) {
        this.vo = vo;
    }
}
