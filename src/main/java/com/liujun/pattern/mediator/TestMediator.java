package com.liujun.pattern.mediator;

public class TestMediator {
	
	public static void main(String[] args) throws Exception {
		
		
		//1,创建中介者
		AbstractMediator mediator = new MediatorImpl();
		
		//创建刷墙工作
		OperatorInf wall = new OperatorWall();
		
		//进行防水工作
		OperatorInf wator = new OperatorWater();
		
		//贴瓷砖工作
		OperatorInf ceramic = new OperatorCerimic();
		
		//添加工作信息
		mediator.add("wall", wall);
		mediator.add("water", wator);
		mediator.add("ceramic", ceramic);
		
		//开始刷墙工作
		mediator.work("wall");
	}

}
