package my.juc.lock_t;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author 华安  mashuai_bj@si-tech.com.cn
 * @Title:
 * @Date: Create in 14:31 2018/2/8
 * @Description:
 */
public class lock_t {

    public static  int  i=100;
    Object objLock = new Object();
    Lock lock = new ReentrantLock();
    public static void main(String[] args) {
        final lock_t t = new lock_t();
        for(int i=0;i<100;i++){
//            new Thread(){
//                public void run() {
//                    t.doSomeThing();
//                };
//            }.start();
            new Thread(()->{
                t.doSomeThing();
            }).start();
        }
    }
//    public void doSomeThing(){
//        lock.lock();
//        try {
//            System.out.println(lock_t.i--);
//        }finally {
//            lock.unlock();
//        }
//    }

    public void doSomeThing(){

        synchronized (objLock){
            System.out.println(lock_t.i--);
        }
    }
} 