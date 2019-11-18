package tech.mystox.test.maintest;

import java.util.concurrent.atomic.AtomicInteger;


public class Atomicity {
    private static volatile int nonAtomicCounter = 0;
    private static long nonVolNonAtomicCountter = 0;
    private static AtomicInteger atomicCounter = new AtomicInteger(0);
    private static int times = 0;

    //对单个的普通 变量的写用同一个监视器同步
    public static synchronized void set(long l) {
        nonVolNonAtomicCountter = l;
    }

    //对单个的普通变量的读用同一个监视器同步
    public static synchronized long get() {
        return nonVolNonAtomicCountter;
    }

    public static void getAndIncrement () { //普通方法调用
        long temp = get();           //调用已同步的读方法
        temp += 1L;                  //普通写操作
        set(temp);                   //调用已同步的写方法
    }

    public static void caculate() {
        times++;


            new Thread(new Runnable() {
                @Override
                public void run() {
        for (int i = 0; i < 100000; i++) {
                    getAndIncrement();
                    nonAtomicCounter++;
                    atomicCounter.incrementAndGet();
        }
                    System.out.println("over");
                }
            }).start();

//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//        }
    }

    public static void main(String[] args) {
//        caculate();
        while (times <= 20) {
            System.out.println(times +"----"+ nonVolNonAtomicCountter);
//            nonAtomicCounter = 0;
//            atomicCounter.set(0);
//            set(0);
            caculate();
        }
//        try
//        {
//            Thread.sleep(1000l);
//        } catch (InterruptedException e)
//        {
//            e.printStackTrace();
//        }
        System.out.println("nonVolNonAtomicCountter: " + times + ":"
                + nonVolNonAtomicCountter);
        System.out.println("Non-atomic counter: " + times + ":"
                + nonAtomicCounter);
        System.out.println("Atomic counter: " + times + ":" + atomicCounter);
    }
}