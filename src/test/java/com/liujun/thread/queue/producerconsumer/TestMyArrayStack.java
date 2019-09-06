package com.liujun.thread.queue.producerconsumer;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * 测试并发栈
 *
 * @author liujun
 * @version 0.0.1
 * @date 2018/11/08
 */
public class TestMyArrayStack {

  public static void main(String[] args) {

    final MyArrayStack stack = new MyArrayStack(8);

    new Thread(
            new Runnable() {
              @Override
              public void run() {
                int index = 0;
                while (true) {
                  stack.put(index);
                  index++;
                }
              }
            })
        .start();

    new Thread(
            new Runnable() {
              @Override
              public void run() {
                while (true) {

                  System.out.println(stack.get());
                  System.out.println(stack.get());
                  System.out.println("当前队列大小:" + stack.size());
                }
              }
            })
        .start();
  }
}
