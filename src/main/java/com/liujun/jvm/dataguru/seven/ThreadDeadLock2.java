package com.liujun.jvm.dataguru.seven;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class ThreadDeadLock2 {

    /**
     * 进行死锁线程测试A
    * 源文件名：ThreadDeadLock2.java
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
         * 等待的锁
        * @字段说明 objectB
        */
        private Object objectB;

        /**
         * 当前A对象持有
        * @字段说明 queueA
        */
        private Object objectA;

        /**
         * 控制线程的运行
        * @字段说明 cynlic
        */
        private CyclicBarrier cynlic;

        public lockA(Object objectB, Object objectA, CyclicBarrier cynlic) {
            this.objectB = objectB;
            this.objectA = objectA;
            this.cynlic = cynlic;
        }

        public void run() {
            synchronized (objectA) {

                // 需要保证每个线程都能持有对象
                try {
                    cynlic.await();
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                synchronized (objectB) {
                    System.out.println("当前对象持有A,等待B");
                }
            }
        }

    }

    /**
     * 进行死锁线程测试B
     * 源文件名：ThreadDeadLock2.java
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
         * 等待的锁
         */
        private Object objectC;

        /**
         * 当前对象持有
         */
        private Object objectB;

        /**
         * 控制线程的运行
        * @字段说明 cynlic
        */
        private CyclicBarrier cynlic;

        public lockB(Object objectC, Object objectB, CyclicBarrier cynlic) {
            this.objectC = objectC;
            this.objectB = objectB;
            this.cynlic = cynlic;
        }

        public void run() {
            synchronized (objectB) {

                // 需要保证每个线程都能持有对象
                try {
                    cynlic.await();
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                synchronized (objectC) {
                    System.out.println("当前对象持有B,等待C");
                }
            }
        }
    }

    /**
     * 进行死锁线程测试B
     * 源文件名：ThreadDeadLock2.java
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
         * 等待的锁
         */
        private Object objectD;

        /**
         * 当前对象持有
         */
        private Object objectC;

        /**
         * 控制线程的运行
        * @字段说明 cynlic
        */
        private CyclicBarrier cynlic;

        public lockC(Object objectD, Object objectC, CyclicBarrier cynlic) {
            this.objectD = objectD;
            this.objectC = objectC;
            this.cynlic = cynlic;
        }

        public void run() {
            synchronized (objectC) {

                // 需要保证每个线程都能持有对象
                try {
                    cynlic.await();
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                synchronized (objectD) {
                    System.out.println("当前对象持有C,等待D");
                }
            }
        }
    }

    /**
     * 进行死锁线程测试B
     * 源文件名：ThreadDeadLock2.java
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
         * 等待的锁
         */
        private Object objectA;

        /**
         * 当前对象持有
         */
        private Object objectD;

        /**
         * 控制线程的运行
        * @字段说明 cynlic
        */
        private CyclicBarrier cynlic;

        public lockD(Object objectA, Object objectD, CyclicBarrier cynlic) {
            this.objectA = objectA;
            this.objectD = objectD;
            this.cynlic = cynlic;
        }

        public void run() {
            synchronized (objectD) {

                // 需要保证每个线程都能持有对象
                try {
                    cynlic.await();
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                synchronized (objectA) {
                    System.out.println("当前对象持有D,等待A");
                }
            }
        }
    }

    public Runnable getRunA(Object objectB, Object objectA, CyclicBarrier cynlic) {
        return new ThreadDeadLock2.lockA(objectB, objectA, cynlic);
    }

    public Runnable getRunB(Object objectC, Object objectB, CyclicBarrier cynlic) {
        return new ThreadDeadLock2.lockB(objectC, objectB, cynlic);
    }

    public Runnable getRunC(Object objectD, Object objectC, CyclicBarrier cynlic) {
        return new ThreadDeadLock2.lockC(objectD, objectC, cynlic);
    }

    public Runnable getRunD(Object objectA, Object objectD, CyclicBarrier cynlic) {
        return new ThreadDeadLock2.lockD(objectA, objectD, cynlic);
    }

    public static void main(String[] args) {

        ThreadDeadLock2 dealLock = new ThreadDeadLock2();

        Object objectA = new Object();
        Object objectB = new Object();
        Object objectC = new Object();
        Object objectD = new Object();

        // 保证4个线程都能持有对象
        CyclicBarrier cynlic = new CyclicBarrier(4);

        // 进行死锁线程启动
        Thread threadA = new Thread(dealLock.getRunA(objectB, objectA, cynlic));
        threadA.setName("Thread-A");
        threadA.start();

        Thread threadB = new Thread(dealLock.getRunB(objectC, objectB, cynlic));
        threadB.setName("Thread-B");
        threadB.start();

        Thread threadC = new Thread(dealLock.getRunC(objectD, objectC, cynlic));
        threadC.setName("Thread-C");
        threadC.start();

        Thread threadD = new Thread(dealLock.getRunD(objectA, objectD, cynlic));
        threadD.setName("Thread-D");
        threadD.start();

    }

}
