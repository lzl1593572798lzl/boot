package schedule.myjob;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * create by lzl ON 2018/07/31
 */
public class MyThread implements Runnable {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyThread.class);

    private String name;

    public MyThread() {
    }

    public MyThread(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        Thread thread = Thread.currentThread();
        LOGGER.info("MyThread.name: {},线程池中线程的名称: {}",name,thread.getName());
    }
}
