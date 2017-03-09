package com.liujun.thread.callback;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class CallBackImpl {

    ExecutorService executor = Executors.newFixedThreadPool(10);

    /**
     * 进行回调异步执行
    * 方法描述
    * @param back
    * @return
    * @创建日期 2016年12月22日
    */
    public int doIt(final CallBackInf back) {

        Callable<Integer> call = new Callable<Integer>() {

            @Override
            public Integer call() throws Exception {

                // 进行当前任务执行
                try {
                    Thread.currentThread().sleep(1000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                // 进行回调执行
                return back.callback("over");
            }
        };

        FutureTask<Integer> task = new FutureTask<>(call);

        executor.submit(task);

        // 获取结果
        try {
            return task.get();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ExecutionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return -1;

    }

}
