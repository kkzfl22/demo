package com.liujun.pattern.mediator;

/**
 * 刷墙工人的实现，用于实现
 * 
 * @author liujun
 * 
 * @date 2015年5月6日
 * @vsersion 0.0.1
 */
public class OperatorWall implements OperatorInf {

	@Override
	public void mySelfWork(AbstractMediator mediator) {
		System.out.println("当前刷墙工人正在做刷墙工作。。。");
	}

	@Override
	public void nofiflyOther(AbstractMediator mediator) throws Exception {
		System.out.println("刷墙工作完成了，通知其他防水工作开始");
		mediator.work("water");
	}

}
