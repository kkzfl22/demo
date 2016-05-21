package com.liujun.pattern.state.authorized;

/**
 * 普通权限
 * @author liujun
 *
 */
public class ReadState implements AuthState
{
	/**
	 * 上下文对象
	 */
	private FileContext context;
	
	public ReadState(FileContext context)
	{
		this.context = context;
	}

	@Override
	public boolean read()
	{
		System.out.println("当前普通权限中。。。，可以读取。。。");
		System.out.println(context);
		return true;
	}

	@Override
	public boolean write()
	{
		System.out.println("当前普通权限中。。。，不能写入");
		return false;
	}

	@Override
	public boolean upName()
	{
		System.out.println("当前普通权限中。。。，不能修改名称");
		return false;
	}

}
