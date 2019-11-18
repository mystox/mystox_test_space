package tech.mystox.test.servicer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.mystox.test.hadoop.rpc.entity.ControllerData;
import tech.mystox.test.hadoop.rpc.entity.InfoData;
import tech.mystox.test.hadoop.rpc.entity.WorkData;

import java.util.Map;

/**
 * Created by mystoxlol on 2019/1/21, 13:46.
 * company: kongtrolink
 * description:
 * update record:
 */
@Service
public class DataCollector
{
    @Autowired
    ControllerInstance controllerInstance;
    public Map<String, Object> getSystemData()
    {
        //todo 获取系统信息
        return null;
    }

    public Map<String, Object> getOSData()
    {
        //todo 获取系统信息
        return null;
    }
    public Map<String, Object> getServerData()
    {
        //todo 获取系统信息
        return null;
    }
    public WorkData constructWorkData(){
        //todo

        return new WorkData();
    }

     public InfoData constructInfoData(){
        //todo

        return new InfoData();
    }
     public ControllerData constructControllerData(){
         //todo
        return new ControllerData();
    }


}
