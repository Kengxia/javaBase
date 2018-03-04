package my.juc.lock_t;

import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock是一个可重入的互斥锁，又被称为“独占锁”。
 * 顾名思义，ReentrantLock锁在同一个时间点只能被一个线程锁持有；而可重入的意思是，
 * ReentrantLock锁，可以被单个线程多次获取。
 * ReentrantLock分为“公平锁”和“非公平锁”。它们的区别体现在获取锁的机制上是否公平。
 * “锁”是为了保护竞争资源，防止多个线程同时操作线程而出错，ReentrantLock在同一个时间点只能被一个线程获取(当某线程获取到“锁”时，其它线程就必须等待)；
 * ReentraantLock是通过一个FIFO的等待队列来管理获取该锁所有线程的。
 * 在“公平锁”的机制下，线程依次排队获取锁；而“非公平锁”在锁是可获取状态时，不管自己是不是在队列的开头都会获取锁。
 */
public class reentrantLockT {

    private int ticketNum = 1000;//初始化1000张票
    Object synObj = new Object();
    ReentrantLock lock = new ReentrantLock();

    /**
     * 没有锁
     */
    public void Reduce() {
        while (true) {
            if (ticketNum > 0) {
                try {
                    Thread.sleep(0);
                    System.out.println(Thread.currentThread().getName() + "...sale" + ticketNum--);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            } else {
                break;
            }
        }
    }

    /**
     * synchronized 同步
     */
    public void ReduceBySyn() {
        while (true) {
            if (ticketNum > 1) {
                synchronized (synObj) {
                    System.out.println(Thread.currentThread().getName() + "...sale....synchronized" + ticketNum--);
                }
            } else {
                break;
            }
        }
    }

    /**
     * lock 同步
     */
    void ReduceByLock() {
        while (true) {
            if (ticketNum > 1) {
                try {
                    lock.lock();
//                    Thread.sleep(10);
                    System.out.println(Thread.currentThread().getName() + "...sale....Lock" + ticketNum--);

                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    lock.unlock();
                }
            } else {
                break;
            }
        }
    }


    public static void main(String[] args) {
        reentrantLockT t = new reentrantLockT();
        new Thread(new Runnable() {
            @Override
            public void run() {
                t.Reduce();
//                t.ReduceBySyn();
//                t.ReduceByLock();
            }
        }, "A").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                t.Reduce();
//                t.ReduceBySyn();
                t.ReduceByLock();
            }
        }, "B").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                t.Reduce();
//                t.ReduceBySyn();
//                t.ReduceByLock();
            }
        }, "C").start();
    }
}


