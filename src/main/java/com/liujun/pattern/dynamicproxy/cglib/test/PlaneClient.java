package com.liujun.pattern.dynamicproxy.cglib.test;

import com.liujun.pattern.dynamicproxy.cglib.CglibProxy;


/**
 * @author liujun
 *
 * @date 2015年5月4日
 * @vsersion 0.0.1
 */
public class PlaneClient   {

	
	public static void main(String[] args) throws Exception {
		PlaneServiceImpl flyService = new PlaneServiceImpl();
		
		CglibProxy cglib = new CglibProxy();
		
		//进行代理
		FlyService flyHandleObj = (FlyService) cglib.newProxyInstance(flyService.getClass());
		
		flyHandleObj.flyMethod();
	}

}
