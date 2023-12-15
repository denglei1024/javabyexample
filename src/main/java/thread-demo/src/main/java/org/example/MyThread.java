package org.example;

import java.util.Objects;
import java.util.Vector;

/**
 * @author dengl02
 * @description
 * @date 2023/9/26
 */
public class MyThread{
    public static void main(String[] args) {
        Vector<String> vector = new Vector<>();
        // 创建并启动一个新线程
        Thread thread = new Thread(() -> {
            // 运行状态（Running）
            System.out.println("线程正在运行...");
            try {
                // 使线程休眠一段时间
                Thread.sleep(2000); // 超时等待（Timed Waiting）
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        // 新建状态（New）
        System.out.println("线程状态： " + thread.getState());

        // 启动线程
        thread.start();

        // 就绪状态（Runnable） - 在这里可能会在运行前被阻塞
        // 此时线程还没有分配CPU时间片
        System.out.println("线程状态： " + thread.getState());

        try {
            // 主线程休眠一段时间
            Thread.sleep(1000);
            // 阻塞状态（Blocked） - 在这里可能会等待获取锁资源
            System.out.println("线程状态： " + thread.getState());

            // 主线程等待子线程执行完成
            thread.join();

            // 终止状态（Terminated）
            System.out.println("线程状态： " + thread.getState());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
