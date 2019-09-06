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
public class MyArrayStackSyn {

  /** 存储数据的对象 */
  private final int[] queue;

  /** 容量 */
  private final int capacity;

  /** 当前存储的大小 */
  private int size;

  public MyArrayStackSyn(int capacity) {
    this.queue = new int[capacity];
    this.capacity = capacity;
  }

  /**
   * 插入数据
   *
   * @param value
   */
  public void put(int value) {

    synchronized (this) {
      while (size >= capacity) {
        try {
          this.wait();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }

      queue[size] = value;
      size++;
      this.notifyAll();
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
    int getValue;

    synchronized (this) {
      while (size < 1) {
        try {
          this.wait();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }

      getValue = queue[size - 1];
      size--;
      this.notifyAll();
    }

    return getValue;
  }
}
