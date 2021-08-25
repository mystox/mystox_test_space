package tech.mystox.test.swagger.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "测试接口文档")
@RestController
@RequestMapping("/testController")
public class TestController {


    @RequestMapping(value = "/testVo", method = RequestMethod.POST)
    public JsonResult<RspVo> testVo(@RequestBody TestQuery testQuery) {


        return new JsonResult<>();
    }


}
