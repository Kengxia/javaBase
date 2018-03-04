package my.thread_t;

/**
 * notifyAll
 */
public class WaitT_1 {


    public static void main(String[] args) {
        Object lock = new Object();
        myThread t = new myThread(lock, "A");
        myThread t1 = new myThread(lock, "B");
        myThread t2 = new myThread(lock, "C");
        myThread t3 = new myThread(lock, "D");
        t.start();
        t1.start();
        t2.start();
        t3.start();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        synchronized (lock) {

            lock.notifyAll();
        }
    }


}

class myThread extends Thread {

    private Object lock;

    public myThread(Object lock, String name) {
        super(name);
        this.lock = lock;

    }

    @Override
    public void run() {
        synchronized (lock) {
            try {
                System.out.println(Thread.currentThread().getName() + "wait...");
                lock.wait();
                // 打印输出结果
                System.out.println(Thread.currentThread().getName() + " continue");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}