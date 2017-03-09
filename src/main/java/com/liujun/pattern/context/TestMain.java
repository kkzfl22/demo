package com.liujun.pattern.context;

public class TestMain {

    public static void main(String[] args) {
        Context context = new Context("这是基本\r\n");
        
        context.doStrategyInvoke("1");
        
        System.out.println("结果:"+context.getBaseText());
        
    }
    
}
