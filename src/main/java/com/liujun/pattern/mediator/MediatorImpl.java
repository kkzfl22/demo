package com.liujun.pattern.mediator;

/**
 * 中介信息实现
 * @author liujun
 *
 * @date 2015年5月6日
 * @vsersion 0.0.1
 */
public class MediatorImpl extends AbstractMediator {

	@Override
	public void work(String oper) throws Exception {
		//1,协议各工作进行
		OperatorInf operat = this.getOperMap().get(oper);
		
		if(null != operat)
		{
			//执行当前工作
			//执行当前工作
			operat.mySelfWork(this);
			
			//协议其他工作进行
			operat.nofiflyOther(this);
			
			
		}
	}

}
