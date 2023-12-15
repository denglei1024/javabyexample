package org.example;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author dengl02
 * @description
 * @date 2023/9/26
 */
public class FixedThreadPoolExample {
    public static void main(String[] args) {
        // 创建一个固定大小为 3 的线程池
        ExecutorService executor = Executors.newFixedThreadPool(3);
        // 提交任务给线程池
        for (int i = 0; i < 10; i++) {
            final Integer taskNumber = i;
            executor.execute(()->{
                System.out.println("任务" + taskNumber + "在线程" + Thread.currentThread().getName() + "上运行");
                try {
                    Thread.sleep(1000);// 模拟任务运行时间
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            });
        }
        executor.shutdown();
        while (!executor.isTerminated()) {

        }
        //// 等待线程池终止，最多等待5秒
        //if (!executor.awaitTermination(5, TimeUnit.SECONDS)) {
        //    // 如果超时还未终止，可以选择其他操作，如强制关闭
        //    executor.shutdownNow();
        //}
        System.out.println("所有任务执行完毕，线程池已经关闭.");
    }
}
