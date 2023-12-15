package org.example;

import java.util.concurrent.CountDownLatch;

/**
 * @author dengl02
 * @description
 * @date 2023/9/28
 */
public class SequentialExecutionExample {
    public static void main(String[] args) {
        CountDownLatch latch1 = new CountDownLatch(1);
        CountDownLatch latch2 = new CountDownLatch(1);
        Thread t1 = new Thread(() -> {
            // T1的任务代码
            System.out.println("T1 执行");
            latch1.countDown(); // 让T2继续执行
        });

        Thread t2 = new Thread(() -> {
            try {
                latch1.await(); // 等待T1执行完成
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            // T2的任务代码
            System.out.println("T2 执行");
            latch2.countDown(); // 让T3继续执行
        });

        Thread t3 = new Thread(() -> {
            try {
                latch2.await(); // 等待T2执行完成
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            // T3的任务代码
            System.out.println("T3 执行");
        });

        t1.start();
        t2.start();
        t3.start();

        // 等待所有线程执行完成
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
