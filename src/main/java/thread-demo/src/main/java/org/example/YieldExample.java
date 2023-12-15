package org.example;

import java.util.Vector;

/**
 * @author dengl02
 * @description
 * @date 2023/9/28
 */
public class YieldExample {
    public static void main(String[] args) {
        Vector
        Thread t1 = new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                System.out.println("Thread 1 - Count: " + i);
                Thread.yield(); // 暗示愿意让出CPU执行时间片
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                System.out.println("Thread 2 - Count: " + i);
                Thread.yield(); // 暗示愿意让出CPU执行时间片
            }
        });

        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

