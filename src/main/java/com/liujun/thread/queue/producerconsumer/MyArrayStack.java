package com.liujun.thread.queue.producerconsumer;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用java数组来实现一个个生产生消费者的队列
 *
 * @author liujun
 * @version 0.0.1
 * @date 2018/11/08
 */
public class MyArrayStack {

  /** 存储数据的对象 */
  private final int[] queue;

  /** 容量 */
  private final int capacity;

  /** 当前存储的大小 */
  private int size;

  /** 独占锁实现 */
  private Lock relock = new ReentrantLock();

  /** 放入锁 */
  private Condition putCondition = relock.newCondition();

  /** 获取锁 */
  private Condition getCondition = relock.newCondition();

  public MyArrayStack(int capacity) {
    this.queue = new int[capacity];
    this.capacity = capacity;
  }

  /**
   * 插入数据
   *
   * @param value
   */
  public void put(int value) {
    relock.lock();
    try {
      while (size >= capacity) {
        putCondition.await();
      }
      queue[size] = value;
      size++;
      getCondition.signal();
    } catch (InterruptedException e) {
      e.printStackTrace();
    } finally {
      relock.unlock();
    }
  }

  public int size() {
    return size;
  }

  /**
   * 获取数据
   *
   * @return 队列中的数据
   */
  public int get() {
    int getValue = 0;

    relock.lock();
    try {
      while (size < 1) {
        getCondition.await();
      }

      getValue = queue[size - 1];
      size--;
      putCondition.signal();
    } catch (InterruptedException e) {
      e.printStackTrace();
    } finally {
      relock.unlock();
    }

    return getValue;
  }
}
