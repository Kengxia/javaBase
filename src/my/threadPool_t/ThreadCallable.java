package my.threadPool_t;

import java.util.concurrent.Callable;

/**
 * @author 华安  mashuai_bj@si-tech.com.cn
 * @Title:
 * @Date: Create in 10:19 2018/2/8
 * @Description:
 */
public class ThreadCallable implements Callable {
    @Override
    public Object call() throws Exception {
//        System.out.println("ssss");
        return "sss";
    }
}