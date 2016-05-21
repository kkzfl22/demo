package com.liujun.pattern.state.authorized;

/**
 * 用户权限管理
 * @author liujun
 *
 */
public interface AuthState
{
	/**
	 * 读取数据权限
	 * @param cons
	 */
	public boolean read();
	
	/**
	 * 写入权限
	 * @return
	 */
	public boolean write(); 
	
	/**
	 * 修改文件名
	 * @return
	 */
	public boolean upName();
	
}
