package tech.mystox.test.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by mystox on 2024/6/6, 15:26.
 * company:
 * description: 重入锁
 * update record:
 */
public class ReentrantLockExample {
    private final ReentrantLock lock = new ReentrantLock();

    public void outerMethod() {
        lock.lock();
        try {
            System.out.println("Outer method"+lock.isLocked());
            innerMethod();
        } finally {
            lock.unlock();
        }
    }

    public void innerMethod() {
        lock.lock();
        try {
            System.out.println("Inner method");
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        ReentrantLockExample example = new ReentrantLockExample();
        example.outerMethod();
    }
}
