package tech.mystox.demo.service;

import org.springframework.stereotype.Component;

/**
 * Created by mystoxlol on 2019/10/15, 15:56.
 * company: kongtrolink
 * description:
 * update record:
 */
@Component
public class ClassSynFuncTest extends Thread {


    public String addSomething(String param) {
        try {
            System.out.println(param+"start");
            Thread.sleep(10000l);
            System.out.println(param +"end");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return param;
    }

    public synchronized String addSomethingSyn(String param) {
        try {
            System.out.println(param+"sstart");
            Thread.sleep(10000l);
            System.out.println(param +"send");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return param;
    }

}
