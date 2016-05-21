package com.liujun.pattern.state.authorized;

/**
 * 写入状态
 * @author liujun
 *
 */
public class WriteState implements AuthState
{
	
	/**
	 * 上下文对象
	 */
	private FileContext context;
	
	public WriteState(FileContext context)
	{
		this.context = context;
	}

	@Override
	public boolean read()
	{
		System.out.println("当前有写入权限，当然可以读取");
		return true;
	}

	@Override
	public boolean write()
	{
		System.out.println("当前有写入权限，当然可以写入");
		return true;
	}

	@Override
	public boolean upName()
	{
		System.out.println("当前有写入权限，不能修改名称");
		return false;
	}

}
