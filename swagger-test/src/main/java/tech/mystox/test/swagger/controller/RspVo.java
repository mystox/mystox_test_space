package tech.mystox.test.swagger.controller;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by mystox on 2021/8/25, 14:09.
 * company:
 * description:
 * update record:
 */
@ApiModel
public class RspVo {
    @ApiModelProperty(value = "响应名称")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
