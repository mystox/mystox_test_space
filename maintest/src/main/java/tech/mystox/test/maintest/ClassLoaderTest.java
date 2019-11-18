package tech.mystox.test.maintest;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * Created by mystoxlol on 2019/1/10, 9:28.
 * company: kongtrolink
 * description: 类的加载器测试
 * update record:
 */
public class ClassLoaderTest
{
    public static void main(String[] args) throws MalformedURLException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException
    {
        String url = "E:\\IdeaProjects\\mystox_test_space\\out\\artifacts\\mystoxUtil\\mystoxUtil.jar";
        File file = new File(url);
        URL url1 = file.toURI().toURL();
//        url1 = new URL("http://localhost:8100/mystoxUtil.jar");
        System.out.println(url1);
        ClassLoader classLoader = new URLClassLoader(new URL[] { url1 });
        Thread.currentThread().setContextClassLoader(classLoader);
        Class<?> clazz = classLoader.loadClass("tech.mystox.test.util.MystoxUtil");// 使用loadClass方法加载class,这个class是在urls参数指定的classpath下边。
        Method taskMethod = clazz.getMethod("sayHello",String.class);
        Object foo = taskMethod.invoke(clazz.newInstance(),"foo");
        System.out.println("结果"+foo);
    }
}
