package my.thread_t;

/**
 * wait/notify 必须在synchronized(obj){......}的内部才能够去调用
 * 否则就会报错：java.lang.IllegalMonitorStateException:current thread not owner
 * wait()会释放对象的同步锁，而sleep()则不会释放锁
 *
 *
 */
public class WaitT {
    private static final  Object obj = new Object();

    public static void main(String[] args) {

        synchronized (obj){
            try {
                System.out.println("开始启动线程");
                new WaitT().startThread();
                obj.wait(); //阻塞,知道有notify
                System.out.println("end.......");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }
    public void startThread(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (obj){
                    System.out.println("线程启动啦");
                    obj.notify();
                }

            }
        }).start();

    }
}



