package com.liujun.jvm.dataguru.seven;

public class ThreadDeadLock {

    /**
     * 进行死锁测试的线程A
    * 源文件名：ThreadDeadLock.java
    * 文件版本：1.0.0
    * 创建作者：liujun
    * 创建日期：2016年6月19日
    * 修改作者：liujun
    * 修改日期：2016年6月19日
    * 文件描述：TODO
    * 版权所有：Copyright 2016 zjhz, Inc. All Rights Reserved.
    */
    class lockA implements Runnable {

        /**
         * 等待D线程的锁释放
        * @字段说明 queue
        */
        private OneWaitQueue queueD;

        /**
         * 当前的线程A所持有的锁
        * @字段说明 queueA
        */
        private OneWaitQueue queueA;

        public lockA(OneWaitQueue queueD, OneWaitQueue queueA) {
            this.queueD = queueD;
            this.queueA = queueA;
        }

        public void run() {
            try {
                Object value = queueD.take();
                System.out.println("获取对象信息:" + value);

                queueA.put("A线程放入...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 进行死锁测试的线程B
     * 源文件名：ThreadDeadLock.java
     * 文件版本：1.0.0
     * 创建作者：liujun
     * 创建日期：2016年6月19日
     * 修改作者：liujun
     * 修改日期：2016年6月19日
     * 文件描述：TODO
     * 版权所有：Copyright 2016 zjhz, Inc. All Rights Reserved.
     */
    class lockB implements Runnable {

        /**
         * 等待A线程的锁释放
         * @字段说明 queue
         */
        private OneWaitQueue queueA;

        /**
         * 当前的线程B所持有的锁
         * @字段说明 queueA
         */
        private OneWaitQueue queueB;

        public lockB(OneWaitQueue queueA, OneWaitQueue queueB) {
            this.queueA = queueA;
            this.queueB = queueB;
        }

        public void run() {
            try {
                Object value = queueA.take();
                System.out.println("获取对象信息:" + value);
                queueB.put("B线程放入...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 进行死锁测试的线程C
     * 源文件名：ThreadDeadLock.java
     * 文件版本：1.0.0
     * 创建作者：liujun
     * 创建日期：2016年6月19日
     * 修改作者：liujun
     * 修改日期：2016年6月19日
     * 文件描述：TODO
     * 版权所有：Copyright 2016 zjhz, Inc. All Rights Reserved.
     */
    class lockC implements Runnable {

        /**
         * 等待B线程的锁释放
         * @字段说明 queue
         */
        private OneWaitQueue queueB;

        /**
         * 当前的线程C所持有的锁
         * @字段说明 queueA
         */
        private OneWaitQueue queueC;

        public lockC(OneWaitQueue queueB, OneWaitQueue queueC) {
            this.queueB = queueB;
            this.queueC = queueC;
        }

        public void run() {
            try {
                Object value = queueB.take();
                System.out.println("获取对象信息:" + value);
                queueC.put("C线程放入...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 进行死锁测试的线程D
     * 源文件名：ThreadDeadLock.java
     * 文件版本：1.0.0
     * 创建作者：liujun
     * 创建日期：2016年6月19日
     * 修改作者：liujun
     * 修改日期：2016年6月19日
     * 文件描述：TODO
     * 版权所有：Copyright 2016 zjhz, Inc. All Rights Reserved.
     */
    class lockD implements Runnable {

        /**
         * 等待A线程的锁释放
         * @字段说明 queue
         */
        private OneWaitQueue queueC;

        /**
         * 当前的线程B所持有的锁
         * @字段说明 queueA
         */
        private OneWaitQueue queueD;

        public lockD(OneWaitQueue queueC, OneWaitQueue queueD) {
            this.queueC = queueC;
            this.queueD = queueD;
        }

        public void run() {
            try {
                Object value = queueC.take();
                System.out.println("获取对象信息:" + value);
                queueD.put("D线程放入...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public Runnable getRunA(OneWaitQueue queueD, OneWaitQueue queueA) {
        return new ThreadDeadLock.lockA(queueD, queueA);
    }

    public Runnable getRunB(OneWaitQueue queueA, OneWaitQueue queueB) {
        return new ThreadDeadLock.lockB(queueA, queueB);
    }

    public Runnable getRunC(OneWaitQueue queueB, OneWaitQueue queueC) {
        return new ThreadDeadLock.lockC(queueB, queueC);
    }

    public Runnable getRunD(OneWaitQueue queueC, OneWaitQueue queueD) {
        return new ThreadDeadLock.lockD(queueC, queueD);
    }

    public static void main(String[] args) {

        ThreadDeadLock dealLock = new ThreadDeadLock();

        OneWaitQueue queueA = new OneWaitQueue();
        OneWaitQueue queueB = new OneWaitQueue();
        OneWaitQueue queueC = new OneWaitQueue();
        OneWaitQueue queueD = new OneWaitQueue();

        // 进行死锁线程启动
        Thread threadA = new Thread(dealLock.getRunA(queueD, queueA));
        threadA.setName("Thread-A");
        threadA.start();

        Thread threadB = new Thread(dealLock.getRunB(queueA, queueB));
        threadB.setName("Thread-B");
        threadB.start();

        Thread threadC = new Thread(dealLock.getRunC(queueB, queueC));
        threadC.setName("Thread-C");
        threadC.start();

        Thread threadD = new Thread(dealLock.getRunD(queueC, queueD));
        threadD.setName("Thread-D");
        threadD.start();

    }
}
