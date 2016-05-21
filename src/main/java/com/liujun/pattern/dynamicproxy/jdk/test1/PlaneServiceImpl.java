package com.liujun.pattern.dynamicproxy.jdk.test1;

/**
 * 飞机飞的实现
 * @author liujun
 *
 * @date 2015年5月4日
 * @vsersion 0.0.1
 */
public class PlaneServiceImpl implements FlyService {

	@Override
	public void flyMethod() {
		System.out.println("当前飞机在飞。。。。");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
