package my.thread_t;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 生产这消费者
 */
public class WaitT_2 {

    public static void main(String[] args) {
        Queue<String> store = new LinkedList<String>();
        new product(store,"product").start();
        for(int i=0;i<5;i++){
            new consume(store,"consome_"+i).start();
        }
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for(int i=5;i<10;i++){
            new consume(store,"consome_"+i).start();
        }


    }
    static class product extends Thread{

        private Queue<String> store;
        public  product(Queue<String> store,String name){
            super(name);
            this.store = store;

        }
        @Override
        public void run() {
            synchronized (store){
                //不停的往仓库里放
                while(true){
                    while(store.size()==10){
                        try {
                            store.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println("往store中放入mk");
                    store.add("mk");
                    store.notifyAll();
                }
            }
        }
    }
    static class consume extends Thread{

        private Queue<String> store;
        public  consume(Queue<String> store,String name){
            super(name);
            this.store = store;

        }

        @Override
        public void run() {
            synchronized (store){
                while (store.isEmpty()){
                    try {
                        System.out.println("仓库中没有可消费的 wait");
                        store.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println (Thread.currentThread().getName() +"消费"+store.remove());
                store.notifyAll();
            }
        }
    }
}
