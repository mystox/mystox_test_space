package tech.mystox.springboot.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Created by mystoxlol on 2019/1/10, 15:08.
 * company: kongtrolink
 * description:
 * update record:
 */
@Component
public class ContextBeanUtils implements ApplicationContextAware
{
    private static ApplicationContext context;

    public static <T> T getBean(Class<T> clazz)
    {
        return context.getBean(clazz);
    }

    public static ApplicationContext getApplicationContext()
    {
        return context;
    }
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException
    {
        ContextBeanUtils.context = applicationContext;
    }
}
