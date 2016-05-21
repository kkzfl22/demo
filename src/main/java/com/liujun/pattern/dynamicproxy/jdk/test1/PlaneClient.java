package com.liujun.pattern.dynamicproxy.jdk.test1;

import com.liujun.pattern.dynamicproxy.cglib.CglibProxy;
import com.liujun.pattern.dynamicproxy.jdk.InvocationHandler;
import com.liujun.pattern.dynamicproxy.jdk.Proxy;
import com.liujun.pattern.dynamicproxy.jdk.TimeHandler;


/**
 * @author liujun
 *
 * @date 2015年5月4日
 * @vsersion 0.0.1
 */
public class PlaneClient   {

	
	public static void main(String[] args) throws Exception {
		FlyService flyService = new PlaneServiceImpl();
		
		//得到时间计算的实现
		InvocationHandler timeHandler = new TimeHandler(flyService);
		
		//进行代理
		FlyService flyHandleObj = (FlyService) Proxy.newProxyInstance(FlyService.class, timeHandler);
		
		flyHandleObj.flyMethod();
	}

}
