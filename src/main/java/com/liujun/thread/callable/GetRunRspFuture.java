package com.liujun.thread.callable;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class GetRunRspFuture {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(10);

        Future<Integer> rsp = executor.submit(new CountBack());

        try {
            int sp = rsp.get();

            System.out.println("计算结果：" + sp);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ExecutionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
