package com.liujun.pattern.context;

import java.util.HashMap;
import java.util.Map;

/**
 * 上下文对象
 * data : 2017年3月9日
 * version : 0.0.1
 * @author liujun
 */
public class Context {

    /**
     * 基础的字符串
     */
    protected String baseText;

    /**
     * 策略
     */
    private static Map<String, Strategy> StrategyMap = new HashMap<>();

    static {
        StrategyMap.put("1", new StrategyAppend());
    }

    public Context(String baseText) {
        super();
        this.baseText = baseText;
    }

    /**
     * 公共调用
     */
    public void commstart() {
        baseText += "公共开始\r\n";
    }

    /**
     * 进行策略的调用
     */
    public void doStrategyInvoke(String key) {
        Strategy strategy = StrategyMap.get(key);
        if (null != strategy) {
            // 进行公共的调用
            this.commstart();
            // 进行策略的调用
            strategy.doStrategy(this);
            // 进行公共调用
            this.commEnd();
        }
    }

    /**
     * 公共调用
     */
    public void commEnd() {
        baseText += "公共结束\r\n";
    }

    public String getBaseText() {
        return baseText;
    }
    

}
