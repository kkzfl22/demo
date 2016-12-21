package com.liujun.thread.callable;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class GetRunRspFutureTask {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(10);

        FutureTask<Integer> task = new FutureTask<>(new CountBack());

        executor.submit(task);

        try {
            int sp = task.get();

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
