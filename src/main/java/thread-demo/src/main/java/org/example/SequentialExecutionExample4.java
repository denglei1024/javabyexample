package org.example;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @author dengl02
 * @description
 * @date 2023/9/28
 */
public class SequentialExecutionExample4 {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newSingleThreadExecutor();

        executor.submit(() -> {
            // T1的任务代码
            System.out.println("T1 执行");
        });

        executor.submit(() -> {
            // T2的任务代码
            System.out.println("T2 执行");
        });

        executor.submit(() -> {
            // T3的任务代码
            System.out.println("T3 执行");
        });

        executor.shutdown();
    }
}
