package my.juc.lock_t;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * JUC中的reentranLock 和condition 实现生产消费者
 * <p>
 * http://www.cnblogs.com/skywang12345/p/3496101.html
 */
class Depot {

    private int capacity;
    private int size;
    private Lock lock; // 独占锁
    private Condition fullCondtion;            // 生产条件
    private Condition emptyCondtion;        // 消费条件

    public Depot(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.lock = new ReentrantLock();
        this.fullCondtion = lock.newCondition();
        this.emptyCondtion = lock.newCondition();
    }

    public void product(int val) {
        lock.lock();
        try {
            int left = val;
            while (left > 0) {
                // 库存已满时，等待“消费者”消费产品。
                while (size >= capacity) {
                    fullCondtion.await();
                }
                int inc = (size + left) > capacity ? (capacity - size) : left;
                size = size + inc;
                System.out.println(left);
                System.out.println(inc);
                left = left - inc;
                System.out.println(left);
                System.out.printf("%s produce(%3d) --> left=%3d, inc=%3d, size=%3d\n",
                        Thread.currentThread().getName(), val, left, inc, size);
                // 通知“消费者”可以消费了。
                emptyCondtion.signal();
            }

        } catch (Exception e) {
//            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void consume(int val) {
        lock.lock();
        int left = val;
        try {
            // left 表示“客户要消费数量”(有可能消费量太大，库存不够，需多此消费)
            while (left > 0) {
                while (size <= 0)
                    emptyCondtion.await();
                // 获取“实际消费的数量”(即库存中实际减少的数量)
                // 如果“库存”<“客户要消费的数量”，则“实际消费量”=“库存”；
                // 否则，“实际消费量”=“客户要消费的数量”。
                int dec = (size < left) ? size : left;
                size -= dec;
                left -= dec;
                System.out.printf("%s consume(%3d) <-- left=%3d, dec=%3d, size=%3d\n",
                        Thread.currentThread().getName(), val, left, dec, size);
                fullCondtion.signal();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public String toString() {
        return "capacity:" + capacity + ", actual size:" + size;
    }

}

class Producer {
    private Depot depot;

    public Producer(Depot depot) {
        this.depot = depot;
    }

    // 消费产品：新建一个线程向仓库中生产产品。
    public void produce(final int val) {

        new Thread() {
            public void run() {
                depot.product(val);
            }
        }.start();
    }
}

class Customer {
    private Depot depot;

    public Customer(Depot depot) {
        this.depot = depot;
    }

    // 消费产品：新建一个线程从仓库中消费产品。
    public void consume(final int val) {
        new Thread() {
            public void run() {
                depot.consume(val);
            }
        }.start();
    }
}


public class reentranLockT1{

    public static void main(String[] args) {
        Depot mDepot = new Depot(100);
        Producer mPro = new Producer(mDepot);
        Customer mCus = new Customer(mDepot);
        mPro.produce(180);
        mPro.produce(120);
        mCus.consume(90);
        mCus.consume(150);
        mPro.produce(110);
    }

}
