package com.liujun.pattern.state.authorized;

/**
 * 管理员权限
 * 
 * @author liujun
 * 
 */
public class AllState implements AuthState
{

	/**
	 * 上下文对象
	 */
	private FileContext context;

	public AllState(FileContext context)
	{
		this.context = context;
	}

	@Override
	public boolean read()
	{
		System.out.println("当前管理员，可以读取");
		return true;
	}

	@Override
	public boolean write()
	{
		System.out.println("当前管理员，可以写入");
		return true;
	}

	@Override
	public boolean upName()
	{
		System.out.println("当前管理员，可以修改名称");
		return true;
	}

}
