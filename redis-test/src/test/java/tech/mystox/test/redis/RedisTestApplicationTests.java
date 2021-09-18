package tech.mystox.test.redis;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisStringCommands;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.types.Expiration;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTestApplicationTests {

    @Autowired
    RedisTemplate stringRedisTemplate;

    @Test
    public void contextLoads() {
        System.out.println(":123123");

        stringRedisTemplate.opsForValue().set("adsdfadsf","111");

        Long adsdfadsf = stringRedisTemplate.getExpire("adsdfadsf");

        Boolean adsdfadsf1 = stringRedisTemplate.expire("adsdfadsf1", 1L, TimeUnit.MILLISECONDS);
        System.out.println(adsdfadsf);
        System.out.println(adsdfadsf1);
        String key = "c";

//        SessionCallback<Boolean> sessionCallback = new SessionCallback<Boolean>() {
//            List<Object> exec = null;
//            @Override
//            @SuppressWarnings("unchecked")
//            public Boolean execute(RedisOperations operations) throws DataAccessException {
//                operations.multi();
//                stringRedisTemplate.opsForValue().setIfAbsent(key,"a");
//                exec = operations.exec();
//                Boolean o = (Boolean) exec.get(0);
//                if (!o) return false;
//                stringRedisTemplate.expire(key,100,TimeUnit.SECONDS);
//                operations.multi();
//                exec = operations.exec();
//                return true;
//            }
//        };
//        Boolean execute = stringRedisTemplate.execute(sessionCallback);
//        System.out.println(execute);


        RedisSerializer<String> keySerializer = stringRedisTemplate.getKeySerializer();
        byte[] rawKey = keySerializer.serialize(key);
        byte[] rawValue = ((RedisSerializer<String>)stringRedisTemplate.getValueSerializer()).serialize("value");
        Expiration expiration = Expiration.from(100, TimeUnit.SECONDS);
        Boolean execute = (Boolean) stringRedisTemplate.execute((connection) ->
                connection.set(rawKey, rawValue, expiration, RedisStringCommands.SetOption.ifAbsent()), true);
        System.out.println(execute);

    }

    @Autowired
    RedisTemplate<String, Object> secondRedisTemplateKeyString;
    @Test
    public void TestKey() {
        Set<String> keys = secondRedisTemplateKeyString.keys("illness:*");
        System.out.println(keys);
        keys.forEach(System.out::println);
    }
    @Test
    public void TestListRange() {
        String rangeTest = "rangeTest";
        for(int i = 0; i<200; i++){
            secondRedisTemplateKeyString.opsForList().leftPush(rangeTest, i);
            secondRedisTemplateKeyString.opsForList().trim(rangeTest,0,99);
        }
        List<Object> rangeTests = secondRedisTemplateKeyString.opsForList().range(rangeTest, 0, 99);
        System.out.println(JSONObject.toJSON(rangeTests));
        System.out.println(rangeTests.size());

    }
    @Test
    public void TestScoreSet() {
        String scoreSet = "scoreSet";

        for (int i =0; i<200; i++)
        {
            double random = Math.random();
            JSONObject j = new JSONObject();
            j.put("i", i);
            j.put("random", random);
            secondRedisTemplateKeyString.opsForZSet().add(scoreSet, j, (int)(random*1000));
            secondRedisTemplateKeyString.opsForZSet().removeRangeByScore(scoreSet,0, 499);
        }
        Set<Object> objects = secondRedisTemplateKeyString.opsForZSet().rangeByScore(scoreSet, 500, 1000);
        System.out.println(objects.size());
        System.out.println(JSONObject.toJSON(objects));
        //        secondRedisTemplateKeyString.opsForSet().add()


    }



}
