package tech.mystox.test.swagger.controller;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

@Api(tags = "测试接口文档")
@RestController
@RequestMapping("/testController")
public class TestController {


    @ApiOperation(value = "测试视图接口value",notes = "notes相关信息")
    @RequestMapping(value = "/testVo", method = RequestMethod.POST)
    public JsonResult<RspVo> testVo(@ApiParam(value = "请求参数",defaultValue = "111",required = true) @RequestParam String requestParam,
                                    @ApiParam(value = "请求枚举",defaultValue = "LEAD_ELECTRIC",required = true) @RequestParam PictureTypeEnum pictureParam,
                                    @RequestBody TestQuery testQuery) {



        return new JsonResult<>();
    }

    public static void main(String[] args) {
        Byte[] bytes = new Byte[2];
        bytes[0] = 16;
        bytes[1] = 2;
        JSONObject o = new JSONObject();
        o.put("abc", bytes);
        System.out.println(o);
    }


}
