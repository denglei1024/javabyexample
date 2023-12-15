package org.example;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author dengl02
 * @description
 * @date 2023/9/26
 */
public class NotifyThreadExample {
    private Queue<Integer> sharedQueue = new LinkedList<>();

    public synchronized void produce(Integer item){
        while (sharedQueue.size()>10){
           try {
               wait();
           }catch (InterruptedException e){
               Thread.currentThread().interrupt();
           }
        }
        sharedQueue.add(item);
        System.out.println("生产：" + item);
        notify();//通知等待的消费者线程
    }

    public synchronized void consume(){
        while (sharedQueue.isEmpty()){
            try {
                wait();
            }catch (InterruptedException e){
                Thread.currentThread().interrupt();
            }
        }
        Integer item = sharedQueue.poll();
        System.out.println("消费：" + item);
        notify();//通知等待的生产者线程
    }

    public static void main(String[] args) {
        NotifyThreadExample notifyThreadExample = new NotifyThreadExample();
        Thread producerThread = new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                notifyThreadExample.produce(i);
            }
        });
        Thread consumerThread = new Thread(() -> {
            for (int i = 0; i < 3 ; i++) {
                notifyThreadExample.consume();
            }
        });
        producerThread.start();
        consumerThread.start();
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
