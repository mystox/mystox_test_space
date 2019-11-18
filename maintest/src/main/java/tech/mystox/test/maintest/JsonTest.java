package tech.mystox.test.maintest;


import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mystoxlol on 2019/4/10, 10:07.
 * company: kongtrolink
 * description:
 * update record:
 */
public class JsonTest {
    public static void main(String[] args)
    {
        Map<String, Object> map = new HashMap<>();
        JSONObject o = (JSONObject) JSONObject.toJSON(map);
    }
}
