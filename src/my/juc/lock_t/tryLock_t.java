package my.juc.lock_t;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author 华安  mashuai_bj@si-tech.com.cn
 * @Title:
 * @Date: Create in 15:06 2018/2/8
 * @Description:
 *
 * try_lock 尝试获取lock 返回获取结果
 *
 *
 */
public class tryLock_t {

    private ArrayList<Integer> arrayList = new ArrayList<Integer>();
    private Lock lock = new ReentrantLock();    //注意这个地方
    public static void main(String[] args) {
        final tryLock_t test = new tryLock_t();
        new Thread() {
            public void run() {
                test.insert(Thread.currentThread());
            }
        }.start();
        new Thread() {
            public void run() {
                test.insert(Thread.currentThread());
            }
        }.start();
    }
    public void insert(Thread thread) {
        if(lock.tryLock()) {
            try {
                System.out.println(thread.getName()+"得到了锁");
                Thread.sleep(1000);
                for(int i=0;i<5;i++) {
                    arrayList.add(i);
                }
            } catch (Exception e) {
                // TODO: handle exception
            }finally {
                System.out.println(thread.getName()+"释放了锁");
                lock.unlock();
            }
        } else {
            System.out.println(thread.getName()+"获取锁失败");
        }
    }
} 