package org.example;

/**
 * @author dengl02
 * @description
 * @date 2023/9/26
 */
public class StopThreadWithInterruptExample {
    public static void main(String[] args) {
        StopThreadWithInterruptExample stopThreadWithInterruptExample = new StopThreadWithInterruptExample();
        Thread thread = new Thread(stopThreadWithInterruptExample::runTask);
        thread.start();
        try {
            //让线程运行一段时间
            Thread.sleep(5000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        thread.interrupt();
    }
    public void runTask(){
        while (!Thread.currentThread().isInterrupted()){
            System.out.println("线程执行任务");
            try {
                //模拟线程执行耗时
                Thread.sleep(1000);
            }catch (InterruptedException e){
                //保留线程中断状态
                Thread.currentThread().interrupt();
                System.out.println("线程被中断");
            }
        }
        System.out.println("线程执行结束");
    }
}
