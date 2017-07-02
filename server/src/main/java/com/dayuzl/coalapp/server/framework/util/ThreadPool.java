package com.dayuzl.coalapp.server.framework.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by dangw on 2016/11/4.
 */
public class ThreadPool {
    private static Logger logger = LoggerFactory.getLogger(ThreadPool.class);

    private static ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(100);

    public static void execute(Runnable command) {
        logger.debug("当前线程激活数量>>>" + executor.getActiveCount() + " ，当前队列数量>>>" + executor.getQueue().size()
                + ",已经完成任务>>" + executor.getCompletedTaskCount());
        executor.execute(command);
    }

    public static void execute(Runnable command, long delayseconds) {
        logger.debug("当前线程激活数量>>>" + executor.getActiveCount() + " ，当前队列数量>>>" + executor.getQueue().size());
        executor.schedule(command, delayseconds, TimeUnit.SECONDS);
    }

    public static void shutdown() {
        executor.shutdown();
    }

    public static boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
        return executor.awaitTermination(timeout, unit);
    }


    public static void remove(Runnable Task) {
        executor.remove(Task);
    }
}