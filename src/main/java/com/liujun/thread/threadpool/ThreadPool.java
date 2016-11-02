package com.liujun.thread.threadpool;

public interface ThreadPool<Job extends Runnable> {

    /**
     * 执行一个Job
    * 方法描述
    * @param job
    * @创建日期 2016年11月2日
    */
    public void execute(Job job);

    /**
     * 关闭线程池
    * 方法描述
    * @创建日期 2016年11月2日
    */
    public void shutdown();

    /**
     * 添加工作线程
    * 方法描述
    * @param num
    * @创建日期 2016年11月2日
    */
    public void addWorkers(int num);

    /**
     * 减少线程池 
    * 方法描述
    * @param num
    * @创建日期 2016年11月2日
    */
    public void removeWorker(int num);

    /**
     * 得到当前正在运行的job的大小
    * 方法描述
    * @return
    * @创建日期 2016年11月2日
    */
    public int getWorkSize();

}
