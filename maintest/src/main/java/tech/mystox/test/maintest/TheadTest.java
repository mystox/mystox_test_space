package tech.mystox.test.maintest;

import static java.lang.Thread.currentThread;

/**
 * Created by mystoxlol on 2019/10/17, 16:39.
 * company: kongtrolink
 * description:
 * update record:
 */
public class TheadTest {



    private synchronized void print() {
        String name = currentThread().getName();
        System.out.println(name);
    }

}
