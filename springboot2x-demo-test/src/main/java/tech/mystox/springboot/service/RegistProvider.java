package tech.mystox.springboot.service;

/**
 * Created by mystoxlol on 2019/1/17, 17:03.
 * company: kongtrolink
 * description:
 * update record:
 */
public interface RegistProvider
{
    void register(Object context) throws Exception;
    void unregister(Object context) throws Exception;
    void getPath(Object context) throws Exception;

}
