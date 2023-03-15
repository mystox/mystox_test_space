package tech.mystox.test.fastjson;

import com.alibaba.fastjson.JSONObject;
import tech.mystox.test.fastjson.pojo.AssetDTO;
import tech.mystox.test.fastjson.pojo.QueryDTO;
import tech.mystox.test.fastjson.pojo.RelationShipDTO;

/**
 * Created by mystox on 2023/3/14, 18:46.
 * company:
 * description:
 * update record:
 */
public class FastjsonTest {
    public static void main(String[] args) {
        QueryDTO queryDTOFather = new QueryDTO();
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
    }
}
