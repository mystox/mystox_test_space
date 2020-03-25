package tech.mystox.test.config;

import com.alibaba.fastjson.support.spring.FastJsonRedisSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.session.SessionRepository;
import org.springframework.session.data.redis.RedisOperationsSessionRepository;
import org.springframework.session.data.redis.config.ConfigureRedisAction;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.web.http.CookieHttpSessionIdResolver;
import org.springframework.session.web.http.DefaultCookieSerializer;
import org.springframework.session.web.http.HttpSessionIdResolver;

import java.net.UnknownHostException;

/**
 * Created by mystox on 2018/6/19.
 */
@Configuration
@EnableRedisHttpSession
public class HttpSessionConfig {

    /**
     * @Date 14:55 2019/11/18
     * @Param No such property: code for class: Script1
     * @return org.springframework.session.web.http.HttpSessionIdResolver
     * @Author mystox
     * @Description //设置sessionId 策略器的一些方式
     **/
    @Bean
    public HttpSessionIdResolver httpSessionStrategy()//springboot 2.0 需要将HttpSessionStrategy 改为idResolver
    {
        CookieHttpSessionIdResolver cookieHttpSessionIdResolver = new CookieHttpSessionIdResolver();
        DefaultCookieSerializer cookieSerializer = new DefaultCookieSerializer();
//        cookieSerializer.setUseSecureCookie(false);
        cookieSerializer.setUseBase64Encoding(false); //关闭session的加密
//        cookieSerializer.setUseHttpOnlyCookie(false);
        cookieHttpSessionIdResolver.setCookieSerializer(cookieSerializer);
        return cookieHttpSessionIdResolver;
    }

    @Bean
    public ConfigureRedisAction configureRedisAction()
    {
        return ConfigureRedisAction.NO_OP;
    }

    /**
     * 设置spring session redis 序列化方式
     *
     * @return
     */
    @Bean
    public SessionRepository sessionRepository(RedisTemplate<Object, Object> redisTemplate) throws UnknownHostException {
        RedisOperationsSessionRepository sessionRepository = new RedisOperationsSessionRepository(redisTemplate);
        FastJsonRedisSerializer<Object> fastJsonRedisSerializer = new FastJsonRedisSerializer<>(Object.class);
        sessionRepository.setDefaultSerializer(fastJsonRedisSerializer);
        sessionRepository.setDefaultMaxInactiveInterval(1800);
        return sessionRepository;
    }
}
