package tech.mystox.springboot;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Springboot2xDemoTestApplicationTests {

    @Test
    public void contextLoads() {

    }

    @Autowired
    RedisTemplate redisTemplate;

    @Test
    public void testRedis() {
        redisTemplate.delete("abc");
    }

    @Test
    public void testSpringClassLoader() {

        try {


            String url = "E:\\IdeaProjects\\mystox_test_space\\out\\artifacts\\springbootLoaderTest\\springbootLoaderTest.jar";
            File file = new File(url);
            URL url1 = file.toURI().toURL();
            System.out.println(url1);
            ClassLoader classLoader = new URLClassLoader(new URL[]{url1});
            Thread.currentThread().setContextClassLoader(classLoader);
            Class<?> clazz = classLoader.loadClass("tech.mystox.springboot.utils.ClassLoaderTest");// 使用loadClass方法加载class,这个class是在urls参数指定的classpath下边。
            Method taskMethod = clazz.getMethod("loderTest");
            taskMethod.invoke(clazz.newInstance());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testRestTemplate() throws InterruptedException {
        testRestTemplate(true);
    }

    public void testRestTemplate(Boolean b) throws InterruptedException {

        LocalDateTime startTime = LocalDateTime.now();
        System.out.println("测试开始" + startTime);
        ThreadPoolExecutor executor = new ThreadPoolExecutor(40, 10000, 1000L,
                TimeUnit.SECONDS, new LinkedBlockingQueue<>(), new ThreadPoolExecutor.CallerRunsPolicy());
        //        ThreadPoolTaskExecutor demoExecutor = new ThreadPoolTaskExecutor();
        //        demoExecutor.setCorePoolSize(24);
        //        demoExecutor.initialize();
        //        demoExecutor.setAwaitTerminationSeconds(10000);
        //        demoExecutor.setWaitForTasksToCompleteOnShutdown(true);
        int subjectCount = 10;
        int fileCount = 2;
        for (int i = 0; i < subjectCount; i++) {
            RestTemplate restTemplate = new RestTemplate();
            Runnable runnable = () -> {
                for (int j = 0; j < fileCount; j++) {
                    long currentTimeMillis = System.currentTimeMillis();
                    ResponseEntity<byte[]> forEntity = restTemplate.getForEntity("http://36.134.6.117:9000/admin/file/getPdfWithSign/3e10d79227df38e59a1369c4a9f039b357690ac7df2d666b9a433a6b04fe43a4c63ede502b4024aff022b80dcb6deacb", byte[].class);
                    byte[] body = forEntity.getBody();
                    //                    System.out.println(body.length / 1024 / 1024);
                    System.out.println(Thread.currentThread().getName() + "end!" + "消耗时间:[" + (System.currentTimeMillis() - currentTimeMillis) + "]连续点击次数:[" + j + "] 文件大小：" + body.length / 1024 / 1024);
                }

            };
            Thread thread = new Thread(runnable);
            thread.setName("人员编号" + i);
            executor.execute(thread);
        }
        executor.shutdown();
        if (b) {
            executor.awaitTermination(100000, TimeUnit.SECONDS);
        } else {
            executor.awaitTermination(10, TimeUnit.SECONDS);
        }
        //        Thread.sleep(100000);
        LocalDateTime endTime = LocalDateTime.now();
        System.out.println("测试结束!" + endTime + "并发发人数：" + subjectCount + "点击次数" + fileCount + "所用时间（分）：" + (endTime.toInstant(ZoneOffset.UTC).getEpochSecond() - startTime.toInstant(ZoneOffset.UTC).getEpochSecond()) / 60);

    }

    @Test
    public void restRestTemplateBatch() throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            testRestTemplate(i % 3 == 0);
        }
    }

}

