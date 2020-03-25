package tech.mystox.mongodb;

import com.alibaba.fastjson.JSONObject;
import com.mongodb.DBObject;
import com.mongodb.MongoClientURI;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.Fields;
import org.springframework.data.mongodb.core.mapreduce.MapReduceCounts;
import org.springframework.data.mongodb.core.mapreduce.MapReduceOptions;
import org.springframework.data.mongodb.core.mapreduce.MapReduceResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.UnknownHostException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MongodbTestSpace1xApplicationTests {


    @Autowired
    MongoTemplate mongoTemplate;

    @Test
    public void contextLoads() {


        Criteria criteria = new Criteria();
        criteria.and("status").is("A");
        Query query = Query.query(criteria);
        MapReduceOptions options = MapReduceOptions.options();
        options.outputCollection("test_map");
        String map = "function(){\n" +
                "emit(this.cust_id,this.amount);\n" +
                "};";
        String reduce = "function(key,values){\n" +
                "   \n" +
                "    return values.length;\n" +
                "};";
        String collectionName = "testMapReduce";
        MapReduceResults<JSONObject> testMapReduce = mongoTemplate.mapReduce(query, collectionName, map, reduce, options, JSONObject.class);


        DBObject rawResults = testMapReduce.getRawResults();
        String outputCollection = testMapReduce.getOutputCollection();
        List<JSONObject> all = mongoTemplate.findAll(JSONObject.class, outputCollection);

        System.out.println(outputCollection);

        MapReduceCounts counts = testMapReduce.getCounts();
        System.out.println(counts);
        System.out.println(rawResults);


    }

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Test
    public void testGroupBy() {
        /*logger.info("start");
        Criteria criteria = Criteria.where("treport").lte(new Date(1575155428528L)).and("deviceId").in(new String[]{"10010_1021006"});
        Aggregation aggregation = Aggregation.newAggregation(Aggregation.match(criteria), Aggregation.group("targetLevelName").count().as("count"));
        AggregationResults<JSONObject> aggregate = mongoTemplate.aggregate(aggregation, "YYDS_TOWER_SERVER_1.0.0_alarm_history_2019_49", JSONObject.class);
        logger.info("end");
        DBObject rawResults = aggregate.getRawResults();
        System.out.println(rawResults);*/


        mongoTemplate = getMongoTemplate("mongodb://172.16.6.9:27017/scloud-product");
        logger.info("start");

        Aggregation aggregation = Aggregation.newAggregation(
                project("tierCode").andExpression("substr(tierCode,0,4)").as("tierCode")
                        .and("fsu.strId").as("fsuId"),
                group("tierCode","fsuId").count().as("count"),
//                group("tierCode").count().as("count"),
//                project("count").and("tierCode").previousOperation(),
                sort(Sort.Direction.ASC, "tierCode")
        );
        AggregationResults results = mongoTemplate.aggregate(aggregation, "GuoDong" + "_alarm", JSONObject.class);
        System.out.println(results.getMappedResults());
        logger.info("end");


    }

    @Test
    public void testAlarmCount() {
        logger.info("start");
        String map = "function(){\n" +
                "emit(this.targetLevelName,1);\n" +
                "};";
        String reduce = "function(key,values){\n" +
                "   \n" +
                "    return  Array.sum(values);\n" +
                "};";
        String collectionName = "testMapReduce";
        String table = "YYDS_TOWER_SERVER_1.0.0_alarm_history_2019_49";
        Criteria criteria = Criteria.where("treport").lte(new Date(1575155428528L)).and("deviceId").in(new String[]{"10010_1021006"});
        MapReduceResults<JSONObject> group = mongoTemplate.mapReduce(
                Query.query(criteria), table, map, reduce, JSONObject.class);
        String outputCollection = group.getOutputCollection();
        Iterator<JSONObject> iterator = group.iterator();
        Object o = JSONObject.toJSON(iterator);
        System.out.println(o);

        List<JSONObject> alarm_count = mongoTemplate.findAll(JSONObject.class, "alarm_count");
        System.out.println(alarm_count);
        logger.info("end");
        DBObject rawResults = group.getRawResults();
        Assert.assertNotNull(rawResults);
        System.out.println(rawResults);


    }

    public MongoTemplate getMongoTemplate(String uri) {
        try {
            SimpleMongoDbFactory simpleMongoDbFactory = new SimpleMongoDbFactory(new MongoClientURI(uri));
            //额外连接参数设置
            return new MongoTemplate(simpleMongoDbFactory);
        } catch (UnknownHostException e) {

        }
        return mongoTemplate;
    }

    @Test
    public void testGroupByReport() {
        logger.info("start");
        String map = "function(){\n" +
                "        var type = this.deviceInfos.type;\n" +
                "        emit(this.targetLevelName,\n" +
                "        {arr:[{deviceType:type,count:1}],name:this.name,fsuOfflineCount:1}\n" +
                "        )}";
        String reduce = "function (key,values){\n" +
                "            var keyName = '" + "整流模块01故障告警" + "';\n" +
                "            var ret = {arr:[]};\n" +
                "            var deviceTypeMap = {};\n" +
                "            var fsuOfflineCountTemp=0;\n" +
                "            for(var i = 0; i<values.length;i++){\n" +
                "                var ia = values[i];\n" +
                "                for(var j in ia.arr){\n" +
                "                    var deviceType = ia.arr[j].deviceType;\n" +
                "                    var count = ia.arr[j].count;\n" +
                "                    deviceType in deviceTypeMap?deviceTypeMap[deviceType] += count:deviceTypeMap[deviceType] = count;\n" +
                "                    }\n" +
                "                var name = ia.name;\n" +
                "                var fsuOfflineCount = ia.fsuOfflineCount;\n" +
                "                if(name == keyName) fsuOfflineCountTemp += fsuOfflineCount;\n" +
                "                \n" +
                "            }\n" +
                "            ret['name'] = keyName;\n" +
                "            ret['fsuOfflineCount'] = fsuOfflineCountTemp;\n" +
                "            for(var d in deviceTypeMap){\n" +
                "                var entity = {deviceType:d,count:deviceTypeMap[d]};\n" +
                "                ret.arr.push(entity);\n" +
                "            }\n" +
                "//             if(key == '一级告警') {\n" +
                "//             print(JSON.stringify(values));\n" +
                "//             print('end------------- '+JSON.stringify(ret));\n" +
                "            return ret;\n" +
                "        }";
        String table = "YYDS_TOWER_SERVER_1.0.0_alarm_history_2019_48";
//        Criteria criteria = Criteria.where("treport").lte(new Date(1575155428528L)).and("deviceId").in(new String[]{"10010_1021006"});
//        options.outputCollection("");
        MapReduceResults<JSONObject> group = mongoTemplate.mapReduce(
                Query.query(Criteria.where("treport").lte(new Date(1574750000000L))), table, map, reduce, JSONObject.class);
        Object o = JSONObject.toJSON(group);
        for (JSONObject result : group) {
            System.out.println(result);
        }
        System.out.println(o);

//        List<JSONObject> alarm_count = mongoTemplate.findAll(JSONObject.class, "alarm_count");
//        System.out.println(alarm_count);
        logger.info("end");


    }

    @Test
    public void testGroupByAlarmReduce() {
        mongoTemplate = getMongoTemplate("mongodb://172.16.5.61:27017/reports_server");
        logger.info("start");
        Criteria criteria = Criteria.where("deleteFlag").is(false);
        Fields fields = Fields.fields();
        fields = fields.and("province");
        fields = fields.and("municipality");
        fields = fields.and("county");
        Aggregation aggregation = Aggregation.newAggregation(Aggregation.match(criteria),
                Aggregation.group(fields).sum("alarmCount").as("alarmCount")
                        .sum("alarmRecoveryCount").as("alarmRecoveryCount"));
        AggregationResults<JSONObject> aggregate = mongoTemplate
                .aggregate(aggregation, "t_report_opera_execute_temp_alarm_count_5e4e29550023643940edf844",
                        JSONObject.class);
        List<JSONObject> mappedResults = aggregate.getMappedResults();
        System.out.println(mappedResults);
        logger.info("end");
        DBObject rawResults = aggregate.getRawResults();
        System.out.println(rawResults);


    }


    @Test
    public void testGroupByFsuOffLine() {
        logger.info("start");
        String map = "function(){\n" +
                "        var duration = (this.trecover-this.treport)/1000/60;\n" +
                "        emit(1,{duration:duration,count:1})\n" +
                "        }";
        String reduce = "function (key,values){\n" +
                "            var ret = {};\n" +
                "            var duration = 0;\n" +
                "            var count = 0;\n" +
                "            for(var i = 0; i<values.length;i++){\n" +
                "                var value = values[i];\n" +
                "                duration += value.duration;\n" +
                "                count += value.count;\n" +
                "            }\n" +
                "            ret['duration'] = duration;\n" +
                "            ret['count'] = count;\n" +
                "            return ret;\n" +
                "        }";
        String table = "YYDS_TOWER_SERVER_1.0.0_alarm_history_2019_48";
//        options.outputCollection("");
        Criteria criteria = Criteria.where("treport").lte(new Date(1574750000000L));
        criteria.and("name").is("整流模块01故障告警");
        criteria.and("state").is("已消除");
        MapReduceResults<JSONObject> group = mongoTemplate.mapReduce(
                Query.query(criteria),
                table, map, reduce, JSONObject.class);
        Object o = JSONObject.toJSON(group);
        for (JSONObject result : group) {
            System.out.println(result);
        }
        System.out.println(o);

//        List<JSONObject> alarm_count = mongoTemplate.findAll(JSONObject.class, "alarm_count");
//        System.out.println(alarm_count);
        logger.info("end");


    }

    public static void main(String[] args)
    {

//        Date date = new Date();
//        int weeksInWeekYear = Calendar.getInstance().get(Calendar.WEEK_OF_YEAR);
//        System.out.println(weeksInWeekYear);

        JSONObject j = new JSONObject();
        j.put("d","123");
        long sum = 0;
        Double a = j.getDouble("d");
        System.out.println(a);

        System.out.println(JSONObject.toJSONString("2018-12".split("-")));
    }


    @Test
    public void testFind() {

        mongoTemplate = getMongoTemplate("mongodb://172.16.6.9:27017/scloud-product");
        Query query = Query.query(Criteria.where("value.0418101001").exists(true).and("deviceCode").is("33010441830001"));
        query.with(new Sort(Sort.Direction.ASC, "time"));
        List<JSONObject> jsonObjects = mongoTemplate.find(
                query,
                JSONObject.class, "YYDS_history");
        System.out.println(jsonObjects);
    }
}
