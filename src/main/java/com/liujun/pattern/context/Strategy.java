package com.liujun.pattern.context;

/**
 * 定义策略接口
 * data : 2017年3月9日
 * version : 0.0.1
 * @author liujun
 */
public interface Strategy {

    /**
     * 进宪策略接口的定义
     * @param context
     */
    public void doStrategy(Context context);
    
}
