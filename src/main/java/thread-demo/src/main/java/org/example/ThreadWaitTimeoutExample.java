package org.example;

/**
 * @author dengl02
 * @description
 * @date 2023/9/26
 */
public class ThreadWaitTimeoutExample {
    public static void main(String[] args) {
        final Object lock = new Object();
        Thread waitingThread = new Thread(()->{
            synchronized (lock) {
                System.out.println("等待线程开始等待...");
                try {
                    lock.wait(3000);// 等待3s，超时自动唤醒
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                System.out.println("等待线程被唤醒。");
            }
        });
        waitingThread.start();
        // 主线程等待等待线程执行完成
        try {
            waitingThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("等待线程执行完毕...");
    }
}
