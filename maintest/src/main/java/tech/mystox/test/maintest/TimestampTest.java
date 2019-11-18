package tech.mystox.test.maintest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by mystoxlol on 2019/3/25, 17:32.
 * company: kongtrolink
 * description:
 * update record:
 */
public class TimestampTest
{


    public static void main(String[] args)
    {
        List<Long> times = new ArrayList<>();
        Collections.synchronizedList(times);
        for (int i = 0; i<100000; i++){
        Runnable runnable  = () ->
        {
                long t = System.nanoTime();
                times.add(t);
        };
        runnable.run();
        }
        System.out.println(times.size());
        for (long time :times)
        {
            System.out.println(time);
        }
//        System.out.println(System.currentTimeMillis());
    }

}
