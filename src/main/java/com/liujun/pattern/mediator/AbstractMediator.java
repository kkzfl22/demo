package com.liujun.pattern.mediator;

import java.util.HashMap;
import java.util.Map;

/**
 * 中介者父类，用于提供一些基本的中介的信息抽象
 * 
 * @author liujun
 *
 * @date 2015年5月6日
 * @vsersion 0.0.1
 */
public abstract class AbstractMediator {
	
	/**
	 * 用来存放操作者集合信息
	 */
	private Map<String, OperatorInf> operMap = new HashMap<String, OperatorInf>();
	
	/**
	 * 对外提供服务
	 * @throws Exception
	 */
	public abstract void work(String oper) throws Exception;
	
	
	
	public Map<String, OperatorInf> getOperMap() {
		return operMap;
	}




	public void add(String key,OperatorInf oper)
	{
		operMap.put(key, oper);
	}
	
}
