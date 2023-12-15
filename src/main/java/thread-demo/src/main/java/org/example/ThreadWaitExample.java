package org.example;

/**
 * @author dengl02
 * @description
 * @date 2023/9/26
 */
public class ThreadWaitExample {
    public static void main(String[] args) {
        final Object lock = new Object();
        Thread waitingThread = new Thread(()->{
            synchronized (lock) {
                System.out.println("等待线程开始等待...");
                try {
                    lock.wait();// 等待其他线程唤醒
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                System.out.println("等待线程被唤醒。");
            }
        });

        Thread notifyingThread = new Thread(()->{
            try {
                Thread.sleep(2000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            synchronized (lock){
                System.out.println("通知线程开始唤醒等待线程...");
                lock.notify();//唤醒等待线程
            }
        });
        waitingThread.start();
        notifyingThread.start();
    }
}
