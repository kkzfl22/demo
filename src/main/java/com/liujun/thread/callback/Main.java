package com.liujun.thread.callback;

public class Main {

    public static void main(String[] args) {
        CallBackImpl back = new CallBackImpl();
        int value = back.doIt(new CallBackInf() {

            @Override
            public int callback(String param) {
                return 2 + 7;
            }
        });

        System.out.println("回调异步执行的结果:" + value);

    }

}
