package org.example;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author dengl02
 * @description
 * @date 2023/9/26
 */
public class ScheduledThreadPoolExample {
    public static void main(String[] args) {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(3);
        // 延迟任务：延迟2秒后执行
        executor.schedule(() -> {
            System.out.println("延迟任务1执行了");
        }, 2, TimeUnit.SECONDS);


        // 周期性任务：延迟1秒后，每隔3秒执行一次
        executor.scheduleAtFixedRate(() -> {
            System.out.println("周期性任务执行了");
        }, 1, 3, TimeUnit.SECONDS);

        // 周期性任务：延迟1秒后，每隔2秒执行一次，任务执行完后等待1秒再执行下一次
        executor.scheduleWithFixedDelay(() -> {
            System.out.println("周期性任务2执行了");
        }, 1, 2, TimeUnit.SECONDS);

        // 主线程等待足够长的时间，以便观察周期性任务的执行
        try {
            Thread.sleep(10000); // 等待10秒
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 关闭线程池，不再接受新任务
        executor.shutdown();
    }
}
