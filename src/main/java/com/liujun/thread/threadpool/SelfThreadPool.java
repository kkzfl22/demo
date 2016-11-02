package com.liujun.thread.threadpool;

import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;

public class SelfThreadPool<Job extends Runnable> implements ThreadPool<Job> {

    /**
     * 最大工作线程运行数量
    * @字段说明 MAX_WORK_NUM
    */
    private static final int MAX_WORK_NUM = 10;

    /**
     * 默认工作的线程数量
    * @字段说明 DEF_WORK_NUM
    */
    private static final int DEF_WORK_NUM = 5;

    /**
     * 最小线程数量
    * @字段说明 MIN_WORK_NUM
    */
    private static final int MIN_WORK_NUM = 1;

    /**
     * 使用队列来存放工作线程,最大限制为10
    * @字段说明 listWork
    */
    private final Queue<Worker> WORK_QUEUE = new LinkedBlockingDeque<Worker>(MAX_WORK_NUM);

    /**
     * 用来提交执行的任务的队列
    * @字段说明 JOB_QUEUE
    */
    private final BlockingQueue<Job> JOB_QUEUE = new LinkedBlockingQueue<Job>();

    /**
     * 当前工作线程的数量
    * @字段说明 workSize
    */
    private Integer workSize = 0;

    public SelfThreadPool(int size) {
        int runWork = size;
        if (size >= MAX_WORK_NUM) {
            runWork = MAX_WORK_NUM;
        } else if (size <= MIN_WORK_NUM) {
            runWork = MIN_WORK_NUM;
        }

        // 始化线程池
        this.initWorkPool(runWork);
    }

    public SelfThreadPool() {
        // 无参，构造默认大小的线程池
        this.initWorkPool(DEF_WORK_NUM);
    }

    /**
     * 初始化线程池大小
    * 方法描述
    * @param poolSize
    * @创建日期 2016年11月3日
    */
    private void initWorkPool(int poolSize) {

        synchronized (workSize) {
            workSize = workSize + poolSize;
        }

        for (int i = 0; i < poolSize; i++) {

            Worker workRun = new Worker();

            WORK_QUEUE.offer(workRun);

            // 启动线程，并执行
            Thread runWorkThread = new Thread(workRun);

            runWorkThread.start();

        }
    }

    @Override
    public void execute(Job job) {

        // 提交一个任务
        JOB_QUEUE.offer(job);

    }

    @Override
    public void shutdown() {
        for (Worker worker : WORK_QUEUE) {
            worker.shutdown();
        }

    }

    @Override
    public void addWorkers(int num) {
        // 计算当前的工作线程数
        if (num > 0) {
            if (workSize + num <= MAX_WORK_NUM) {
                this.initWorkPool(num);
            }
        }

    }

    @Override
    public void removeWorker(int num) {
        if (num > 0) {
            if (workSize - num >= MIN_WORK_NUM) {
                for (int i = 0; i < num; i++) {
                    Worker work = WORK_QUEUE.remove();
                    work.shutdown();
                }

                synchronized (workSize) {
                    workSize = workSize - num;
                }
            }
        }
    }

    @Override
    public int getWorkSize() {
        return workSize;
    }

    /**
     * 工作线程
    * 源文件名：SelfThreadPool.java
    * 文件版本：1.0.0
    * 创建作者：liujun
    * 创建日期：2016年11月3日
    * 修改作者：liujun
    * 修改日期：2016年11月3日
    * 文件描述：liujun
    * 版权所有：Copyright 2016 zjhz, Inc. All Rights Reserved.
    */
    public class Worker implements Runnable {

        /**
         * 当前的工作线程是否在运行
        * @字段说明 isRun
        */
        private volatile boolean isRun = true;

        @Override
        public void run() {

            while (isRun) {

                // 获取一个对象
                try {
                    Job job = JOB_QUEUE.take();
                    System.out.println("当前线程:" + Thread.currentThread().getId() + "执行");
                    // 进行运行
                    job.run();

                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }
        }

        public void shutdown() {
            this.isRun = false;
        }

    }

}
