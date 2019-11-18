package tech.mystox.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

@SpringBootApplication
public class Springboot2xDemoTestApplication {
@Autowired

	public static void main(String[] args) {
		SpringApplication.run(Springboot2xDemoTestApplication.class, args);
		testSpringClassLoader();
	}




	/**
	 * 测试spring_class 动态加载
	 */
	public static void testSpringClassLoader()
	{

		try
		{


			String url = "./springbootLoaderTest.jar";
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
}

