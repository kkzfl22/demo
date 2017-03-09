package com.liujun.pattern.context;

public class StrategyAppend implements Strategy {

    @Override
    public void doStrategy(Context context) {
        context.baseText+="添加字符串;\r\n";
    }

}
