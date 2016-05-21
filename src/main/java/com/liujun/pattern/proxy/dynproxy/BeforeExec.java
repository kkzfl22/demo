package com.liujun.pattern.proxy.dynproxy;

public interface BeforeExec
{
	/**
	 * 在代码运行之前调用
	 */
	public void beforeCall();
}
