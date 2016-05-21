package com.liujun.pattern.mediator;

/**
 * 当前为贴瓷砖的人
 * @author liujun
 *
 * @date 2015年5月6日
 * @vsersion 0.0.1
 */
public class OperatorCerimic implements OperatorInf {

	@Override
	public void mySelfWork(AbstractMediator mediator) {
		System.out.println("当前为贴瓷砖的工作正在贴瓷砖");
	}

	@Override
	public void nofiflyOther(AbstractMediator mediator) throws Exception {
		System.out.println("贴瓷砖完毕");
	}

}
