package com.liujun.thread.queue.producerconsumer;

/**
 * 测试并发栈
 *
 * @author liujun
 * @version 0.0.1
 * @date 2018/11/08
 */
public class TestMyArrayStackSyn {

  public static void main(String[] args) {

    final MyArrayStackSyn stack = new MyArrayStackSyn(8);

    new Thread(
            new Runnable() {
              @Override
              public void run() {
                int index = 0;
                while (true) {
                  stack.put(index);
                  index += 2;
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
                  System.out.println("当前队列大小:" + stack.size());
                }
              }
            })
        .start();
  }
}
