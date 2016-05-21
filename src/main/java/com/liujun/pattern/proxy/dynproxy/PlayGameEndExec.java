package com.liujun.pattern.proxy.dynproxy;

public class PlayGameEndExec implements EndExec
{

	@Override
	public void endExec()
	{
		System.out.println("当前代码发生在代发生之后");
	}


}
