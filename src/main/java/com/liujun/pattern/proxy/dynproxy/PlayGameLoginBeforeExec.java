package com.liujun.pattern.proxy.dynproxy;

/**
 * 在登陆之前运行
 * @author liujun
 *
 */
public class PlayGameLoginBeforeExec implements BeforeExec
{

	@Override
	public void beforeCall()
	{
		System.out.println("当前代码在登陆之前运行!");
	}

}
