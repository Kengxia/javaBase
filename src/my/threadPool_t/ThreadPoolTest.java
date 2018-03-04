package my.threadPool_t;

import java.util.concurrent.*;

/**
 * @author 华安  mashuai_bj@si-tech.com.cn
 * @Title:
 * @Date: Create in 10:18 2018/2/8
 * @Description:
 */
public class ThreadPoolTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        /**
         * ThreadPoolExecutor 的构造方法
         * 通过不同的构造方法以及参数的不同可以创建不同类型的ThreadPool
         * public ThreadPoolExecutor(
         *  int corePoolSize,
         *  int maximumPoolSize,
         *  long keepAliveTime,
         *  TimeUnit unit,
         *  BlockingQueue<Runnable> workQueue,
         *  ThreadFactory threadFactory,
         *  RejectedExecutionHandler handler)
         *
         */
        //1、CachedThreadPool
        ThreadPoolExecutor cachedThreadPool = new ThreadPoolExecutor(0, Integer.MAX_VALUE,
                60L, TimeUnit.SECONDS,
                new SynchronousQueue<Runnable>());
        Future future = cachedThreadPool.submit(new ThreadCallable());
        System.out.println(future.get().toString());

        //2、SingleThreadPool
        ThreadPoolExecutor singleThreadPool = new ThreadPoolExecutor(1, 1,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>());
    }
} 