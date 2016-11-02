package com.liujun.thread.threadpool;

public class RunTestPool {

    public static void main(String[] args) {
        SelfThreadPool<Runnable> pool = new SelfThreadPool<Runnable>(3);
        for (int i = 0; i < 10; i++) {
            pool.execute(new Runnable() {
                public void run() {
                    System.out.println("当前对象提交");
                }
            });
        }

        // pool.shutdown();
    }

}
