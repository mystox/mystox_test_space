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

    public TestVo getVo() {
        return vo;
    }

    public void setVo(TestVo vo) {
        this.vo = vo;
    }
}
