package com.liujun.pattern.state.authorized;

public class FileContext implements AuthState
{
	/**
	 * 读取权限
	 */
	private AuthState read = new ReadState(this);

	/**
	 * 读取权限
	 */
	private AuthState write = new WriteState(this);

	/**
	 * 读取权限
	 */
	private AuthState all = new AllState(this);

	/**
	 * 当前用户权限
	 */
	private AuthState currAuthState;

	public AuthState getCurrAuthState()
	{
		return currAuthState;
	}

	public AuthState getRead()
	{
		return read;
	}

	public AuthState getWrite()
	{
		return write;
	}

	public AuthState getAll()
	{
		return all;
	}

	public void setCurrAuthState(AuthState currAuthState)
	{
		this.currAuthState = currAuthState;
	}

	public FileContext()
	{
		this.currAuthState = this.read;
	}

	@Override
	public boolean read()
	{
		return this.getCurrAuthState().read();
	}

	@Override
	public boolean write()
	{
		return this.getCurrAuthState().write();
	}

	@Override
	public boolean upName()
	{
		return this.getCurrAuthState().upName();
	}
	
}
