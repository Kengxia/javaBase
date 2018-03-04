package my.juc.lock_t;

import java.util.Collections;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author 华安  mashuai_bj@si-tech.com.cn
 * @Title:
 * @Date: Create in 15:32 2018/2/8
 * @Description:
 */
public class reentrantReadWriteLock_t {

    private ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();

    public static void main(String[] args)  {
        final reentrantReadWriteLock_t test = new reentrantReadWriteLock_t();

        new Thread(){
            public void run() {
                try {
                    test.get(Thread.currentThread());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            };
        }.start();

        new Thread(){
            public void run() {
                try {
                    test.get(Thread.currentThread());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            };
        }.start();

    }
    //没有加读写锁的时候，线程的读是顺序执行的
//    public synchronized void get(Thread thread) {
//        long start = System.currentTimeMillis();
//        while(System.currentTimeMillis() - start <= 1) {
//            System.out.println(thread.getName()+"正在进行读操作");
//        }
//        System.out.println(thread.getName()+"读操作完毕");
//    }
    public void get(Thread thread) throws InterruptedException {
        rwl.readLock().lock();
        try {
            long start = System.currentTimeMillis();
            while(System.currentTimeMillis() - start <= 10) {
                Thread.sleep(1);
                System.out.println(thread.getName()+"正在进行读操作");
            }
            System.out.println(thread.getName()+"读操作完毕");
        } finally {
            rwl.readLock().unlock();
        }
    }
} 