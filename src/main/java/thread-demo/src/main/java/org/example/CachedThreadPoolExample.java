package org.example;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author dengl02
 * @description
 * @date 2023/9/26
 */
public class CachedThreadPoolExample {
    public static void main(String[] args) {
        // 创建一个线程池
        ExecutorService executor = Executors.newCachedThreadPool();
        // 提交任务给线程池
        for (int i = 0; i < 10; i++) {
            final Integer taskNumber = i;
            executor.submit(()->{
                System.out.println("任务" + taskNumber + "在线程" + Thread.currentThread().getName() + "上运行");
                try {
                    Thread.sleep(2000);// 模拟任务运行时间
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            });
        }
        executor.shutdown();
        //检查线程池是否已经回收，没有回收就自旋一会
        while (!executor.isTerminated()) {

        }
        System.out.println("所有任务执行完毕，线程池已经关闭.");
    }
}
