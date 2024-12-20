package tech.mystox.test.fastjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson2.JSONObject;
import tech.mystox.test.fastjson.pojo.LocalDateTimeDTO;

/**
 * Created by mystox on 2023/3/14, 18:46.
 * company:
 * description:
 * update record:
 */
public class FastjsonTest {
    public static void main(String[] args) {
        /*QueryDTO queryDTOFather = new QueryDTO();
        QueryDTO queryDTOOutDoor = new QueryDTO();
        QueryDTO queryDTOEquipment = new QueryDTO();
        RelationShipDTO relationShipDTO = new RelationShipDTO();
        relationShipDTO.setPoint(1);
//        AssetDTO assetDTO = new AssetDTO();
//        assetDTO.setQueryCI(Arrays.asList(queryDTOFather, queryDTOOutDoor, queryDTOEquipment));
//        assetDTO.setQueryRe(Arrays.asList(relationShipDTO,relationShipDTO));
        AssetDTO assetDTO = AssetDTO.BuilderQueryCI.startBuild()
                .queryCI(queryDTOFather).queryCI(queryDTOOutDoor).queryCI(queryDTOEquipment)
                .queryRe(relationShipDTO).queryRe(relationShipDTO)
                .page(1).pageSize(9999)
                .create();

        System.out.println(assetDTO);
        String x = JSONObject.toJSONString(assetDTO);
        System.out.println(x);
        AssetDTO assetDTO1 = JSONObject.parseObject(x, AssetDTO.class);
        System.out.println(assetDTO1);


        JSONObject test = new JSONObject();
        test.put("live_url", "123");
        //test.puT("LIVE_URL", "123")
        CameraVO cameraVO = test.toJavaObject(CameraVO.class);
        JSONArray array = new JSONArray();
        array.add(test);
        System.out.println(cameraVO);
        List<CameraVO> cameraVOS = array.toJavaList(CameraVO.class);
        System.out.println(cameraVOS);*/

        String p = "{\"liveStartTime\":\"2024-02-26T16:00:00.000Z\",\"name\":\"ddd\",\"signEndTime\":\"2024-02-25T16:00:00.000Z\",\"signStartDate\":\"2024-02-24T16:00:00.000Z\"}";

        LocalDateTimeDTO meetingUploadBPO = JSONObject.parseObject(p, LocalDateTimeDTO.class);
        System.out.println(meetingUploadBPO);
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(SerializerFeature.WriteDateUseDateFormat);
        Object o = JSON.toJSON(meetingUploadBPO, fastJsonConfig.getSerializeConfig());
        String s = JSON.toJSONString(meetingUploadBPO, SerializerFeature.WriteDateUseDateFormat);
        com.alibaba.fastjson.JSONObject jsonObject = JSON.parseObject(s);
        System.out.println(jsonObject);
        System.out.println(s);
        System.out.println(o);
    }
}
