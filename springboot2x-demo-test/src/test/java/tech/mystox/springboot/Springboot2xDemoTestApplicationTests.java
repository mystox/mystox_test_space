package tech.mystox.springboot;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

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
	public void testSpringClassLoader()
	{

		try
		{


			String url = "E:\\IdeaProjects\\mystox_test_space\\out\\artifacts\\springbootLoaderTest\\springbootLoaderTest.jar";
			File file = new File(url);
			URL url1 = file.toURI().toURL();
			System.out.println(url1);
			ClassLoader classLoader = new URLClassLoader(new URL[] { url1 });
			Thread.currentThread().setContextClassLoader(classLoader);
			Class<?> clazz = classLoader.loadClass("tech.mystox.springboot.utils.ClassLoaderTest");// 使用loadClass方法加载class,这个class是在urls参数指定的classpath下边。
			Method taskMethod = clazz.getMethod("loderTest");
			taskMethod.invoke(clazz.newInstance());
		} catch (MalformedURLException e)
		{
			e.printStackTrace();
		} catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		} catch (NoSuchMethodException e)
		{
			e.printStackTrace();
		} catch (IllegalAccessException e)
		{
			e.printStackTrace();
		} catch (InvocationTargetException e)
		{
			e.printStackTrace();
		} catch (InstantiationException e)
		{
			e.printStackTrace();
		}
	}


	public static void main(String[] args)
	{
	}

}

