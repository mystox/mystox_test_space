package tech.mystox.test;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by mystox on 2023/9/26, 14:23.
 * company:
 * description:
 * update record:
 */
public class ThreadLocalTest {


    public static void main(String[] args) {
        // 创建两个不同的类，它们可以访问相同线程专属的对象
        ClassA classA = new ClassA();
        ClassB classB = new ClassB();

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        ExecutorService executorService1 = Executors.newCachedThreadPool();
        // 在不同的线程中调用这些类的方法
        Thread thread1 = new Thread(() -> {
            classA.doSomething();
        });
        thread1.setName("a");

        Thread thread2 = new Thread(() -> {
            classB.doSomething();
        });
        thread2.setName("b");

        thread1.start();
        thread2.start();
        executorService1.execute(thread1);
        executorService1.execute(thread1);
        executorService1.execute(thread1);
        executorService1.execute(thread1);
        executorService1.execute(thread1);
        executorService1.execute(thread1);
        executorService1.execute(thread1);
        executorService1.execute(thread2);
//        executorService.execute(thread1);
//        executorService.execute(thread2);
//        thread1.setName("a11");
//        executorService.execute(thread1);
    }
}

class SharedObject {
    private String data;

    public SharedObject(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }
}

class ThreadLocalContainer {
    private static ThreadLocal<SharedObject> threadLocal = ThreadLocal.withInitial(() -> {
        // 初始化每个线程的独立对象
        return new SharedObject("Thread-Local Data : "+Thread.currentThread().getName());
    });

    private static ThreadLocal<Set<String>> set = ThreadLocal.withInitial(() -> {
        // 初始化每个线程的独立对象
        return new HashSet<>();
    });

    public static SharedObject getSharedObject() {
        return threadLocal.get();
    }

    public static Set<String> getSet() {
        return set.get();
    }
}

class ClassA {
    public void doSomething() {
        SharedObject sharedObject = ThreadLocalContainer.getSharedObject();
        ThreadLocalContainer.getSet().add("1111");
        String data = sharedObject.getData();
        System.out.println("ClassA: " + data);
    }
}

class ClassB {
    public void doSomething() {
        SharedObject sharedObject = ThreadLocalContainer.getSharedObject();
        ThreadLocalContainer.getSet().add("bbbbbbb");

        String data = sharedObject.getData();
        System.out.println("ClassB: " + data);
    }
}