package org.example;

import java.util.concurrent.Semaphore;

/**
 * @author dengl02
 * @description
 * @date 2023/9/28
 */
public class SequentialExecutionExample3 {
    public static void main(String[] args) {
        Semaphore semaphore1 = new Semaphore(1);
        Semaphore semaphore2 = new Semaphore(1);
        Semaphore semaphore3 = new Semaphore(1);

        try {
            semaphore2.acquire();
            semaphore3.acquire();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        Thread t1 = new Thread(() -> {
            try {
                semaphore1.acquire();
                // T1的任务代码
                System.out.println("T1 执行");
                Thread.sleep(2000);
                semaphore2.release(); // 释放T2
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                semaphore2.acquire();
                // T2的任务代码
                System.out.println("T2 执行");
                Thread.sleep(2000);
                semaphore3.release(); // 释放T3
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        Thread t3 = new Thread(() -> {
            try {
                semaphore3.acquire();
                // T3的任务代码
                System.out.println("T3 执行");
                semaphore1.release(); // 释放T1
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        t1.start();
        t2.start();
        t3.start();

    }
}
