package com.liujun.pattern.mediator;

/**
 * 进行防水工作
 * @author liujun
 *
 * @date 2015年5月6日
 * @vsersion 0.0.1
 */
public class OperatorWater implements OperatorInf {

	@Override
	public void mySelfWork(AbstractMediator mediator) {
		System.out.println("当前防水工作正在进行");
	}

	@Override
	public void nofiflyOther(AbstractMediator mediator) throws Exception {
		System.out.println("当前防水工作完成，通知贴砖工作，贴瓷砖");
		mediator.work("ceramic");
	}

}
