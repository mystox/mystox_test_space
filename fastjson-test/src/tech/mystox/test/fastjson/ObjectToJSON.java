package tech.mystox.test.fastjson;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import tech.mystox.test.fastjson.pojo.AssetDTO;

/**
 * Created by mystox on 2023/9/7, 14:52.
 * company:
 * description:
 * update record:
 */
public class ObjectToJSON {
    public static void main(String[] args) {
        JSONObject to = (JSONObject) JSON.toJSON(new AssetDTO());
//        Map<String,String> map = ((JSONObject) JSON.toJSON(new AssetDTO())).toJavaObject();
//        System.out.println(map);
    }
}
